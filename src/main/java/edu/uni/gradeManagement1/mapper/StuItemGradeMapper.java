package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.StuItemGrade;
import edu.uni.gradeManagement1.bean.StuItemGradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}