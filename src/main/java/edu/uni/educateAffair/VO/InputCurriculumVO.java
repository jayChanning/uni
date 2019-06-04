package edu.uni.educateAffair.VO;

/**
 * @Author:梁俊杰
 * @Description:
 * @Date:Created in 11:04 2019/6/1
 */
public class InputCurriculumVO {
    private Long universityId;
    private Long semesterId;
    private Integer sweek;
    private Integer eweek;
    private boolean oddweek;
    private boolean evenweek;
    private String dayId;
    private Integer startTime;
    private Integer endTime;
    private Long employeeId;
    private Long classId;
    private Long courseId;
    private Long fieldId;

    public boolean isOddweek() {
        return oddweek;
    }

    public void setOddweek(boolean oddweek) {
        this.oddweek = oddweek;
    }

    public boolean isEvenweek() {
        return evenweek;
    }

    public void setEvenweek(boolean evenweek) {
        this.evenweek = evenweek;
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

    public Integer getSweek() {
        return sweek;
    }

    public void setSweek(Integer sweek) {
        this.sweek = sweek;
    }

    public Integer getEweek() {
        return eweek;
    }

    public void setEweek(Integer eweek) {
        this.eweek = eweek;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    @Override
    public String toString() {
        return "InputCurriculumVO{" +
                "universityId=" + universityId +
                ", semesterId=" + semesterId +
                ", sweek=" + sweek +
                ", eweek=" + eweek +
                ", oddweek=" + oddweek +
                ", evenweek=" + evenweek +
                ", dayId='" + dayId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", employeeId=" + employeeId +
                ", classId=" + classId +
                ", courseId=" + courseId +
                ", fieldId=" + fieldId +
                '}';
    }
}
