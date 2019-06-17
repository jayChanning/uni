package edu.uni.educateAffair.service.impl;

import edu.uni.educateAffair.bean.Teachingtask;
import edu.uni.educateAffair.bean.TeachingtaskExample;
import edu.uni.educateAffair.mapper.TeachingtaskMapper;
import edu.uni.educateAffair.service.TeachingtaskService;
import edu.uni.professionalcourses.bean.Course;
import edu.uni.professionalcourses.bean.CourseExample;
import edu.uni.professionalcourses.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:梁俊杰
 * @Description:
 * @Date:Created in 16:02 2019/5/14
 */
@Service
public class TeachingtaskServiceImpl implements TeachingtaskService {

    @Autowired
    private TeachingtaskMapper teachingtaskMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> selectCourseBySidCidEid(Long sid, Long cid, Long eid) {
        TeachingtaskExample example = new TeachingtaskExample();
        TeachingtaskExample.Criteria criteria = example.createCriteria();
        criteria.andSemesterIdEqualTo(sid);
        criteria.andClassIdEqualTo(cid);

        Long uid = teachingtaskMapper.selectUserIdByEmployeeId(eid);
        criteria.andWorkerIdEqualTo(uid);
        List<Teachingtask> teachingtaskList = teachingtaskMapper.selectByExample(example);
        List<Course> courseList = new ArrayList<>();
        for(Teachingtask tea : teachingtaskList){
            List<Course> course = new ArrayList<>();
            CourseExample example1 = new CourseExample();
            CourseExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andIdEqualTo(tea.getCourseId());
            course = courseMapper.selectByExample(example1);
            courseList.add(course.get(0));
        }
        return courseList ;
    }
    // todo 学期Id集合，教师id集合，课程id集合， 班级id集合
    // todo 返回eA_teaching_task实体集合，校历id换取学期id

}
