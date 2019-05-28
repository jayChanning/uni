package edu.uni.gradeManagement1.service;

import edu.uni.gradeManagement1.bean.StuItemGradeDetail;

/**
 * @author 蔡政堂
 * create 2019/4/29
 * modified 2019/4/29
 * description TODO
 */
public interface StuItemGradeDetailService {
    /**
     * 录入成绩到成绩项明细表
     * @param sigd
     * @return 是否成功（true or false）
     */
    boolean insert(StuItemGradeDetail sigd);
}
