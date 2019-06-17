package edu.uni.educateAffair.service;

import edu.uni.professionalcourses.bean.Course;

import java.util.List;

/**
 * @Author:梁俊杰
 * @Description:
 * @Date:Created in 16:02 2019/5/14
 */
public interface TeachingtaskService {

    List<Course> selectCourseBySidCidEid(Long sid, Long cid, Long eid);
}
