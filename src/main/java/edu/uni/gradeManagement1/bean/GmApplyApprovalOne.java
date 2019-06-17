package edu.uni.gradeManagement1.bean;

import java.util.Date;

public class GmApplyApprovalOne {
    private Long id;

    private Long gmApplyId;

    private Integer step;

    private Byte applicationStatus;

    private String reason;

    private Long byWho;

    private Date datetime;

    private Byte deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGmApplyId() {
        return gmApplyId;
    }

    public void setGmApplyId(Long gmApplyId) {
        this.gmApplyId = gmApplyId;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Byte getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(Byte applicationStatus) {
        this.applicationStatus = applicationStatus;
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

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "GmApplyApprovalOne{" +
                "id=" + id +
                ", gmApplyId=" + gmApplyId +
                ", step=" + step +
                ", applicationStatus=" + applicationStatus +
                ", reason='" + reason + '\'' +
                ", byWho=" + byWho +
                ", datetime=" + datetime +
                ", deleted=" + deleted +
                '}';
    }
}