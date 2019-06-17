package edu.uni.gradeManagement1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.educateAffair.service.SemesterService;
import edu.uni.gradeManagement1.bean.StuItemGrade;
import edu.uni.gradeManagement1.service.StuItemGradeService;
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
 * create 2019/4/29
 * modified 2019/4/29
 * description TODO
 */

@Api(description = "成绩管理：成绩项主表得分模块")
@Controller
@RequestMapping("json/gradeManagement1")
public class StuItemGradeController {
    @Autowired
    private StuItemGradeService stuItemGradeService;
    @Autowired
    private RedisCache cache;
    @Autowired
    private SemesterService semesterService;

    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
//        gm_c_StuItemGrade_{成绩主表id}
        public static final String Receive_CacheNamePrefix = "gm_c_StuItemGrade_";
//        gm_StuItemGrade_listAll
        public static final String ListAll_CacheName = "gm_StuItemGrade_listAll";
    }

    /**
     * 新增成绩项得分
     * @param stuItemGrade
     * @return
     */
    @ApiOperation(value = "新增成绩项得分", notes = "")
    @ApiImplicitParam(name = "StuItemGrade",value = "成绩得分项实体",required = true,dataType = "StuItemGrade")
    @PostMapping("/StuItemGrade")
    @ResponseBody
    public Result create(@RequestBody(required = false) StuItemGrade stuItemGrade){
        if (stuItemGrade != null){
            boolean success = stuItemGradeService.insert(stuItemGrade);
            if (success){
                //clean up cache
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else {
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }


}
