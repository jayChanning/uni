package edu.uni.gradeManagement1.controller;

import edu.uni.auth.bean.User;
import edu.uni.auth.service.AuthService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.gradeManagement1.service.GmApplyApprovalOneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
* @author 陈少鑫
* @description 对申请表进行审核的处理类
* @date 16:25 2019-05-09
* @modified 16:25 2019-05-09
*/

/**
 *  关于涉及小莫的部分我暂时都用定值，到时统一修改
 */
@Api(description = "陈少鑫：成绩管理模块：对申请表进行审核的处理类")
@Controller
@RequestMapping(value = "/json/gradeManagement1")
public class GmApplyApprovalOneController {

    @Resource
    GmApplyApprovalOneService gmApplyApprovalOneService;

    @Resource
    AuthService authService;

    @RequestMapping(value = "/dwabyTea", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "教师审核的处理接口", notes = "已测试")
    public Result dealWithApplbyTea(
            @RequestBody Map<String,String> map
            /*@ApiParam(value = "申请表id 即审批的此条记录id")
                               @RequestParam(value = "applyId")long applyId,
                               @ApiParam(value = "状态 1 同意 2 不同意")
                               @RequestParam(value = "status")byte status,
                               @ApiParam(value = "回复")
                               @RequestParam(value = "reply")String reason*/
    ) throws Exception {

        System.out.println("applyId:"+map.get("applyId") + " status:"+map.get("status") + " reason:" + map.get("reply") + " newScore:" + map.get("newScore"));
        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");
        // Result result
        return Result.build(ResultType.Success)
                .appendData("data",gmApplyApprovalOneService
                        .dealWithApplyByTea(
                                Long.valueOf(map.get("applyId"))
                                ,Byte.valueOf(map.get("status"))
                                ,map.get("reply"),
                                Double.valueOf(map.get("newScore"))
                        ) == true ? 1 : 0);
                //.appendData("isTea",gmApplyApprovalOneService.dealWithApply(applyId, status, reason) );
    }

    @RequestMapping(value = "/dwabyAud", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "教务员审核的处理接口", notes = "已测试")
    public Result dealWithApplbyAud(
            @RequestBody Map<String,String> map
      /*      @ApiParam(value = "申请表id 即审批的此条记录id")
                                    @RequestParam(value = "applyId")long applyId,
                                    @ApiParam(value = "状态 1 同意 2 不同意")
                                    @RequestParam(value = "status")byte status,
                                    @ApiParam(value = "回复")
                                    @RequestParam(value = "reply")String reason*/) throws Exception {

        System.out.println("applyId:"+map.get("applyId") + " status:"+map.get("status") + " reason:" + map.get("reply"));
        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");
        gmApplyApprovalOneService.dealWithApplyByAud(Long.valueOf(map.get("applyId")),Byte.valueOf(map.get("status")), map.get("reply"));
        return Result.build(ResultType.Success);
        //.appendData("isTea",gmApplyApprovalOneService.dealWithApply(applyId, status, reason) );
    }

}
