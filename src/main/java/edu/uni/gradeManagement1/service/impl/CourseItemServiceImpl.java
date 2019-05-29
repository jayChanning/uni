package edu.uni.gradeManagement1.service.impl;

import edu.uni.gradeManagement1.bean.CourseItem;
import edu.uni.gradeManagement1.bean.CourseItemExample;
import edu.uni.gradeManagement1.mapper.CourseItemMapper;
import edu.uni.gradeManagement1.pojo.Item;
import edu.uni.gradeManagement1.service.CourseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 蔡政堂
 * create 2019/4/29
 * modified 2019/4/29
 * description TODO
 */

@Service
public class CourseItemServiceImpl implements CourseItemService {
    @Autowired
    private CourseItemMapper courseItemMapper;  //此处报错可忽略，编译后会自动生成

    /**
     * 实现课程成绩评分组成项表的录入
     * @param courseItem
     * @return
     */
    @Override
    public boolean insert(CourseItem courseItem) {
        if (courseItemMapper.insertSelective(courseItem)>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 根据id查询课程成绩评分组成项
     * @param id
     * @return
     */
    @Override
    public CourseItem select(Long id) {
        return courseItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有的课程成绩评分组成项
     * @return
     */
    @Override
    public List<CourseItem> selectAll() {
        CourseItemExample example = new CourseItemExample();
        CourseItemExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo((byte)0);
        List<CourseItem> allCourseItem = courseItemMapper.selectByExample(example);
        return allCourseItem;
    }

}
