package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.CourseItemDetail;
import edu.uni.gradeManagement1.bean.CourseItemDetailExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface CourseItemDetailMapper {
    int countByExample(CourseItemDetailExample example);

    int deleteByExample(CourseItemDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseItemDetail record);

    int insertSelective(CourseItemDetail record);

    List<CourseItemDetail> selectByExample(CourseItemDetailExample example);

    CourseItemDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseItemDetail record, @Param("example") CourseItemDetailExample example);

    int updateByExample(@Param("record") CourseItemDetail record, @Param("example") CourseItemDetailExample example);

    int updateByPrimaryKeySelective(CourseItemDetail record);

    int updateByPrimaryKey(CourseItemDetail record);


    /**
     * 树状结构数据
     * @param id 成绩主表id
     * @return 一个HashMap类型的List
     */
    @Select(value = "SELECT\n" +
            "\tstu_item_grade_one.id AS itemGradeId,\n" +
            "\tcourse_item_detail_one.id AS itemDetailId,\n" +
            "\tcourse_item_one.`name`,\n" +
            "\tcourse_item_one.`count`,\n" +
            "\tcourse_item_detail_one.number,\n" +
            "\tstu_item_grade_detail_one.stu_item_grade_id AS isIn \n" +
            "FROM\n" +
            "\t(((( stu_grade_main_one INNER JOIN stu_item_grade_one ON stu_item_grade_one.stu_grade_main_id = stu_grade_main_one.id AND stu_grade_main_id = ${id} ) \n" +
            "\tINNER JOIN course_item_one ON course_item_one.id = stu_item_grade_one.course_item_id )\n" +
            "\tINNER JOIN course_item_detail_one ON course_item_one.id = course_item_detail_one.course_item_id ) \n" +
            "\tLEFT JOIN stu_item_grade_detail_one ON stu_item_grade_detail_one.stu_item_grade_id = stu_item_grade_one.id AND stu_item_grade_detail_one.course_item_detail_id = course_item_detail_one.id AND stu_item_grade_detail_one.`delete` = 0) \n" +
            "ORDER BY\n" +
            "\tcourse_item_one.`name`,\n" +
            "\tcourse_item_detail_one.number")
    List<HashMap> choose(@Param(value = "id") long id);  //id为成绩主表的id

    /**
     * TODO 根据授课安排表id，课程编号course.id，学期id查询出成绩主表的id
     * 然后自动生成一个班的成绩组成项
     * @param taskId 授课安排表id ea_teaching_task.id
     * @param cId 课程编号course.id
     * @param semesterId 学期id semester.id
     * @return 返回main表的id List集合
     */
    @Select(value = "SELECT main.id AS mainId\n" +
            "FROM (ea_teaching_task AS task INNER JOIN classmate AS mate ON task.id = ${taskId} AND task.class_id = mate.class_id ) INNER JOIN stu_grade_main_one AS main ON main.semester_id = ${semesterId} AND main.course_id = ${cId} AND main.student_id = mate.student_id")
    List<Long> autoCreateStuItemGrade(@Param(value = "taskId") long taskId,
                                      @Param(value = "cId") long cId,
                                      @Param(value = "semesterId") long semesterId);

    //    搜索功能
