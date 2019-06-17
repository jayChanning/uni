package edu.uni.gradeManagement1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.gradeManagement1.bean.GmApplyApprovalOne;
import edu.uni.gradeManagement1.bean.GmApplyOne;
import edu.uni.gradeManagement1.config.GradeManagementConfig;
import edu.uni.gradeManagement1.mapper.GmApplyApprovalOneMapper;
import edu.uni.gradeManagement1.mapper.GmApplyOneMapper;
import edu.uni.gradeManagement1.mapper.StuItemGradeDetailMapper;
import edu.uni.gradeManagement1.pojo.MyAppl;
import edu.uni.gradeManagement1.service.GmApplyOneService;
import edu.uni.gradeManagement1.utils.CheckState;
import edu.uni.gradeManagement1.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
public class GmApplyOneServiceImpl implements GmApplyOneService {

    @Resource
    private GmApplyOneMapper gmApplyOneMapper;
    @Resource
    private GmApplyApprovalOneMapper gmApplyApprovalOneMapper;
    @Resource
    private StuItemGradeDetailMapper stuItemGradeDetailMapper;
    @Resource
    private UploadUtil uploadUtil;
    @Autowired
    private GradeManagementConfig gradeManagementConfig;


    @Override
    public GmApplyOne selectById(Long id) {
        return gmApplyOneMapper.selectByPrimaryKey(id);
    }

    /**
     * @author  陈少鑫
     * @description  非学生角色查询申请人的所有申请记录
     * @date 19:23 2019-05-04
     * @modified 19:23 2019-05-04
     */
    @Override
    public PageInfo<MyAppl> selectByWho(Long id, int pageNum,int pageSize) {
        // PageHelper.startPage(pageNum, gradeManagementConfig.getPageSize());
        PageHelper.startPage(pageNum, pageSize);
        //  System.out.println(gmApplyOneMapper.selectByWho(id));
        List<MyAppl> hashMapList = gmApplyOneMapper.selectByWho(id);
        MyAppl temp;
        if ( null != hashMapList){
            PageInfo<MyAppl> pageInfo = new PageInfo<>(hashMapList);
            for (Object myappl:
                    pageInfo.getList()) {
                temp = ((MyAppl)myappl);
                temp.setCourseItem(temp.getName(),temp.getNumber());
            }
            return pageInfo;
        }
        else
            return null;
    }

    /**
     * @author  陈少鑫
     * @description  学生角色查询申请人的所有申请记录
     * @date 19:23 2019-05-04
     * @modified 19:23 2019-05-04
     */
    @Override
    public PageInfo<MyAppl> selectByStuId(Long id, int pageNum,int pageSize) {
        // PageHelper.startPage(pageNum, gradeManagementConfig.getPageSize());
        PageHelper.startPage(pageNum, pageSize);
        //  System.out.println(gmApplyOneMapper.selectByWho(id));
        List<MyAppl> hashMapList = gmApplyOneMapper.selectByStuId(id);
        MyAppl temp;
        if ( null != hashMapList){
            PageInfo<MyAppl> pageInfo = new PageInfo<>(hashMapList);
            for (Object myappl:
                    pageInfo.getList()) {
                temp = ((MyAppl)myappl);
                //System.out.println(temp);
                temp.setCourseItem(temp.getName(),temp.getNumber());
            }
            return pageInfo;
        }
        else
            return null;
    }
    /**
     * @author 陈少鑫
     * @description  查询收件人的所有申请记录
     * @date 19:25 2019-05-04
     * @modified 19:25 2019-05-04
     */
    @Override
    public PageInfo<MyAppl> selectBySend(Long id,int pageNum,int pageSize) {

       // PageHelper.startPage(pageNum, gradeManagementConfig.getPageSize());
         PageHelper.startPage(pageNum, pageSize);

        //  System.out.println(gmApplyOneMapper.selectByWho(id));
        List<MyAppl> hashMapList = gmApplyOneMapper.selectBySend(id);
        MyAppl temp;
        if ( null != hashMapList){
            PageInfo<MyAppl> pageInfo = new PageInfo<>(hashMapList);
            for (Object myappl:
                 pageInfo.getList()) {
                temp = ((MyAppl)myappl);
                temp.setCourseItem(temp.getName(),temp.getNumber());
            }
            return pageInfo;
        }
        else
            return null;
    }


