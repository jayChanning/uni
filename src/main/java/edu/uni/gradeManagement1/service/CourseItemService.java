package edu.uni.gradeManagement1.service;


import com.github.pagehelper.PageInfo;
import edu.uni.gradeManagement1.bean.CourseItem;

import java.util.HashMap;
import java.util.List;

/**
 * @author 陈少鑫 蔡政堂，林晓锋
 * create: 2019.4.26
 * modified: 2019.4.26
 * 功能：此接口为controller层提供对course_item表的增查改操作
 */

public interface CourseItemService {


    /**
     * 录入成绩到课程成绩评分组成项表
     * author 蔡政堂
     ** @param courseItem courseItem对象
     * @return true of false
     */
    boolean insert(CourseItem courseItem);

    /**
     * 根据id查询课程成绩评分组成项
     * author 蔡政堂
     ** @param id course_item表的id
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
     * author 蔡政堂
     * @param pageNum
     * @return
     */
    PageInfo<HashMap> findAll(long id, int pageNum);


    /**
     * 查找指定id的记录
     * author 陈少鑫
     * @param id course_item主键id
     * @return 返回CourseIterm实例
     */

    CourseItem selectById(Long id);


    /**
     *  author 林晓峰
     */
    //查询组成项得分
    List<HashMap> selectCourseItem(Long id);
    /**
     *  author 林晓峰
     */
    //查询组成项名称为作业的内容及得分
    List<HashMap> selectCourseItemDetail1(Long course_item_id, Long stu_item_grade_id);
    /**
     *  author 林晓峰
     */
    //查询组成项名称为考勤的内容及得分
    List<HashMap> selectCourseItemDetail2(Long course_item_id, Long stu_item_grade_id);
    /**
     *  author 林晓峰
     */
    //查询组成项名称为期中考试的内容及得分
    List<HashMap> selectCourseItemDetail3(Long course_item_id, Long stu_item_grade_id);
    /**
     *  author 林晓峰
     */
    //查询组成项名称为实验的内容及得分
    List<HashMap> selectCourseItemDetail4(Long course_item_id, Long stu_item_grade_id);
    /**
     *  author 林晓峰
     */
    //查询组成项名称为期末考试的内容及得分
    List<HashMap> selectCourseItemDetail5(Long course_item_id, Long stu_item_grade_id);
    /**
     *  author 林晓峰
     */
    //查询组成项名称为其他的内容及得分
    List<HashMap> selectCourseItemDetail6(Long course_item_id, Long stu_item_grade_id);

}
