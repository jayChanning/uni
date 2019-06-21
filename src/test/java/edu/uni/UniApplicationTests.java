package edu.uni;

import edu.uni.educateAffair.service.SemesterService;
import edu.uni.gradeManagement1.bean.CourseItem;
import edu.uni.gradeManagement1.bean.StuItemGradeDetail;
import edu.uni.gradeManagement1.config.GradeManagementConfig;
import edu.uni.gradeManagement1.mapper.CourseItemDetailMapper;
import edu.uni.gradeManagement1.mapper.CourseItemMapper;
import edu.uni.gradeManagement1.mapper.StuItemGradeDetailMapper;
import edu.uni.gradeManagement1.pojo.ExcelDeal;
import edu.uni.gradeManagement1.service.DaoDiService;
import edu.uni.gradeManagement1.service.StuItemGradeDetailService;
import edu.uni.gradeManagement1.utils.ExcelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniApplicationTests {

    @Autowired
    GradeManagementConfig gradeManagementConfig;
    @Resource
    DaoDiService daoDiService;
    @Autowired
    StuItemGradeDetailService itemGradeDetailService;
    @Autowired
    StuItemGradeDetailMapper stuItemGradeDetailMapper;
    @Autowired
    StuItemGradeDetailService stuItemGradeDetailService;

    @Test
    public void ddd() {
        //service
        System.out.println(stuItemGradeDetailMapper.getItemGradeANDItemDetailID(116, 1, 1));
        System.out.println(stuItemGradeDetailService.findItemGradeANDItemDetailId((long) 116, 1, 1));

    }

    @Test
    public void demo_1() {
        ArrayList<Long> list = new ArrayList<>();
        list.add((long) 384);
        list.add((long) 44);
        list.add((long) 1);
        list.add((long) 5);
        list.add((long) 1);
        System.out.println("list: " + list);
        System.out.println(list.get(0) + "/" + list.get(1));
//        List<Long> list2 = itemGradeDetailService.findItemGradeANDItemDetailId((long) 116, 1, 1);
//        System.out.println("list2: " + list2);
//        System.out.println("list2: " + list2.get(0) + "/toString():" + list2.get(0).toString());
        list.clear();
        List<HashMap> maps = itemGradeDetailService.findItemGradeANDItemDetailId((long) 116, 1, 1);
//        maps.listIterator().next();

        System.out.println("maps: " + maps.size() + "//" + maps);
        System.out.println("itemDetailId = " + maps.get(0).get("itemDetailId"));
        System.out.println("itemGradeId = " + maps.get(0).get("itemGradeId"));

    }

    /**
     * test date: 2019/6/19
     * description TODO 这里测试excel解析类，已测试！可用！！
     * @throws Exception
     */
    @Test
    public void excelAnalyse() throws Exception{
        //这个路径按实际需求，路径分隔符需要为双发斜杠
//        String filePath = "D:\\JayChoi\\codes\\Idea\\uni\\src\\main\\resources\\excelTest\\exceldemo.xlsx";
        String filePath = "D:\\JayChoi\\codes\\Idea\\uni\\src\\main\\resources\\excelTest\\excelInsert1_1.xlsx";
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

