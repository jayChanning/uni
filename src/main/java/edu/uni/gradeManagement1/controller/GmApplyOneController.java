package edu.uni.gradeManagement1.controller;

import edu.uni.auth.bean.User;
import edu.uni.auth.service.AuthService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.gradeManagement1.bean.GmApplyOne;
import edu.uni.gradeManagement1.config.GradeManagementConfig;
import edu.uni.gradeManagement1.mapper.GmApplyOneMapper;
import edu.uni.gradeManagement1.service.GmApplyOneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 *  关于涉及小莫的部分我暂时都用定值，到时统一修改，service层
 */

/**
 * @Author: 陈少鑫
 * @Description: 对成绩修改请求的处理类
 * @Date: 18:14 2019-04-29
 * @Modified: 18:14 2019-04-29
 */
@Api(description = "陈少鑫：成绩管理模块：对成绩修改请求的处理类")
@Controller
@RequestMapping(value = "/json/gradeManagement1")
public class GmApplyOneController {

    @Resource
    private GmApplyOneService gmApplyOneService;

    @Resource
    private GradeManagementConfig config;
    @Resource
    private GmApplyOneMapper gmApplyOneMapper;

    @Resource
    private AuthService authService;

    @ApiOperation(value = "教师查看我的申请", notes = "已测试")
   // @ApiImplicitParam(name = "id", value = "用户id", dataType = "Long")
    @RequestMapping(value = "/lookmyappl/", method = RequestMethod.GET)
    @ResponseBody
    public Result selectByWho(@ApiParam(value = "页码")
                                  @RequestParam(value = "pageNum") int pageNum) {
/**
 * id从session获取
 */
        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");
        return Result.build(ResultType.Success).appendData("data",gmApplyOneService.selectByWho(user.getId(),pageNum,6));
    }



    @ApiOperation(value = "教师查看我的审核", notes = "已测试")
   // @ApiImplicitParam(name = "id", value = "用户id", dataType = "Long")
    @RequestMapping(value = "/mycheck/", method = RequestMethod.GET)
    @ResponseBody
    public Result selectBySend(@ApiParam(value = "页码")
                               @RequestParam(value = "pageNum") int pageNum){
/**
 * id从session获取
 */
        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");
        return Result.build(ResultType.Success).appendData("data",gmApplyOneService.selectBySend(user.getId(),pageNum,6));
    }
    @ApiOperation(value = "教务员查看我的审核", notes = "已测试")
    // @ApiImplicitParam(name = "id", value = "用户id", dataType = "Long")
    @RequestMapping(value = "/mycheckAud/", method = RequestMethod.GET)
    @ResponseBody
    public Result selectBySenda(@ApiParam(value = "页码")
                               @RequestParam(value = "pageNum") int pageNum){
/**
 * id从session获取
 */
        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");
        return Result.build(ResultType.Success).appendData("data",gmApplyOneService.selectBySend(user.getId(),pageNum,7));
    }

    @ApiOperation(value = "学生角色查看我的申请", notes = "已测试")
    // @ApiImplicitParam(name = "id", value = "用户id", dataType = "Long")
    @RequestMapping(value = "/stulook/", method = RequestMethod.GET)
    @ResponseBody
    public Result selectByStuId(@ApiParam(value = "页码")
                               @RequestParam(value = "pageNum") int pageNum) {
/**
 * id从session获取
 */

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");
        return Result.build(ResultType.Success).appendData("data", gmApplyOneService.selectByStuId(user.getId(), pageNum,7));
    }
        /*
              //    "bySend": 0,
                  "courseId": 0,
                  "reason": "string",
                  "semesterId": 0,
                  "stuGradeMainId": 0,
                  "stuItemGradeDetailOldId": 0,
                  "studentId": 0,
                  "universityId": 0
         */

    @RequestMapping(value = "/submitappl", method = RequestMethod.POST/*,headers = "content-type=multipart/form-data"*/)
    @ApiOperation(value = "提交修改申请给任课教师", notes = "已测试")
/*    @ApiImplicitParams({
          //  @ApiImplicitParam(name = "bySend", value = "收件人id",required = false, dataType = "long"),
            @ApiImplicitParam(name = "courseId", value = "课程id",required = false, dataType = "long"),
            @ApiImplicitParam(name = "reason", value = "原因",required = false, dataType = "String"),
            @ApiImplicitParam(name = "stuGradeMainId", value = "学生主表id",required = false, dataType = "long"),
            @ApiImplicitParam(name = "stuItemGradeDetailOldId", value = "原成绩项明细id",required = false, dataType = "long"),
            @ApiImplicitParam(name = "studentId", value = "学生id",required = false, dataType = "long"),
            @ApiImplicitParam(name = "universityId", value = "学校id",required = false, dataType = "long"),
            @ApiImplicitParam(name = "semesterId", value = "学期id",required = false, dataType = "long")
    })*/

