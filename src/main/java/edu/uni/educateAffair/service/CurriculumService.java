package edu.uni.educateAffair.service;

import edu.uni.educateAffair.VO.CurriculumVO;
import edu.uni.educateAffair.VO.CurriculumWithCondition;
import edu.uni.educateAffair.VO.InputCurriculumVO;
import edu.uni.educateAffair.VO.RegulateCurriculum;
import edu.uni.educateAffair.bean.Curriculum;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Author:梁俊杰
 * @Description:
 * @Date:Created in 17:52 2019/5/4
 */
public interface CurriculumService {
    /**
        *@Author:梁俊杰
        *@Description:新建授课安排表
        *@Date:Created in {17:53} {2019/5/4}
    */
    boolean insertCurriculum(Curriculum curriculum);
    /**
        *@Author:梁俊杰
        *@Description:更改授课安排表
        *@Date:Created in {23:18} {2019/5/4}
    */
    boolean updateCurriculum(Curriculum curriculum);
    /**
        *@Author:梁俊杰
        *@Description:查看全部授课安排表
        *@Date:Created in {23:40} {2019/5/4}
    */
    List<Curriculum> selectAll() throws SQLException;
    /**
        *@Author:梁俊杰
        *@Description:根据ID删除授课安排表
        *@Date:Created in {23:53} {2019/5/4}
    */
    boolean delete(Long id);
    /**
        *@Author:梁俊杰
        *@Description:转换课程表
        *@Date:Created in {22:04} {2019/5/11}
    */
    List<CurriculumVO> Transform(CurriculumWithCondition curriculumWithCondition);
/**
    *@Author:梁俊杰
    *@Description:学期Id集合，教师id集合，课程id集合， 班级id集合
    *@Date:Created in {13:25} {2019/5/17}
*/
    List<Curriculum> selectCurriculumByCondition(List<Long> semesterId, List<Long> employeeId, List<Long> courseId, List<Long> classId);
    /**
        *@Author:梁俊杰
        *@Description:根据学生ID和时间段获取该时间段该学生的授课安排表
        *@Date:Created in {15:10} {2019/5/18}
    */
    List<Long> selectCurriculumByUserAndCanlendar(Long userId, String start, String end);
    /**
        *@Author:梁俊杰
        *@Description:根据学号或教师编号获取当前学期当前周的课程表
        *@Date:Created in {15:34} {2019/5/21}
    */
    List<CurriculumVO> getCurriculumByStudentOrEmployeeAndSemesterIDANDWeek(Long no, Long semesterId, String week);
    /**
     *@Author:梁俊杰
     *@Description:返回学年
     *@Date:Created in {16:35} {2019/5/28}
     */
    List<Integer> selectAllYear();
    /**
        *@Author:梁俊杰
        *@Description:根据学期+开始结束星期+星期几筛选出CanlendarID
        *@Date:Created in {14:48} {2019/6/1}
    */
    List<Long> insertCurriculumForCanlendar(InputCurriculumVO inputCurriculumVO);
    /**
        *@Author:梁俊杰
        *@Description:调课
        *@Date:Created in {11:18} {2019/6/3}
    */
    boolean updateForRegulateCurriculum(RegulateCurriculum regulateCurriculum);

    List<CurriculumVO> selectCurriculumByClassAndSemesterAndWeek(Map<String, Object> map);
}
