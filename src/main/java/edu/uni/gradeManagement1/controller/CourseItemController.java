package edu.uni.gradeManagement1.controller;

import edu.uni.auth.bean.User;
import edu.uni.auth.service.AuthService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.educateAffair.VO.CurriculumVO;
import edu.uni.educateAffair.VO.CurriculumWithCondition;
import edu.uni.educateAffair.service.CurriculumService;
import edu.uni.gradeManagement1.bean.CourseItem;
import edu.uni.gradeManagement1.service.CourseItemService;
import edu.uni.gradeManagement1.service.DaoDiService;
import edu.uni.gradeManagement1.service.StuGradeMainService;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋, 蔡政堂
 * create：2019-4-28
 * modified:2019-5-7
 * 功能：查询学生主表信息
 */
@Api(description = "林晓峰：成绩管理模块：查询成绩明细")
@Controller
@RequestMapping("/json/gradeManagement1")
public class CourseItemController {
    @Autowired
    private CourseItemService courseItemService;
    @Autowired
    AuthService authService;
//
//    @Resource
//    private CourseItem courseItem;


    /**
     * author 蔡政堂
     */
    @Autowired
    private RedisCache cache;
    @Autowired
    private StuGradeMainService gradeMainService;
    @Autowired
    private CurriculumService curriculumService;
    @Autowired
    private DaoDiService daoDiService;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     * author 蔡政堂
     */
    static class CacheNameHelper{
        //gm_courseItem_{成绩主表id}
        private static final String Receive_CacheNamePrefix = "gm_courseItem_";
        // gm_courseItem_list_{页码}
        private static final String List_CacheNamePrefix = "gm_courseItem_list_";
        // gm_courseItem_listByCid_{类别id}_{页码}
        private static final String ListByCid_CacheNamePrefix = "gm_courseItem_listByCid_";

    }

