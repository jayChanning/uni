package edu.uni.gradeManagement1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.gradeManagement1.config.GradeManagementConfig;
import edu.uni.gradeManagement1.mapper.CourseItemDetailMapper;
import edu.uni.gradeManagement1.service.DaoDiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DaoDiserviceImpl implements DaoDiService {

    @Autowired
    private GradeManagementConfig gradeManagementConfig;

    @Autowired
    private CourseItemDetailMapper courseItemDetailMapper;

    @Override
    public PageInfo<HashMap> getFu1(long id, String courseId,int pageNum) {
        gradeManagementConfig.setPageSize(5);
        PageHelper.startPage(pageNum, gradeManagementConfig.getPageSize());
        return new PageInfo<>(courseItemDetailMapper.selectByCourseId(id, courseId));
    }

    @Override
    public PageInfo<HashMap> getFu2(long id, String courseName,int pageNum) {
        gradeManagementConfig.setPageSize(5);
        PageHelper.startPage(pageNum, gradeManagementConfig.getPageSize());
        return new PageInfo<>(courseItemDetailMapper.selectBykc(id, courseName));
    }

    @Override
    public PageInfo<HashMap> getFu3(long id, String courseClass,int pageNum) {
        gradeManagementConfig.setPageSize(5);
        PageHelper.startPage(pageNum, gradeManagementConfig.getPageSize());
        return new PageInfo<>(courseItemDetailMapper.selectBybj(id, courseClass));
    }

    @Override
    public PageInfo<HashMap> getFu4(long id, String courseName, String courseClass,int pageNum) {
        gradeManagementConfig.setPageSize(5);
        PageHelper.startPage(pageNum, gradeManagementConfig.getPageSize());
        return new PageInfo<>(courseItemDetailMapper.selectBybjkc(id, courseClass, courseName));
    }

    @Override
    public PageInfo<HashMap> getFu5(long id, String courseId, String courseClass,int pageNum) {
        gradeManagementConfig.setPageSize(5);
        PageHelper.startPage(pageNum, gradeManagementConfig.getPageSize());

        return new PageInfo<>(courseItemDetailMapper.selectBykecenomc(id, courseClass, courseId));
    }

    @Override
    public PageInfo<HashMap> getFu6(long id, String courseId, String courseName,int pageNum) {
        gradeManagementConfig.setPageSize(5);
        PageHelper.startPage(pageNum, gradeManagementConfig.getPageSize());
        return new PageInfo<>(courseItemDetailMapper.selectBybjkcnomc(id, courseId, courseName));
    }

    @Override
    public PageInfo<HashMap> getFu7(long id, String courseId, String courseName, String courseClass,int pageNum) {
        gradeManagementConfig.setPageSize(5);
        PageHelper.startPage(pageNum, gradeManagementConfig.getPageSize());
        return new PageInfo<>(courseItemDetailMapper.selectBy(id, courseId, courseName, courseClass));
    }

    @Override
    public PageInfo<HashMap> getFuAll(long id,int pageNum) {
        gradeManagementConfig.setPageSize(5);
        PageHelper.startPage(pageNum, gradeManagementConfig.getPageSize());
        return new PageInfo<>(courseItemDetailMapper.selectALL(id));
    }


    @Override
    public List<HashMap> getClass(long classId, long semesterId, long cId) {
        System.out.println("id="+classId+":semesterId="+semesterId+":cId="+cId);
        return courseItemDetailMapper.selectByClassId(classId,semesterId,cId);
    }
}
