package edu.uni.gradeManagement1.service;

import edu.uni.gradeManagement1.bean.StuItemGrade;

import java.util.HashMap;
import java.util.List;

/**
 * @author 陈少鑫、蔡政堂
 * create: 2019.4.26
 * modified: 2019.4.26
 * 功能：此接口为controller层提供对stu_item_grade表的增查改操作
 */

public interface StuItemGradeService {


    /**
     * author 蔡政堂
     * 录入成绩到成绩项得分
     * @param stuItemGrade
     * @return 是否成功（true or false）
     */
    boolean insert(StuItemGrade stuItemGrade);

    /**
     * 修改表stu_item_grade的指定id的deleted字段
     * author 陈少鑫
     * @param effective true-有效 false-无效
     * @param id stu_item_grade.id
     * @return 如果成功返回true
     */
    boolean changState(Long id, boolean effective);

    /**
     *
     * 获取表stu_item_grade所有stu_grade_main_id字段值为id的记录
     * author 陈少鑫
     * @param id stu_grade_main_id
     * @return List<StuItemGrade>
     */
    List<HashMap> selectByStuMId(Long id);

    /**
     * 修改表stu_item_grade的score字段
     * author 陈少鑫
     * @param newScore 新得分
     * @param id 主键id
     * @return 如果成功返回true
     */
    boolean uploadScore(double newScore, Long id);

}
