package edu.uni.gradeManagement1.bean;

import java.util.Date;

public class GmApplyOne {
    private Long id;

    private Long approvalMainId;

    private Long universityId;

    private Long semesterId;

    private Long studentId;

    private Long courseId;

    private Long stuGradeMainId;

    private Long stuItemGradeDetailId;

    private String attachment;

    private String reason;

    private Long byWho;

    private Long bySend;

    private Date datatime;

    private Byte deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApprovalMainId() {
        return approvalMainId;
    }

    public void setApprovalMainId(Long approvalMainId) {
        this.approvalMainId = approvalMainId;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStuGradeMainId() {
        return stuGradeMainId;
    }

    public void setStuGradeMainId(Long stuGradeMainId) {
        this.stuGradeMainId = stuGradeMainId;
    }

    public Long getStuItemGradeDetailId() {
        return stuItemGradeDetailId;
    }

    public void setStuItemGradeDetailId(Long stuItemGradeDetailId) {
        this.stuItemGradeDetailId = stuItemGradeDetailId;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Long getByWho() {
        return byWho;
    }

    public void setByWho(Long byWho) {
        this.byWho = byWho;
    }

    public Long getBySend() {
        return bySend;
    }

    public void setBySend(Long bySend) {
        this.bySend = bySend;
    }

    public Date getDatatime() {
        return datatime;
    }

    public void setDatatime(Date datatime) {
        this.datatime = datatime;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }
}