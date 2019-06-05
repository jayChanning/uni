package edu.uni.gradeManagement1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.gradeManagement1.bean.CourseItem;

import java.util.HashMap;
import java.util.List;

/**
 * @author 蔡政堂
 * create 2019/4/29
 * modified 2019/4/29
 * description TODO
 */
public interface CourseItemService {
    /**
     * 录入成绩到课程成绩评分组成项表
     * @param courseItem courseItem对象
     * @return true of false
     */
    boolean insert(CourseItem courseItem);

    /**
     * 根据id查询课程成绩评分组成项
     * @param id course_item表的id
     * @return courseItem对象
     */
    CourseItem select(Long id);

    /**
     * 查询出所有的成绩评分组成项
     * @return courseItem的List对象
     */
    List<CourseItem> selectAll();

    /**
     * TODO 只有pageNum，默认检索全部该教师教的课程
     * @param pageNum
     * @return
     */
    PageInfo<HashMap> findAll(long id, int pageNum);

}
