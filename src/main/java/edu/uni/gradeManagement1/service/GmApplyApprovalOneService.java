package edu.uni.gradeManagement1.service;

/**
* @author 陈少鑫
* @description 此接口为controller层提供对审批申请表的服务
* @date 14:01 2019-05-04
* @modified 14:01 2019-05-04
*/
public interface GmApplyApprovalOneService {


    /**
    * @author 陈少鑫
    * @description 教务员审批申请表
     * @param applyId 申请表id
     * @param status 状态码
     * @param reason 理由
    * @date 21:28 2019-05-04
    * @modified 21:28 2019-05-26
    */
    public boolean dealWithApplyByAud(Long applyId, Byte status, String reason) throws Exception;

    /**
     * @author 陈少鑫
     * @description 教师审批申请表
     * @param applyId 申请表id
     * @param status 状态码
     * @param reason 理由
     * @param newScore 新分数
     * @return 如果不许教务员审核则返回false，反之返回true
     * @date 21:28 2019-05-04
     * @modified 21:28 2019-05-26
     */
    public boolean dealWithApplyByTea(Long applyId, Byte status, String reason, double newScore) throws Exception;

}
