package edu.uni.gradeManagement1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.gradeManagement1.service.DaoDiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(description = "录入成绩显示")
@Controller
@RequestMapping(value = "/json/gradeManagement1")
public class DaoDiController {
    @Resource
    private DaoDiService daoDiService;

    @RequestMapping(value = "/fu", method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "父页面", notes = "已测试")
    public Result daodi(
            @ApiParam(value = "课程名称")
            @RequestParam(value = "courseName", required = false) String courseName,
            @ApiParam(value = "课程编号")
            @RequestParam(value = "courseId", required = false) String courseId,
            @ApiParam(value = "教学班级")
            @RequestParam(value = "courseClass", required = false) String courseClass,
            @ApiParam(value = "页码")
            @RequestParam(value = "pageNum") int pageNum
    ) {
        System.out.println(courseName + ":" + courseId + ":" + courseClass);
        if (courseName == null) {
            if (courseId == null) {
                if (courseClass == null) {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFuAll(1941L, pageNum));
                } else {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFu3(1941L, courseClass, pageNum));
                }
            } else {
                if (courseClass == null) {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFu1(1941L, courseId, pageNum));
                } else {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFu5(1941L, courseId, courseClass, pageNum));
                }
            }
        } else {
            if (courseId == null) {
                if (courseClass == null) {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFu2(1941L, courseName, pageNum));
                } else {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFu4(1941L, courseName, courseClass, pageNum));
                }
            } else {
                if (courseClass == null) {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFu6(1941L, courseId, courseName, pageNum));
                } else {
                    return Result.build(ResultType.Success).appendData("data", daoDiService.getFu7(1941L, courseId, courseName, courseClass, pageNum));
                }
            }
        }
    }

    @RequestMapping(value = "/zi", method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "子页面", notes = "已测试,参数名：classId")
    public Result weishenme(
            @ApiParam(value = "班级Id")
            @RequestParam(value = "classId") long classId
    ) {
        System.out.println(classId);
        return Result.build(ResultType.Success).appendData("data", daoDiService.getClass(classId));

    }
}
