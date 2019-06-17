package edu.uni.gradeManagement1.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.auth.bean.User;
import edu.uni.auth.service.AuthService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.gradeManagement1.bean.StuItemGradeVO;
import edu.uni.gradeManagement1.service.StuItemGradeVOService;
import edu.uni.gradeManagement1.service.StuItemTeacherVOService;
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

/**
 * @author 林晓锋
 * @date 2019/5/18
 * modified: 2019/6/4
 * 功能：查询学生主表信息
 */
@Api(description = "林晓峰：成绩管理模块：查询学生主表信息")
@Controller
@RequestMapping("/json/gradeManagement1")
public class StuItemVOController {
    @Autowired
    StuItemGradeVOService stuItemGradeVOService ;
    @Autowired
    StuItemTeacherVOService stuItemTeacherVOService;
    @Resource
    AuthService authService;

    @ApiOperation(value = "默认查询全部学生成绩", notes = "正在测试")
    @RequestMapping( value ="/selectAll",method = RequestMethod.GET)

    @ResponseBody
    public Result selectAll(
            @ApiParam( name= "pageNum",value ="页码", required = true )
            @RequestParam(name="pageNum", required =true)
            int pageNum ) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        PageInfo<StuItemGradeVO> stuItemGradeVOlist = stuItemGradeVOService.GetselectStuGradeMainAll(pageNum);

        return Result.build(ResultType.Success).appendData("data",stuItemGradeVOlist);
    }


    @ApiOperation(value = "高级查询全部学生成绩", notes = "正在测试")
    @RequestMapping( value ="/selectAllByexample",method = RequestMethod.GET)

    @ResponseBody
    public Result selectAllByexample(
//
            @ApiParam( name= "semesterStart",value ="开始学期")
            @RequestParam(name="semesterStart", required =false)
                    String semesterStart,
            @ApiParam( name= "semesterEnd",value ="结束学期")
            @RequestParam(name="semesterEnd", required =false)
                    String semesterEnd,
            @ApiParam( name= "courseName",value ="课程名称")
            @RequestParam(name="courseName", required =false)
                    String courseName,
            @ApiParam( name= "speciesName",value ="课程种类")
            @RequestParam(name="speciesName", required =false)
                    String speciesName,
            @ApiParam( name= "categoryName",value ="课程类别")
            @RequestParam(name="categoryName", required =false)
                    String categoryName,
            @ApiParam( name= "studentClass",value ="班级")
            @RequestParam(name="studentClass", required =false)
                    String studentClass,
            @ApiParam( name= "studentGrade",value ="年级")
            @RequestParam(name="studentGrade", required =false)
                    String studentGrade,
            @ApiParam( name= "departmentName",value ="学院名称")
            @RequestParam(name="departmentName", required =false)
                    String departmentName) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

         HashMap list = stuItemTeacherVOService.selectTeacherVO(semesterStart,semesterEnd,courseName,speciesName,
                                                                categoryName,studentClass,departmentName,studentGrade);
        return Result.build(ResultType.Success).appendData("data",list);
    }

    @ApiOperation(value = "根据教师id以及当前学期查询学生成绩", notes = "正在测试")
    @RequestMapping( value ="/selectByTeacher",method = RequestMethod.GET)
    @ResponseBody
    public Result selectAllByTeacher() {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        HashMap teacherlist = stuItemTeacherVOService.selectCurrentTeacherVO();

        return Result.build(ResultType.Success).appendData("data",teacherlist);
    }

    @ApiOperation(value = "查询当前学生的全部课程成绩", notes = "正在测试")
    @RequestMapping( value ="/selectcurrentStudentAll",method = RequestMethod.GET)
    @ResponseBody
    public Result selectcurrentStudentAll() {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        List<StuItemGradeVO> currentStudentlist = stuItemGradeVOService.selectcurrentStudentAll();

        return Result.build(ResultType.Success).appendData("data",currentStudentlist);
    }


    @ApiOperation(value = "返回当前学生的全部学期名称", notes = "正在测试")
    @RequestMapping( value ="/getsemesterNameAll",method = RequestMethod.GET)
    @ResponseBody
    public Result getsemesterNameAll() {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        List<String> semesterNamelist = stuItemGradeVOService.getsemesterNameAll();

        return Result.build(ResultType.Success).appendData("data",semesterNamelist);
    }

    @ApiOperation(value = "根据特定学期名称返回特定课程名称", notes = "正在测试")
    @RequestMapping( value ="/getcourseNameAll",method = RequestMethod.GET)
    @ResponseBody
    public Result getcourseNameAll(
              @ApiParam( name= "semesterName",value ="学期名称")
              @RequestParam(name="semesterName", required =false)
              String semesterName ) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        List<String> courseNamelist= stuItemGradeVOService.getcourseNameAll(semesterName);

        return Result.build(ResultType.Success).appendData("data",courseNamelist);
    }

    @ApiOperation(value = "学生根据学期名称和课程名称查询学期课程成绩", notes = "正在测试")
    @RequestMapping( value ="/selectcurrentStudent",method = RequestMethod.GET)
    @ResponseBody
    public Result selectcurrentStudent(
            @ApiParam( name = "semesterName",value = "学期名称")
            @RequestParam( name = "semesterName", required = false)
            String semesterName,
            @ApiParam( name = "courseName",value = "课程名称")
            @RequestParam( name = "courseName", required = false)
            String courseName){

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        HashMap currentSemesterNamelist = stuItemGradeVOService.selectcurrentStudent(semesterName,courseName);

        return Result.build(ResultType.Success).appendData("data",currentSemesterNamelist);
    }

    @ApiOperation(value = "根据课程编号查询课程信息", notes = "正在测试")
    @RequestMapping( value ="/selectBycourseNumber",method = RequestMethod.GET)
    @ResponseBody
    public Result selectBycourseNumber(
            @ApiParam( name= "courseNumber",value ="课程编号",required =true)
            @RequestParam(name="courseNumber")
            Long courseNumber ) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        HashMap course = stuItemGradeVOService.selectBycourseNumber( courseNumber );

        return Result.build(ResultType.Success).appendData("data",course);
    }


    @ApiOperation(value = "根据班级编号查询班级信息", notes = "正在测试")
    @RequestMapping( value ="/selectByclassCode" ,method = RequestMethod.GET)
    @ResponseBody
    public Result selectByclassCode(
            @ApiParam( name= "classCode",value ="班级编号",required =true)
            @RequestParam(name="classCode")
                    Long classCode ) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        HashMap  Code = stuItemGradeVOService.selectByclassCode( classCode );

        return Result.build(ResultType.Success).appendData("data",Code);
    }

    @ApiOperation(value = "根据学生id查询学生信息", notes = "正在测试")
    @RequestMapping( value ="/selectBystudentinform" ,method = RequestMethod.GET)
    @ResponseBody
    public Result selectBystudentinform(
            @ApiParam( name= "studentId",value ="学生id",required =true)
            @RequestParam(name="studentId")
                    Long studentId ) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        HashMap  student = stuItemGradeVOService.selectBystudentinform( studentId );

        return Result.build(ResultType.Success).appendData("data",student);
    }
}
