package edu.uni.gradeManagement1.bean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/5/29
 * modified: 2019/5/29
 * 功能：将学生记录封装成班级记录
 */

@Api( value = "主表",description = "任课教师主表")
public class StuItemTeacherVO {

    @ApiModelProperty(name = "id",value = "主表id",notes = "主表id",required = true)
    private Long id;

    @ApiModelProperty(name = "courseNumber", value = "课程编号",notes = "课程编号",required = true)
    private Long courseNumber;

    @ApiModelProperty(name = "courseName", value = "课程名称",notes = "课程名称",required = true)
    private String courseName;

    @ApiModelProperty(name = "studentClass", value = "班级名称",notes = "班级名称",required = true)
    private String studentClass;

    @ApiModelProperty(name = "studentCode", value = "班级编号",notes = "班级编号",required = true)
    private String studentCode;

    @ApiModelProperty(name = "allNumber",value = "班级人数",notes = "班级人数",required = true)
    private int allNumber;

    @ApiModelProperty(name = "adoptNumber",value = "及格人数",notes = "及格人数",required = true)
    private int adoptNumber;

    @ApiModelProperty(name = "failNumber",value = "不及格人数",notes = "不及格人数",required = true)
    private int failNumber;

    private List<StuItemGradeVO> stuItemGradeVOList;

    //初始化
    public StuItemTeacherVO(Long courseNumber, String studentCode) {
        this.courseNumber = courseNumber;
        this.studentCode = studentCode;
        this.allNumber = 0;
        this.adoptNumber = 0;
        this.failNumber = 0;
        this.stuItemGradeVOList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseNumber() {

        return courseNumber;
    }

    public void setCourseNumber(Long courseNumber) {

        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {

        this.courseName = courseName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {

        this.studentClass = studentClass;
    }

    public String getStudentCode() {

        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public int getAllNumber() {
        return allNumber;
    }

    public void setAllNumber(int allNumber) {

        this.allNumber = allNumber;
    }

    public int getAdoptNumber() {
        return adoptNumber;
    }

    public void setAdoptNumber(int adoptNumber) {

        this.adoptNumber = adoptNumber;
    }

    public int getFailNumber() {
        return failNumber;
    }

    public void setFailNumber(int failNumber) {

        this.failNumber = failNumber;
    }

    public List<StuItemGradeVO> getStuItemGradeVOList() {

        return stuItemGradeVOList;
    }

    public void setStuItemGradeVOList(List<StuItemGradeVO> stuItemGradeVOList) {
        this.stuItemGradeVOList = stuItemGradeVOList;
    }

    @Override
    public String toString() {
        return "StuItemTeacherVO{" +
                "courseNumber=" + courseNumber +
                ", courseName='" + courseName + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", studentCode='" + studentCode + '\'' +
                ", allNumber=" + allNumber +
                ", adoptNumber=" + adoptNumber +
                ", failNumber=" + failNumber +
                ", stuItemGradeVOList=" + stuItemGradeVOList +
                '}';
    }
}
