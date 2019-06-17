package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.GmApplyOne;
import edu.uni.gradeManagement1.bean.GmApplyOneExample;
import edu.uni.gradeManagement1.pojo.MyAppl;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GmApplyOneMapper {

    /**
     * @author 陈少鑫
     * @description 联表查询表gm_apply_approval_one和表gm_apply_one中 by_who = id
     *              且 gm_apply_approval_one.id=gm_apply_one.gm_apply_id的记录
     * @date 15:20 2019-05-04
     * @modified 15:20 2019-05-04
     */
    @Select(value = "SELECT gm_apply_one.id AS applyId,course.`name` AS `course`,course_item_one.`name`,course_item_detail_one.number,student_user.user_name AS studentName,student.stu_no AS studentNumber,class.`name` AS studentClass,gm_apply_one.datatime AS initiationTime,gm_apply_one.reason AS reason,originator_user.user_name AS originatorName,auditor_user.user_name AS auditorName,auditor_department.`name` AS auditorDepartment,gm_apply_approval_one.reason AS reply,gm_apply_one.attachment AS attachment,gm_apply_approval_one.application_status AS `status`,old_detail.score AS oldScore,old_register_user.user_name AS oldRegName,old_register_department.`name` AS oldRegDepartment,old_detail.datetime AS oldRegTime,old_register_user.user_name AS oldAudName,old_register_department.`name` AS oldAudDepartment,old_detail.datetime AS oldAudTime,new_detail.score AS newScore,new_register_user.user_name AS newRegName,new_register_department.`name` AS newRegDepartment,new_detail.datetime AS newRegTime,new_auditor_user.user_name AS newAudName,new_auditor_department.`name` AS newAudDepartment,gm_apply_approval_one.datetime AS newAudTime FROM ((((((((((((((((((((((gm_apply_one INNER JOIN gm_apply_approval_one ON gm_apply_one.id = gm_apply_approval_one.gm_apply_id) INNER JOIN stu_item_grade_detail_one AS old_detail ON gm_apply_one.stu_item_grade_detail_old_id = old_detail.id) INNER JOIN course_item_detail_one ON course_item_detail_one.id = old_detail.course_item_detail_id) INNER JOIN course_item_one ON course_item_detail_one.course_item_id = course_item_one.id) INNER JOIN student ON student.id = gm_apply_one.student_id) INNER JOIN `user` AS student_user ON student_user.id = student.user_id) INNER JOIN `user` AS originator_user ON originator_user.id = gm_apply_one.by_who) INNER JOIN `user` AS auditor_user ON auditor_user.id = gm_apply_one.by_send) INNER JOIN employee AS auditor_employee ON auditor_employee.user_id =auditor_user.id) INNER JOIN department AS auditor_department ON auditor_employee.department_id = auditor_department.id) INNER JOIN `user` AS old_register_user ON old_detail.by_who = old_register_user.id) INNER JOIN employee AS old_register_employee ON old_register_user.id = old_register_employee.user_id) INNER JOIN department AS old_register_department ON old_register_employee.department_id = old_register_department.id) INNER JOIN course ON gm_apply_one.course_id = course.id) INNER JOIN class ON student.class_id = class.id) LEFT JOIN stu_item_grade_detail_one AS new_detail ON gm_apply_one.stu_item_grade_detail_new_id = new_detail.id) LEFT JOIN `user` AS new_register_user ON new_detail.by_who = new_register_user.id) LEFT JOIN employee AS new_register_employee ON new_register_user.id = new_register_employee.user_id) LEFT JOIN department AS new_register_department ON new_register_employee.department_id = new_register_department.id) LEFT JOIN `user` AS new_auditor_user ON gm_apply_one.by_send = new_auditor_user.id) LEFT JOIN employee AS new_auditor_employee ON new_auditor_employee.user_id = new_auditor_user.id) LEFT JOIN department AS new_auditor_department ON new_auditor_employee.department_id = new_auditor_department.id) WHERE gm_apply_one.by_who = ${id} ORDER BY gm_apply_approval_one.application_status,initiationTime")
    List<MyAppl> selectByWho(@Param(value = "id") Long id);

    /**
     * @author 陈少鑫
     * @description 联表查询表gm_apply_approval_one和表gm_apply_one中 by_send = id
     *              且 gm_apply_approval_one.id=gm_apply_one.gm_apply_id的记录
     * @date 15:20 2019-05-04
     * @modified 15:20 2019-05-04
     */

    @Select(value = "SELECT gm_apply_one.id AS applyId,course.`name` AS `course`,course_item_one.`name`,course_item_detail_one.number,student_user.user_name AS studentName,student.stu_no AS studentNumber,class.`name` AS studentClass,gm_apply_one.datatime AS initiationTime,gm_apply_one.reason AS reason,originator_user.user_name AS originatorName,auditor_user.user_name AS auditorName,auditor_department.`name` AS auditorDepartment,gm_apply_approval_one.reason AS reply,gm_apply_one.attachment AS attachment,gm_apply_approval_one.application_status AS `status`,old_detail.score AS oldScore,old_register_user.user_name AS oldRegName,old_register_department.`name` AS oldRegDepartment,old_detail.datetime AS oldRegTime,old_register_user.user_name AS oldAudName,old_register_department.`name` AS oldAudDepartment,old_detail.datetime AS oldAudTime,new_detail.score AS newScore,new_register_user.user_name AS newRegName,new_register_department.`name` AS newRegDepartment,new_detail.datetime AS newRegTime,new_auditor_user.user_name AS newAudName,new_auditor_department.`name` AS newAudDepartment,gm_apply_approval_one.datetime AS newAudTime FROM ((((((((((((((((((((((gm_apply_one INNER JOIN gm_apply_approval_one ON gm_apply_one.id = gm_apply_approval_one.gm_apply_id) INNER JOIN stu_item_grade_detail_one AS old_detail ON gm_apply_one.stu_item_grade_detail_old_id = old_detail.id) INNER JOIN course_item_detail_one ON course_item_detail_one.id = old_detail.course_item_detail_id) INNER JOIN course_item_one ON course_item_detail_one.course_item_id = course_item_one.id) INNER JOIN student ON student.id = gm_apply_one.student_id) INNER JOIN `user` AS student_user ON student_user.id = student.user_id) INNER JOIN `user` AS originator_user ON originator_user.id = gm_apply_one.by_who) INNER JOIN `user` AS auditor_user ON auditor_user.id = gm_apply_one.by_send) INNER JOIN employee AS auditor_employee ON auditor_employee.user_id =auditor_user.id) INNER JOIN department AS auditor_department ON auditor_employee.department_id = auditor_department.id) INNER JOIN `user` AS old_register_user ON old_detail.by_who = old_register_user.id) INNER JOIN employee AS old_register_employee ON old_register_user.id = old_register_employee.user_id) INNER JOIN department AS old_register_department ON old_register_employee.department_id = old_register_department.id) INNER JOIN course ON gm_apply_one.course_id = course.id) INNER JOIN class ON student.class_id = class.id) LEFT JOIN stu_item_grade_detail_one AS new_detail ON gm_apply_one.stu_item_grade_detail_new_id = new_detail.id) LEFT JOIN `user` AS new_register_user ON new_detail.by_who = new_register_user.id) LEFT JOIN employee AS new_register_employee ON new_register_user.id = new_register_employee.user_id) LEFT JOIN department AS new_register_department ON new_register_employee.department_id = new_register_department.id) LEFT JOIN `user` AS new_auditor_user ON gm_apply_one.by_send = new_auditor_user.id) LEFT JOIN employee AS new_auditor_employee ON new_auditor_employee.user_id = new_auditor_user.id) LEFT JOIN department AS new_auditor_department ON new_auditor_employee.department_id = new_auditor_department.id) WHERE gm_apply_one.by_send = ${id} ORDER BY gm_apply_approval_one.application_status,initiationTime")
    List<MyAppl> selectBySend(@Param(value = "id") Long id);

    /**
     * @author 陈少鑫
     * @description 联表查询表gm_apply_approval_one和表gm_apply_one中 student_id = id
     *              且 gm_apply_approval_one.id=gm_apply_one.gm_apply_id的记录
     * @date 15:20 2019-05-04
     * @modified 15:20 2019-05-04
     */
    @Select(value = "SELECT gm_apply_one.id AS applyId,course.`name` AS `course`,course_item_one.`name`,course_item_detail_one.number,student_user.user_name AS studentName,student.stu_no AS studentNumber,class.`name` AS studentClass,gm_apply_one.datatime AS initiationTime,gm_apply_one.reason AS reason,originator_user.user_name AS originatorName,auditor_user.user_name AS auditorName,auditor_department.`name` AS auditorDepartment,gm_apply_approval_one.reason AS reply,gm_apply_one.attachment AS attachment,gm_apply_approval_one.application_status AS `status`,old_detail.score AS oldScore,old_register_user.user_name AS oldRegName,old_register_department.`name` AS oldRegDepartment,old_detail.datetime AS oldRegTime,old_register_user.user_name AS oldAudName,old_register_department.`name` AS oldAudDepartment,old_detail.datetime AS oldAudTime,new_detail.score AS newScore,new_register_user.user_name AS newRegName,new_register_department.`name` AS newRegDepartment,new_detail.datetime AS newRegTime,new_auditor_user.user_name AS newAudName,new_auditor_department.`name` AS newAudDepartment,gm_apply_approval_one.datetime AS newAudTime FROM ((((((((((((((((((((((gm_apply_one INNER JOIN gm_apply_approval_one ON gm_apply_one.id = gm_apply_approval_one.gm_apply_id) INNER JOIN stu_item_grade_detail_one AS old_detail ON gm_apply_one.stu_item_grade_detail_old_id = old_detail.id) INNER JOIN course_item_detail_one ON course_item_detail_one.id = old_detail.course_item_detail_id) INNER JOIN course_item_one ON course_item_detail_one.course_item_id = course_item_one.id) INNER JOIN student ON student.id = gm_apply_one.student_id) INNER JOIN `user` AS student_user ON student_user.id = student.user_id) INNER JOIN `user` AS originator_user ON originator_user.id = gm_apply_one.by_who) INNER JOIN `user` AS auditor_user ON auditor_user.id = gm_apply_one.by_send) INNER JOIN employee AS auditor_employee ON auditor_employee.user_id =auditor_user.id) INNER JOIN department AS auditor_department ON auditor_employee.department_id = auditor_department.id) INNER JOIN `user` AS old_register_user ON old_detail.by_who = old_register_user.id) INNER JOIN employee AS old_register_employee ON old_register_user.id = old_register_employee.user_id) INNER JOIN department AS old_register_department ON old_register_employee.department_id = old_register_department.id) INNER JOIN course ON gm_apply_one.course_id = course.id) INNER JOIN class ON student.class_id = class.id) LEFT JOIN stu_item_grade_detail_one AS new_detail ON gm_apply_one.stu_item_grade_detail_new_id = new_detail.id) LEFT JOIN `user` AS new_register_user ON new_detail.by_who = new_register_user.id) LEFT JOIN employee AS new_register_employee ON new_register_user.id = new_register_employee.user_id) LEFT JOIN department AS new_register_department ON new_register_employee.department_id = new_register_department.id) LEFT JOIN `user` AS new_auditor_user ON gm_apply_one.by_send = new_auditor_user.id) LEFT JOIN employee AS new_auditor_employee ON new_auditor_employee.user_id = new_auditor_user.id) LEFT JOIN department AS new_auditor_department ON new_auditor_employee.department_id = new_auditor_department.id) WHERE student_user.id = ${id} ORDER BY gm_apply_approval_one.application_status,initiationTime")
    List<MyAppl> selectByStuId(@Param(value = "id") Long id);

    /**
     * 判断总评成绩已出
     * @param id
     * @return int 大于零则未出
     */
    @Select(value = "SELECT ( course_item_one.count - COUNT( course_item_detail_one.id ) ) AS `result` FROM course_item_one,course_item_detail_one,(SELECT course_item_detail_one.course_item_id FROM ( SELECT stu_item_grade_detail_one.course_item_detail_id FROM stu_item_grade_detail_one WHERE stu_item_grade_detail_one.id = ${id} ) AS a,course_item_detail_one WHERE course_item_detail_one.id = a.course_item_detail_id ) AS b WHERE course_item_one.id = course_item_detail_one.course_item_id AND course_item_detail_one.course_item_id = b.course_item_id;")
    int isComplete(@Param(value = "id") Long id);

    int countByExample(GmApplyOneExample example);

    int deleteByExample(GmApplyOneExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GmApplyOne record);

    int insertSelective(GmApplyOne record);

    List<GmApplyOne> selectByExample(GmApplyOneExample example);

    GmApplyOne selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GmApplyOne record, @Param("example") GmApplyOneExample example);

    int updateByExample(@Param("record") GmApplyOne record, @Param("example") GmApplyOneExample example);

    int updateByPrimaryKeySelective(GmApplyOne record);

    int updateByPrimaryKey(GmApplyOne record);

}