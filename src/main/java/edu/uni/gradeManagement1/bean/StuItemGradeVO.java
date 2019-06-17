package edu.uni.gradeManagement1.bean;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@Api(value = "主表",description ="显示主表成绩")
public class StuItemGradeVO implements Serializable {
    @ApiModelProperty(name = "id", value = "主表成绩id",notes = "主表成绩id",required = true)
    private Long id;

    @ApiModelProperty(name = "universityId", value = "学校id",notes = "学校id",required = true)
    private Long universityId;

    @ApiModelProperty(name = "universityName", value = "学校名称",notes = "学校名称",required = true)
    private String universityName;

    @ApiModelProperty(name = "semsterId", value = "学期id",notes = "学期id",required = true)
    private Long semesterId;

    @ApiModelProperty(name = "semesterName", value = "学期名",notes = "学期名",required = true)
    private String semesterName;

    @ApiModelProperty(name = "studentId", value = "学生id",notes = "学生id",required = true)
    private Long studentId;

    @ApiModelProperty(name = "studentName", value = "学生姓名",notes = "学生姓名",required = true)
    private String studentName;

    @ApiModelProperty(name = "stuNo", value = "学号",notes = "学号",required = true)
    private String stuNo;

    @ApiModelProperty(name = "studentGrade", value = "年级",notes = "年级",required = true)
    private String studentGrade;

    @ApiModelProperty(name = "studentClass", value = "班级名称",notes = "班级名称",required = true)
    private String studentClass;

    @ApiModelProperty(name = "studentCode", value = "班级编号",notes = "班级编号",required = true)
    private String studentCode;

    @ApiModelProperty(name = "courseId", value = "课程id",notes = "课程id",required = true)
    private Long courseId;

    @ApiModelProperty(name = "courseNumber", value = "课程编号",notes = "课程编号",required = true)
    private Long courseNumber;

    @ApiModelProperty(name = "courseName", value = "课程名称",notes = "课程名称",required = true)
    private String courseName;

    @ApiModelProperty(name = "speciesName", value = "课程种类",notes = "课程种类",required = true)
    private String speciesName ;

    @ApiModelProperty(name = "categoryName", value = "课程类别",notes = "课程类别",required = true)
    private String categoryName ;

    @ApiModelProperty(name = "score", value = "成绩",notes = "成绩",required = true)
    private Double score;

    @ApiModelProperty(name = "point", value = "绩点",notes = "绩点",required = true)
    private Double point;

    @ApiModelProperty(name = "state", value = "考试性质",notes = "考试性质",required = true)
    private Byte state;

    @ApiModelProperty(name = " byWho", value = "写入者id",notes = "写入者id",required = true)
    private Long byWho;

    @ApiModelProperty(name = " teacherName", value = "教师姓名",notes = "教师姓名",required = true)
    private String  teacherName;

    @ApiModelProperty(name = "deleted", value = "是否有效",notes = "是否有效",required = true)
    private Byte deleted;

    @ApiModelProperty(name = "datetime", value = "创建时间",notes = "创建时间",required = true)
    private Date datetime;

    @ApiModelProperty(name = "departmentName", value = "学院名称",notes = "学院名称",required = true)
    private String departmentName;

    @ApiModelProperty(name = "specialtyName",value = "专业名称",notes = "专业名称",required = true)
    private String specialtyName;


    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getUniversityId() {

        return universityId;
    }

    public void setUniversityId(Long universityId) {

        this.universityId = universityId;
    }

    public Long getSemesterId() {

        return semesterId;
    }

    public void setSemesterId(Long semesterId) {

        this.semesterId = semesterId;
    }

    public String getSemesterName() {

        return semesterName;
    }

    public void setSemesterName(String semesterName) {

        this.semesterName = semesterName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {

        this.studentId = studentId;
    }

    public String getStudentName() {

        return studentName;
    }

    public void setStudentName(String studentName) {

        this.studentName = studentName;
    }

    public String getStuNo() {

        return stuNo;
    }

    public void setStuNo(String stuNo) {

        this.stuNo = stuNo;
    }

    public String getStudentGrade() {

        return studentGrade;
    }

    public void setStudentGrade(String studentGrade) {

        this.studentGrade = studentGrade;
    }

    public String getStudentClass() {

        return studentClass;
    }

    public void setStudentClass(String studentClass) {

        this.studentClass = studentClass;
    }

    public Long getCourseId() {

        return courseId;
    }

    public void setCourseId(Long courseId) {

        this.courseId = courseId;
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

    public String getCategoryName() {

        return categoryName;
    }

    public void setCategoryName(String categoryName) {

        this.categoryName = categoryName;
    }

    public Double getPoint() {

        return point;
    }

    public void setPoint(Double point) {

        this.point = point;
    }

    public Byte getState() {

        return state;
    }

    public void setState(Byte state) {

        this.state = state;
    }

    public Long getByWho() {

        return byWho;
    }

    public void setByWho(Long byWho) {

        this.byWho = byWho;
    }

    public String getTeacherName() {

        return teacherName;
    }

    public void setTeacheName(String teacherName) {

        this.teacherName = teacherName;
    }

    public Byte getDeleted() {

        return deleted;
    }

    public void setDeleted(Byte deleted) {

        this.deleted = deleted;
    }

    public Date getDatetime() {

        return datetime;
    }

    public void setDatetime(Date datetime) {

        this.datetime = datetime;
    }

    public String getSpeciesName() {

        return speciesName;
    }

    public void setSpeciesName(String speciesName) {

        this.speciesName = speciesName;
    }

    public String getUniversityName() {

        return universityName;
    }

    public void setUniversityName(String universityName) {

        this.universityName = universityName;
    }

    public Double getScore() {

        return score;
    }

    public void setScore(Double score) {

        this.score = score;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {

        this.departmentName = departmentName;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {

        this.studentCode = studentCode;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }
}
