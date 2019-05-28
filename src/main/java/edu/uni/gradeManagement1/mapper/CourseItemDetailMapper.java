package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.CourseItemDetail;
import edu.uni.gradeManagement1.bean.CourseItemDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}