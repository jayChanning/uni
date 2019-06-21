package edu.uni.gradeManagement1.service;

import edu.uni.gradeManagement1.bean.StuItemGradeDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 陈少鑫 蔡政堂
 * create: 2019.4.26
 * modified: 2019.4.26
 * 功能：此接口为controller层提供对stu_item_grade_detail表的增查改操作
 */

public interface StuItemGradeDetailService {
    /**
     * 录入成绩到成绩项明细表
     * @author 蔡政堂
     * @param stuItemGradeDetail StuItemGradeDetail对象
     * @return 是否成功（true or false）
     */
    boolean insert(StuItemGradeDetail stuItemGradeDetail);

    /**
     * 根据excel表中的学生学号查询获得学生id
     * @author 蔡政堂
     * @param stuNo 学生学号
     * @return stuId 学生id
     */
    long findStuId(String stuNo);

    /**
     * TODO 查找课程组成项明细id和成绩组成项id--service
     * @author 蔡政堂
     * @param mainId 主表id
     * @param courseItemName 课程项name
     * @param itemDetailNumber 课程项序号number
     * @return 返回查询到的组成项明细id和成绩项id
     */
    List<HashMap> findItemGradeANDItemDetailId(Long mainId, int courseItemName, int itemDetailNumber);

    /**
     * 通过学生id，学期id，课程id在成绩主表中查询出主表id
     * @param semesterId 当前学期id
     * @param stuId 当前学生id
     * @param courseId 当前课程id
     * @return 返回成绩主表id
     */
    Long getMainIdByStuId(long semesterId, long courseId, long stuId);

    /**
     * 插入stuItemGradeDetail到表stu_item_grade_detail
     * author 陈少鑫
     * @param stuItemGradeDetail StuItemGradeDetail对象
     * @return 如果成功返回stu_item_grade_detail自增主键id
     */
    Long insertAndReturnId(StuItemGradeDetail stuItemGradeDetail);

    /**
     * 修改表stu_item_grade_detail的指定id的deleted字段
     * author 陈少鑫
     * @param effective true-有效 false-无效
     * @param id stu_item_grade_detail.id
     * @return 如果成功返回true
     */
    boolean changState(Long id, boolean effective);

    /**
     * 获取表stu_item_grade_detail所有stu_item_grade_id字段值为id的记录
     * @author 陈少鑫
     * @param id stu_item_grade_id
     * @return List<StuItemGradeDetail>
     */
    List<StuItemGradeDetail> selectByStuGId(Long id);

}
