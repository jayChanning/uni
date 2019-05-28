package edu.uni;

import edu.uni.gradeManagement1.bean.CourseItem;
import edu.uni.gradeManagement1.config.GradeManagementConfig;
import edu.uni.gradeManagement1.mapper.CourseItemMapper;
import edu.uni.utils.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniApplicationTests {

    @Resource
    GradeManagementConfig gradeManagementConfig;

    @Resource
    public CourseItemMapper courseItemMapper;

    @Test
    public void contextLoads() throws IOException {
        //n年前的成绩
        for (int i = 1; i < 16; i++){
            CourseItem courseItem = new CourseItem();
            courseItem.setRate(0.5);
            courseItem.setCount(i);
            //courseItem.setDatetime(Date.valueOf(LocalDate.now()));
            courseItem.setUniversityId(Long.valueOf(200+i));
            courseItem.setName(Byte.valueOf("2"));
            courseItem.setCourseId(Long.valueOf(20190+i));
            courseItem.setByWho(Long.valueOf("1"));
            courseItem.setDeleted(Byte.valueOf(String.valueOf(i )));

            courseItemMapper.insertSelective(courseItem);
            System.out.println("insert：course ID:"+courseItem.getId());

        }


    }
    @Test
    public void fileUploads(){


        Date date  = new Date();
        String filename = date.getTime() + CommonUtils.generateUUID() + ".txt";
        String filePath = gradeManagementConfig.getAbsoluteGmAttachmentDir() + filename;

        System.out.println(filePath);
        File file = new File(filePath);
        try(
                FileOutputStream fos = new FileOutputStream(filePath)
        ){
            fos.write(123456);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
