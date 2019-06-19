package edu.uni.gradeManagement1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.gradeManagement1.bean.CourseItemDetail;
import edu.uni.gradeManagement1.bean.CourseItemDetailExample;
import edu.uni.gradeManagement1.config.GradeManagementConfig;
import edu.uni.gradeManagement1.mapper.CourseItemDetailMapper;
import edu.uni.gradeManagement1.pojo.Item;
import edu.uni.gradeManagement1.service.CourseItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author 蔡政堂
 * create 2019/5/2
 * modified 2019/6/19
 * description TODO
 */
@Service
public class CourseItemDetailServiceImpl implements CourseItemDetailService {
    @Autowired
    private CourseItemDetailMapper courseItemDetailMapper;  //此处报错可忽略，编译后会自动生成

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


    /**
     * 查询树型数组
     *
     * @param id
     * @return
     */
    @Override
    public List<Item> selectTree(long id) {
        List<HashMap> mapList = courseItemDetailMapper.choose(id);
       // System.out.println(mapList);
        ArrayList<Item> lists = new ArrayList<>();
        HashMap<Integer, Item> hashMap = new HashMap<>();
        HashSet<Integer> hashSet = new HashSet<>();
        for (HashMap hm:
                mapList) {
            int name = (int)hm.get("name");
            int number = (int)hm.get("number");
            int count = (int)hm.get("count");
            long itemGradeId = (long)hm.get("itemGradeId");
            long itemDetailId = (long)hm.get("itemDetailId");
            //6.10 9:23AM  替换以下方法
            if (!hashSet.add(name)){
                Item item = hashMap.get(name);
                if (hm.get("isIn") != null)
                    item.setItemName(number);
//                item.setId(number,(long)hm.get("gradeItemId"),(long)hm.get("ItemDetailId"));//参数名如果错了改正
                item.setId(number,itemGradeId,itemDetailId);
            } else{
                hashMap.put(name,new Item(name,count,itemGradeId,itemDetailId));
                //上面一句话原样如下：
//                hashMap.put(name,new Item(name,count));
                Item item = hashMap.get(name);
                if (hm.get("isIn") != null)
                    item.setItemName(number);
                item.setId(number,(long)hm.get("itemGradeId"),(long)hm.get("itemDetailId"));//参数名如果错了改正
//                item.setId(number,itemGradeId,itemDetailId);
            }
            //替换到此
        }
        for (Item item :
                hashMap.values()) {
            lists.add(item);
        }
        System.out.println(lists);
        return lists;
    }

    /**
     * TODO 此处仅更新courseItemDetail的Content
     * 根据course_item_detail表(课程组成项明细表)的id更新
     * @param courseItemDetail
     * @return boolean 0/1
     */
    @Override
    public boolean updateContent(CourseItemDetail courseItemDetail) {
        int result = courseItemDetailMapper.updateByPrimaryKeySelective(courseItemDetail);
        return result > 0;
    }

    /**
     * TODO 根据授课安排表id，课程编号course.id，学期id查询出成绩主表的id
     * 然后自动生成一个班的成绩组成项
     * @param semesterId 学期id semester.id
     * @param taskId 授课安排表id ea_teaching_task.id
     * @param cId 课程编号course.id
     * @return 返回main表的id Long类型List集合
     */
    @Override
    public List<Long> autoCreateCItemDetail(long semesterId, long taskId, long cId) {
        return courseItemDetailMapper.autoCreateStuItemGrade(taskId, cId, semesterId);
    }
}
