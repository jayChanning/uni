package edu.uni.gradeManagement1.service;

import edu.uni.gradeManagement1.bean.StuItemGrade;

/**
 * @author 蔡政堂
 * create 2019/4/29
 * modified 2019/4/29
 * description TODO
 */
public interface StuItemGradeService {

    /**
     * 录入成绩到成绩项得分
     * @param stuItemGrade
     * @return 是否成功（true or false）
     */
    boolean insert(StuItemGrade stuItemGrade);
}
