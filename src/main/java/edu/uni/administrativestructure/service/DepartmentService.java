package edu.uni.administrativestructure.service;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Department;

import java.util.List;

/**
 author:黄永佳
 create:2019.4.19
 modified:null
 功能:创建DepartmentService接口
 **/
public interface DepartmentService {
    /**
     * 新增部门
     * @param department
     * @return
     */
    boolean insert(Department department);

    /**
     * 删除部门
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 修改部门
     * @param department
     * @return
     */
    boolean update(Department department);

    /**
     * 查询部门详情
     * @param id
     * @return
     */
    Department select(long id);

    /**
     * 分页查询所有部门
     * @param pageNum
     * @return
     */
    PageInfo<Department> selectPage(int pageNum);

    /**
     * 分学校分页查询部门
     * @param universityId
     * @return
     */
    List<Department> selectPageByUniversity(long universityId);

    /**
     * 查找所有部门
     * @return
     */
    List<Department> selectAll();
    /*
    * 根据部门名称模糊查询记录
    * */
    List<Department> selectLikeName(String name);
//查找学院
    List<Department> selectLikeDepartmentName(String name);

    /**
     *@Author:梁俊杰
     *@Description:查找所有学院
     *@Date:Created in {1:47} {2019/6/4}
     */
    List<Department> selectAllInstitute();
}
