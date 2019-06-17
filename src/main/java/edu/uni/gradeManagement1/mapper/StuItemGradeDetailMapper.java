package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.StuItemGradeDetail;
import edu.uni.gradeManagement1.bean.StuItemGradeDetailExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
}