    /**
     * 新增课程成绩评分组成项的记录
     * @param courseItem
     * @return
     * author 蔡政堂
     */
    @ApiOperation(value = "新增课程成绩评分组成项的记录", notes = "已测试!")
    @ApiImplicitParam(name = "courseItem",value = "课程成绩评分组成项实体类",required = true,dataType = "CourseItem")
    @PostMapping("/courseItem")
    @ResponseBody
    public Result create(@RequestBody(required = false) CourseItem courseItem) {
        //注入测试数据到Bean类
        //对前端传来的rate格式化换算
        courseItem.setRate(courseItem.getRate() / 100);
        User user = authService.getUser();
        //初始化前台传来的数据
        courseItem.setUniversityId(user.getUniversityId());
        courseItem.setCourseId((Long.valueOf(courseItem.getCourseId())));
        courseItem.setDeleted(Byte.valueOf("0"));
        /* ByWho id 从session中获取 */
        courseItem.setByWho(user.getId());

        System.out.println("path: courseItem_post-- "+courseItem);


        if (courseItem != null){
            boolean success = courseItemService.insert(courseItem);
            if (success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix+"*");
                cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix+"*");
                return Result.build(ResultType.Success);
            } else {
                return Result.build(ResultType.Failed);

            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 根据id获取课程成绩评分组成项
     * @param id 评分项id
     * @param response
     * @throws IOException
     */  /**
     * author 蔡政堂
     */
//    @ApiOperation(value = "根据id获取成绩评分组成项",notes = "需要id")
//    @ApiImplicitParam(name = "id", value = "评分项id",required = true,dataType = "Long",paramType = "path")
//    @GetMapping("/courseItem/{id}")
    /*public void receive(@PathVariable Long id,
                        HttpServletResponse response ) throws IOException{
        response.setContentType("application/json;charset=utf-8");


        StuGradeMain stuGradeMain = new StuGradeMain();
        Semester semester = new Semester();
        gradeMainService.select((long)1);

        Long semesterId = stuGradeMain.getSemesterId();  //学期id
        System.out.println("courseItemController-test:"+semesterId);
//        semesterService.selectAll();

        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if (json == null){
            CourseItem courseItem = courseItemService.select(id);
            json = Result.build(ResultType.Success).appendData("courseItem",courseItem).convertIntoJSON();
            if (courseItem!= null){
                cache.set(cacheName,json);
            }
        }
        response.getWriter().write(json);
    }*/

    /**
     * 获取所有评分组成项信息--test1--listAll(default)
     * @param response
     * @throws IOException
     */  /**
     * author 蔡政堂
     */
    /*@ApiOperation(value = "获取所有成绩评分组成项",notes = "暂时不使用")
    @GetMapping("/courseItem/listAll")
    public void selectAll(HttpServletResponse response ) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix;
        String json = cache.get(cacheName);

        //查数据库
        if (json == null){
            json = Result.build(ResultType.Success).appendData("courseItem",courseItemService.selectAll()).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }*/

    /**
     * 录入成绩父页面数据 --/listInfo
     * 包含搜索栏的处理也在这
     * @param response
     * author 蔡政堂
     */
    @ApiOperation(value = "录入成绩页面数据，搜索栏所用接口",notes = "可分页传数据，已测试！")
    // @ApiImplicitParam(name = "pageNum", value = "请求的页码",required = true,dataType = "Integer",paramType = "path")
    @GetMapping("/courseItem/listInfo")
    @ResponseBody
    public Result Display2F(@ApiParam(value = "请求的页码", required = true) @RequestParam(value = "pageNum") int pageNum,
                            @ApiParam(value = "课程编号") @RequestParam(value = "courseId", required = false) String courseId,
                            @ApiParam(value = "课程名称") @RequestParam(value = "courseName", required = false ) String courseName,
                            @ApiParam(value = "教学班级-如16软件1") @RequestParam(value = "courseClass", required = false ) String courseClass,
                            HttpServletResponse response) {

        response.setContentType("application/json;charset=utf-8");
        System.out.println("courseItemController Tester: courseId="+courseId+"**courseName="+courseName+"**courseClass="+courseClass);

        //通过session获取userId
        User user = authService.getUser();
        long usrId = user.getId();
        
        //初始化空字符串为null. 如果查询条件为"".
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


    /**
     * 将分页单独出来，仅测试用，现在暂且不需要
     //* @param pageNum
     * @param response
     * date 2019/6/5
     * @throws IOException
     */  /**
     * author 蔡政堂
     */
//    private void cutPage(Integer pageNum, HttpServletResponse response) throws IOException {
//        String json;
//        if (pageNum < 1)
//            pageNum = 1;
//        if (pageNum > 2)
//            pageNum = 2;
//        if (pageNum == 1) {
//            //TEST DATA
//            ArrayList<ItemGradeInfo> arrayList = new ArrayList<>();
//            arrayList.add(new ItemGradeInfo("2017-2018-1", "3", "软件体系结构","16软件1班"));
//            arrayList.add(new ItemGradeInfo("2017-2018-1", "2", "JAVA程序设计","16软件1班"));
//            arrayList.add(new ItemGradeInfo("2017-2018-2", "4", "软件项目管理","16软件2班"));
//            arrayList.add(new ItemGradeInfo("2018-2019-1", "5", "人工智能","16软件1班"));
//            arrayList.add(new ItemGradeInfo("2018-2019-2", "6", "ASP.NET","16软件1班"));
//
//            System.out.println("path:/courseItem/listInfo/page_1--"+arrayList);
//
//            PageInfo pageInfo = new PageInfo<>(arrayList);
//
//            pageInfo.setTotal(7);
//            pageInfo.setPageNum(1);
//            pageInfo.setPageSize(5);
//            pageInfo.setSize(5);
//            pageInfo.setPages(2);
//            pageInfo.setPrePage(0);
//            pageInfo.setNextPage(2);
//            pageInfo.setHasNextPage(true);
//            pageInfo.setHasPreviousPage(false);
//            pageInfo.setIsFirstPage(true);
//            pageInfo.setIsLastPage(false);
//            pageInfo.setLastPage(2);
//            pageInfo.setFirstPage(1);
//
//            json = Result.build(ResultType.Success).appendData("data",pageInfo).convertIntoJSON();
//
//        } else {
//            ArrayList<ItemGradeInfo> arrayList = new ArrayList<>();
//            arrayList.add(new ItemGradeInfo("2018-2019-2", "7", "C语言程序设计","16软件1班"));
//            arrayList.add(new ItemGradeInfo("2018-2019-2", "8", "软件测试技术","16软件1班"));
//
//            System.out.println(new Date().getTime()+"path:/courseItem/listInfo/page_2--"+arrayList);
//            PageInfo pageInfo = new PageInfo<>(arrayList);
//
//            pageInfo.setTotal(7);
//            pageInfo.setPageNum(2);
//            pageInfo.setPageSize(5);
//            pageInfo.setSize(2);
//            pageInfo.setPages(2);
//            pageInfo.setPrePage(1);
//            pageInfo.setNextPage(0);
//            pageInfo.setHasNextPage(false);
//            pageInfo.setHasPreviousPage(true);
//            pageInfo.setIsFirstPage(false);
//            pageInfo.setIsLastPage(true);
//            pageInfo.setLastPage(2);
//            pageInfo.setFirstPage(1);
//
//            json = Result.build(ResultType.Success).appendData("data", pageInfo).convertIntoJSON();
//        }
//        response.getWriter().write(json);
//    }
//
//
//    /**
//     * TODO 查询此教师用户本学期所教的课程
//     * @param response
//     * @throws IOException
//     */
//    @ApiOperation(value = "查询此教师用户本学期所教的课程",notes = "Tested!")
//    @GetMapping("/courseItem/findClass")
//    public void searchFor(@RequestParam Long employeeId, HttpServletResponse response ) throws IOException {
//        response.setContentType("application/json;charset=utf-8");
//        String json = null;
//
//        //调用校历的service层
//        CurriculumWithCondition curriculumWithCondition = new CurriculumWithCondition();
//        curriculumWithCondition.setEmployeeId(employeeId);
//        curriculumWithCondition.setCourse(true);
//        curriculumWithCondition.setClass(true);
//        curriculumWithCondition.setEmployee(true);
//
//        List<CurriculumVO> curriculumList = curriculumService.Transform(curriculumWithCondition);
//        System.out.println(curriculumList);
//        if (json == null) {
//            json = Result.build(ResultType.Success).appendData("item",curriculumService.Transform(curriculumWithCondition)).convertIntoJSON();
//
//        }
//
//        System.out.println(json);
//        response.getWriter().write(json);
//    }
    /**
     * author 蔡政堂
     */
    @ApiOperation(value = "查询学期、校历信息接口", notes = "测试中...")
    @GetMapping("/courseItem/findItem/")
    public void findItem(@RequestParam Long courseId, String courseName, String courseClass,
                         HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String json = null;
        CurriculumWithCondition curriculum = new CurriculumWithCondition();
        curriculum.setClass(true);
        curriculum.setCourse(true);

        List<CurriculumVO> curriculumVOList = curriculumService.Transform(curriculum);
        //checkout curriculumVOList
        System.out.println(curriculumVOList);

        if (json == null) {
            json = Result.build(ResultType.Success).appendData("data", curriculumVOList).convertIntoJSON();
        }
        System.out.println(json);
        response.getWriter().write(json);
    }

    /**
     * 查询组成项得分
     * @param id
     * @return
     */  /**
     * author 林晓锋
     */
    @ApiOperation(value = "查询组成项得分", notes = "已测试")
    @RequestMapping(value = "/selectCourseItem",method = RequestMethod.GET)
    @ResponseBody
    public Result getCourseItem(@ApiParam( name= "id",value ="主表id", required = true )
                                    @RequestParam(name="id", required =true) Long id){
        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");
        List<HashMap> list = courseItemService.selectCourseItem(id);

       return Result.build(ResultType.Success).appendData("data", list);

    }

    /**
     * 查询组成项名称为作业的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     *//**
     * author 林晓锋
     */
    @ApiOperation(value ="查询组成项名称为作业的内容及得分",notes = "正在测试")
    @RequestMapping(value = "/selectCourseItemDetail1",method = RequestMethod.GET)
    @ResponseBody

    public Result selectCourseItemDetail1(
            @ApiParam( name= "course_item_id",value ="课程组成项id", required = true )
            @RequestParam(name="course_item_id")
            Long course_item_id,
            @ApiParam( name= "stu_item_grade_id",value ="组成项得分id", required = true )
            @RequestParam(name="stu_item_grade_id")
            Long stu_item_grade_id ) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");
        List<HashMap> list1 = courseItemService.selectCourseItemDetail1( course_item_id,stu_item_grade_id );

        return Result.build(ResultType.Success).appendData("data",list1);
    }

    /**
     * 查询组成项名称为考勤的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     *//**
     * author 林晓锋
     */
    @ApiOperation(value ="查询组成项名称为考勤的内容及得分",notes = "正在测试")
    @RequestMapping(value = "/selectCourseItemDetail2",method = RequestMethod.GET)
    @ResponseBody

    public Result selectCourseItemDetail2(
            @ApiParam( name= "course_item_id",value ="课程组成项id", required = true )
            @RequestParam(name="course_item_id")
                    Long course_item_id,
            @ApiParam( name= "stu_item_grade_id",value ="组成项得分id", required = true )
            @RequestParam(name="stu_item_grade_id")
                    Long stu_item_grade_id ) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        List<HashMap> list2 = courseItemService.selectCourseItemDetail2( course_item_id,stu_item_grade_id );

        return Result.build(ResultType.Success).appendData("data",list2);
    }

