package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.CourseItem;
import edu.uni.gradeManagement1.bean.CourseItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}