package edu.uni.gradeManagement1.service;

import edu.uni.gradeManagement1.bean.CourseItem;
import edu.uni.gradeManagement1.pojo.*;
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
     * @param courseItem
     * @return true of false
     */
    boolean insert(CourseItem courseItem);

    /**
     * 根据id查询课程成绩评分组成项
     * @param id
     * @return
     */
    CourseItem select(Long id);

    /**
     * 查询出所有的成绩评分组成项
     * @return
     */
    List<CourseItem> selectAll();

}
