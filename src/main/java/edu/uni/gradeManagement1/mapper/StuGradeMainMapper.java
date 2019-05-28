package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.StuGradeMain;
import edu.uni.gradeManagement1.bean.StuGradeMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuGradeMainMapper {
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
}