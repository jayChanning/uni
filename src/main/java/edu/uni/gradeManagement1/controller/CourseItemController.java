package edu.uni.gradeManagement1.controller;

import edu.uni.auth.bean.User;
import edu.uni.auth.service.AuthService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.educateAffair.VO.CurriculumVO;
import edu.uni.educateAffair.VO.CurriculumWithCondition;
import edu.uni.educateAffair.service.CurriculumService;
import edu.uni.gradeManagement1.bean.CourseItem;
import edu.uni.gradeManagement1.bean.CourseItemDetail;
import edu.uni.gradeManagement1.bean.StuItemGrade;
import edu.uni.gradeManagement1.service.*;
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
import java.util.Map;

/**
 * @author 林晓锋, 蔡政堂
 * create：2019-4-28
 * modified:2019-6-19
 * 功能：查询学生主表信息
 */
@Api(description = "林晓峰：成绩管理模块：查询成绩明细,插入课程组成项")
@Controller
@RequestMapping("/json/gradeManagement1")
public class CourseItemController {
    @Autowired
    private CourseItemService courseItemService;
    @Autowired
    AuthService authService;

    /**
     * author 蔡政堂
     */
    @Autowired
    private RedisCache cache;
    @Autowired
    private CourseItemDetailService courseItemDetailService;
    @Autowired
    private CurriculumService curriculumService;
    @Autowired
    private DaoDiService daoDiService;
    @Autowired
    private StuItemGradeService stuItemGradeService;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     * author 蔡政堂
     */
    static class CacheNameHelper {
        //gm_courseItem_{成绩主表id}
        private static final String Receive_CacheNamePrefix = "gm_courseItem_";
        // gm_courseItem_list_{页码}
        private static final String List_CacheNamePrefix = "gm_courseItem_list_";
        // gm_courseItem_listByCid_{类别id}_{页码}
        private static final String ListByCid_CacheNamePrefix = "gm_courseItem_listByCid_";

    }

