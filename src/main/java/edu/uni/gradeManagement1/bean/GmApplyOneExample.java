package edu.uni.gradeManagement1.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GmApplyOneExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GmApplyOneExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andApprovalMainIdIsNull() {
            addCriterion("approval_main_id is null");
            return (Criteria) this;
        }

        public Criteria andApprovalMainIdIsNotNull() {
            addCriterion("approval_main_id is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalMainIdEqualTo(Long value) {
            addCriterion("approval_main_id =", value, "approvalMainId");
            return (Criteria) this;
        }

        public Criteria andApprovalMainIdNotEqualTo(Long value) {
            addCriterion("approval_main_id <>", value, "approvalMainId");
            return (Criteria) this;
        }

        public Criteria andApprovalMainIdGreaterThan(Long value) {
            addCriterion("approval_main_id >", value, "approvalMainId");
            return (Criteria) this;
        }

        public Criteria andApprovalMainIdGreaterThanOrEqualTo(Long value) {
            addCriterion("approval_main_id >=", value, "approvalMainId");
            return (Criteria) this;
        }

        public Criteria andApprovalMainIdLessThan(Long value) {
            addCriterion("approval_main_id <", value, "approvalMainId");
            return (Criteria) this;
        }

        public Criteria andApprovalMainIdLessThanOrEqualTo(Long value) {
            addCriterion("approval_main_id <=", value, "approvalMainId");
            return (Criteria) this;
        }

        public Criteria andApprovalMainIdIn(List<Long> values) {
            addCriterion("approval_main_id in", values, "approvalMainId");
            return (Criteria) this;
        }

        public Criteria andApprovalMainIdNotIn(List<Long> values) {
            addCriterion("approval_main_id not in", values, "approvalMainId");
            return (Criteria) this;
        }

        public Criteria andApprovalMainIdBetween(Long value1, Long value2) {
            addCriterion("approval_main_id between", value1, value2, "approvalMainId");
            return (Criteria) this;
        }

        public Criteria andApprovalMainIdNotBetween(Long value1, Long value2) {
            addCriterion("approval_main_id not between", value1, value2, "approvalMainId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdIsNull() {
            addCriterion("university_id is null");
            return (Criteria) this;
        }

        public Criteria andUniversityIdIsNotNull() {
            addCriterion("university_id is not null");
            return (Criteria) this;
        }

        public Criteria andUniversityIdEqualTo(Long value) {
            addCriterion("university_id =", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdNotEqualTo(Long value) {
            addCriterion("university_id <>", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdGreaterThan(Long value) {
            addCriterion("university_id >", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("university_id >=", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdLessThan(Long value) {
            addCriterion("university_id <", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdLessThanOrEqualTo(Long value) {
            addCriterion("university_id <=", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdIn(List<Long> values) {
            addCriterion("university_id in", values, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdNotIn(List<Long> values) {
            addCriterion("university_id not in", values, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdBetween(Long value1, Long value2) {
            addCriterion("university_id between", value1, value2, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdNotBetween(Long value1, Long value2) {
            addCriterion("university_id not between", value1, value2, "universityId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdIsNull() {
            addCriterion("semester_id is null");
            return (Criteria) this;
        }

        public Criteria andSemesterIdIsNotNull() {
            addCriterion("semester_id is not null");
            return (Criteria) this;
        }

        public Criteria andSemesterIdEqualTo(Long value) {
            addCriterion("semester_id =", value, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdNotEqualTo(Long value) {
            addCriterion("semester_id <>", value, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdGreaterThan(Long value) {
            addCriterion("semester_id >", value, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdGreaterThanOrEqualTo(Long value) {
            addCriterion("semester_id >=", value, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdLessThan(Long value) {
            addCriterion("semester_id <", value, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdLessThanOrEqualTo(Long value) {
            addCriterion("semester_id <=", value, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdIn(List<Long> values) {
            addCriterion("semester_id in", values, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdNotIn(List<Long> values) {
            addCriterion("semester_id not in", values, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdBetween(Long value1, Long value2) {
            addCriterion("semester_id between", value1, value2, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdNotBetween(Long value1, Long value2) {
            addCriterion("semester_id not between", value1, value2, "semesterId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(Long value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(Long value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(Long value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(Long value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(Long value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<Long> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<Long> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(Long value1, Long value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(Long value1, Long value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNull() {
            addCriterion("course_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNotNull() {
            addCriterion("course_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIdEqualTo(Long value) {
            addCriterion("course_id =", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotEqualTo(Long value) {
            addCriterion("course_id <>", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThan(Long value) {
            addCriterion("course_id >", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("course_id >=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThan(Long value) {
            addCriterion("course_id <", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThanOrEqualTo(Long value) {
            addCriterion("course_id <=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIn(List<Long> values) {
            addCriterion("course_id in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotIn(List<Long> values) {
            addCriterion("course_id not in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdBetween(Long value1, Long value2) {
            addCriterion("course_id between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotBetween(Long value1, Long value2) {
            addCriterion("course_id not between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andStuGradeMainIdIsNull() {
            addCriterion("stu_grade_main_id is null");
            return (Criteria) this;
        }

        public Criteria andStuGradeMainIdIsNotNull() {
            addCriterion("stu_grade_main_id is not null");
            return (Criteria) this;
        }

        public Criteria andStuGradeMainIdEqualTo(Long value) {
            addCriterion("stu_grade_main_id =", value, "stuGradeMainId");
            return (Criteria) this;
        }

        public Criteria andStuGradeMainIdNotEqualTo(Long value) {
            addCriterion("stu_grade_main_id <>", value, "stuGradeMainId");
            return (Criteria) this;
        }

        public Criteria andStuGradeMainIdGreaterThan(Long value) {
            addCriterion("stu_grade_main_id >", value, "stuGradeMainId");
            return (Criteria) this;
        }

        public Criteria andStuGradeMainIdGreaterThanOrEqualTo(Long value) {
            addCriterion("stu_grade_main_id >=", value, "stuGradeMainId");
            return (Criteria) this;
        }

        public Criteria andStuGradeMainIdLessThan(Long value) {
            addCriterion("stu_grade_main_id <", value, "stuGradeMainId");
            return (Criteria) this;
        }

        public Criteria andStuGradeMainIdLessThanOrEqualTo(Long value) {
            addCriterion("stu_grade_main_id <=", value, "stuGradeMainId");
            return (Criteria) this;
        }

        public Criteria andStuGradeMainIdIn(List<Long> values) {
            addCriterion("stu_grade_main_id in", values, "stuGradeMainId");
            return (Criteria) this;
        }

        public Criteria andStuGradeMainIdNotIn(List<Long> values) {
            addCriterion("stu_grade_main_id not in", values, "stuGradeMainId");
            return (Criteria) this;
        }

        public Criteria andStuGradeMainIdBetween(Long value1, Long value2) {
            addCriterion("stu_grade_main_id between", value1, value2, "stuGradeMainId");
            return (Criteria) this;
        }

        public Criteria andStuGradeMainIdNotBetween(Long value1, Long value2) {
            addCriterion("stu_grade_main_id not between", value1, value2, "stuGradeMainId");
            return (Criteria) this;
        }

        public Criteria andStuItemGradeDetailIdIsNull() {
            addCriterion("stu_item_grade_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andStuItemGradeDetailIdIsNotNull() {
            addCriterion("stu_item_grade_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andStuItemGradeDetailIdEqualTo(Long value) {
            addCriterion("stu_item_grade_detail_id =", value, "stuItemGradeDetailId");
            return (Criteria) this;
        }

        public Criteria andStuItemGradeDetailIdNotEqualTo(Long value) {
            addCriterion("stu_item_grade_detail_id <>", value, "stuItemGradeDetailId");
            return (Criteria) this;
        }

        public Criteria andStuItemGradeDetailIdGreaterThan(Long value) {
            addCriterion("stu_item_grade_detail_id >", value, "stuItemGradeDetailId");
            return (Criteria) this;
        }

        public Criteria andStuItemGradeDetailIdGreaterThanOrEqualTo(Long value) {
            addCriterion("stu_item_grade_detail_id >=", value, "stuItemGradeDetailId");
            return (Criteria) this;
        }

        public Criteria andStuItemGradeDetailIdLessThan(Long value) {
            addCriterion("stu_item_grade_detail_id <", value, "stuItemGradeDetailId");
            return (Criteria) this;
        }

        public Criteria andStuItemGradeDetailIdLessThanOrEqualTo(Long value) {
            addCriterion("stu_item_grade_detail_id <=", value, "stuItemGradeDetailId");
            return (Criteria) this;
        }

        public Criteria andStuItemGradeDetailIdIn(List<Long> values) {
            addCriterion("stu_item_grade_detail_id in", values, "stuItemGradeDetailId");
            return (Criteria) this;
        }

        public Criteria andStuItemGradeDetailIdNotIn(List<Long> values) {
            addCriterion("stu_item_grade_detail_id not in", values, "stuItemGradeDetailId");
            return (Criteria) this;
        }

        public Criteria andStuItemGradeDetailIdBetween(Long value1, Long value2) {
            addCriterion("stu_item_grade_detail_id between", value1, value2, "stuItemGradeDetailId");
            return (Criteria) this;
        }

        public Criteria andStuItemGradeDetailIdNotBetween(Long value1, Long value2) {
            addCriterion("stu_item_grade_detail_id not between", value1, value2, "stuItemGradeDetailId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIsNull() {
            addCriterion("attachment is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIsNotNull() {
            addCriterion("attachment is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentEqualTo(String value) {
            addCriterion("attachment =", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotEqualTo(String value) {
            addCriterion("attachment <>", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentGreaterThan(String value) {
            addCriterion("attachment >", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentGreaterThanOrEqualTo(String value) {
            addCriterion("attachment >=", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLessThan(String value) {
            addCriterion("attachment <", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLessThanOrEqualTo(String value) {
            addCriterion("attachment <=", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLike(String value) {
            addCriterion("attachment like", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotLike(String value) {
            addCriterion("attachment not like", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentIn(List<String> values) {
            addCriterion("attachment in", values, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotIn(List<String> values) {
            addCriterion("attachment not in", values, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentBetween(String value1, String value2) {
            addCriterion("attachment between", value1, value2, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotBetween(String value1, String value2) {
            addCriterion("attachment not between", value1, value2, "attachment");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andByWhoIsNull() {
            addCriterion("by_who is null");
            return (Criteria) this;
        }

        public Criteria andByWhoIsNotNull() {
            addCriterion("by_who is not null");
            return (Criteria) this;
        }

        public Criteria andByWhoEqualTo(Long value) {
            addCriterion("by_who =", value, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoNotEqualTo(Long value) {
            addCriterion("by_who <>", value, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoGreaterThan(Long value) {
            addCriterion("by_who >", value, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoGreaterThanOrEqualTo(Long value) {
            addCriterion("by_who >=", value, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoLessThan(Long value) {
            addCriterion("by_who <", value, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoLessThanOrEqualTo(Long value) {
            addCriterion("by_who <=", value, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoIn(List<Long> values) {
            addCriterion("by_who in", values, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoNotIn(List<Long> values) {
            addCriterion("by_who not in", values, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoBetween(Long value1, Long value2) {
            addCriterion("by_who between", value1, value2, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoNotBetween(Long value1, Long value2) {
            addCriterion("by_who not between", value1, value2, "byWho");
            return (Criteria) this;
        }

        public Criteria andBySendIsNull() {
            addCriterion("by_send is null");
            return (Criteria) this;
        }

        public Criteria andBySendIsNotNull() {
            addCriterion("by_send is not null");
            return (Criteria) this;
        }

        public Criteria andBySendEqualTo(Long value) {
            addCriterion("by_send =", value, "bySend");
            return (Criteria) this;
        }

        public Criteria andBySendNotEqualTo(Long value) {
            addCriterion("by_send <>", value, "bySend");
            return (Criteria) this;
        }

        public Criteria andBySendGreaterThan(Long value) {
            addCriterion("by_send >", value, "bySend");
            return (Criteria) this;
        }

        public Criteria andBySendGreaterThanOrEqualTo(Long value) {
            addCriterion("by_send >=", value, "bySend");
            return (Criteria) this;
        }

        public Criteria andBySendLessThan(Long value) {
            addCriterion("by_send <", value, "bySend");
            return (Criteria) this;
        }

        public Criteria andBySendLessThanOrEqualTo(Long value) {
            addCriterion("by_send <=", value, "bySend");
            return (Criteria) this;
        }

        public Criteria andBySendIn(List<Long> values) {
            addCriterion("by_send in", values, "bySend");
            return (Criteria) this;
        }

        public Criteria andBySendNotIn(List<Long> values) {
            addCriterion("by_send not in", values, "bySend");
            return (Criteria) this;
        }

        public Criteria andBySendBetween(Long value1, Long value2) {
            addCriterion("by_send between", value1, value2, "bySend");
            return (Criteria) this;
        }

        public Criteria andBySendNotBetween(Long value1, Long value2) {
            addCriterion("by_send not between", value1, value2, "bySend");
            return (Criteria) this;
        }

        public Criteria andDatatimeIsNull() {
            addCriterion("datatime is null");
            return (Criteria) this;
        }

        public Criteria andDatatimeIsNotNull() {
            addCriterion("datatime is not null");
            return (Criteria) this;
        }

        public Criteria andDatatimeEqualTo(Date value) {
            addCriterion("datatime =", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeNotEqualTo(Date value) {
            addCriterion("datatime <>", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeGreaterThan(Date value) {
            addCriterion("datatime >", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeGreaterThanOrEqualTo(Date value) {
            addCriterion("datatime >=", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeLessThan(Date value) {
            addCriterion("datatime <", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeLessThanOrEqualTo(Date value) {
            addCriterion("datatime <=", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeIn(List<Date> values) {
            addCriterion("datatime in", values, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeNotIn(List<Date> values) {
            addCriterion("datatime not in", values, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeBetween(Date value1, Date value2) {
            addCriterion("datatime between", value1, value2, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeNotBetween(Date value1, Date value2) {
            addCriterion("datatime not between", value1, value2, "datatime");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Byte value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Byte value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Byte value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Byte value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Byte value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Byte value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Byte> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Byte> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Byte value1, Byte value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Byte value1, Byte value2) {
            addCriterion("deleted not between", value1, value2, "deleted");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}