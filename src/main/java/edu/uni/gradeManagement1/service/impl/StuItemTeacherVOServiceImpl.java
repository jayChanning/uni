package edu.uni.gradeManagement1.service.impl;

import edu.uni.educateAffair.service.SemesterService;
import edu.uni.gradeManagement1.bean.StuItemGradeVO;
import edu.uni.gradeManagement1.bean.StuItemTeacherVO;
import edu.uni.gradeManagement1.service.StuItemGradeVOService;
import edu.uni.gradeManagement1.service.StuItemTeacherVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/5/29
 * modified: 2019/5/29
 * 功能：将学生记录封装成班级记录
 */

@Service
public class StuItemTeacherVOServiceImpl implements StuItemTeacherVOService {

    @Autowired
    StuItemGradeVOService stuItemGradeVOService;

    @Autowired
    SemesterService semesterService;
    /**
     * 将高级筛选的学生记录封装成班级记录
     * @param semesterStart
     * @param semesterEnd
     * @param courseName
     * @param speciesName
     * @param categoryName
     * @param studentClass
     * @param departmentName
     * @param studentGrade
     * @return HashMap
     */
    public HashMap selectTeacherVO(String semesterStart,String semesterEnd,String courseName,
                                   String speciesName,String categoryName, String studentClass,
                                   String departmentName,String studentGrade){


        //根据筛选条件查询学生成绩记录
        List<StuItemGradeVO> stuItemGradeVOList= stuItemGradeVOService.selectByexample(semesterStart,semesterEnd,courseName, speciesName,
                                             categoryName, studentClass,departmentName,studentGrade);

        HashSet<String> hashSet = new HashSet<>();
        HashMap<String, StuItemTeacherVO> hashMap = new HashMap<>();

        //将学生记录封装成班级记录
        long id = 0L;
        for( StuItemGradeVO s :stuItemGradeVOList )
        {
            //根据班级编号和课程编号筛选该班级课程已经存在
            if( !hashSet.add(s.getCourseNumber() + s.getStudentCode()))
            {
                StuItemTeacherVO h = hashMap.get(s.getCourseNumber() + s.getStudentCode());
                h.setAllNumber(h.getAllNumber()+1);//班级人数
                h.setCourseName(s.getCourseName());//课程名称
                h.setStudentClass(s.getStudentClass());//班级名称
                if(s.getScore()>=60)
                {
                    h.setAdoptNumber(h.getAdoptNumber()+1);//及格人数
                }
                else
                {
                    h.setFailNumber(h.getFailNumber()+1);//不及格人数
                }
                h.getStuItemGradeVOList().add(s);//班级明细
            }
            else
            {
                hashMap.put(s.getCourseNumber() + s.getStudentCode(),new StuItemTeacherVO(s.getCourseNumber(), s.getStudentCode()));
                StuItemTeacherVO h = hashMap.get(s.getCourseNumber() + s.getStudentCode());

                id++;
                h.setId(id);//主表id
                h.setAllNumber(h.getAllNumber()+1);//班级人数
                h.setCourseName(s.getCourseName());//课程名称
                h.setStudentClass(s.getStudentClass());//班级名称
               if(s.getScore()>=60)
                {
                   h.setAdoptNumber(h.getAdoptNumber()+1);//及格人数
               }
                else
               {
                    h.setFailNumber(h.getFailNumber()+1);//不及格人数
               }
                h.getStuItemGradeVOList().add(s);//班级明细
            }

        }

        List<StuItemTeacherVO>  stuItemTeacherList = new ArrayList<>();
        int total = 0;//记录总数
        int pageSize = 12;
        HashMap<String,Object> hashMap1 = new HashMap<>();
        for( StuItemTeacherVO b:hashMap.values())
        {
            stuItemTeacherList.add(b);
            total++;

        }

        hashMap1.put("data",stuItemTeacherList);
        hashMap1.put("total",total);
        hashMap1.put("pageSize",pageSize);
        String semesterName = null;
        if( semesterStart!= null && semesterStart!="" && semesterEnd!=null &&semesterEnd!="" )
        {
            if( !semesterStart.equals(semesterEnd) )
            {
                semesterName = semesterStart + '至' + semesterEnd;
            }
            else
            {
                semesterName = semesterStart;
            }
        }
        else
        {
            //根据时间获取当前学期id
            Long semesterId = semesterService.getThisTimeSemesterId();
            //根据学期id获取学期名称
             semesterName = stuItemGradeVOService.selectBysemesterId(semesterId);
        }
        hashMap1.put("semesterName",semesterName);
        return hashMap1;
    }

    /**
     * 将当前教师以及当前学期的学生记录封装成班级记录
     * @param
     * @return
     */
    @Override
    public HashMap selectCurrentTeacherVO(){
        //根据筛选条件查询学生成绩记录
        List<StuItemGradeVO> stuItemGradeVOList= stuItemGradeVOService.selectByTeacher();

        HashSet<String> hashSet = new HashSet<>();
        HashMap<String, StuItemTeacherVO> hashMap = new HashMap<>();

        //将学生记录封装成班级记录
        long id = 0L;
        for( StuItemGradeVO s :stuItemGradeVOList )
        {
            //根据班级编号和课程编号筛选该班级课程已经存在
            if( !hashSet.add(s.getCourseNumber() + s.getStudentCode()))
            {
                StuItemTeacherVO h = hashMap.get(s.getCourseNumber() + s.getStudentCode());
                h.setAllNumber(h.getAllNumber()+1);//班级人数
                h.setCourseName(s.getCourseName());//课程名称
                h.setStudentClass(s.getStudentClass());//班级名称
                if(s.getScore()>=60)
                {
                    h.setAdoptNumber(h.getAdoptNumber()+1);//及格人数
                }
                else
                {
                    h.setFailNumber(h.getFailNumber()+1);//不及格人数
                }
                h.getStuItemGradeVOList().add(s);//班级明细
            }
            else
            {
                hashMap.put(s.getCourseNumber() + s.getStudentCode(),new StuItemTeacherVO(s.getCourseNumber(), s.getStudentCode()));
                StuItemTeacherVO h = hashMap.get(s.getCourseNumber() + s.getStudentCode());

                id++;
                h.setId(id);//主表id
                h.setAllNumber(h.getAllNumber()+1);//班级人数
                h.setCourseName(s.getCourseName());//课程名称
                h.setStudentClass(s.getStudentClass());//班级名称
                if(s.getScore()>=60)
                {
                    h.setAdoptNumber(h.getAdoptNumber()+1);//及格人数
                }
                else
                {
                    h.setFailNumber(h.getFailNumber()+1);//不及格人数
                }
                h.getStuItemGradeVOList().add(s);//班级明细
            }

        }

        List<StuItemTeacherVO>  stuItemTeacherList = new ArrayList<>();
        int total = 0;//记录总数
        int pageSize = 12;
        HashMap<String,Object> hashMap1 = new HashMap<>();
        for( StuItemTeacherVO b:hashMap.values())
        {
            stuItemTeacherList.add(b);
            total++;

        }

        hashMap1.put("data",stuItemTeacherList);
        hashMap1.put("total",total);
        hashMap1.put("pageSize",pageSize);
        //根据时间获取当前学期id
        Long semesterId = semesterService.getThisTimeSemesterId();
        //根据学期id获取学期名称
        String semesterName = stuItemGradeVOService.selectBysemesterId(semesterId);
        hashMap1.put("semesterName",semesterName);

        return hashMap1;
    }
}
