package edu.uni.gradeManagement1.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

//必须extends BaseRowModel
public class ExcelDeal extends BaseRowModel {
    //excel 的列数 从0开始
    //必须有
    @ExcelProperty(index = 0)
    private String stuNo;
    //必须有
    @ExcelProperty(index = 1)
    private String stuName;
    //必须有
    @ExcelProperty(index = 2)
    private String courseItem;
    //必须有
    @ExcelProperty(index = 3)
    private int itemNo;
    //必须有
    @ExcelProperty(index = 4)
    private double score;
    //必须有
    @ExcelProperty(index = 5)
    private String notes;
    //必须有
    @ExcelProperty(index = 6)
    private int courseNo;
    //必须有
    @ExcelProperty(index = 7)
    private String courseName;
    //必须有
    @ExcelProperty(index = 8)
    private String classNo;
    //必须有
    @ExcelProperty(index = 9)
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

    public String getCourseItem() {
        return courseItem;
    }

    public void setCourseItem(String courseItem) {
        this.courseItem = courseItem;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
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
                ", courseItem='" + courseItem + '\'' +
                ", itemNo='" + itemNo + '\'' +
                ", score='" + score + '\'' +
                ", notes='" + notes + '\'' +
                ", courseNo='" + courseNo + '\'' +
                ", courseName='" + courseName + '\'' +
                ", classNo='" + classNo + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}

