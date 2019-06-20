package edu.uni.gradeManagement1.utils;

import edu.uni.gradeManagement1.config.GradeManagementConfig;
import edu.uni.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;


/**
* @author 陈少鑫
* @description 成绩管理子系统文件上传工具类,在service层或controller层使用@Autowired,@Resource注入
* @date 2019.4.26
* @modified 23:57 2019-05-11
*/
//关键点一 申明为spring组件
@Component
public class UploadUtil {

    //为什么注入为空呢
    @Autowired
    private GradeManagementConfig gradeManagementConfig;
    private static UploadUtil uploadUtil;  //关键点二 静态初使化 一个工具类  这样是为了在spring初使化之前

    public void setGradeManagementConfig(GradeManagementConfig gradeManagementConfig) {
        this.gradeManagementConfig = gradeManagementConfig;
    }
    //关键点三  通过@PostConstruct 和 @PreDestroy 方法 实现初始化和销毁bean之前进行的操作
    @PostConstruct
    public void init() {
        uploadUtil = this;
        uploadUtil.gradeManagementConfig = this.gradeManagementConfig;
    }

    /**
     * 判断此文件扩展名是否为指定扩展名
     * @param file 文件
     * @param extension 扩展名
     * @return 如果文件的扩展名==extension 返回true，否则返回false
     *
     */
    private static boolean IsFileExtension(MultipartFile file, String extension) {

        return file.getOriginalFilename().endsWith(extension);

    }

    /**
     * 上传文件
     * @param file MultipartFile 为空抛出exception
     * @return 上传成功返回filepath,否则抛出异常
     * @throws Exception
     */

    public String uploadFile(MultipartFile file) throws Exception {

      //  System.out.println(uploadUtil.gradeManagementConfig == null);
      //  System.out.println(uploadUtil.gradeManagementConfig.getAbsoluteGmAttachmentDir());
        if (file == null) throw new Exception("文件不能为空");
        //return null;
       // if (!IsFileExtension(file,"zip")) throw new Exception("只能上传zip文件");

        String filename = CommonUtils.generateUUID() + file.getOriginalFilename();
       // System.out.println(filename);
        String filePath = uploadUtil.gradeManagementConfig.getAbsoluteGmAttachmentDir() + "//" + filename;
      //  System.out.println(filePath);
        file.transferTo(new File(filePath));
        //方便前台地址栏，直接转义存到数据库
        return filePath.replace(":", "%3A").replace("/", "%2F");
    }
/*    public String uploadFile(MultipartFile file) throws Exception {
        if (file == null) throw new Exception("文件不能为空");
        String filename = CommonUtils.generateUUID() + file.getOriginalFilename();
        String filePath = gradeManagementConfig.getAbsoluteGmAttachmentDir();
        file.transferTo(new File(filePath + filename));
       // System.out.println(filePath + filename);
        return gradeManagementConfig.getGmAttachmentDir()+filename;
    }*/

/*    public String uploadFile(MultipartFile file) throws Exception {
        if (file == null) throw new Exception("文件不能为空");
        String filename = CommonUtils.generateUUID() + file.getOriginalFilename();
        String filePath = ResourceUtils.getURL("classpath:").getPath()
                + File.separator + "static"
                + File.separator + "GMattachment" + filename;
        file.transferTo(new File(filePath));
        System.out.println(filePath);
        return filePath;
    }*/



    /**
     * 解析zip的每个文件并保存，未实现
     * @return ArrayList zip里每个文件的保存路径集合,按zip文件顺序
     */
    public static ArrayList parseZipAndUpload(){
        return null;
    }

}
