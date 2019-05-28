package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.GmApplyApprovalOne;
import edu.uni.gradeManagement1.bean.GmApplyApprovalOneExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GmApplyApprovalOneMapper {
    int countByExample(GmApplyApprovalOneExample example);

    int deleteByExample(GmApplyApprovalOneExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GmApplyApprovalOne record);

    int insertSelective(GmApplyApprovalOne record);

    List<GmApplyApprovalOne> selectByExample(GmApplyApprovalOneExample example);

    GmApplyApprovalOne selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GmApplyApprovalOne record, @Param("example") GmApplyApprovalOneExample example);

    int updateByExample(@Param("record") GmApplyApprovalOne record, @Param("example") GmApplyApprovalOneExample example);

    int updateByPrimaryKeySelective(GmApplyApprovalOne record);

    int updateByPrimaryKey(GmApplyApprovalOne record);
}