package edu.uni.gradeManagement1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.auth.service.AuthService;
import edu.uni.educateAffair.service.SemesterService;
import edu.uni.gradeManagement1.bean.StuItemGradeVO;
import edu.uni.gradeManagement1.config.GradeManagementConfig;
import edu.uni.gradeManagement1.mapper.StuItemGradeVOmapper;
import edu.uni.gradeManagement1.service.StuItemGradeVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 林晓锋
 * @date 2019/5/18 11:14
 * modified: 2019/6/1
 * 功能;根据条件查询学生成绩
 */
@Service
public class StuItemGradeVOServiceImpl implements StuItemGradeVOService {


    @Autowired
    StuItemGradeVOmapper stuItemGradeVOmapper;
    @Autowired
    private GradeManagementConfig globalConfig;

    @Autowired
    AuthService authService;

    @Autowired
    SemesterService semesterService;

    /**
     * 默认查询全部学生成绩
     * @param
     * @return
     */
    @Override
    public List<StuItemGradeVO> selectStuGradeMainAll() {

        List<StuItemGradeVO> stuItemGradeVOlist  = stuItemGradeVOmapper.selectAll();

        return stuItemGradeVOlist;
    }

    /**
     * 默认查询全部学生成绩并执行分页操作
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<StuItemGradeVO> GetselectStuGradeMainAll(int pageNum ) {

        PageHelper.startPage(pageNum, globalConfig.getPageSize());

        List<StuItemGradeVO> stuItemGradeVOlist = selectStuGradeMainAll();

        if( stuItemGradeVOlist != null )
            return new PageInfo<>(stuItemGradeVOlist);
        else
            return null;

    }



    /**
     * 多条件查询学生成绩
     * @param
     * @return
     */
    @Override
    public List<StuItemGradeVO> selectByexample(String semesterStart, String semesterEnd, String courseName,
                                                String speciesName, String categoryName, String studentClass,
                                                String departmentName, String studentGrade) {

        Long semesterStartId=0L;
        Long semesterEndId =0L;
        List<Long> semesterList = null;
        if( semesterStart != null && semesterStart !=  "" &&semesterEnd != null && semesterEnd != "" )
        {
            semesterStartId = selectBysemesterName(semesterStart);
            semesterEndId = selectBysemesterName(semesterEnd);

            //开始学期与结束学期id集合
            if( semesterStartId <= semesterEndId )
            {
                semesterList = semesterService.selectByfromtoEnd(semesterStartId,semesterEndId);
            }
            else
            {
                return new ArrayList<>();
            }
        }

        System.out.println("semesterStartId=" + semesterStartId+"semesterEndId="+semesterEndId);

        List<StuItemGradeVO> stuItemGradeVOlist = selectStuGradeMainAll();

        //按学期名称查询
        List<StuItemGradeVO> semesterNamelist = new ArrayList<>();

        if( semesterList != null && semesterList.size() != 0 )
        {
            for( Long s :semesterList)
            {
                if (s!= null) {
                    for (int i = 0; i < stuItemGradeVOlist.size(); i++) {
                        if (s.equals(stuItemGradeVOlist.get(i).getSemesterId())) {

                            semesterNamelist.add(stuItemGradeVOlist.get(i));
                        }
                    }
                }
            }
        }
        else {
            semesterNamelist.addAll(stuItemGradeVOlist);
        }

        //按照课程名称模糊查询
        List<StuItemGradeVO> courseNamelist = new ArrayList<>();

        if (courseName != null && courseName != "" )
        {
            Pattern pattern = Pattern.compile(courseName);

            for (int i = 0; i < semesterNamelist.size(); i++)
            {
                Matcher matcher = pattern.matcher(semesterNamelist.get(i).getCourseName());
                if (matcher.find()) {

                    courseNamelist.add(semesterNamelist.get(i));
                }
            }
        } else {
            courseNamelist.addAll(semesterNamelist);
        }

        //按照课程种类查询
        List<StuItemGradeVO> speciesNamelist = new ArrayList<>();

        if (speciesName != null && speciesName != "" )
        {
            Pattern pattern = Pattern.compile(speciesName);
            for (int i = 0; i < courseNamelist.size(); i++) {
                Matcher matcher = pattern.matcher(courseNamelist.get(i).getSpeciesName());
                if (matcher.find()) {

                    speciesNamelist.add(courseNamelist.get(i));
                }
            }
        } else {
            speciesNamelist.addAll(courseNamelist);
        }

        //按照课程类别查询
        List<StuItemGradeVO> categoryNamelist = new ArrayList<>();

        if (categoryName != null && categoryName != "" )
        {
            Pattern pattern =Pattern.compile(categoryName);
            for (int i = 0; i < speciesNamelist.size(); i++)
            {
                Matcher matcher = pattern.matcher(speciesNamelist.get(i).getCategoryName());
                if (matcher.find()) {

                    categoryNamelist.add(speciesNamelist.get(i));
                }
            }
        } else {
            categoryNamelist.addAll(speciesNamelist);
        }

        //按照班级查询
        List<StuItemGradeVO> studentClasslist = new ArrayList<>();

        if (studentClass != null && studentClass != "" )
        {
            Pattern pattern = Pattern.compile(studentClass);
            for (int i = 0; i < categoryNamelist.size(); i++)
            {
                Matcher matcher = pattern.matcher(categoryNamelist.get(i).getStudentClass());
                if (matcher.find()) {

                    studentClasslist.add(categoryNamelist.get(i));
                }
            }
        } else {
            studentClasslist.addAll(categoryNamelist);
        }

        //按照年级查询
        List<StuItemGradeVO> studentGradelist = new ArrayList<>();

        if (studentGrade != null && studentGrade != "" )
        {
            Pattern pattern = Pattern.compile(studentGrade);
            for (int i = 0; i < studentClasslist.size(); i++)
            {
                Matcher matcher = pattern.matcher(studentClasslist.get(i).getStudentGrade());
                if (matcher.find()) {

                    studentGradelist.add(studentClasslist.get(i));
                }
            }
        } else {
            studentGradelist.addAll(studentClasslist);
        }


        //按照学院名称查询
        List<StuItemGradeVO> departmentNamelist = new ArrayList<>();

        if (departmentName != null && departmentName != "" )
        {
            Pattern pattern = Pattern.compile(departmentName);
            for (int i = 0; i < studentGradelist.size(); i++)
            {
                Matcher matcher = pattern.matcher(studentGradelist.get(i).getDepartmentName());
                if (matcher.find()) {

                    departmentNamelist.add(studentGradelist.get(i));
                }
            }
        } else {

            departmentNamelist.addAll(studentGradelist);
        }

        return departmentNamelist;
    }

