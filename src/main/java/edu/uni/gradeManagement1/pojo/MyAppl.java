package edu.uni.gradeManagement1.pojo;

import edu.uni.gradeManagement1.utils.MyDataUtil;

import java.util.Date;

/**
* @author 陈少鑫
* @description 查看申请 封装成的简单实体类发送前台
* @date 10:41 2019-05-11
* @modified 10:41 2019-05-11
*/

public class MyAppl {

    private long applyId;  //此纪录的id（重要
    private String course;  //课程           // 有课程id 找凯丰  course表
    private String courseItem;      //成绩明细项
    private int name;
    private int number;
    private String studentName; //学生姓名     //user表
    private long studentNumber; //学号       //student表 - user表
    private String studentClass; //班级      //student表 - user表
    private String initiationTime; //发起时间
    private String reason; //理由
    private String originatorName; //发起人姓名   user表
    private String auditorName; //审核人姓名    //  user表
    private String auditorDepartment; //审核人部门  //employee - subdepartment
    private String reply; //审核人回复
    private String attachment; //附件
    private String status; //状态

    //*****旧记录
    private double oldScore; //旧分数
    private String oldRegName; //旧登分人姓名  //user表
    private String oldRegDepartment; //旧登分人部门  //employee - subdepartment
    private String oldRegTime; //旧登分时间
    private String oldAudName; //旧审核人姓名  //user表
    private String oldAudDepartment; //旧审核人部门  //employee - subdepartment
    private String oldAudTime; //旧审核时间

    //*****新记录
    private double newScore; //新分数
    private String newRegName; //新登分人姓名    //user表
    private String newRegDepartment; //新登分人部门    //employee - subdepartment
    private String newRegTime; //新登分时间
    private String newAudName; //新审核人姓名  //user表
    private String newAudDepartment; //新审核人部门    //employee - subdepartment
    private String newAudTime; //新审核时间


    public String getCourseItem() {
        return courseItem;
    }

    public void setCourseItem(int item, int count) {
        String ss;
       // System.out.println(item);
        switch (item){
            case 1:
                ss = "作业 #"+count;
                break;
            case 2:
                ss = "考勤 #"+count;
                break;
            case 3:
                ss = "期中大题 #"+count;
                break;
            case 4:
                ss = "实验 #"+count;
                break;
            case 5:
                ss = "期末大题 #"+count;
                break;
            default:
                ss = "其他 #"+count;
                break;
        }
        this.courseItem = ss;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getApplyId() {
        return applyId;
    }

    public void setApplyId(long applyId) {
        this.applyId = applyId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getInitiationTime() {
        return initiationTime;
    }

    public void setInitiationTime(Date initiationTime) {
        this.initiationTime = MyDataUtil.getFormatYMDDateTime(initiationTime);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOriginatorName() {
        return originatorName;
    }

    public void setOriginatorName(String originatorName) {
        this.originatorName = originatorName;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getAuditorDepartment() {
        return auditorDepartment;
    }

    public void setAuditorDepartment(String auditorDepartment) {
        this.auditorDepartment = auditorDepartment;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        if (status == 0)
            this.status = "待审核";
        else if (status == 1)
            this.status = "同意待教务员审核";
        else if (status == 2)
            this.status = "不同意";
        else if (status == 3)
            this.status = "同意";
        else
            this.status = "教务员不同意";
    }

    public double getOldScore() {
        return oldScore;
    }

    public void setOldScore(double oldScore) {
        this.oldScore = oldScore;
    }

    public String getOldRegName() {
        return oldRegName;
    }

    public void setOldRegName(String oldRegName) {
        this.oldRegName = oldRegName;
    }

    public String getOldRegDepartment() {
        return oldRegDepartment;
    }

    public void setOldRegDepartment(String oldRegDepartment) {
        this.oldRegDepartment = oldRegDepartment;
    }

    public String getOldRegTime() {
        return oldRegTime;
    }

    public void setOldRegTime(Date oldRegTime) {
        this.oldRegTime = MyDataUtil.getFormatYMDDateTime(oldRegTime);;
    }

    public String getOldAudName() {
        return oldAudName;
    }

    public void setOldAudName(String oldAudName) {
        this.oldAudName = oldAudName;
    }

    public String getOldAudDepartment() {
        return oldAudDepartment;
    }

    public void setOldAudDepartment(String oldAudDepartment) {
        this.oldAudDepartment = oldAudDepartment;
    }

    public String getOldAudTime() {
        return oldAudTime;
    }

    public void setOldAudTime(Date oldAudTime) {
        this.oldAudTime = MyDataUtil.getFormatYMDDateTime(oldAudTime);;
    }

    public double getNewScore() {
        return newScore;
    }

    public void setNewScore(double newScore) {
        this.newScore = newScore;
    }

    public String getNewRegName() {
        return newRegName;
    }

    public void setNewRegName(String newRegName) {
        this.newRegName = newRegName;
    }

    public String getNewRegDepartment() {
        return newRegDepartment;
    }

    public void setNewRegDepartment(String newRegDepartment) {
        this.newRegDepartment = newRegDepartment;
    }

    public String getNewRegTime() {
        return newRegTime;
    }

    public void setNewRegTime(Date newRegTime) {
        this.newRegTime = MyDataUtil.getFormatYMDDateTime(newRegTime);;
    }

    public String getNewAudName() {
        return newAudName;
    }

    public void setNewAudName(String newAudName) {
        this.newAudName = newAudName;
    }

    public String getNewAudDepartment() {
        return newAudDepartment;
    }

    public void setNewAudDepartment(String newAudDepartment) {
        this.newAudDepartment = newAudDepartment;
    }

    public String getNewAudTime() {
        return newAudTime;
    }

    public void setNewAudTime(Date newAudTime) {
        this.newAudTime = MyDataUtil.getFormatYMDDateTime(newAudTime);;
    }


}
