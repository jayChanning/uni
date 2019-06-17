package edu.uni.gradeManagement1.service;


import com.github.pagehelper.PageInfo;
import edu.uni.gradeManagement1.bean.GmApplyOne;
import edu.uni.gradeManagement1.pojo.MyAppl;

/**
* @author 陈少鑫
* @description 此接口为controller层提供对申请表的服务
* @date 14:34 2019-05-04
* @modified 14:34 2019-05-04
*/

public interface GmApplyOneService {

    /**
    * @author 陈少鑫
    * @description  返回所查找id的申请记录
    * @date 13:38 2019-05-05
    * @modified 13:38 2019-05-05
    */
    GmApplyOne selectById(Long Id);

    /**
    * @author  陈少鑫
    * @description  查询申请人的所有申请记录
    * @date 19:23 2019-05-04
    * @modified 19:23 2019-05-04
    */

    PageInfo<MyAppl> selectByWho(Long id, int pageNum, int pageSize);


    /**
    * @author 陈少鑫
    * @description  查询收件人的所有申请记录
    * @date 19:25 2019-05-04
    * @modified 19:25 2019-05-04
    */

    PageInfo<MyAppl> selectBySend(Long id, int pageNum, int pageSize);

    /**
     * @author 陈少鑫
     * @description  查询user(此user为学生)id的所有申请记录
     * @date 19:25 2019-05-04
     * @modified 19:25 2019-05-04
     */

    PageInfo<MyAppl> selectByStuId(Long id, int pageNum, int pageSize);

    /**
    * @author 陈少鑫
    * @description 插入一条申请记录,并生成一条审批记录，状态未读
    * @date 19:59 2019-05-04
    * @modified 19:59 2019-05-04
    */

    boolean insertApplyByOne(GmApplyOne gmApplyOne);


    /**
     * @author 陈少鑫
     * @description 插入一条复核记录（即任课教师向教务员请求确认）,并生成一条审批记录，状态未读
     * @date 19:59 2019-05-04
     * @modified 19:59 2019-05-04
     */

    boolean insertApplyByTeacher(Long applyId, String reason) throws Exception;

}
