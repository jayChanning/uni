package edu.uni.gradeManagement1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.gradeManagement1.bean.StuItemGradeDetail;
import edu.uni.gradeManagement1.service.StuItemGradeDetailService;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private RedisCache cache;

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
     * @param
     * @return
     */
    @ApiOperation(value = "新增成绩得分项明细记录",notes = "测试中。。。")
//    @ApiImplicitParam(name = "stuItemGradeDetail",value = "成绩明细表实体类",required = true,dataType = "StuItemGradeDetail")
    @PostMapping("/stuItemGradeDetail")
    @ResponseBody
    public Result create(@RequestBody Map<String,String> map) {
        StuItemGradeDetail stuItemGradeDetail = new StuItemGradeDetail();
        System.out.println("****----"+map+"----****");
        /*  初始化数据 */
        //教师ID暂无法获取，先固定
        stuItemGradeDetail.setByWho((long) 1941);
        stuItemGradeDetail.setUniversityId((long) 1);
        // 默认0有效
//        stuItemGradeDetail.setDelete((byte)0);

        stuItemGradeDetail.setNote(map.get("note"));
        stuItemGradeDetail.setScore(Double.valueOf(map.get("score")));
        stuItemGradeDetail.setAttachment(map.get("uploadAddr"));
        stuItemGradeDetail.setStuItemGradeId(Long.valueOf(map.get("itemGradeId")));
        stuItemGradeDetail.setCourseItemDetailId(Long.valueOf(map.get("itemDetailId")));

        System.out.println("injected-Object<stuItemGradeDetail> : " + stuItemGradeDetail.toString()+"***end print");

//
//
//        if (stuItemGradeDetail != null){
//            boolean success = stuItemGradeDetailService.insert(stuItemGradeDetail);
//            if (success){
//                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix+"*");
//                cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix+"*");
//                return Result.build(ResultType.Success);
//            } else {
//                return Result.build(ResultType.Failed);
//            }
//        }
        return Result.build(ResultType.ParamError);
    }

}
