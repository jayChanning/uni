package edu.uni.gradeManagement1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.gradeManagement1.bean.CourseItemDetail;
import edu.uni.gradeManagement1.bean.CourseItemDetailExample;
import edu.uni.gradeManagement1.config.GradeManagementConfig;
import edu.uni.gradeManagement1.mapper.CourseItemDetailMapper;
import edu.uni.gradeManagement1.service.CourseItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 蔡政堂
 * create 2019/5/2
 * modified 2019/5/11
 * description TODO
 */
@Service
public class CourseItemDetailServiceImpl implements CourseItemDetailService {
    @Autowired
    private CourseItemDetailMapper courseItemDetailMapper;

    @Autowired
    private GradeManagementConfig gradeManagementConfig;

    /**
     * 录入成绩到课程组成项明细表中
     *
     * @param cid
     * @return 是否成功（true or false）
     */
    @Override
    public boolean insert(CourseItemDetail cid) {
        if (courseItemDetailMapper.insertSelective(cid)>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 查询成绩组成项明细（有哪些组成项明细）
     * @param id 成绩项id
     * @return
     */
    @Override
    public CourseItemDetail select(long id) {
        return courseItemDetailMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询所有组成项明细
     * @param pageNum 页码
     * @return
     */
    @Override
    public PageInfo<CourseItemDetail> selectPage(int pageNum) {
        //开启分页查询
        PageHelper.startPage(pageNum,gradeManagementConfig.getPageSize());

        //无条件查询
        List<CourseItemDetail> itemDetails = courseItemDetailMapper.selectByExample(null);
        if (itemDetails != null){
            return new PageInfo<>(itemDetails);
        }else {
            return null;
        }

    }

    /**
     * 分类分页查询所有组成项明细
     * @param pageNum 页码
     * @param courseItemId 组成项id
     * @return
     */
    @Override
    public PageInfo<CourseItemDetail> selectPageByCourseItem(int pageNum, long courseItemId){
        PageHelper.startPage(pageNum, gradeManagementConfig.getPageSize());   //分页查询开启

        //创建查询条件
        CourseItemDetailExample example = new CourseItemDetailExample();
        CourseItemDetailExample.Criteria criteria = example.createCriteria();
        criteria.andCourseItemIdEqualTo(courseItemId);
        //根据条件查询

        //无条件查询
        List<CourseItemDetail> itemDetails = courseItemDetailMapper.selectByExample(example);
        if (itemDetails != null){
            return new PageInfo<>(itemDetails);
        }else {
            return null;
        }
    }
}
