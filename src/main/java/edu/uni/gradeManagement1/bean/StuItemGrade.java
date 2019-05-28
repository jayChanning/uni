package edu.uni.gradeManagement1.bean;

import java.util.Date;

public class StuItemGrade {
    private Long id;

    private Long universityId;

    private Long stuGradeMainId;

    private Long courseItemId;

    private Double score;

    private String note;

    private Date datetime;

    private Long byWho;

    private Byte deleted;

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

    public Long getStuGradeMainId() {
        return stuGradeMainId;
    }

    public void setStuGradeMainId(Long stuGradeMainId) {
        this.stuGradeMainId = stuGradeMainId;
    }

    public Long getCourseItemId() {
        return courseItemId;
    }

    public void setCourseItemId(Long courseItemId) {
        this.courseItemId = courseItemId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Long getByWho() {
        return byWho;
    }

    public void setByWho(Long byWho) {
        this.byWho = byWho;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }
}