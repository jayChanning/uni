package edu.uni.gradeManagement1.service;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

//TODO 录入成绩
public interface DaoDiService {
    //TODO getFu1-- 这是选择特定的课程 根据课程编号 classId我需要，不许删，不需显示
    PageInfo<HashMap> getFu1(long id, String courseId, int pageNum);

    //TODO    getFu2-- 这是选择特定的课程 根据课程名称 classId我需要，不许删，不需显示
    PageInfo<HashMap> getFu2(long id, String courseName, int pageNum);

    //TODO    getFu3-- 这是选择特定的班级 根据班级名称 classId我需要，不许删，不需显示
    PageInfo<HashMap> getFu3(long id, String courseClass, int pageNum);

    //TODO    getFu4-- 这是选择特定课程名称的特定班级 根据课程名称 根据班级名称 classId我需要，不许删，不需显示
    PageInfo<HashMap> getFu4(long id, String courseName, String courseClass, int pageNum);

    //TODO  getFu5-- 这是选择特定课程编号的特定班级 根据课程编号 根据班级名称 classId我需要，不许删，不需显示
    PageInfo<HashMap> getFu5(long id, String courseId, String courseClass, int pageNum);

    //TODO   getFu6-- 这是选择特定课程编号的特定课程名称 根据课程编号 根据课程名称 classId我需要，不许删，不需显示
    PageInfo<HashMap> getFu6(long id, String courseId, String courseName, int pageNum);

    //TODO  getFu7-- 这是选择特定课程编号的特定课程名称的特定班级名称 根据课程编号 根据课程名称 根据班级名称 classId我需要，不许删，不需显示
    PageInfo<HashMap> getFu7(long id, String courseId, String courseName, String courseClass, int pageNum);

    //TODO  getFuAll-- 这是默认当前学期 classId我需要，不许删，不需显示

    PageInfo<HashMap> getFuAll(long id, int pageNum);

    //TODO  getClass-- 这是子页面 这是选择某个班级的学生记录,参数为班级id,学期id(semesterId),课程id(cId)

    List<HashMap> getClass(long id, long semesterId, long cId);
}
