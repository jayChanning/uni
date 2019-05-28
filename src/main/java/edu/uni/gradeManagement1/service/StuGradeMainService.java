package edu.uni.gradeManagement1.service;

import edu.uni.gradeManagement1.bean.StuGradeMain;

/**
 * @author 蔡政堂
 * create 2019/4/29
 * modified 2019/4/29
 * description TODO
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
}
