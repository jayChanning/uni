package edu.uni.gradeManagement1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.gradeManagement1.bean.StuGradeMain;
import edu.uni.gradeManagement1.service.StuGradeMainService;
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
 * mdified 2019/4/29
 * description TODO
 */

@Api(description = "成绩管理：成绩主表模块")
@Controller
@RequestMapping("json/gradeManagement1")
public class StuGradeMainController {
    @Autowired
    private StuGradeMainService stuGradeMainService;
    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        //gm_stuGradeMain_{成绩主表id}
        private static final String Receive_CacheNamePrefix = "gm_stuGradeMain_";
        // gm_stuGradeMain_list_{页码}
        private static final String List_CacheNamePrefix = "gm_stuGradeMain_list_";
        // gm_stuGradeMain_listByCid_{类别id}_{页码}
        private static final String ListByCid_CacheNamePrefix = "gm_stuGradeMain_listByCid_";

    }

    /**
     * 新增成绩主表的记录
     * @param stuGradeMain
     * @return
     */
    @ApiOperation(value = "新增成绩主表的记录", notes = "")
    @ApiImplicitParam(name = "stuGradeMain",value = "成绩主表实体类",required = true,dataType = "StuGradeMain")
    @PostMapping("/stuGradeMain")
    @ResponseBody
    public Result create(@RequestBody(required = false) StuGradeMain stuGradeMain) {
        if (stuGradeMain != null){
            boolean success = stuGradeMainService.insert(stuGradeMain);
            if (success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            } else {
                return Result.build(ResultType.Failed);

            }
        }
        return Result.build(ResultType.ParamError);
    }


}
