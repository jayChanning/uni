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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @param stuItemGradeDetail
     * @return
     */
    @ApiOperation(value = "新增成绩得分项明细记录",notes = "")
    @ApiImplicitParam(name = "stuItemGradeDetail",value = "成绩明细表实体类",required = true,dataType = "StuItemGradeDetail")
    @PostMapping("/stuItemGradeDetail")
    @ResponseBody
    public Result create(@RequestBody(required = false) StuItemGradeDetail stuItemGradeDetail) {
        if (stuItemGradeDetail != null){
            boolean success = stuItemGradeDetailService.insert(stuItemGradeDetail);
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

}
