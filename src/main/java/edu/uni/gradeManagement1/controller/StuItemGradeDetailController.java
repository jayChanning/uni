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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 蔡政堂
 * create 2019/5/5
 * modified 2019/6/20
 * description TODO 录入成绩得分项明细的接口
 */

@Api(description = "蔡政堂: 成绩管理：录入成绩得分项明细模块")
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

    /**
     * 内部类，使用Redis缓存
     */
    static class CacheNameHelper {
        //gm_stuItemGradeDetail_{成绩明细项id}
        private static final String Receive_CacheNamePrefix = "gm_stuItemGradeDetail_";
        //gm_stuItemGradeDetail_list_{页码}
        private static final String List_CacheNamePrefix = "gm_stuItemGradeDetail_list_";
        //gm_stuItemGradeDetail_listByCid_{类别id}_{页码}
        private static final String ListByCid_CacheNamePrefix = "gm_stuItemGradeDetail_listByCid_";
    }

    //static HashMap<Long,Long> myUserId = new HashMap<>();

    /**
     * 新增成绩项明细记录
     *
     * @param map 用于接收前台传来的成绩项明细json对象
     *            map对应InsertGradeDetail对象，map.get("前端规定的参数名")
     * @return ResultType
     */
    @ApiOperation(value = "新增成绩得分项明细记录", notes = "已测试！")
    @ApiImplicitParam(name = "gradeDetail", value = "新增成绩得分项明细记录", required = true, dataType = "InsertGradeDetail")
    @PostMapping("/stuItemGradeDetail")
    @ResponseBody
    public Result create(@RequestBody Map<String, InsertGradeDetail> map, HttpServletRequest request) {
        User user = authService.getUser();
        if (user == null)
            return Result.build(ResultType.Failed, "你沒有登錄");
        InsertGradeDetail gradeDetail = map.get("params");
        HttpSession session = request.getSession();
        session.setAttribute("useId", user.getId());
        long usrId = user.getId();
        long universityId = user.getUniversityId();
        StuItemGradeDetail stuItemGradeDetail = new StuItemGradeDetail();
        System.out.println("****----" + map + "----****");
        /*  初始化数据 */
        //教师ID从session中获取，即对应userId
        stuItemGradeDetail.setByWho(usrId);
        stuItemGradeDetail.setUniversityId(universityId);
        // 默认0有效,亦暂且固定
        stuItemGradeDetail.setDelete((byte) 0);
        /* inject detail data into Object StuItemGradeDetail */
        stuItemGradeDetail.setNote(gradeDetail.getNote());
        stuItemGradeDetail.setScore(gradeDetail.getScore());
        stuItemGradeDetail.setAttachment(gradeDetail.getUploadAddr());
        stuItemGradeDetail.setStuItemGradeId(gradeDetail.getItemGradeId());
        stuItemGradeDetail.setCourseItemDetailId(gradeDetail.getItemDetailId());
        System.out.println(stuItemGradeDetail.toString());

//        System.out.println("Injected-Object<stuItemGradeDetail> : " + stuItemGradeDetail.toString()+"***end print");

        if (stuItemGradeDetail != null) {
            boolean success = stuItemGradeDetailService.insert(stuItemGradeDetail);
//            boolean success = false;
            if (success) {
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix + "*");

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

    /**
     * Excel导入功能接口，自动解析并插入成绩明细表中
     *
     * @param map 用Map键值对接收前端的数据
     * @return ResultType success/paramError/failed
     * @throws Exception
     * @author 蔡政堂
     */
    @ApiOperation(value = "Excel导入功能，自动解析", notes = "测试中。。。")
    @PostMapping("/stuExcelUpload")
    @ResponseBody
    public Result excelImport(@RequestBody Map<String, String> map, HttpServletRequest request) throws Exception {
        System.out.println("******" + map + "******");
        /* 先从session中获得当前登录的教师id (user_id)  */
        User user = authService.getUser();
        System.out.println("user:" + user + "//userId=" + user.getId());
        long temp_usrId;
        if (user == null)
            //session失效时临时代替getUser()方法
            temp_usrId = 1952;
        else
            temp_usrId = user.getId();

        long semesterId = Long.valueOf(map.get("semesterId"));  //学期id
        long courseId = Long.valueOf(map.get("courseId"));   //课程id
        String excelAddr = map.get("excelAddr");   //excel上传地址
//        excel的上传路径(绝对路径, 且是存在的)
        String filePath = excelAddr.replace("%3A", ":").replace("%2F", "/");
        System.out.println("excel上传的实际路径: " + filePath);
        List<Object> list = ExcelUtil.excelReadService(filePath, ExcelDeal.class);

        int insertCount = 0;
        for (int i = 0; i < list.size(); i++) {
//            ExcelDeal excelDeal = (ExcelDeal) list.get(i);
//            System.out.println(excelDeal.toString());

            //根据excel表的学生学号查询获得学生id
            String stuNo = ((ExcelDeal) list.get(i)).getStuNo();
            long stuId = stuItemGradeDetailService.findStuId(stuNo);
            System.out.println("stu-Id = " + stuId);

            //接下来通过学生id查询获得主表id
            long mainId = stuItemGradeDetailService.getMainIdByStuId(semesterId, courseId, stuId);
            System.out.println("main-Id = " + mainId);

            //通过成绩主表id查询获得成绩项id和课程组成项明细id
            List<HashMap> hashMapList = stuItemGradeDetailService.findItemGradeANDItemDetailId(mainId, ((ExcelDeal) list.get(i)).getCourseItem(), ((ExcelDeal) list.get(i)).getItemNo());
            long itemGradeId = (long) hashMapList.get(0).get("itemGradeId");      //成绩组成项id
            long itemDetailId = (long) hashMapList.get(0).get("itemDetailId");   //课程组成项明细id
            System.out.println("item-grade-id/item-detail-id = " + itemGradeId + "/" + itemDetailId);

            /**
             * 这是将成绩写入数据库的最终操作！
             */
            // System.out.println("temp:"+temp_usrId);
            /* 新建一个对象并初始化，为写入数据库准备 */
            StuItemGradeDetail stuItemGradeDetail = new StuItemGradeDetail();
            stuItemGradeDetail.setStuItemGradeId(itemGradeId);
            stuItemGradeDetail.setCourseItemDetailId(itemDetailId);
            //批量导入成绩不能上传附件，因为无法把附件和每个学生对应
            stuItemGradeDetail.setAttachment("NULL_ADDR");
            stuItemGradeDetail.setScore(((ExcelDeal) list.get(i)).getScore());
            stuItemGradeDetail.setNote(((ExcelDeal) list.get(i)).getNotes());
//            stuItemGradeDetail.setUniversityId(usr.getUniversityId());
            //学校id固定为1
            stuItemGradeDetail.setUniversityId(1L);
//            System.out.println(usr.getUserName()+" 's University_ID is: "+usr.getUniversityId());
//            stuItemGradeDetail.setByWho(usr.getId());
            stuItemGradeDetail.setByWho(temp_usrId);
            stuItemGradeDetail.setDelete(Byte.valueOf("0"));
            System.out.println("成绩明细:" + stuItemGradeDetail.toString());

            boolean gradeDetail_success = stuItemGradeDetailService.insert(stuItemGradeDetail);
//            boolean gradeDetail_success = true;
            if (gradeDetail_success) {
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix + "*");

                /*  根据courseItemDetail的id更新课程组成项明细表的描述(content)  */
                CourseItemDetail courseItemDetail = new CourseItemDetail();
                //根据id查找到当前记录
                courseItemDetail.setId(itemDetailId);
                //更新课程组成项明细表的content
                courseItemDetail.setContent(((ExcelDeal) list.get(i)).getDetailContent());
                courseItemDetailService.updateContent(courseItemDetail);
                insertCount++;
                continue;
            } //item_detail insert succeeded
            System.out.println("[CHECKOUT]第" + i + "个学生" + ((ExcelDeal) list.get(i)).getStuNo() + "成绩录入失败！");
            ResultType.Failed.setMSG("[CHECKOUT]第" + i + "个学生" + ((ExcelDeal) list.get(i)).getStuNo() + "成绩录入失败！");
            return Result.build(ResultType.Failed);
        } //for循环
        System.out.println("END!!--insertCount=" + insertCount);
        if (insertCount == list.size()) {
            ResultType.Success.setMSG("已成功录入" + insertCount + "个学生的成绩！");
            return Result.build(ResultType.Success);
        }
        return Result.build(ResultType.ParamError);
    }

}
