package edu.uni.gradeManagement1.controller;

import edu.uni.auth.bean.User;
import edu.uni.auth.service.AuthService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.gradeManagement1.service.DaoDiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Api(description = "录入成绩显示")
@Controller
@RequestMapping(value = "/json/gradeManagement1")
public class DaoDiController {
    @Resource
    private DaoDiService daoDiService;
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/fu", method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "父页面", notes = "已测试")
    public Result parentPg(
            @ApiParam(value = "课程名称")
            @RequestParam(value = "courseName", required = false) String courseName,
            @ApiParam(value = "课程编号")
            @RequestParam(value = "courseId", required = false) String courseId,
            @ApiParam(value = "教学班级")
            @RequestParam(value = "courseClass", required = false) String courseClass,
            @ApiParam(value = "页码", required = true)
            @RequestParam(value = "pageNum") int pageNum
    ) {
        System.out.println("DaoDiController--"+courseName + ":" + courseId + ":" + courseClass);
        /* 从session中获取userId */
        User user = authService.getUser();
        long usrId = user.getId();
        System.out.println("auth get userId="+usrId);
        //初始化空字符串为null
        if (courseClass == "")
            courseClass = null;
        if (courseId == "")
            courseId = null;
        if (courseName == "")
            courseName = null;

        if (courseName == null) {
            if (courseId == null) {
                if (courseClass == null) {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFuAll(usrId, pageNum));
                } else {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFu3(usrId, courseClass, pageNum));
                }
            } else {
                if (courseClass == null) {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFu1(usrId, courseId, pageNum));
                } else {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFu5(usrId, courseId, courseClass, pageNum));
                }
            }
        } else {
            if (courseId == null) {
                if (courseClass == null) {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFu2(usrId, courseName, pageNum));
                } else {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFu4(usrId, courseName, courseClass, pageNum));
                }
            } else {
                if (courseClass == null) {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFu6(usrId, courseId, courseName, pageNum));
                } else {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFu7(usrId, courseId, courseName, courseClass, pageNum));
                }
            }
        }
    }

    @RequestMapping(value = "/zi", method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "子页面", notes = "已测试,参数名：classId,cId,semesterId")
    public Result childrenPg(
            @ApiParam(value = "班级Id")
            @RequestParam(value = "classId") long classId,
            @ApiParam(value = "学期Id")
            @RequestParam(value = "semesterId") long semesterId,
            @ApiParam(value = "课程Id")
            @RequestParam(value = "cId") long cId
    ) {
        System.out.println("classId="+classId+":semesterId="+semesterId+":cId="+cId);

        List<HashMap> dao =daoDiService.getClass(classId,semesterId,cId);
//        System.out.println(dao);
        return Result.build(ResultType.Success).appendData("data", dao).appendData("total",dao.size());

    }
}
