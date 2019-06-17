package edu.uni.gradeManagement1.service;


import edu.uni.gradeManagement1.bean.StuGradeMain;

/**
 * @author 陈少鑫 蔡政堂
 * create: 2019.4.26
 * modified: 2019.4.26
 * 功能：此接口为controller层提供对stu_item_grade表的增查改操作
 */

public interface StuGradeMainService {

    /**
     *
     * 录入成绩到学生成绩主表
     * @param sgm StuGradeMain对象
     * @return 是否成功true或false
     */
    boolean insert(StuGradeMain sgm);

    StuGradeMain select(Long id);

    /**
     * 修改表stu_item_grade的指定id的deleted字段
     * author 陈少鑫
     * @param effective true-有效 false-无效
     * @param id stu_item_grade.id
     * @return 如果成功返回true
     */
    boolean changState(Long id, boolean effective);

    /**
     * 修改表stu_grade_main的Score字段
     * author 陈少鑫
     * @param newScore 新得分
     * @param id 主键id
     * @return 如果成功返回true
     */
    boolean uploadScore(double newScore, Long id);


}
