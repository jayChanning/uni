package edu.uni.gradeManagement1.controller;

import edu.uni.auth.bean.User;
import edu.uni.auth.service.AuthService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.gradeManagement1.bean.CourseItemDetail;
import edu.uni.gradeManagement1.service.CourseItemDetailService;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author 蔡政堂
 * create 2019/5/5
 * modified 2019/6/20
 * description TODO 课程组成项明细模块Controller
 */

@Api(description = "蔡政堂: 成绩管理：课程组成项明细模块")
@Controller
@RequestMapping("json/gradeManagement1")
public class CourseItemDetailController {
    @Autowired
    private CourseItemDetailService courseItemDetailService;
    @Autowired
    private RedisCache cache;
    @Autowired
    private AuthService authService;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        //gm_courseItemDetail_{成绩主表id}
        private static final String Receive_CacheNamePrefix = "gm_courseItemDetail_";
        // gm_courseItemDetail_list_{页码}
        private static final String List_CacheNamePrefix = "gm_courseItemDetail_list_";
        // gm_courseItemDetail_listByCid_{类别id}_{页码}
        private static final String ListByCid_CacheNamePrefix = "gm_courseItemDetail_listByCid_";
    }

    /**
     * 新增课程组成项明细记录
     * @param courseItemDetail
     * @return
     */
    @ApiOperation(value = "新增课程组成项明细表记录",notes = "已测试！")
    @ApiImplicitParam(name = "courseItemDetail",value = "课程组成项明细表实体类",required = true,dataType = "CourseItemDetail")
    @PostMapping("/courseItemDetail")
    @ResponseBody
    public Result create(@RequestBody(required = false) CourseItemDetail courseItemDetail){
        User user = authService.getUser();
        if (user == null) {
            System.out.println("您没有登录！没有权限录入组成项明细！");
            ResultType.Failed.setMSG("您没有登录！没有权限录入组成项明细！");
            return Result.build(ResultType.Failed);
        } else {
            if (courseItemDetail != null){
                boolean success = courseItemDetailService.insert(courseItemDetail);
                if (success){
                    cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                    cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix + "*");
                    return Result.build(ResultType.Success);
                } else{
                    return Result.build(ResultType.Failed);
                }
            }
            return Result.build(ResultType.ParamError);
        }

    }

    /**
     * <p>
     *     获取树状数组
     * </p>
     * @param id 成绩主表Id
     * @return 成绩主表Id
     */
    @ApiOperation(value = "获取树状数组", notes = "已实现")
    @GetMapping("/courseItemDetail/itemName")
    @ResponseBody
    public Result getInfo(@ApiParam(value = "成绩主表Id")
                          @RequestParam(name = "id") long id) {

        //System.out.println(id);
        //System.out.println(courseItemDetailService.selectTree(id));

        return Result.build(ResultType.Success).appendData("data", courseItemDetailService.selectTree(id));

    }

    /**
     * 根据页码分页查询所有的组成项明细记录
     * @param pageNum
     * @param response
     * @throws IOException
     */
    /*@ApiOperation(value = "根据页码分页查询所有的组成项明细记录",notes = "测试中，传入页码可查询")
    @ApiImplicitParam(name = "pageNum",value = "页码",required = true,dataType = "Integer",paramType = "path")
    @GetMapping(value = "/courseItemDetail/list/{pageNum}")
    public void list(@PathVariable Integer pageNum,
                     HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix+pageNum;
        String json = cache.get(cacheName);
        if (json == null) {
            PageInfo<CourseItemDetail> pageInfo = courseItemDetailService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if (pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }*/

    /**
     * 根据组成项id和页码来分页查询组成项明细
     * @param courseItemId 组成项id
     * @param pageNum 页码
     * @param response
     * @throws IOException
     */
    /*@ApiOperation(value = "根据组成项id和页码来分页查询组成项明细",notes = "测试中")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseItemId",value = "组成项id",required = true,dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "pageNum",value = "页码",required = true,dataType = "Integer",paramType = "path")
    })
    @GetMapping(value = "/courseItemDetail/listByCourseItemId/{courseItemId}/{pageNum}")
    public void listByCourseItemId(@PathVariable Long courseItemId,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByCid_CacheNamePrefix+courseItemId+"_"+pageNum;
        String json = cache.get(cacheName);
        if (json == null) {
            PageInfo<CourseItemDetail> pageInfo = courseItemDetailService.selectPageByCourseItem(pageNum, courseItemId);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if (pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }*/

}
