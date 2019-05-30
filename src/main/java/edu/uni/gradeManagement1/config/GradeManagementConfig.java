package edu.uni.gradeManagement1.config;

import edu.uni.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
* @author
* @description 成绩管理配置类
* @date 13:33 2019-05-04
* @modified 13:33 2019-05-10
*/

@Component
@PropertySource("classpath:config/gm.properties")
//@ConfigurationProperties(prefix="uni.gm")
public class GradeManagementConfig {

    // 引入总配置类
    @Resource
    private GlobalConfig globalConfig;

    @Value("${uni.gm.gmAttachmentDir}")
    private String gmAttachmentDir;

    @Value("${uni.gm.pageSize}")
    private Integer pageSize;

    public GradeManagementConfig() {
    }

    //2019.5.29 21:31 on windows 10
    //please delete this commit on another device!

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public String getAbsoluteGmAttachmentDir() {
        return globalConfig.getUploadRootDir() + gmAttachmentDir;
    }

    public String getGmAttachmentDir() {
        return gmAttachmentDir;
    }

    public void setGmAttachmentDir(String gmAttachmentDir) {
        this.gmAttachmentDir = gmAttachmentDir;
    }
}
