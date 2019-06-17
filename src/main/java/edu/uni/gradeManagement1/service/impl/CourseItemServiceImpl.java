package edu.uni.gradeManagement1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.gradeManagement1.bean.CourseItem;
import edu.uni.gradeManagement1.bean.CourseItemExample;
import edu.uni.gradeManagement1.config.GradeManagementConfig;
import edu.uni.gradeManagement1.mapper.CourseItemMapper;
import edu.uni.gradeManagement1.service.CourseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 蔡政堂，林晓峰，陈少鑫
 * create: 2019.4.26
 * modified: 2019.4.26
 * 功能：此实现类实现接口CourseItemService
 */

@Service
public class CourseItemServiceImpl implements CourseItemService {

    @Resource
    private CourseItemMapper courseItemMapper;
    @Autowired
    private GradeManagementConfig gmConfig;

    /**
     * 实现课程成绩评分组成项表的录入
     * author 蔡政堂
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
     * 根据id查询课程成绩评分组成项 author 蔡政堂
     * @param id
     * @return
     */
    @Override
    public CourseItem select(Long id) {
        return courseItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有的课程成绩评分组成项 author 蔡政堂
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

    /**
     * 暂不可用 author 蔡政堂
     * @param id
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<HashMap> findAll(long id, int pageNum) {
        PageHelper.startPage(pageNum, gmConfig.getPageSize());
        return null;
    }


    /**
     * 查找指定id的记录
     * author 陈少鑫
     * @param id course_item主键id
     * @return 返回CourseIterm实例
     */


    @Override
    public CourseItem selectById(Long id) {
       return courseItemMapper.selectByPrimaryKey(id);
    }


    /**
     * author 林晓锋
     * 查询组成项及其得分
     * @param id
     * @return
     */
    @Override
    public List<HashMap> selectCourseItem (Long id) {

        return courseItemMapper.selectCourseItem( id ) ;
    }

    /**author 林晓锋
     * 查询组成项名称为作业的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    @Override
    public List<HashMap> selectCourseItemDetail1(Long course_item_id,Long stu_item_grade_id ) {

        return courseItemMapper.selectCourseItemDetail1(course_item_id,stu_item_grade_id );
    }

    /**author 林晓锋
     * 查询组成项名称为考勤的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    @Override
    public List<HashMap> selectCourseItemDetail2(Long course_item_id,Long stu_item_grade_id) {

        return courseItemMapper.selectCourseItemDetail2(course_item_id,stu_item_grade_id);
    }


    /**author 林晓锋
     * 查询组成项名称为期中考试的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    @Override
    public List<HashMap> selectCourseItemDetail3(Long course_item_id,Long stu_item_grade_id) {

        return courseItemMapper.selectCourseItemDetail3(course_item_id,stu_item_grade_id);
    }

    /**author 林晓锋
     * 查询组成项名称为实验的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    @Override
    public List<HashMap> selectCourseItemDetail4(Long course_item_id,Long stu_item_grade_id) {

        return courseItemMapper.selectCourseItemDetail4(course_item_id,stu_item_grade_id);
    }

    /**author 林晓锋
     * 查询组成项名称为期末考试的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    @Override
    public List<HashMap> selectCourseItemDetail5(Long course_item_id,Long stu_item_grade_id) {

        return courseItemMapper.selectCourseItemDetail5(course_item_id,stu_item_grade_id);
    }

    /**author 林晓锋
     * author 林晓锋
     * 查询组成项名称为其他的内容及得分
     * @param course_item_id
     * @param stu_item_grade_id
     * @return
     */
    @Override
    public List<HashMap> selectCourseItemDetail6(Long course_item_id,Long stu_item_grade_id) {

        return courseItemMapper.selectCourseItemDetail6(course_item_id,stu_item_grade_id);
    }
}
