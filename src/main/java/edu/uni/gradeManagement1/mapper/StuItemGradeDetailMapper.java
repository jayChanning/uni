package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.StuItemGradeDetail;
import edu.uni.gradeManagement1.bean.StuItemGradeDetailExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StuItemGradeDetailMapper {
    int countByExample(StuItemGradeDetailExample example);

    int deleteByExample(StuItemGradeDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StuItemGradeDetail record);

    int insertSelective(StuItemGradeDetail record);

    List<StuItemGradeDetail> selectByExample(StuItemGradeDetailExample example);

    StuItemGradeDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StuItemGradeDetail record, @Param("example") StuItemGradeDetailExample example);

    int updateByExample(@Param("record") StuItemGradeDetail record, @Param("example") StuItemGradeDetailExample example);

    int updateByPrimaryKeySelective(StuItemGradeDetail record);

    int updateByPrimaryKey(StuItemGradeDetail record);

    @Select(value = " SELECT a.stu_item_grade_id AS stuItemGradeId, a.score, a.`delete` FROM stu_item_grade_detail_one AS a INNER JOIN stu_item_grade_detail_one AS b ON a.stu_item_grade_id = b.stu_item_grade_id WHERE b.id = ${id};")
    List<StuItemGradeDetail> selectBy(@Param("id") Long id);

    /**
     * TODO 根据excel表中的学生学号查询获得学生id
     * @param stuNo 学生学号
     * @return stuId 学生id
     */
    @Select(value = "SELECT student.id AS stuId FROM student WHERE stu_no = ${stuNo};")
    long findStuIdByStuNo(@Param("stuNo") String stuNo);

    /**
     * TODO 查找课程组成项明细id和成绩组成项id--mapper
     * @param id 主表id
     * @param courseName 课程项name
     * @param itemNo 课程项序号number
     * @return 返回查询到的组成项明细id和成绩项id
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
            "\tINNER JOIN course_item_one ON course_item_one.id = stu_item_grade_one.course_item_id and course_item_one.`name` = ${courseName})\n" +
            "\tINNER JOIN course_item_detail_one ON course_item_one.id = course_item_detail_one.course_item_id  and course_item_detail_one.number= ${itemNo}) \n" +
            "\tLEFT JOIN stu_item_grade_detail_one ON stu_item_grade_detail_one.stu_item_grade_id = stu_item_grade_one.id AND stu_item_grade_detail_one.course_item_detail_id = course_item_detail_one.id AND stu_item_grade_detail_one.`delete` = 0) \n" +
            "ORDER BY\n" +
            "\tcourse_item_one.`name`,\n" +
            "\tcourse_item_detail_one.number")
    List<HashMap> getItemGradeANDItemDetailID(@Param("id") long id, @Param("courseName") int courseName, @Param("itemNo") int itemNo);
//    List<Map<String,Long>> getItemGradeANDItemDetailID(@Param("id") long id, @Param("courseName") int courseName, @Param("itemNo") int itemNo);

    /**
     * 通过学生id，学期id，课程id在成绩主表中查询出主表id
     * @param semesterId 当前学期id
     * @param stuId 当前学生id
     * @param courseId 当前课程id
     * @return 返回成绩主表id
     */
    @Select(value = "SELECT stu_grade_main_one.id AS mainId FROM stu_grade_main_one WHERE semester_id = ${semesterId} AND course_id = ${courseId} AND student_id = ${stuId};")
    Long getMainIdByStuANDSmtIdAndCsId(@Param("semesterId") long semesterId,
                                       @Param("stuId") long stuId,
                                       @Param("courseId") long courseId);

}