    /**
     * @author 陈少鑫
     * @description 插入一条申请记录,并生成一条审批记录，状态待审核
     * @date 19:59 2019-05-04
     * @modified 19:59 2019-05-04
     */
    @Override
    public boolean insertApplyByOne(GmApplyOne gmApplyOne) {
        //*****前台获取
        //gmApplyOne.setBySend();
        //gmApplyOne.setCourseId();
        //gmApplyOne.setReason();
        // gmApplyOne.setSemesterId();
        //gmApplyOne.setStuGradeMainId();
        // gmApplyOne.setStuItemGradeDetailOldId();
        //gmApplyOne.setUniversityId();
        //gmApplyOne.setStudentId();
        /*
                  "bySend": 0,
                  "courseId": 0,
                  "reason": "string",
                  "semesterId": 0,
                  "stuGradeMainId": 0,
                  "stuItemGradeDetailOldId": 0,
                  "studentId": 0,
                  "universityId": 0
         */

        /**
         *
         *   "courseId": 0, 课程ID 要
         *   "reason": "string",  理由  要
         *   "semesterId": 0, 学期ID  要
         *   "stuGradeMainId": 0, 学生成绩主表ID 要
         *   "stuItemGradeDetailOldId": 0,  成绩明细项记录旧ID 要
         *   "studentId": 0, 学生ID 要
         *   "universityId": 0  学校ID  要
         *   "approvalMainId": 0,审批业务ID  他人获取
         *   "bySend": 0,接收方  不要
         *   "attachment": "string",  生成
         *   "byWho": 0,  申请人 用户id
         *   "datatime": "2019-05-05T12:42:39.702Z", 创建时间  生成
         *   "deleted": "string",  本记录是否有效 0：有效，1：无效  生成
         *   "id": 0, 成绩申请表ID    生成
         *   "stuItemGradeDetailNewId": 0,  成绩明细项记录新ID  生成
         */

        //*******业务id, 收件人***小莫******
        gmApplyOne.setApprovalMainId(1L);
        gmApplyOne.setBySend(1941L);
        //***************申请人id**SESSION**************
        Long userId = new Random(47).nextInt(3)>1?1941L:945L;
       // Long userId = (long)(new Random(47).nextInt(3)+1);
        gmApplyOne.setByWho(userId);
        //*************************

      //  String filePath = uploadUtil.uploadFile(file);
       /// gmApplyOne.setAttachment(filePath);
        gmApplyOne.setDeleted((byte) 0);
        gmApplyOne.setStuItemGradeDetailNewId(null);
        gmApplyOne.setDatatime(null);
        gmApplyOne.setId(null);
        gmApplyOneMapper.insertSelective(gmApplyOne);
        System.out.println(gmApplyOne);

        /**
         * 生成一条审批记录，状态，待审核
         */
        GmApplyApprovalOne gmApplyApprovalOne = new GmApplyApprovalOne();
        gmApplyApprovalOne.setGmApplyId(gmApplyOne.getId());
        //******************步骤编号
        gmApplyApprovalOne.setStep(1);
        //*******************************
        gmApplyApprovalOne.setApplicationStatus(CheckState.UNREVIEWED.getStatus());
        gmApplyApprovalOne.setReason("");
        gmApplyApprovalOne.setByWho(gmApplyOne.getBySend());
        gmApplyApprovalOne.setDeleted((byte) 0);
        System.out.println(gmApplyApprovalOne.toString());
        gmApplyApprovalOneMapper.insertSelective(gmApplyApprovalOne);
        return true;
    }

    /**
     * @author 陈少鑫
     * @description 插入一条复核记录（即任课教师向教务员请求确认）,
     * 生成一条审批记录，状态待审核
     *
     *
     * @date 19:59 2019-05-04
     * @modified 19:59 2019-05-04
     */

    @Override
    public boolean insertApplyByTeacher(Long applyId, String reason) throws Exception {

        //学生的申请记录 改为老师的申请记录
        GmApplyOne gmApplyOne = gmApplyOneMapper.selectByPrimaryKey(applyId);
        System.out.println(gmApplyOne);

        gmApplyOne.setByWho(gmApplyOne.getBySend());
        //*******************, 收件人***********审批业务Id******
        gmApplyOne.setBySend(85L);
        //***************************************
        gmApplyOne.setReason(reason);

       // String filePath = uploadUtil.uploadFile(file);
        //System.out.println(filePath);
         //直接就是学生提交的附件
        gmApplyOne.setAttachment(gmApplyOne.getAttachment());

        gmApplyOne.setDeleted((byte) 0);
        gmApplyOne.setDatatime(null);
        gmApplyOne.setId(null);

        System.out.println(gmApplyOne);
        gmApplyOneMapper.insertSelective(gmApplyOne);

        /**
         * 生成一条新成绩项明细，delete字段无效
         * 废弃，新成绩明细在教师审核时生成
         */

//        StuItemGradeDetail stuItemGradeDetail = stuItemGradeDetailMapper
//                .selectByPrimaryKey(gmApplyOne.getStuItemGradeDetailOldId());
//        System.out.println(stuItemGradeDetail);
//
//        stuItemGradeDetail.setDatetime(null);
//        stuItemGradeDetail.setId(null);
//        stuItemGradeDetail.setDelete((byte)1);
//        stuItemGradeDetail.setScore(newScore);
//        stuItemGradeDetailMapper.insertSelective(stuItemGradeDetail);
//
//        //任课老师申请记录新成绩项明细id
//        gmApplyOne.setStuItemGradeDetailNewId(stuItemGradeDetail.getId());
//        //学生申请记录新成绩明细id
//        GmApplyOne gmApplyOne1 = new GmApplyOne();
//        gmApplyOne1.setId(stuItemGradeDetail.getId());
//        gmApplyOne1.setStuItemGradeDetailNewId(stuItemGradeDetail.getId());
//        gmApplyOneMapper.updateByPrimaryKeySelective(gmApplyOne1);
//
//        System.out.println(gmApplyOne);
//        gmApplyOneMapper.insertSelective(gmApplyOne);
        /**
         * 生成一条审批记录，状态，待审核
         */
        GmApplyApprovalOne gmApplyApprovalOne = new GmApplyApprovalOne();
        gmApplyApprovalOne.setGmApplyId(gmApplyOne.getId());
        //******************步骤编号*******************
        gmApplyApprovalOne.setStep(2);
        //***************************************
        gmApplyApprovalOne.setApplicationStatus((byte) 0);
        gmApplyApprovalOne.setReason("");
        gmApplyApprovalOne.setByWho(gmApplyOne.getBySend());
        gmApplyApprovalOne.setDeleted((byte) 0);
        System.out.println(gmApplyApprovalOne);
        gmApplyApprovalOneMapper.insertSelective(gmApplyApprovalOne);
        return true;
    }

}