    /**
     * 新增课程成绩评分组成项的记录
     *
     * @param
     * @return author 蔡政堂
     */
    @ApiOperation(value = "新增课程成绩评分组成项的记录", notes = "已测试!")
    @ApiImplicitParam(name = "courseItem", value = "课程成绩评分组成项实体类", required = true, dataType = "CourseItem")
    @PostMapping("/courseItem")
    @ResponseBody
//    public Result create(@RequestBody(required = false) CourseItem courseItem) {
    public Result create(@RequestBody(required = false)Map<String,String> map) {
        System.out.println(map);
        /* 从session中获取user信息 */
        User user = authService.getUser();
        /* 注入测试数据到Bean类 */
        CourseItem courseItem = new CourseItem();
        //对前端传来的rate换算
        courseItem.setRate(Double.valueOf(map.get("rate")) / 100);
        //初始化前台传来的数据
        courseItem.setUniversityId(user.getUniversityId());
        courseItem.setCourseId((Long.valueOf(map.get("courseId"))));
        courseItem.setName(Byte.valueOf(map.get("name")));
        courseItem.setCount(Integer.valueOf(map.get("count")));
//        courseItem.setDeleted(Byte.valueOf("0"));

        // ByWho 从session中获取user.id
        courseItem.setByWho(user.getId());
        System.out.println("path: courseItem_post-- " + courseItem);
        //用于查询成绩主表id的两个id值
        long taskId = Long.valueOf(map.get("taskId"));
        long semesterId = Long.valueOf(map.get("semesterId"));
        long cId = courseItem.getCourseId();

        if (courseItem != null) {
            boolean success = courseItemService.insert(courseItem);
            if (success) {
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix + "*");
                /**
                 * 自动生成course_item对应的course_item_detail项
                 */
                long courseItemId = courseItem.getId();
                int itemCount = courseItem.getCount();
                byte name = courseItem.getName();
                String courseItemDetailName;
                switch (name) {
                    case 1:
                        courseItemDetailName = "作业";
                        break;
                    case 2:
                        courseItemDetailName = "考勤";
                        break;
                    case 3:
                        courseItemDetailName = "期中测试";
                        break;
                    case 4:
                        courseItemDetailName = "实验";
                        break;
                    case 5:
                        courseItemDetailName = "期末考试";
                        break;
                    case 6:
                        courseItemDetailName = "其他";
                        break;
                    default:
                        courseItemDetailName = "无效的课程组成项明细名！!";
                        break;
                }
                for (int i = 1; i <= itemCount; i++) {
                    CourseItemDetail itemDetail = new CourseItemDetail();
                    itemDetail.setUniversityId(courseItem.getUniversityId());
                    itemDetail.setCourseItemId(courseItemId);
                    itemDetail.setNumber(i);
                    itemDetail.setContent("第" + i + "次" + courseItemDetailName);
                    itemDetail.setByWho(courseItem.getByWho());
                    itemDetail.setDeleted(Byte.valueOf("0"));
                    courseItemDetailService.insert(itemDetail);
//                    System.out.println("courseItemDetail=" + itemDetail);
                }
                /**
                 * 自动生成stu_item_grade表的学生对应的成绩组成项
                 */
                List<Long> list = courseItemDetailService.autoCreateCItemDetail(semesterId, taskId, cId);
                for (int j = 0; j < list.size();j++){
//                    System.out.println("list["+j+"]="+list.get(j));
                    StuItemGrade stuItemGrade = new StuItemGrade();
                    stuItemGrade.setUniversityId(user.getUniversityId());
                    stuItemGrade.setStuGradeMainId(list.get(j));
                    stuItemGrade.setCourseItemId(courseItemId);
                    stuItemGrade.setScore(0.00);
                    stuItemGrade.setNote("");
                    stuItemGrade.setByWho(user.getId());
                    stuItemGrade.setDeleted(Byte.valueOf("0"));
                    stuItemGradeService.insert(stuItemGrade);
                }

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
     * author 蔡政堂
     * @param response
     */
    @ApiOperation(value = "录入成绩页面数据，搜索栏所用接口", notes = "可分页传数据，已测试！")
    // @ApiImplicitParam(name = "pageNum", value = "请求的页码",required = true,dataType = "Integer",paramType = "path")
    @GetMapping("/courseItem/listInfo")
    @ResponseBody
    public Result Display2F(@ApiParam(value = "请求的页码", required = true) @RequestParam(value = "pageNum") int pageNum,
                            @ApiParam(value = "课程编号") @RequestParam(value = "courseId", required = false) String courseId,
                            @ApiParam(value = "课程名称") @RequestParam(value = "courseName", required = false) String courseName,
                            @ApiParam(value = "教学班级-如16软件1") @RequestParam(value = "courseClass", required = false) String courseClass,
                            HttpServletResponse response) {

        response.setContentType("application/json;charset=utf-8");
        System.out.println("courseItemController Tester: courseId=" + courseId + "**courseName=" + courseName + "**courseClass=" + courseClass);

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
     */
    /**
     * author 林晓锋
     */
    @ApiOperation(value = "查询组成项得分", notes = "已测试")
    @RequestMapping(value = "/selectCourseItem", method = RequestMethod.GET)
    @ResponseBody
    public Result getCourseItem(@ApiParam(name = "id", value = "主表id", required = true)
                                @RequestParam(name = "id", required = true) Long id) {
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
     */
    /**
     * author 林晓锋
     */
    @ApiOperation(value = "查询组成项名称为作业的内容及得分", notes = "正在测试")
    @RequestMapping(value = "/selectCourseItemDetail1", method = RequestMethod.GET)
    @ResponseBody

    public Result selectCourseItemDetail1(
            @ApiParam(name = "course_item_id", value = "课程组成项id", required = true)
            @RequestParam(name = "course_item_id")
                    Long course_item_id,
            @ApiParam(name = "stu_item_grade_id", value = "组成项得分id", required = true)
            @RequestParam(name = "stu_item_grade_id")
                    Long stu_item_grade_id) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");
        List<HashMap> list1 = courseItemService.selectCourseItemDetail1(course_item_id, stu_item_grade_id);

        return Result.build(ResultType.Success).appendData("data", list1);
    }

    /**
     * 查询组成项名称为考勤的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    /**
     * author 林晓锋
     */
    @ApiOperation(value = "查询组成项名称为考勤的内容及得分", notes = "正在测试")
    @RequestMapping(value = "/selectCourseItemDetail2", method = RequestMethod.GET)
    @ResponseBody

    public Result selectCourseItemDetail2(
            @ApiParam(name = "course_item_id", value = "课程组成项id", required = true)
            @RequestParam(name = "course_item_id")
                    Long course_item_id,
            @ApiParam(name = "stu_item_grade_id", value = "组成项得分id", required = true)
            @RequestParam(name = "stu_item_grade_id")
                    Long stu_item_grade_id) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        List<HashMap> list2 = courseItemService.selectCourseItemDetail2(course_item_id, stu_item_grade_id);

        return Result.build(ResultType.Success).appendData("data", list2);
    }

    /**
     * 查询组成项名称为期中考试的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    /**
     * author 林晓锋
     */
    @ApiOperation(value = "查询组成项名称为期中的内容及得分", notes = "正在测试")
    @RequestMapping(value = "/selectCourseItemDetail3", method = RequestMethod.GET)
    @ResponseBody

    public Result selectCourseItemDetail3(
            @ApiParam(name = "course_item_id", value = "课程组成项id", required = true)
            @RequestParam(name = "course_item_id")
                    Long course_item_id,
            @ApiParam(name = "stu_item_grade_id", value = "组成项得分id", required = true)
            @RequestParam(name = "stu_item_grade_id")
                    Long stu_item_grade_id) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        List<HashMap> list3 = courseItemService.selectCourseItemDetail3(course_item_id, stu_item_grade_id);

        return Result.build(ResultType.Success).appendData("data", list3);
    }

    /**
     * 查询组成项名称为实验的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    /**
     * author 林晓锋
     */
    @ApiOperation(value = "查询组成项名称为实验的内容及得分", notes = "正在测试")
    @RequestMapping(value = "/selectCourseItemDetail4", method = RequestMethod.GET)
    @ResponseBody

    public Result selectCourseItemDetail4(
            @ApiParam(name = "course_item_id", value = "课程组成项id", required = true)
            @RequestParam(name = "course_item_id")
                    Long course_item_id,
            @ApiParam(name = "stu_item_grade_id", value = "组成项得分id", required = true)
            @RequestParam(name = "stu_item_grade_id")
                    Long stu_item_grade_id) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        List<HashMap> list4 = courseItemService.selectCourseItemDetail4(course_item_id, stu_item_grade_id);

        return Result.build(ResultType.Success).appendData("data", list4);
    }

