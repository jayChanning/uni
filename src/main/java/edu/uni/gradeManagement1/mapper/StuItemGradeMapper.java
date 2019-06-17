package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.StuItemGrade;
import edu.uni.gradeManagement1.bean.StuItemGradeExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface StuItemGradeMapper {

    int countByExample(StuItemGradeExample example);

    int deleteByExample(StuItemGradeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StuItemGrade record);

    int insertSelective(StuItemGrade record);

    List<StuItemGrade> selectByExample(StuItemGradeExample example);

    StuItemGrade selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StuItemGrade record, @Param("example") StuItemGradeExample example);

    int updateByExample(@Param("record") StuItemGrade record, @Param("example") StuItemGradeExample example);

    int updateByPrimaryKeySelective(StuItemGrade record);

    int updateByPrimaryKey(StuItemGrade record);

    @Select("SELECT score,rate,stu_item_grade_one.deleted FROM stu_item_grade_one INNER JOIN course_item_one ON stu_item_grade_one.course_item_id = course_item_one.id WHERE stu_item_grade_one.stu_grade_main_id = ${id};")
    List<HashMap> scoreAndRate(@Param(value = "id") long id);
}