    /**
     * 根据教师id以及当前学期查询学生成绩
     * @param
     * @return
     */
    @Override
    public List<StuItemGradeVO> selectByTeacher(){

        Long userId=  authService.getUser().getId();//1941L;
        //根据时间获取当前学期id
        Long semesterId = semesterService.getThisTimeSemesterId();
        //System.out.println("semesterId="+semesterId);
        List<StuItemGradeVO> stuItemGradeVOlist = selectStuGradeMainAll();
        //System.out.println("stuItemGradeVOlist.size() =" + stuItemGradeVOlist.size());

        //根据教师id以及当前学期查询
        List<StuItemGradeVO> semesterIdlist = new ArrayList<>();

        if ( semesterId!= null && userId!= null ) {

            for (int i = 0; i < stuItemGradeVOlist.size(); i++) {
                if (semesterId.equals(stuItemGradeVOlist.get(i).getSemesterId())
                    &&userId.equals(stuItemGradeVOlist.get(i).getByWho())) {

                    semesterIdlist.add(stuItemGradeVOlist.get(i));
                }
            }

        }

        return semesterIdlist;
    }

    /**
     * 根据学期名称获取学期id
     * @param semesterName
     * @return
     */
    @Override
    public Long selectBysemesterName(String semesterName) {

        return stuItemGradeVOmapper.selectBysemesterName(semesterName);
    }

    /**
     * 根据学期id获取学期名称
     * @param semesterId
     * @return
     */
    @Override
    public String selectBysemesterId( Long semesterId ){

        return stuItemGradeVOmapper.selectBysemesterId( semesterId );
    }


    //获取当前学生的学期名称集合
    List<String> semesterNamelist = new ArrayList<>();
    //特定学期的特定课程名称集合
    List<String> courseNamelist = new ArrayList<>();
    /**
     * 查询当前学生的全部课程成绩
     * @return
     */
    @Override
    public List<StuItemGradeVO> selectcurrentStudentAll(){

        //userId
        Long userId=  authService.getUser().getId();
        List<StuItemGradeVO> currentStudentlist = stuItemGradeVOmapper.selectcurrentStudentAll(userId);

        //清空学期名称list
        semesterNamelist.clear();
        courseNamelist.clear();
        HashSet<String> hashSet = new HashSet<>();
//        HashMap<String,Object> hashMap = new HashMap<>();
        for ( StuItemGradeVO s:currentStudentlist )
        {
            if(hashSet.add(s.getSemesterName()))
            {
//                hashMap.put("semesterName",s.getSemesterName());
                semesterNamelist.add(s.getSemesterName());

            }

        }

        return currentStudentlist;
    }

    /**
     * 返回当前学生的全部学期名称
     * @return
     */
    @Override
    public List<String> getsemesterNameAll() {

        return semesterNamelist;
    }