    /**
     * 查询组成项名称为期中考试的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     *//**
     * author 林晓锋
     */
    @ApiOperation(value ="查询组成项名称为期中的内容及得分",notes = "正在测试")
    @RequestMapping(value = "/selectCourseItemDetail3",method = RequestMethod.GET)
    @ResponseBody

    public Result selectCourseItemDetail3(
            @ApiParam( name= "course_item_id",value ="课程组成项id", required = true )
            @RequestParam(name="course_item_id")
                    Long course_item_id,
            @ApiParam( name= "stu_item_grade_id",value ="组成项得分id", required = true )
            @RequestParam(name="stu_item_grade_id")
                    Long stu_item_grade_id ) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        List<HashMap> list3 = courseItemService.selectCourseItemDetail3(course_item_id,stu_item_grade_id );

        return Result.build(ResultType.Success).appendData("data",list3);
    }

    /**
     * 查询组成项名称为实验的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     *//**
     * author 林晓锋
     */
    @ApiOperation(value ="查询组成项名称为实验的内容及得分",notes = "正在测试")
    @RequestMapping(value = "/selectCourseItemDetail4",method = RequestMethod.GET)
    @ResponseBody

    public Result selectCourseItemDetail4(
            @ApiParam( name= "course_item_id",value ="课程组成项id", required = true )
            @RequestParam(name="course_item_id")
                    Long course_item_id,
            @ApiParam( name= "stu_item_grade_id",value ="组成项得分id", required = true )
            @RequestParam(name="stu_item_grade_id")
                    Long stu_item_grade_id ) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        List<HashMap> list4 = courseItemService.selectCourseItemDetail4( course_item_id,stu_item_grade_id );

        return Result.build(ResultType.Success).appendData("data",list4);
    }

    /**
     * 查询组成项名称为期末考试的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     *//**
     * author 林晓锋
     */
    @ApiOperation(value ="查询组成项名称为期末考试的内容及得分",notes = "正在测试")
    @RequestMapping(value = "/selectCourseItemDetail5",method = RequestMethod.GET)
    @ResponseBody

    public Result selectCourseItemDetail5(
            @ApiParam( name= "course_item_id",value ="课程组成项id", required = true )
            @RequestParam(name="course_item_id")
                    Long course_item_id,
            @ApiParam( name= "stu_item_grade_id",value ="组成项得分id", required = true )
            @RequestParam(name="stu_item_grade_id")
                    Long stu_item_grade_id ) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        List<HashMap> list5 = courseItemService.selectCourseItemDetail5( course_item_id,stu_item_grade_id);

        return Result.build(ResultType.Success).appendData("data",list5);
    }

    /**
     * 查询组成项名称为其他的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     *//**
     * author 林晓锋
     */
    @ApiOperation(value ="查询组成项名称为其他的内容及得分",notes = "正在测试")
    @RequestMapping(value = "/selectCourseItemDetail6",method = RequestMethod.GET)
    @ResponseBody

    public Result selectCourseItemDetail6(
            @ApiParam( name= "course_item_id",value ="课程组成项id", required = true )
            @RequestParam(name="course_item_id")
                    Long course_item_id,
            @ApiParam( name= "stu_item_grade_id",value ="组成项得分id", required = true )
            @RequestParam(name="stu_item_grade_id")
                    Long stu_item_grade_id ) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        List<HashMap> list6 = courseItemService.selectCourseItemDetail6( course_item_id,stu_item_grade_id);

        return Result.build(ResultType.Success).appendData("data",list6);
    }

}
