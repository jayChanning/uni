package edu.uni.gradeManagement1.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

//必须extends BaseRowModel
public class ExcelDeal extends BaseRowModel {
    //excel 的列数 从0开始
    //必须有  学号
    @ExcelProperty(index = 0)
    private String stuNo;
    //必须有  姓名
    @ExcelProperty(index = 1)
    private String stuName;
    //必须有  课程组成项代号 (1作业, 2考勤.....)
    @ExcelProperty(index = 2)
    private int courseItem;
    //必须有  组成项序号
    @ExcelProperty(index = 3)
    private int itemNo;
    //必须有  作业内容描述
    @ExcelProperty(index = 4)
    private String detailContent;
    //必须有  成绩
    @ExcelProperty(index = 5)
    private double score;
    //必须有  评语
    @ExcelProperty(index = 6)
    private String notes;
    //必须有  课程编号
    @ExcelProperty(index = 7)
    private int courseNo;
    //必须有  课程名称
    @ExcelProperty(index = 8)
    private String courseName;
    //必须有  班级号(班级代码)
    @ExcelProperty(index = 9)
    private String classNo;
    //必须有  班级名称
    @ExcelProperty(index = 10)
    private String className;

    /* 必须有无参构造器 */
    public ExcelDeal() {
    }

    /* 必须有getter和setter方法 */

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getCourseItem() {
        return courseItem;
    }

    public void setCourseItem(int courseItem) {
        this.courseItem = courseItem;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    /* toString()方法 */
    @Override
    public String toString() {
        return "ExcelDeal{" +
                "stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", courseItem=" + courseItem +
                ", itemNo=" + itemNo +
                ", detailContent='" + detailContent + '\'' +
                ", score=" + score +
                ", notes='" + notes + '\'' +
                ", courseNo=" + courseNo +
                ", courseName='" + courseName + '\'' +
                ", classNo='" + classNo + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}