    /**
     * 返回特定学期的特定课程名称集合
     * @param semesterName
     * @return
     */
    @Override
    public  List<String> getcourseNameAll( String semesterName ) {

        if( semesterName != null && semesterName != "" )
        {
            List<StuItemGradeVO> currentStudentlist = selectcurrentStudentAll();

            courseNamelist.clear();
            for( StuItemGradeVO s:currentStudentlist )
            {
                if( semesterName.equals(s.getSemesterName()) )
                {
                    courseNamelist.add(s.getCourseName());
                }

            }
        }
        else
        {
            List<StuItemGradeVO> currentStudentlist = selectcurrentStudentAll();

            courseNamelist.clear();

            HashSet<String> hashSet1 =  new HashSet<>();
            for( StuItemGradeVO s:currentStudentlist )
            {
                if( hashSet1.add(s.getCourseName()) )
                {
                    courseNamelist.add(s.getCourseName());
                }

            }
        }

        return courseNamelist;
    }





    /**
     * 根据学期名称和课程名称查询学期课程成绩
     * @param semesterName
     * @return
     */
    @Override
    public   HashMap selectcurrentStudent(String semesterName,String courseName) {

        //默认查询该学生的全部课程成绩
        List<StuItemGradeVO> currentStudentlist = selectcurrentStudentAll();

        //根据学期名称查询学期课程成绩
        List<StuItemGradeVO> currentSemesterNamelist = new ArrayList<>();

        int total = 0;
        int pageSize = 12;
        if( semesterName != null && semesterName != "" )
        {
            //学期名称模糊查询
            Pattern pattern = Pattern.compile(semesterName);
            for( StuItemGradeVO s:currentStudentlist )
            {
                Matcher matcher = pattern.matcher(s.getSemesterName());
                if(matcher.find())
                {
                    currentSemesterNamelist.add(s);
                }
            }

        }
        else
        {
            currentSemesterNamelist.addAll(currentStudentlist);
        }


        List<StuItemGradeVO> currentcourseNamelist = new ArrayList<>();

        if( courseName != null && courseName != "" )
        {
            //课程名称模糊查询
            Pattern pattern = Pattern.compile(courseName);
            for( StuItemGradeVO s:currentSemesterNamelist )
            {
                Matcher matcher = pattern.matcher(s.getCourseName());
                if(matcher.find())
                {
                    currentcourseNamelist.add(s);
                }
            }

              //少鑫使用正则表达式进行查询的方法
//            System.out.println(courseName);
//            System.out.println("123456".matches(".*4.*"));
//
//            for( int i =0;i<currentSemesterNamelist.size();i++ )
//            {
//                if (currentSemesterNamelist.get(i).getCourseName().matches(".*"+courseName+".*"))
//                {
//                    currentcourseNamelist.add(currentSemesterNamelist.get(i));
//                }
//
//            }
        }
        else
        {
            currentcourseNamelist.addAll(currentSemesterNamelist);
        }

        total =  currentcourseNamelist.size();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("total",total);
        hashMap.put("pageSize",pageSize);
        if(currentStudentlist.size() != 0)
        {
            hashMap.put("stuNo",currentStudentlist.get(0).getStuNo());
            hashMap.put("studentName",currentStudentlist.get(0).getStudentName());
            hashMap.put("specialtyName",currentStudentlist.get(0).getSpecialtyName());
            hashMap.put("departmentName",currentStudentlist.get(0).getDepartmentName());
            hashMap.put("studentClass",currentStudentlist.get(0).getStudentClass());
        }
        else
        {
            hashMap.put("stuNo","");
            hashMap.put("studentName","");
            hashMap.put("specialtyName","");
            hashMap.put("departmentName","");
            hashMap.put("studentClass","");
        }

        //根据时间获取当前学期id
        Long semesterId = semesterService.getThisTimeSemesterId();
        //根据学期id获取学期名称
        String semesterName1 = selectBysemesterId(semesterId);
        hashMap.put("semesterName",semesterName1);
        hashMap.put("data",currentcourseNamelist);

        return hashMap;
    }

    /**
     * 根据课程编号查询课程信息
     * @param courseNumber
     * @return
     */
    @Override
    public HashMap selectBycourseNumber( Long courseNumber ) {

        return stuItemGradeVOmapper.selectBycourseNumber(courseNumber);
    }

    /**
     * 根据班级编号查询班级信息
     * @param classCode
     * @return
     */
    @Override
    public HashMap selectByclassCode( Long classCode) {

        return  stuItemGradeVOmapper.selectByclassCode(classCode);
    }

    /**
     * 根据学生id查询学生信息
     * @param studentId
     * @return
     */
    @Override
    public HashMap selectBystudentinform( Long studentId){

        return stuItemGradeVOmapper.selectBystudentinform(studentId);
    }
}
