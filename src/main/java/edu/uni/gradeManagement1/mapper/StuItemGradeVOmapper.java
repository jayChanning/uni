package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.StuItemGradeVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface StuItemGradeVOmapper {

    /**
     * 默认查询全部学生成绩
     * @return
     */
    @Select( value = "SELECT "
            + "`a`.`id` AS id, "
            + "`a`.`university_id` AS `universityId`, "
            + "`b`.`name` AS `universityName`, "
            + "`a`.`semester_id` AS `semesterId`, "
            + "`c`.`name` AS `semesterName`, "
            + "`a`.`student_id` AS `studentId`, "
            + "`d`.`stu_no` AS `stuNo`, "
            + "`d`.`grade` AS `studentGrade`, "
            + "`e`.`user_name` AS `studentName`, "
            + "`f`.`name` AS `studentClass`, "
            + "`f`.`code` AS `studentCode`, "
            + "`a`.`by_who` AS `byWho`, "
            + "`h`.`user_name` AS `teacherName`, "
            + "`a`.`score` AS `score`, "
            + "`a`.`state` AS `state`, "
            + "`a`.`point` AS `point`, "
            + "`a`.`deleted` AS `deleted`, "
            + "`a`.`datetime` AS `datetime`, "
            + "`a`.`course_id` AS courseId, "
            + "`i`.`number` AS `courseNumber`, "
            + "`i`.`name` AS `courseName`, "
            + "`j`.`name` AS `categoryName`, "
            + "`k`.`name` AS `speciesName`, "
            + "`L`.`name` AS `departmentName`, "
            + "`m`.`name` AS `specialtyName` "
            + "FROM "
            + "stu_grade_main_one AS a, "
            + "university AS b, "
            + "ea_semester AS c, "
            + "student AS d, "
            + "`user` AS e, "
            + "class AS f, "
            + "employee AS g, "
            + "`user` AS h, "
            + "course AS i, "
            + "course_category AS j, "
            + "course_species AS k, "
            + "department AS L, "
            + "specialty AS m "
            + "WHERE "
            + "`a`.`university_id` = `b`.`id` "
            + "AND `a`.`semester_id` = `c`.`id` "
            + "AND `a`.`student_id` = `d`.`id` "
            + "AND `d`.`user_id` = `e`.`id` "
            + "AND `d`.`class_id` = `f`.`id` "
            + "AND `a`.`by_who` = `h`.`id` "
            + "AND `g`.`user_id` = `h`.`id` "
            + "AND `a`.`course_id` = `i`.`id` "
            + "AND `i`.`category_id` = `j`.`id` "
            + "AND `i`.`species_id` = `k`.`id` "
            + "AND `g`.`department_id` = `L`.`id` "
            + "AND `d`.`specialty_id` = `m`.`id` "
    )
    List<StuItemGradeVO> selectAll();

    /**
     * 根据学期名称获取学期id
     * @param semesterName
     * @return
     */
    @Select( value = "SELECT `id` FROM `ea_semester` "
             + "WHERE `name` = '${semesterName}'")
    Long selectBysemesterName(@Param(value = "semesterName") String semesterName);

    /**
     * 根据学期id获取学期名称
     * @param semesterId
     * @return
     */
    @Select( value = "SELECT `name` FROM `ea_semester` "
             + "WHERE `id` = ${semesterId} "  )
    String selectBysemesterId(@Param(value = "semesterId") Long semesterId);

    /**
     * 查询当前学生的全部课程成绩
     * @return
     */
    @Select( value = "SELECT "
            + "`a`.`id` AS id, "
            + "`a`.`university_id` AS `universityId`, "
            + "`b`.`name` AS `universityName`, "
            + "`a`.`semester_id` AS `semesterId`, "
            + "`c`.`name` AS `semesterName`, "
            + "`a`.`student_id` AS `studentId`, "
            + "`d`.`stu_no` AS `stuNo`, "
            + "`d`.`grade` AS `studentGrade`, "
            + "`e`.`user_name` AS `studentName`, "
            + "`f`.`name` AS `studentClass`, "
            + "`f`.`code` AS `studentCode`, "
            + "`a`.`by_who` AS `byWho`, "
            + "`h`.`user_name` AS `teacherName`, "
            + "`a`.`score` AS `score`, "
            + "`a`.`state` AS `state`, "
            + "`a`.`point` AS `point`, "
            + "`a`.`deleted` AS `deleted`, "
            + "`a`.`datetime` AS `datetime`, "
            + "`a`.`course_id` AS courseId, "
            + "`i`.`number` AS `courseNumber`, "
            + "`i`.`name` AS `courseName`, "
            + "`j`.`name` AS `categoryName`, "
            + "`k`.`name` AS `speciesName`, "
            + "`L`.`name` AS `departmentName`, "
            + "`m`.`name` AS `specialtyName` "
            + "FROM "
            + "stu_grade_main_one AS a, "
            + "university AS b, "
            + "ea_semester AS c, "
            + "student AS d, "
            + "`user` AS e, "
            + "class AS f, "
            + "employee AS g, "
            + "`user` AS h, "
            + "course AS i, "
            + "course_category AS j, "
            + "course_species AS k, "
            + "department AS L, "
            + "specialty AS m "
            + "WHERE "
            + "`a`.`university_id` = `b`.`id` "
            + "AND `a`.`semester_id` = `c`.`id` "
            + "AND `a`.`student_id` = `d`.`id` "
            + "AND `d`.`user_id` = `e`.`id` "
            + "AND `d`.`class_id` = `f`.`id` "
            + "AND `a`.`by_who` = `h`.`id` "
            + "AND `g`.`user_id` = `h`.`id` "
            + "AND `a`.`course_id` = `i`.`id` "
            + "AND `i`.`category_id` = `j`.`id` "
            + "AND `i`.`species_id` = `k`.`id` "
            + "AND `g`.`department_id` = `L`.`id` "
            + "AND `d`.`specialty_id` = `m`.`id` "
            + "AND `e`.`id` = ${userId} ORDER BY `a`.`semester_id` DESC"
    )
    List<StuItemGradeVO> selectcurrentStudentAll(@Param(value = "userId") Long userId);

    /**
     * 根据课程编号查询课程信息
     * @param courseNumber
     * @return
     */
    @Select(value = "SELECT " +
            "`a`.`number` AS `number`," +
            "`a`.`name` AS `courseName`," +
            "`a`.`ename` AS `courseEname`," +
            "`b`.`name` AS `speciesName`," +
            "`c`.`name` AS `categoryName`," +
            "`d`.`name` AS `classificationName`," +
            "`e`.`name` AS `exam_typeName`," +
            "`f`.`name` AS `exam_modeName`," +
            "`a`.`hour` AS `hour`," +
            "`a`.`credit` AS `credit` " +
            "FROM " +
            "course AS a ," +
            "course_species AS b," +
            "course_category AS c," +
            "course_classification AS d," +
            "exam_type AS e," +
            "exam_mode AS f " +
            "WHERE " +
            "`a`.`species_id` = `b`.`id` " +
            "AND `a`.`category_id` = `c`.`id` " +
            "AND `a`.`classification_id` = `d`.`id` " +
            "AND `a`.`exam_type_id` = `e`.`id` " +
            "AND `a`.`exam_mode_id` = `f`.`id` " +
            "AND `a`.`number` = ${courseNumber}")
    HashMap selectBycourseNumber(@Param(value = "courseNumber") Long courseNumber);

    /**
     * 根据班级编号查询班级信息
     * @param classCode
     * @return
     */
    @Select(value = "SELECT " +
            "`a`.`name` AS `className`," +
            "`a`.`code` AS `classCode`," +
            "`b`.`name` AS `departmentName`," +
            "`c`.`name` AS `specialtyName`," +
            "`a`.`cyear` AS `classYear`," +
            "`a`.`cmonth` AS `classMonth`," +
            "`a`.`clength` AS `classLength`," +
            "`e`.`user_name` AS `headteacherName`," +
            "`a`.`cover` AS `classCover` " +
            "FROM " +
            "class AS a," +
            "department AS b," +
            "specialty AS c ," +
            "employee AS d," +
            "`user` AS e " +
            "WHERE " +
            "`a`.`department_id` = `b`.`id` " +
            "AND `a`.`specialty_id` = `c`.`id` " +
            "AND `a`.`headteacher` = `d`.`id` " +
            "AND `d`.`user_id` = `e`.`id` " +
            "AND `a`.`code` = ${classCode}")
    HashMap selectByclassCode(@Param(value = "classCode") Long classCode);

    /**
     * 根据学生id查询学生信息
     * @param studentId
     * @return
     */
    @Select( value = "SELECT" +
            "`a`.`stu_no` AS stuNo," +
            "`b`.`name` AS `studentName`," +
            "`a`.`begin_learn_date` AS `begin_learn_date`," +
            "`a`.`grade` AS `grade`," +
            "`c`.`name` AS `specialtyName`," +
            "`d`.`name` AS `className`," +
            "`e`.`political` AS `political`," +
            "`f`.`detail` AS `address`," +
            "`g`.`content` AS `commuication` " +
            "FROM" +
            "(" +
            "(" +
            "(" +
            "(" +
            "(" +
            "( student AS a INNER JOIN `user` AS b ON `a`.`user_id` = `b`.`id` AND `a`.`id` = ${studentId} )" +
            "INNER JOIN specialty AS c ON `a`.`specialty_id` = `c`.`id` " +
            ")" +
            "INNER JOIN class AS d ON `a`.`class_id` = `d`.`id` " +
            ")" +
            "INNER JOIN political_affiliation AS e ON `a`.`political_id` = `e`.`id` " +
            ")" +
            "LEFT JOIN address AS f ON `a`.`home_address_id` = `f`.`id` " +
            ")" +
            "LEFT JOIN ecomm AS g ON `a`.`phone_ecomm_id` = `g`.`id` " +
            ") ")
    HashMap selectBystudentinform(@Param(value = "studentId") Long studentId);
}