package edu.uni;

import edu.uni.educateAffair.service.SemesterService;
import edu.uni.gradeManagement1.bean.CourseItem;
import edu.uni.gradeManagement1.bean.StuItemGradeDetail;
import edu.uni.gradeManagement1.config.GradeManagementConfig;
import edu.uni.gradeManagement1.mapper.CourseItemDetailMapper;
import edu.uni.gradeManagement1.mapper.CourseItemMapper;
import edu.uni.gradeManagement1.pojo.ExcelDeal;
import edu.uni.gradeManagement1.pojo.ExcelDemoClass;
import edu.uni.gradeManagement1.service.DaoDiService;
import edu.uni.gradeManagement1.utils.ExcelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniApplicationTests {

    @Autowired
    GradeManagementConfig gradeManagementConfig;
    @Autowired
    private SemesterService semesterService;

    @Resource
    public CourseItemMapper courseItemMapper;

    @Resource
    DaoDiService daoDiService;
    @Resource
    public CourseItemDetailMapper courseItemDetailMapper;
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

//            courseItemMapper.insertSelective(courseItem);
            System.out.println("insert：course ID:"+courseItem.getId());

        }


    }

    /**
     * test date: 2019/6/5
     * description TODO 这里测试excel解析类，仍在测试，咱不可用
     * @throws Exception
     */
    @Test
    public void excelAnalyse() throws Exception{
        //这个路径按实际需求，路径分隔符需要为双发斜杠
//        String filePath = "D:\\JayChoi\\codes\\Idea\\uni\\src\\main\\resources\\excelTest\\exceldemo.xlsx";
        String filePath = "D:\\JayChoi\\codes\\Idea\\uni\\src\\main\\resources\\excelTest\\excelInsert1.xlsx";
        //MultipartFile multiplePartfile = ;

        //***以下代码可以复制黏贴，把filepath，ExcelDemoClass改为自定义的*******************************************************
        List<Object> list = ExcelUtil.excelReadService(filePath,ExcelDeal.class);
//        List<Object> list = ExcelUtil.excelReadService(multiplePartfile, ExcelDeal.class);
        for (int i = 0; i < list.size(); i++){
            ExcelDeal excelDeal = (ExcelDeal)list.get(i);
            //---- do what you want to do
            StuItemGradeDetail stuItemGradeDetail = new StuItemGradeDetail();
            stuItemGradeDetail.setScore(((ExcelDeal) list.get(i)).getScore());
            System.out.println(excelDeal.toString());
            System.out.println(((ExcelDeal) list.get(i)).getStuName()+"的成绩是："+stuItemGradeDetail.getScore());
        }
        //*********结束***************************************************
    }

    @Test
    public void fileUploads(){
        List<HashMap> dao =daoDiService.getClass(22,40,25);
        System.out.println(dao);
       // System.out.println(courseItemDetailMapper.selectByClassId(22,40,25));
        //Long testId = semesterService.getThisTimeSemesterId();
       // System.out.println("This is Tester: getThisTimeSemesterId()=" + testId);

        /*Date date  = new Date();
        String filename = date.getTime() + CommonUtils.generateUUID() + ".txt";
        String filePath = gradeManagementConfig.getAbsoluteGmAttachmentDir() + filename;

        System.out.println(filePath);
        File file = new File(filePath);
        try(
                FileOutputStream fos = new FileOutputStream(filePath)
        ){
            fos.write(123456);
        }catch (Exception e) {
            e.printStackTrace();*/
    }

}

