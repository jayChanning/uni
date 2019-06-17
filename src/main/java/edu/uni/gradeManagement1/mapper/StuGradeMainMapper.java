package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.StuGradeMain;
import edu.uni.gradeManagement1.bean.StuGradeMainExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface StuGradeMainMapper {

    /**
    * @author 陈少鑫
    * @description 查询指定id的所有记录
    * @date 19:18 2019-05-12
    * @modified 19:18 2019-05-12
    */
    @Select(value = "SELECT * FROM stu_grade_main WHERE id = ${id};")
    List<StuGradeMain> see(@Param(value = "id") Long id);
    int countByExample(StuGradeMainExample example);

    int deleteByExample(StuGradeMainExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StuGradeMain record);

    int insertSelective(StuGradeMain record);

    List<StuGradeMain> selectByExample(StuGradeMainExample example);

    StuGradeMain selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StuGradeMain record, @Param("example") StuGradeMainExample example);

    int updateByExample(@Param("record") StuGradeMain record, @Param("example") StuGradeMainExample example);

    int updateByPrimaryKeySelective(StuGradeMain record);

    int updateByPrimaryKey(StuGradeMain record);

    @Select("SELECT course_item_one.`name`,course_item_one.count,course_item_detail_one.number FROM stu_grade_main_one,stu_item_grade_one,course_item_one,course_item_detail_one WHERE course_item_one.id = stu_item_grade_one.course_item_id AND stu_item_grade_one.stu_grade_main_id = stu_grade_main_one.id AND course_item_one.id = course_item_detail_one.course_item_id AND stu_grade_main_id = ${id} ORDER BY course_item_one.`name`,course_item_detail_one.number;")
    List<HashMap> choose(@Param(value = "id") long id);


}