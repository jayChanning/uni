package edu.uni.gradeManagement1.service.impl;

import edu.uni.gradeManagement1.bean.StuItemGradeDetail;
import edu.uni.gradeManagement1.mapper.StuItemGradeDetailMapper;
import edu.uni.gradeManagement1.service.StuItemGradeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author 蔡政堂，林晓峰，陈少鑫
 * create: 2019.4.26
 * modified: 2019.4.26
 * 功能：此实现类实现接口StuItemGradeDetailService
 */

@Service
public class StuItemGradeDetailServiceImpl implements StuItemGradeDetailService {

    @Resource
    private StuItemGradeDetailMapper stuItemGradeDetailMapper;
    @Autowired
    private StuItemGradeDetailMapper sigdMapper;

    /**
     * 录入成绩到成绩项明细表
     * @author 蔡政堂
     * @param sigd StuItemGradeDetail对象
     * @return 是否成功（true or false）
     */

    @Override
    public boolean insert(StuItemGradeDetail sigd) {
        if (sigdMapper.insertSelective(sigd)>0){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 插入stuItemGradeDetail到表stu_item_grade_detail
     * @author 陈少鑫
     * @param stuItemGradeDetail StuItemGradeDetail对象
     * @return 如果成功返回stu_item_grade_detail自增主键id
     */
    @Override
    public Long insertAndReturnId(StuItemGradeDetail stuItemGradeDetail) {
        /*
        在xml中<insert id="insert" keyProperty="id" useGeneratedKeys="true",
        并删掉sql语句id字段（因为自增）,实现插入返回自增id
        顺便看到这里，把所有的xml里的delete字段加上``,字段与delete关键字冲突
         */
        stuItemGradeDetailMapper.insertSelective(stuItemGradeDetail);
        return stuItemGradeDetail.getId();
    }

    /**
     * 修改表stu_item_grade_detail的指定id的deleted字段
     * @author 陈少鑫
     * @param effective true-有效 false-无效
     * @param id stu_item_grade_detail.id
     * @return 如果成功返回true
     */
    @Override
    public boolean changState(Long id, boolean effective) {
        StuItemGradeDetail stuItemGradeDetail = new StuItemGradeDetail();
        stuItemGradeDetail.setId(id);
        if (effective) {
            stuItemGradeDetail.setDelete(Byte.valueOf("0"));
        }
        else {
            stuItemGradeDetail.setDelete(Byte.valueOf("1"));
        }
        stuItemGradeDetailMapper
                .updateByPrimaryKeySelective(stuItemGradeDetail);
        return true;
    }
    /**
     * 获取表stu_item_grade_detail所有stu_item_grade_id字段值为id的记录
     * @author 陈少鑫
     * @param id stu_item_grade_id
     * @return List<StuItemGradeDetail>
     */
    @Override
    public List<StuItemGradeDetail> selectByStuGId(Long id) {
        return stuItemGradeDetailMapper.selectBy(id);
    }

    /**
     * 根据excel表中的学生学号查询获得学生id
     * @author 蔡政堂
     * @param stuNo 学生学号
     * @return stuId 学生id
     */
    @Override
    public long findStuId(String stuNo) {
        return stuItemGradeDetailMapper.findStuIdByStuNo(stuNo);
    }

    /**
     * TODO 查找课程组成项明细id和成绩组成项id-imlp
     * @author 蔡政堂
     * @param mainId 主表id
     * @param courseItemName 课程项name
     * @param itemDetailNumber 课程项序号number
     * @return 返回查询到的组成项明细id和成绩项id
     */
    @Override
    public List<HashMap> findItemGradeANDItemDetailId(Long mainId, int courseItemName, int itemDetailNumber) {
        return stuItemGradeDetailMapper.getItemGradeANDItemDetailID(mainId,courseItemName,itemDetailNumber);
    }

    /**
     * 通过学生id，学期id，课程id在成绩主表中查询出主表id
     * @author 蔡政堂
     * @param semesterId 当前学期id
     * @param stuId 当前学生id
     * @param courseId 当前课程id
     * @return 返回成绩主表id
     */
    @Override
    public Long getMainIdByStuId(long semesterId, long courseId, long stuId) {
        return stuItemGradeDetailMapper.getMainIdByStuANDSmtIdAndCsId(semesterId, stuId, courseId);
    }
}
