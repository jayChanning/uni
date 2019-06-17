package edu.uni.gradeManagement1.service;

import java.util.HashMap;

/**
 * @author 林晓锋
 * @date 2019/5/29
 * modified: 2019/6/1
 * 功能：将学生记录封装成班级记录
 */
public interface StuItemTeacherVOService {

    //将高级筛选的学生记录封装成班级记录
    HashMap selectTeacherVO(String semesterStart, String semesterEnd, String courseName,
                            String speciesName, String categoryName, String studentClass,
                            String departmentName, String studentGrade);

    //将当前教师以及当前学期的学生记录封装成班级记录
    HashMap selectCurrentTeacherVO();
}
