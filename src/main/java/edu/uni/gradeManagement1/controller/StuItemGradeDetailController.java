package edu.uni.gradeManagement1.controller;

import edu.uni.auth.bean.User;
import edu.uni.auth.service.AuthService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.gradeManagement1.bean.CourseItemDetail;
import edu.uni.gradeManagement1.bean.StuItemGradeDetail;
import edu.uni.gradeManagement1.pojo.ExcelDeal;
import edu.uni.gradeManagement1.pojo.InsertGradeDetail;
import edu.uni.gradeManagement1.service.CourseItemDetailService;
import edu.uni.gradeManagement1.service.StuItemGradeDetailService;
import edu.uni.gradeManagement1.utils.ExcelUtil;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author 蔡政堂
 * create 2019/5/5
 * modified 2019/5/5
 * description TODO
 */

@Api(description = "成绩管理：成绩得分项明细模块")
@Controller
@RequestMapping("json/gradeManagement1")

public class StuItemGradeDetailController {
    @Autowired
    private StuItemGradeDetailService stuItemGradeDetailService;
    @Autowired
    private CourseItemDetailService courseItemDetailService;
    @Autowired
    private RedisCache cache;
    @Autowired
    private AuthService authService;

    static class CacheNameHelper {
        //gm_stuItemGradeDetail_{成绩明细项id}
        private static final String Receive_CacheNamePrefix = "gm_stuItemGradeDetail_";
        //gm_stuItemGradeDetail_list_{页码}
        private static final String List_CacheNamePrefix = "gm_stuItemGradeDetail_list_";
        //gm_stuItemGradeDetail_listByCid_{类别id}_{页码}
        private static final String ListByCid_CacheNamePrefix = "gm_stuItemGradeDetail_listByCid_";
    }

    /**
     * 新增成绩项明细记录
     * @param map 用于接收前台传来的成绩项明细json对象
     *  map对应InsertGradeDetail对象，map.get("前端规定的参数名")
     * @return ResultType
     */
    @ApiOperation(value = "新增成绩得分项明细记录",notes = "已测试！")
    @ApiImplicitParam(name = "gradeDetail",value = "新增成绩得分项明细记录",required = true,dataType = "InsertGradeDetail")
    @PostMapping("/stuItemGradeDetail")
    @ResponseBody
    public Result create(@RequestBody Map<String,InsertGradeDetail> map) {
        InsertGradeDetail gradeDetail = map.get("params");
        User user = authService.getUser();
        long usrId = user.getId();
        long universityId = user.getUniversityId();
        StuItemGradeDetail stuItemGradeDetail = new StuItemGradeDetail();
        System.out.println("****----"+map+"----****");
        /*  初始化数据 */
        //教师ID从session中获取，即对应userId
        stuItemGradeDetail.setByWho(usrId);
        stuItemGradeDetail.setUniversityId(universityId);
        // 默认0有效,亦暂且固定
//        stuItemGradeDetail.setDelete((byte)0);
        /* inject detail data into Object StuItemGradeDetail */
        stuItemGradeDetail.setNote(gradeDetail.getNote());
        stuItemGradeDetail.setScore(gradeDetail.getScore());
        stuItemGradeDetail.setAttachment(gradeDetail.getUploadAddr());
        stuItemGradeDetail.setStuItemGradeId(gradeDetail.getItemGradeId());
        stuItemGradeDetail.setCourseItemDetailId(gradeDetail.getItemDetailId());

//        System.out.println("Injected-Object<stuItemGradeDetail> : " + stuItemGradeDetail.toString()+"***end print");

        if (stuItemGradeDetail != null) {
            boolean success = stuItemGradeDetailService.insert(stuItemGradeDetail);
            if (success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix+"*");
                cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix+"*");

                /*  根据courseItemDetail的id更新课程组成项明细表的描述(content)  */
                CourseItemDetail courseItemDetail = new CourseItemDetail();
                //根据id查找到当前记录
                courseItemDetail.setId(gradeDetail.getItemDetailId());
                //更新课程组成项明细表的content
                courseItemDetail.setContent(gradeDetail.getContent());
                boolean update_success = courseItemDetailService.updateContent(courseItemDetail);
                if (update_success) {
                    return Result.build(ResultType.Success);
                } else {
                    return Result.build(ResultType.Failed);
                }

            } else {
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }


    @ApiOperation(value = "Excel导入功能，自动解析", notes = "测试中。。。")
    @ApiImplicitParam(name = "excelUpload",value = "Excel批量导入",required = true)
    @PostMapping("/stuExcelUpload")
    @ResponseBody
    public Result excelUpload(@RequestBody Map<String, String> map) throws Exception {
        long semesterId = Long.valueOf(map.get("semesterId"));  //学期id
        long courseId = Long.valueOf(map.get("courseId"));   //课程id
        long classId = Long.valueOf(map.get("classId"));   //班级id
//        excel的上传路径(绝对路径, 且是存在的)
//        String filePath = "D:\\JayChoi\\codes\\Idea\\uni\\src\\main\\resources\\excelTest\\excelInsert1.xlsx";
        String filePath = map.get("excelAddr").replace("%3A", ":").replace("%2F", "/");
        System.out.println(filePath);
        List<Object> list = ExcelUtil.excelReadService(filePath, ExcelDeal.class);
        for (int i = 0; i < list.size(); i++) {
            ExcelDeal excelDeal = (ExcelDeal) list.get(i);
            System.out.println(excelDeal.toString());
            //根据excel表的学生学号查询获得学生id
            String stuNo = ((ExcelDeal) list.get(i)).getStuNo();
            long stuId = stuItemGradeDetailService.findStuId(stuNo);
            //已经获得了学生id，接下来写获取组成项明细和成绩项id，早上加班！！！！！！！！！

        }

        return Result.build(ResultType.ParamError);
    }


}
