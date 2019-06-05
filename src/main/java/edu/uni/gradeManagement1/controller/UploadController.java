package edu.uni.gradeManagement1.controller;


import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.gradeManagement1.utils.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* @author 陈少鑫
* @description 成绩管理： 文件上传，适用于所有文件类型
* @date 23:24 2019-06-05
* @modified 23:24 2019-06-05
*/
@Api(description = "成绩管理： 文件上传，适用于所有文件类型")
@Controller
@RequestMapping(value = "/json/gradeManagement1")
public class UploadController {

    @Resource
    UploadUtil uploadUtil;
    @RequestMapping(value = "/upload",  method = {RequestMethod.POST} )
    @ResponseBody
    @ApiOperation(value = "文件上传", notes = "已测试")
    public Result upload(HttpServletRequest request,
                         @ApiParam(value = "附件", required = true)
                         @RequestParam(value = "file") MultipartFile file
                         ) throws Exception {
        return Result.build(ResultType.Success).appendData("data",uploadUtil.uploadFile(file));

    }
}
