package edu.uni.gradeManagement1.service.impl;

import edu.uni.gradeManagement1.bean.GmApplyApprovalOne;
import edu.uni.gradeManagement1.bean.GmApplyApprovalOneExample;
import edu.uni.gradeManagement1.bean.GmApplyOne;
import edu.uni.gradeManagement1.bean.StuItemGradeDetail;
import edu.uni.gradeManagement1.mapper.GmApplyApprovalOneMapper;
import edu.uni.gradeManagement1.mapper.GmApplyOneMapper;
import edu.uni.gradeManagement1.mapper.StuItemGradeDetailMapper;
import edu.uni.gradeManagement1.service.CourseItemService;
import edu.uni.gradeManagement1.service.GmApplyApprovalOneService;
import edu.uni.gradeManagement1.service.StuGradeMainService;
import edu.uni.gradeManagement1.service.StuItemGradeDetailService;
import edu.uni.gradeManagement1.utils.CheckState;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class GmApplyApprovalOneServiceImpl implements GmApplyApprovalOneService {

    @Resource
    private GmApplyApprovalOneMapper gmApplyApprovalOneMapper;

    @Resource
    private StuItemGradeDetailService stuItemGradeDetailService;

    @Resource
    private StuItemGradeDetailMapper stuItemGradeDetailMapper;

    @Resource
    private GmApplyOneMapper gmApplyOneMapper;

    @Resource
    private StuItemGradeServiceImpl stuItemGradeService;

    @Resource
    private StuGradeMainService stuGradeMainService;

    @Resource
    private CourseItemService courseItemService;


    /**
     * @author 陈少鑫
     * @description 教师审批申请表
     * @param applyId 申请表id
     * @param status 状态码
     * @param reason 理由
     * @param newScore 新分数
     * @return 如果不许教务员审核则返回false，反之返回true
     * @date 21:28 2019-05-04
     * @modified 21:28 2019-05-04
     */
    @Override
    public boolean dealWithApplyByTea(Long applyId, Byte status, String reason, double newScore) throws Exception {

        byte b = status;
        //获取申请表
        GmApplyOne gmApplyOne = gmApplyOneMapper.selectByPrimaryKey(applyId);

        //获取申请表对应的审核表
        GmApplyApprovalOneExample example = new GmApplyApprovalOneExample();
        GmApplyApprovalOneExample.Criteria criteria = example.createCriteria();
        criteria.andGmApplyIdEqualTo(applyId);
        GmApplyApprovalOne gmApplyApprovalOne = gmApplyApprovalOneMapper
                .selectByExample(example).get(0);

        boolean result = false;
        if (status == 1){
            /**
             * 生成一条新明细
             */
            StuItemGradeDetail stuItemGradeDetail = stuItemGradeDetailMapper
                    .selectByPrimaryKey(gmApplyOne.getStuItemGradeDetailOldId());


            stuItemGradeDetail.setDatetime(null);
            stuItemGradeDetail.setId(null);
            stuItemGradeDetail.setDelete((byte)1);//默认无 效
            stuItemGradeDetail.setScore(newScore);
            stuItemGradeDetailMapper.insertSelective(stuItemGradeDetail);
            System.out.println(stuItemGradeDetail);
            //申请表新成绩明细Id
        GmApplyOne gmApplyOne1 = new GmApplyOne();
        gmApplyOne1.setId(gmApplyOne.getId());
        gmApplyOne1.setStuItemGradeDetailNewId(stuItemGradeDetail.getId());
        gmApplyOneMapper.updateByPrimaryKeySelective(gmApplyOne1);
            /****************************
             *  如果是所有组成项全部录完，即总成绩已经最终形成，则修改需要经过教务员的审核
             *  否则，不需要经过教务员审核，即status是 同意 CheckState.AGREE.getStatus()
             *  也就不用在提交申请给教务员了，设一个boolean给前台，如果为true则需提交申请给教务员
             * ***************************/
            boolean isComplete;
            if (gmApplyOneMapper.isComplete(gmApplyOne.getStuItemGradeDetailOldId()) > 0) isComplete = false;
            else isComplete = true;
            if (!isComplete){  //不需教务员审核
                //新明细默认无效，需置为有效
                stuItemGradeDetailService.changState(gmApplyOne.getStuItemGradeDetailNewId(), true);
                status = CheckState.AGREE.getStatus();
                result = false;
            } else {   //需教务员审核
                status = CheckState.REVIEWED_BY_ACADMIN.getStatus();
                result = true;
            }
        }
        else
            //不同意
            status = CheckState.DISAGREE.getStatus();
        gmApplyApprovalOne.setDatetime(new Date());
        gmApplyApprovalOne.setApplicationStatus(status);
        gmApplyApprovalOne.setReason(reason);
        gmApplyApprovalOneMapper.updateByPrimaryKeySelective(gmApplyApprovalOne);
        System.out.println(gmApplyApprovalOne);
        System.out.println("status:"+b+" result:"+result);
        return result;
    }


    /**
     * @author 陈少鑫
     * @description 教务员审批申请表
     * @param applyId 申请表id
     * @param status 状态码
     * @param reason 理由
     * @date 21:28 2019-05-04
     * @modified 21:28 2019-05-04
     */


    @Override
    public boolean dealWithApplyByAud(Long applyId, Byte status, String reason) throws Exception {
        //获取申请表
        GmApplyOne gmApplyOne = gmApplyOneMapper.selectByPrimaryKey(applyId);

        //获取申请表对应的审核表
        GmApplyApprovalOneExample example = new GmApplyApprovalOneExample();
        GmApplyApprovalOneExample.Criteria criteria = example.createCriteria();
        criteria.andGmApplyIdEqualTo(applyId);
        GmApplyApprovalOne gmApplyApprovalOne = gmApplyApprovalOneMapper
                .selectByExample(example).get(0);
        System.out.println(1);
        if (status == 1) {  //教务员同意
            status = CheckState.AGREE.getStatus();
            //旧成绩明细无效
            stuItemGradeDetailService.changState(gmApplyOne.getStuItemGradeDetailOldId(), false);
            System.out.println(2);
            //新成绩明细有效
            stuItemGradeDetailService.changState(gmApplyOne.getStuItemGradeDetailNewId(), true);
            System.out.println(3);

            //更新 成绩项得分
            updategItem(gmApplyOne.getStuItemGradeDetailNewId());
            System.out.println(4);
            //更新 总成绩得分
            updatefinalGrade(gmApplyOne.getApprovalMainId());
            System.out.println(5);

        } else {
            //教务员不同意
            status = CheckState.DISAGREE_BY_ACADMIN.getStatus();
        }
        gmApplyApprovalOne.setDatetime(new Date());
        gmApplyApprovalOne.setApplicationStatus(status);
        gmApplyApprovalOne.setReason(reason);
        System.out.println(gmApplyApprovalOne);
        gmApplyApprovalOneMapper.updateByPrimaryKeySelective(gmApplyApprovalOne);

        return true;
    }


/**
     * @author 陈少鑫
     * @description 审批申请表
     * @param applyId 申请表id
     * @param status 状态码  1 同意 2 不同意
     * @param reason 理由
     * @date 21:28 2019-05-04
     * @modified 21:28 2019-05-10
     * @return boolean 如果是任课教师，返回true,否则返回false
     */
    /*
   // @Override
    public boolean dealWithApply(Long applyId, Byte status, String reason) throws Exception {

        //获取申请表
        GmApplyOne gmApplyOne = gmApplyOneMapper.selectByPrimaryKey(applyId);

        //获取申请表对应的审核表
        GmApplyApprovalOneExample example = new GmApplyApprovalOneExample();
        GmApplyApprovalOneExample.Criteria criteria = example.createCriteria();
        criteria.andGmApplyIdEqualTo(applyId);
        GmApplyApprovalOne gmApplyApprovalOne = gmApplyApprovalOneMapper
                .selectByExample(example).get(0);

        //获取审批的环节  从小莫模块获取step  暂时用step1 step2 替代
        int step1 = 1;
        int step2 = 2;
        //********************
        System.out.println(gmApplyApprovalOne);
        //任课教师审批
        if (gmApplyApprovalOne.getStep() == step1) {
            if (status == 1)
                //同意待教务员审核
                status = CheckState.REVIEWED_BY_ACADMIN.getStatus();
            else
                //不同意
                status = CheckState.NO_DISAGREE.getStatus();

            gmApplyApprovalOne.setApplicationStatus(status);
            gmApplyApprovalOne.setReason(reason);
            gmApplyApprovalOneMapper.updateByPrimaryKeySelective(gmApplyApprovalOne);
        } else if (gmApplyApprovalOne.getStep() == step2) { //教务员审批
            if (status == 1) {  //教务员同意
                status = CheckState.DISAGREE_BY_ACADMIN.getStatus();
                //旧成绩明细无效
                stuItemGradeDetailService.changState(gmApplyOne.getStuItemGradeDetailOldId(), false);
                //新成绩明细有效
                stuItemGradeDetailService.changState(gmApplyOne.getStuItemGradeDetailNewId(), true);

                //更新 成绩项得分
                updategItem(gmApplyOne.getStuItemGradeDetailNewId());
                //更新 总成绩得分
                updatefinalGrade(gmApplyOne.getApprovalMainId());

            } else {
                //教务员不同意
                status = CheckState.NO_DISAGREE_BY_ACADMIN.getStatus();
            }
            gmApplyApprovalOne.setApplicationStatus(status);
            gmApplyApprovalOne.setReason(reason);
            gmApplyApprovalOneMapper.updateByPrimaryKeySelective(gmApplyApprovalOne);

        } else { throw new Exception("系统异常"); }

        return gmApplyApprovalOne.getStep() == step1;
    }*/

    /**
     * 修改成绩项得分
     * 成绩项得分 = 各 成绩项明细 之和 的 平均值
     * @param id stu_item_grade_detail_id 某一个成绩评分明细项id
     * @return
     */
    private void updategItem(Long id){
        System.out.println("ID"+id);

        List<StuItemGradeDetail> detailList = stuItemGradeDetailService.selectByStuGId(id);
        double score = 0;
        int i = 0;
        for (StuItemGradeDetail datail :
                detailList) {
            if (datail.getDelete()==0) {  //有效成绩
                score += datail.getScore();
                i++;
            }
        }
        System.out.println(score);
        stuItemGradeService.uploadScore(score/i, detailList.get(0).getStuItemGradeId());
        System.out.println(6);
    }

    /**
     * 修改总成绩
     *  总成绩=各 课程成绩评分项百分比（course_item） X 成绩项得分 之和
     * @param id stu_grade_main_id 某一个课程成绩项id
     * @return
     */
    private void updatefinalGrade(Long id){
        System.out.println("ID"+id);
        List<HashMap> gradeList = stuItemGradeService.selectByStuMId(id);
        double score = 0;
        int i = 0;
        for (HashMap grade:
             gradeList) {
            if ((int)grade.get("deleted") == 0){
                score += (double)grade.get("score") * (double)grade.get("rate");
            }
        }
        System.out.println(score);
        stuGradeMainService.uploadScore(score, id);
        System.out.println(8);
    }

}