   //接收参数为实体对象跟MultipartFile对象报错 ，找了好久，找到个解决方法，但我不会用，https://ask.csdn.net/questions/695481
    //无奈只能这样解决了。。。
    @ResponseBody
    public Result putAppl(
            @RequestBody Map<String, String> map
  /*                        @ApiParam(value = "附件", required = true)
                          @RequestParam(value = "filePath") String filePath,
                       //   @ApiParam(value = "收件人id", required = true)
                        //  @RequestParam(value = "bySend",required = true) long bySend,
                          @ApiParam(value = "课程id", required = true)
                          @RequestParam(value = "courseId",required = true) long courseId,
                          @ApiParam(value = "原因", required = true)
                          @RequestParam(value = "reason",required = true) String reason,
                          @ApiParam(value = "学生主表id", required = true)
                          @RequestParam(value = "stuGradeMainId",required = true) long stuGradeMainId,
                          @ApiParam(value = "原成绩项明细id", required = true)
                          @RequestParam(value = "stuItemGradeDetailOldId",required = true) long stuItemGradeDetailOldId,
                          @ApiParam(value = "学生id", required = true)
                          @RequestParam(value = "studentId",required = true) long studentId,
                          @ApiParam(value = "学校id", required = true)
                          @RequestParam(value = "universityId",required = true) long universityId,
                          @ApiParam(value = "学期id", required = true)
                          @RequestParam(value = "semesterId",required = true) long semesterId*/) {
        System.out.println(map);
        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");
     //   System.out.println(file == null);
        GmApplyOne gmApplyOne = new GmApplyOne();
       // gmApplyOne.setBySend(bySend);
        gmApplyOne.setCourseId(Long.valueOf(map.get("courseId")));
        gmApplyOne.setReason(map.get("reason"));
        gmApplyOne.setStuGradeMainId(Long.valueOf(map.get("stuGradeMainId")));
        gmApplyOne.setStuItemGradeDetailOldId(Long.valueOf(map.get("stuItemGradeDetailOldId")));
        gmApplyOne.setUniversityId(Long.valueOf(map.get("universityId")));
        gmApplyOne.setStudentId(Long.valueOf(map.get("studentId")));
        gmApplyOne.setSemesterId(Long.valueOf(map.get("semesterId")));
        gmApplyOne.setAttachment(map.get("filePath"));
        System.out.println(gmApplyOne.toString());
        try {
            gmApplyOneService.insertApplyByOne(gmApplyOne);
        }catch (Exception e){
            e.printStackTrace();
            return Result.build(ResultType.ParamError);
        }

        return Result.build(ResultType.Success);
    }

    @RequestMapping(value = "/submitapplBytea", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "任课教师提交申请给教务员", notes = "已测试")
    public Result putApplByTea(
            @RequestBody Map<String,String> map
           /*// @ApiParam(value = "附件", required = true)
           // @RequestParam(value = "file", required = true) MultipartFile file,
           // @RequestBody Map<String,String> map
            @ApiParam(value = "申请表id 即审批的此条记录id", required = true)
            @RequestParam(value = "applyId", required = true) long applyId,
          //  @ApiParam(value = "收件人", required = true)
           // @RequestParam(value = "bySend", required = true) Long bySend,
            @ApiParam(value = "理由", required = true)
            @RequestParam(value = "reason", required = true) String reason
          //  @ApiParam(value = "新分数", required = true)
          //  @RequestParam(value = "newScore", required = true) double newScore*/
    ) {
        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");
        System.out.println("applyId:"+map.get("applyId") + " reason:" + map.get("reason"));
        try {
            gmApplyOneService.insertApplyByTeacher(Long.valueOf(map.get("applyId")),map.get("reason"));
        }catch (Exception e){
            e.getStackTrace();
            return Result.build(ResultType.ParamError);
        }
        return Result.build(ResultType.Success);
    }
}