    /**
     * 查询组成项名称为期末考试的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    /**
     * author 林晓锋
     */
    @ApiOperation(value = "查询组成项名称为期末考试的内容及得分", notes = "正在测试")
    @RequestMapping(value = "/selectCourseItemDetail5", method = RequestMethod.GET)
    @ResponseBody

    public Result selectCourseItemDetail5(
            @ApiParam(name = "course_item_id", value = "课程组成项id", required = true)
            @RequestParam(name = "course_item_id")
                    Long course_item_id,
            @ApiParam(name = "stu_item_grade_id", value = "组成项得分id", required = true)
            @RequestParam(name = "stu_item_grade_id")
                    Long stu_item_grade_id) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        List<HashMap> list5 = courseItemService.selectCourseItemDetail5(course_item_id, stu_item_grade_id);

        return Result.build(ResultType.Success).appendData("data", list5);
    }

    /**
     * 查询组成项名称为其他的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    /**
     * author 林晓锋
     */
    @ApiOperation(value = "查询组成项名称为其他的内容及得分", notes = "正在测试")
    @RequestMapping(value = "/selectCourseItemDetail6", method = RequestMethod.GET)
    @ResponseBody

    public Result selectCourseItemDetail6(
            @ApiParam(name = "course_item_id", value = "课程组成项id", required = true)
            @RequestParam(name = "course_item_id")
                    Long course_item_id,
            @ApiParam(name = "stu_item_grade_id", value = "组成项得分id", required = true)
            @RequestParam(name = "stu_item_grade_id")
                    Long stu_item_grade_id) {

        System.out.println(authService.getUser());
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");

        List<HashMap> list6 = courseItemService.selectCourseItemDetail6(course_item_id, stu_item_grade_id);

        return Result.build(ResultType.Success).appendData("data", list6);
    }

}
