package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.GmApplyOne;
import edu.uni.gradeManagement1.bean.GmApplyOneExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GmApplyOneMapper {
    int countByExample(GmApplyOneExample example);

    int deleteByExample(GmApplyOneExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GmApplyOne record);

    int insertSelective(GmApplyOne record);

    List<GmApplyOne> selectByExample(GmApplyOneExample example);

    GmApplyOne selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GmApplyOne record, @Param("example") GmApplyOneExample example);

    int updateByExample(@Param("record") GmApplyOne record, @Param("example") GmApplyOneExample example);

    int updateByPrimaryKeySelective(GmApplyOne record);

    int updateByPrimaryKey(GmApplyOne record);
}