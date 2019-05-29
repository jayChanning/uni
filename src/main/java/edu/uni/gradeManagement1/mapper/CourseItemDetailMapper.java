package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.CourseItemDetail;
import edu.uni.gradeManagement1.bean.CourseItemDetailExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CourseItemDetailMapper {

    //下面的id是stu_grade_main表的id
    @Select("SELECT course_item_one.`name`,course_item_one.count,course_item_detail_one.number FROM stu_grade_main_one,stu_item_grade_one,course_item_one,course_item_detail_one WHERE course_item_one.id = stu_item_grade_one.course_item_id AND stu_item_grade_one.stu_grade_main_id = stu_grade_main_one.id AND course_item_one.id = course_item_detail_one.course_item_id AND stu_grade_main_id = ${id} ORDER BY course_item_one.`name`,course_item_detail_one.number;")
    List<HashMap> choose(@Param(value = "id") long id);

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
}