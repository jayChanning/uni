package edu.uni.gradeManagement1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.gradeManagement1.bean.StuItemGradeVO;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/5/18 11:12
 * modified :  2019/6/2
 */
public interface StuItemGradeVOService {

    //默认查询全部学生成绩
    List<StuItemGradeVO> selectStuGradeMainAll();

    //默认查询全部学生成绩并执行分页操作
    PageInfo<StuItemGradeVO> GetselectStuGradeMainAll(int pageNum);

    //多条件查询学生成绩（学期名、课程名称、课程种类、课程类别、班级、学号、学院名称）
    List<StuItemGradeVO> selectByexample(String semesterStart, String semesterEnd, String courseName,
                                         String speciesName, String categoryName, String studentClass,
                                         String departmentName, String studentGrade);

    //根据教师id以及当前学期查询学生成绩
    List<StuItemGradeVO> selectByTeacher();

    //根据学期名称获取学期id（高级筛选时需要）
    Long selectBysemesterName(String semesterName);

    //根据学期id获取学期名称（获取当前学期）
    String selectBysemesterId(Long semesterId);

    //查询当前学生的全部课程成绩
    List<StuItemGradeVO> selectcurrentStudentAll();

    //返回当前学生的全部学期名称
    List<String> getsemesterNameAll();

    //根据特定学期名称返回特定课程名称
    List<String> getcourseNameAll(String semesterName);

    //根据学期名称查询学期课程成绩
    HashMap selectcurrentStudent(String semesterName, String courseName);

    //根据课程编号查询课程信息
    HashMap selectBycourseNumber(Long courseNumber);

    //根据班级编号查询班级信息
    HashMap selectByclassCode(Long classCode);

    //根据学生id查询学生信息
    HashMap selectBystudentinform(Long studentId);
}
