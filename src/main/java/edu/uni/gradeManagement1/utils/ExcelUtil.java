package edu.uni.gradeManagement1.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import edu.uni.bean.ResultType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
* @author 陈少鑫
* @description excel解析工具类
* @date 19:13 2019-05-12
* @modified 19:13 2019-05-12
*/

public class ExcelUtil {

    /**
     *  解析excel
     * @param file MultipartFile 这个是上传时spring提供的
     * @param clazz 要解析成的类 如ItemDetailExcel
     * @return
     */
    public static List<Object> excelReadService(MultipartFile file, Class<? extends BaseRowModel> clazz) throws Exception{
        if (file.isEmpty()) throw new Exception(ResultType.ParamError.getMSG());
        if(!(file.getOriginalFilename().endsWith(".xlsx")||file.getOriginalFilename().endsWith(".xls"))) throw new Exception(ResultType.ParamError.getMSG());
        // System.out.println(file.getName()+":"+file.getOriginalFilename()+":"+file.getContentType());
        InputStream in;
        try {
            in = file.getInputStream();
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception(ResultType.Failed.getMSG());
        }
        List<Object> datas = excelReadService(in,clazz);
        try {

            in.close();
        } catch (Exception e){
          //  e.printStackTrace();
            throw new Exception(ResultType.Failed.getMSG());
        }
        return datas;
    }

    /**
     *  解析excel
     * @param filePath 此文件的路径
     * @param clazz 要解析成的类 如ItemDetailExcel
     * @return
     */
    public static List<Object> excelReadService(String filePath, Class<? extends BaseRowModel> clazz) throws Exception {
        if(!(filePath.endsWith(".xlsx")||filePath.endsWith(".xls"))) throw new Exception(ResultType.ParamError.getMSG());
        List<Object> datas = null;
        try(
               InputStream file = new FileInputStream(filePath);
                ){
            datas = excelReadService(file,clazz);
        } catch (Exception e){
            //  e.printStackTrace();
            throw new Exception(ResultType.Failed.getMSG());
        }
        return datas;
    }

    public static List<Object> excelReadService(InputStream file, Class<? extends BaseRowModel> clazz) throws Exception {

        return EasyExcelFactory.read(file, new Sheet(1,2, clazz));  //从第二行开始读取

    }
}