//    以下为父表
    //TODO  selectALL-- 这是默认当前学期  cId(别名)是课程自增id  courseId是课程编号
    @Select(value = "SELECT task.id AS taskId, se.`name` AS semester ,se.id AS semesterId,course.id AS cId , course.number AS courseId, course.`name` AS courseName, class.`name` AS courseClass,course_species.`name` AS courseType,course_category.`name` AS courseCategory, course.`hour` AS classHour, course.credit AS credit, class.id AS classId FROM ((((((ea_semester AS se INNER JOIN ea_teaching_task AS task ON se.id = task.semester_id AND NOW() BETWEEN se.`start` AND se.`end` ) INNER JOIN `user` ON task.worker_id = `user`.id AND `user`.id = ${id})  INNER JOIN course ON task.course_id = course.id)INNER JOIN class ON task.class_id=class.id) INNER JOIN course_species ON course.species_id = course_species.id)INNER JOIN course_category ON course.category_id = course_category.id)")
    /**
     * 这是默认当前学期
     */
    List<HashMap> selectALL(@Param(value = "id") long id);

    //TODO getFu1 selectByCourseId-- 这是选择特定的课程 根据课程编号
    @Select(value = "SELECT task.id AS taskId, se.`name` AS semester ,se.id AS semesterId,course.id AS cId , course.number AS courseId, course.`name` AS courseName, class.`name` AS courseClass,course_species.`name` AS courseType,course_category.`name` AS courseCategory, course.`hour` AS classHour, course.credit AS credit, class.id AS classId FROM ((((((ea_semester AS se INNER JOIN ea_teaching_task AS task ON se.id = task.semester_id) INNER JOIN `user` ON task.worker_id = `user`.id AND `user`.id = ${id})  INNER JOIN course ON task.course_id = course.id AND course.number = '${courseId}')INNER JOIN class ON task.class_id=class.id) INNER JOIN course_species ON course.species_id = course_species.id)INNER JOIN course_category ON course.category_id = course_category.id) ")
    /**
     * 这是选择特定的课程 根据课程编号
     */
    List<HashMap> selectByCourseId(@Param(value = "id") long id, @Param(value = "courseId") String courseId);

    //TODO   getFu2 selectBykc-- 这是选择特定的课程 根据课程名称
    @Select(value = " SELECT task.id AS taskId, se.`name` AS semester ,se.id AS semesterId,course.id AS cId , course.number AS courseId, course.`name` AS courseName, class.`name` AS courseClass,course_species.`name` AS courseType,course_category.`name` AS courseCategory, course.`hour` AS classHour, course.credit AS credit, class.id AS classId FROM ((((((ea_semester AS se INNER JOIN ea_teaching_task AS task ON se.id = task.semester_id) INNER JOIN `user` ON task.worker_id = `user`.id AND `user`.id = ${id})  INNER JOIN course ON task.course_id = course.id AND course.`name` = '${courseName}')INNER JOIN class ON task.class_id=class.id) INNER JOIN course_species ON course.species_id = course_species.id)INNER JOIN course_category ON course.category_id = course_category.id) ")
    /**
     * 这是选择特定的课程 根据课程名称
     */
    List<HashMap> selectBykc(@Param(value = "id") long id, @Param(value = "courseName") String courseName);

    //TODO  getFu3 selectBybj -- 这是选择特定的班级 根据班级名称 classId我需要，不许删，不需显示
    @Select(value = " SELECT task.id AS taskId, se.`name` AS semester ,se.id AS semesterId,course.id AS cId , course.number AS courseId, course.`name` AS courseName, class.`name` AS courseClass,course_species.`name` AS courseType,course_category.`name` AS courseCategory, course.`hour` AS classHour, course.credit AS credit, class.id AS classId  FROM ((((((ea_semester AS se INNER JOIN ea_teaching_task AS task ON se.id = task.semester_id) INNER JOIN `user` ON task.worker_id = `user`.id AND `user`.id = ${id})  )  INNER JOIN course ON task.course_id = course.id)INNER JOIN class ON task.class_id=class.id) INNER JOIN course_species ON course.species_id = course_species.id)INNER JOIN course_category ON course.category_id = course_category.id WHERE class.`name` LIKE '%${courseClass}%' ")
    /**
     * 这是选择特定的班级 根据班级名称
     */
    List<HashMap> selectBybj(@Param(value = "id") long id, @Param(value = "courseClass") String courseClass);

    //TODO getFu4  selectBybjkc -- 这是选择特定课程名称的特定班级 根据课程名称 根据班级名称
    @Select(value = " SELECT task.id AS taskId, se.`name` AS semester ,se.id AS semesterId,course.id AS cId , course.number AS courseId, course.`name` AS courseName, class.`name` AS courseClass,course_species.`name` AS courseType,course_category.`name` AS courseCategory, course.`hour` AS classHour, course.credit AS credit, class.id AS classId FROM ((((((ea_semester AS se INNER JOIN ea_teaching_task AS task ON se.id = task.semester_id) INNER JOIN `user` ON task.worker_id = `user`.id AND `user`.id = ${id})  )  INNER JOIN course ON task.course_id = course.id)INNER JOIN class ON task.class_id=class.id) INNER JOIN course_species ON course.species_id = course_species.id)INNER JOIN course_category ON course.category_id = course_category.id WHERE class.`name` LIKE '%${courseClass}%' AND course.`name` LIKE '%${courseName}%' ")
    /**
     * 这是选择特定课程名称的特定班级 根据课程名称 根据班级名称
     */
    List<HashMap> selectBybjkc(@Param(value = "id") long id,
                               @Param(value = "courseClass") String courseClass,
                               @Param(value = "courseName") String courseName);

    //TODO getFu5 selectBykecenomc-- 这是选择特定课程编号的特定班级 根据课程编号 根据班级名称
    @Select(value = " SELECT task.id AS taskId, se.`name` AS semester ,se.id AS semesterId,course.id AS cId , course.number AS courseId, course.`name` AS courseName, class.`name` AS courseClass,course_species.`name` AS courseType,course_category.`name` AS courseCategory, course.`hour` AS classHour, course.credit AS credit, class.id AS classId  FROM ((((((ea_semester AS se INNER JOIN ea_teaching_task AS task ON se.id = task.semester_id) INNER JOIN `user` ON task.worker_id = `user`.id AND `user`.id = ${id})  )  INNER JOIN course ON task.course_id = course.id)INNER JOIN class ON task.class_id=class.id) INNER JOIN course_species ON course.species_id = course_species.id)INNER JOIN course_category ON course.category_id = course_category.id WHERE class.`name` LIKE '%${courseClass}%' AND course.number LIKE '%${courseId}%' ")
    /**
     * 这是选择特定课程编号的特定班级 根据课程编号 根据班级名称
     */
    List<HashMap> selectBykecenomc(@Param(value = "id") long id,
                                   @Param(value = "courseClass") String courseClass,
                                   @Param(value = "courseId") String courseId);

    //TODO  getFu6 selectBybjkcnomc-- 这是选择特定课程编号的特定课程名称 根据课程编号 根据课程名称
    @Select(value = " SELECT task.id AS taskId, se.`name` AS semester ,se.id AS semesterId,course.id AS cId , course.number AS courseId, course.`name` AS courseName, class.`name` AS courseClass,course_species.`name` AS courseType,course_category.`name` AS courseCategory, course.`hour` AS classHour, course.credit AS credit, class.id AS classId FROM ((((((ea_semester AS se INNER JOIN ea_teaching_task AS task ON se.id = task.semester_id) INNER JOIN `user` ON task.worker_id = `user`.id AND `user`.id = ${id})  )  INNER JOIN course ON task.course_id = course.id)INNER JOIN class ON task.class_id=class.id) INNER JOIN course_species ON course.species_id = course_species.id)INNER JOIN course_category ON course.category_id = course_category.id WHERE course.`name` LIKE '%${courseName}%' AND course.number LIKE '%${courseId}%' ")
    /**
     * 这是选择特定课程编号的特定课程名称 根据课程编号 根据课程名称
     */
    List<HashMap> selectBybjkcnomc(@Param(value = "id") long id,
                                   @Param(value = "courseId") String courseId,
                                   @Param(value = "courseName") String courseName);

    //TODO getFu7 selectBy-- 这是选择特定课程编号的特定课程名称的特定班级名称 根据课程编号 根据课程名称 根据班级名称
    @Select(value = "SELECT task.id AS taskId, se.`name` AS semester ,se.id AS semesterId,course.id AS cId , course.number AS courseId, course.`name` AS courseName, class.`name` AS courseClass,course_species.`name` AS courseType,course_category.`name` AS courseCategory, course.`hour` AS classHour, course.credit AS credit, class.id AS classId FROM ((((((ea_semester AS se INNER JOIN ea_teaching_task AS task ON se.id = task.semester_id) INNER JOIN `user` ON task.worker_id = `user`.id AND `user`.id = ${id})  )  INNER JOIN course ON task.course_id = course.id)INNER JOIN class ON task.class_id=class.id) INNER JOIN course_species ON course.species_id = course_species.id)INNER JOIN course_category ON course.category_id = course_category.id WHERE course.`name` LIKE '%${courseName}%' AND course.number LIKE '%${courseId}%'  AND class.`name` LIKE '%${courseClass}%' ")
    /**
     * 这是选择特定课程编号的特定课程名称的特定班级名称 根据课程编号 根据课程名称 根据班级名称
     */
    List<HashMap> selectBy(@Param(value = "id") long id,
                           @Param(value = "courseId") String courseId,
                           @Param(value = "courseName") String courseName,
                           @Param(value = "courseClass") String courseClass);

    /**
     * 以下为子表
     * TODO  selectByClassId-- 这是子页面 这是选择某个班级的学生记录
     */
    @Select(value = "SELECT student.stu_no AS stuNo,`user`.user_name AS stuName, monentClass.`name` AS stuClass, ecomm.content AS contact , stu_grade_main_one.id AS mainId, " +
            "student.id AS stuId FROM (((((classmate INNER JOIN student ON classmate.student_id = student.id  AND classmate.class_id = ${classId}) INNER JOIN `user` ON `user`.id = student.user_id) " +
            "INNER JOIN class AS monentClass ON student.class_id = monentClass.id)INNER JOIN stu_grade_main_one ON stu_grade_main_one.course_id = ${cId} AND stu_grade_main_one.semester_id = ${semesterId} AND stu_grade_main_one.student_id = student.id)LEFT " +
            "JOIN ecomm ON student.phone_ecomm_id = ecomm.id) ORDER BY monentClass.`name`,student.stu_no")
    List<HashMap> selectByClassId(@Param(value = "classId") long classId,
                                  @Param(value = "semesterId") long semesterId,
                                  @Param(value = "cId") long cId);

}