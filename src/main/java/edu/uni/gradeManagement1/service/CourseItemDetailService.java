package edu.uni.gradeManagement1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.gradeManagement1.bean.CourseItemDetail;

/**
 * @author 蔡政堂
 * create 2019/5/2
 * modified 2019/5/11
 * description TODO
 */
public interface CourseItemDetailService {
    /**
     * 录入成绩到课程组成项明细表中
     * @param cid
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
     * @return
     */
    PageInfo<CourseItemDetail> selectPage(int pageNum);

    /**
     * 分类分页查询所有组成项明细
     * @param pageNum 页码
     * @param courseItemId 组成项id
     * @return
     */
    PageInfo<CourseItemDetail> selectPageByCourseItem(int pageNum, long courseItemId);
}
