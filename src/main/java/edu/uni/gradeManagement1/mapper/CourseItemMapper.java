package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.CourseItem;
import edu.uni.gradeManagement1.bean.CourseItemExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface CourseItemMapper {

    int countByExample(CourseItemExample example);

    int deleteByExample(CourseItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseItem record);

    int insertSelective(CourseItem record);

    List<CourseItem> selectByExample(CourseItemExample example);

    CourseItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseItem record, @Param("example") CourseItemExample example);

    int updateByExample(@Param("record") CourseItem record, @Param("example") CourseItemExample example);

    int updateByPrimaryKeySelective(CourseItem record);

    int updateByPrimaryKey(CourseItem record);


    /**
     * @author 林晓锋
     * 根据主表id查询组成项得分
     *
     * @param id
     * @return
     */
    @Select(value = "SELECT `b`.`id` AS `course_Item_Id`,`name`,`rate`,`count`,`c`.`score`,`c`.`id` AS `stu_item_grade_Id` "
            + "FROM stu_grade_main_one AS a ,course_item_one AS b,stu_item_grade_one AS c "
            + "WHERE `a`.`id` = `c`.`stu_grade_main_id` AND `b`.`id` = `c`.`course_item_id` "
            + "AND `a`.`id` =${id}  AND `c`.`deleted` = 0 ORDER BY `b`.`name` ")
    List<HashMap> selectCourseItem(@Param(value = "id") Long id);

    /**
     * 查询组成项名称为作业的内容及得分
     * @author 林晓锋
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    @Select(value = "SELECT `d`.`number`,`content`,`attachment`,`e`.`score`,`e`.`id` AS `stu_item_grade_detail_id` "
            + "FROM  course_item_one AS c, course_item_detail_one AS d, "
            + "stu_item_grade_detail_one AS e,stu_item_grade_one AS f  "
            + "WHERE `c`.`id` = `d`.`course_item_id` "
            + "AND `d`.`id` = `e`.`course_item_detail_id` "
            + "AND `f`.`id` = `e`.`stu_item_grade_id` "
            + "AND `c`.`id` = ${course_item_id} "
            + "AND `f`.`id` = ${stu_item_grade_id} "
            + "AND `c`.`name` = 1 "
            + "AND `e`.`delete` = 0 "
            + "ORDER BY `d`.`number` ")
    List<HashMap> selectCourseItemDetail1(@Param(value = "course_item_id") Long course_item_id, @Param(value = "stu_item_grade_id") Long stu_item_grade_id);

    /** @author 林晓锋
     * 查询组成项名称为考勤的内容及得分
     *@param course_item_id
     *@param stu_item_grade_id
     * @return
     */
    @Select(value = "SELECT `d`.`number`,`content`,`attachment`,`e`.`score`,`e`.`id` AS `stu_item_grade_detail_id` "
            + "FROM  course_item_one AS c, course_item_detail_one AS d, "
            + "stu_item_grade_detail_one AS e,stu_item_grade_one AS f  "
            + "WHERE `c`.`id` = `d`.`course_item_id` "
            + "AND `d`.`id` = `e`.`course_item_detail_id` "
            + "AND `f`.`id` = `e`.`stu_item_grade_id` "
            + "AND `c`.`id` = ${course_item_id} "
            + "AND `f`.`id` = ${stu_item_grade_id} "
            + "AND `c`.`name` = 2 "
            + "AND `e`.`delete` = 0 "
            + "ORDER BY `d`.`number` ")
    List<HashMap> selectCourseItemDetail2(@Param(value = "course_item_id") Long course_item_id, @Param(value = "stu_item_grade_id") Long stu_item_grade_id);


    /** @author 林晓锋
     * 查询组成项名称为期中考试的内容及得分
     *
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    @Select(value = "SELECT `d`.`number`,`content`,`attachment`,`e`.`score`,`e`.`id` AS `stu_item_grade_detail_id` "
            + "FROM  course_item_one AS c, course_item_detail_one AS d, "
            + "stu_item_grade_detail_one AS e,stu_item_grade_one AS f  "
            + "WHERE `c`.`id` = `d`.`course_item_id` "
            + "AND `d`.`id` = `e`.`course_item_detail_id` "
            + "AND `f`.`id` = `e`.`stu_item_grade_id` "
            + "AND `c`.`id` = ${course_item_id} "
            + "AND `f`.`id` = ${stu_item_grade_id} "
            + "AND `c`.`name` = 3 "
            + "AND `e`.`delete` = 0 "
            + "ORDER BY `d`.`number` ")
    List<HashMap> selectCourseItemDetail3(@Param(value = "course_item_id") Long course_item_id, @Param(value = "stu_item_grade_id") Long stu_item_grade_id);


    /** @author 林晓锋
     * 查询组成项名称为实验的内容及得分
     *
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    @Select(value = "SELECT `d`.`number`,`content`,`attachment`,`e`.`score`,`e`.`id` AS `stu_item_grade_detail_id` "
            + "FROM  course_item_one AS c, course_item_detail_one AS d, "
            + "stu_item_grade_detail_one AS e,stu_item_grade_one AS f  "
            + "WHERE `c`.`id` = `d`.`course_item_id` "
            + "AND `d`.`id` = `e`.`course_item_detail_id` "
            + "AND `f`.`id` = `e`.`stu_item_grade_id` "
            + "AND `c`.`id` = ${course_item_id} "
            + "AND `f`.`id` = ${stu_item_grade_id} "
            + "AND `c`.`name` = 4 "
            + "AND `e`.`delete` = 0 "
            + "ORDER BY `d`.`number` ")
    List<HashMap> selectCourseItemDetail4(@Param(value = "course_item_id") Long course_item_id, @Param(value = "stu_item_grade_id") Long stu_item_grade_id);


    /** @author 林晓锋
     * 查询组成项名称为期末考试的内容及得分
     *
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    @Select(value = "SELECT `d`.`number`,`content`,`attachment`,`e`.`score`,`e`.`id` AS `stu_item_grade_detail_id` "
            + "FROM  course_item_one AS c, course_item_detail_one AS d, "
            + "stu_item_grade_detail_one AS e,stu_item_grade_one AS f  "
            + "WHERE `c`.`id` = `d`.`course_item_id` "
            + "AND `d`.`id` = `e`.`course_item_detail_id` "
            + "AND `f`.`id` = `e`.`stu_item_grade_id` "
            + "AND `c`.`id` = ${course_item_id} "
            + "AND `f`.`id` = ${stu_item_grade_id} "
            + "AND `c`.`name` = 5 "
            + "AND `e`.`delete` = 0 "
            + "ORDER BY `d`.`number` ")
    List<HashMap> selectCourseItemDetail5(@Param(value = "course_item_id") Long course_item_id, @Param(value = "stu_item_grade_id") Long stu_item_grade_id);


    /** @author 林晓锋
     * 查询组成项名称为其他的内容及得分
     *
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    @Select(value = "SELECT `d`.`number`,`content`,`attachment`,`e`.`score`,`e`.`id` AS `stu_item_grade_detail_id` "
            + "FROM  course_item_one AS c, course_item_detail_one AS d, "
            + "stu_item_grade_detail_one AS e,stu_item_grade_one AS f  "
            + "WHERE `c`.`id` = `d`.`course_item_id` "
            + "AND `d`.`id` = `e`.`course_item_detail_id` "
            + "AND `f`.`id` = `e`.`stu_item_grade_id` "
            + "AND `c`.`id` = ${course_item_id} "
            + "AND `f`.`id` = ${stu_item_grade_id} "
            + "AND `c`.`name` = 6 "
            + "AND `e`.`delete` = 0 "
            + "ORDER BY `d`.`number` ")
    List<HashMap> selectCourseItemDetail6(@Param(value = "course_item_id") Long course_item_id, @Param(value = "stu_item_grade_id") Long stu_item_grade_id);

}