package edu.uni.gradeManagement1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.gradeManagement1.bean.CourseItemDetail;
import edu.uni.gradeManagement1.pojo.Item;

import java.util.List;

/**
 * @author 蔡政堂
 * create 2019/5/2
 * modified 2019/5/11
 * description TODO
 */
public interface CourseItemDetailService {
    /**
     * 录入成绩到课程组成项明细表中
     * @param cid courseItemDetail id
     * @return 是否成功（true or false）
     */
    boolean insert(CourseItemDetail cid);

    /**
     * 查询成绩组成项（有哪些组成项）
     * @param id 成绩项id
     * @return
     */
    CourseItemDetail select(long id);

    /**
     * 分页查询所有组成项
     * @param pageNum 页码
     * @return page分页信息
     */
    PageInfo<CourseItemDetail> selectPage(int pageNum);

    /**
     * 分类分页查询所有组成项明细
     * @param pageNum 页码
     * @param courseItemId 组成项id
     * @return
     */
    PageInfo<CourseItemDetail> selectPageByCourseItem(int pageNum, long courseItemId);


    /**
     * 查询树型数组
     * TODO 查询树型数组
     * @return
     */
    List<Item> selectTree(long id);

    /**
     * TODO 此处仅更新courseItemDetail的Content
     * 根据course_item_detail表(课程组成项明细表)的id更新
     * @param courseItemDetail
     * @return boolean 0/1
     */
    boolean updateContent(CourseItemDetail courseItemDetail);

    /**
     * TODO 根据授课安排表id，课程编号course.id，学期id查询出成绩主表的id
     * 然后自动生成一个班的成绩组成项
     * @param semesterId 学期id semester.id
     * @param taskId 授课安排表id ea_teaching_task.id
     * @param cId 课程编号course.id
     * @return 返回main表的id Long类型List集合
     */
    List<Long> autoCreateCItemDetail(long semesterId, long taskId, long cId);
}
