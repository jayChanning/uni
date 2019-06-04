package edu.uni.educateAffair.service.impl;

import edu.uni.educateAffair.VO.CurriculumVO;
import edu.uni.educateAffair.VO.CurriculumWithCondition;
import edu.uni.educateAffair.bean.*;
import edu.uni.educateAffair.mapper.CanlendarMapper;
import edu.uni.educateAffair.mapper.CurriculumMapper;
import edu.uni.educateAffair.mapper.SemesterMapper;
import edu.uni.educateAffair.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

/**
 * @Author:梁俊杰
 * @Description:
 * @Date:Created in 17:54 2019/5/4
 */
@Service
public class CurriculumServiceImpl implements CurriculumService {

    private final int isUse = 0;
    private final int notUse = 1;

    @Autowired
    private CurriculumMapper curriculumMapper;
    @Autowired
    private SemesterMapper semesterMapper;
    @Autowired
    private CanlendarMapper canlendarMapper;


    @Override
    public boolean insertCurriculum(Curriculum curriculum) {
        {return (curriculumMapper.insert(curriculum)>0);}
    }

    @Override
    public boolean updateCurriculum(Curriculum curriculum) {
        CurriculumExample example = new CurriculumExample();
        CurriculumExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(curriculum.getId());
        //修改原字段Detele属性为1
        Curriculum DeleteCurriculum = new Curriculum();
        DeleteCurriculum.setDeleted(notUse);
        curriculumMapper.updateByExampleSelective(DeleteCurriculum,example);
        //新增字段
        curriculum.setDatetime(Calendar.getInstance().getTime());
        curriculum.setByWho((long)1);
        curriculum.setDeleted(isUse);
        boolean success = (curriculumMapper.insert(curriculum) > 0);
        return success;
    }

    @Override
    public List<Curriculum> selectAll() throws SQLException {
        List<Curriculum> AllCurriculum = new ArrayList<Curriculum>();
        CurriculumExample example = new CurriculumExample();
        CurriculumExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(isUse);
        AllCurriculum = curriculumMapper.selectByExample(example);
        if (AllCurriculum == null){
            throw new SQLException();
        }
        return AllCurriculum;
    }

    @Override
    public boolean delete(Long id) {
        CurriculumExample example = new CurriculumExample();
        CurriculumExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        Curriculum DeleteCurriculum = new Curriculum();
        DeleteCurriculum.setDeleted(notUse);

        boolean success = (curriculumMapper.updateByExampleSelective(DeleteCurriculum,example) > 0);

        return success;
    }

    //TODO 根据教师ID获取教师某一天的课程
    public List<Curriculum> selectCurriculumByDateAndTeacherId(Long teacherId , Date date){
        CurriculumExample example = new CurriculumExample();
        CurriculumExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeIdEqualTo(teacherId);
        return null;
    }

/*    @Override
    public List<CurriculumVO> Transform(Curriculum curriculum) {

            System.out.println("接收的课程表ID" + curriculum.toString());
            CurriculumExample example = new CurriculumExample();
            CurriculumExample.Criteria criteria = example.createCriteria();
            if (curriculum.getCanlendarId() != null){
                criteria.andCanlendarIdEqualTo(curriculum.getCanlendarId());
            }
            if (curriculum.getTimeTableId() != null){
                criteria.andTimeTableIdEqualTo(curriculum.getTimeTableId());
            }
            if (curriculum.getFieldId() != null){
                criteria.andFieldIdEqualTo(curriculum.getFieldId());
            }
            if (curriculum.getClassId() != null){
                criteria.andClassIdEqualTo(curriculum.getClassId());
            }
            if (curriculum.getEmployeeId() != null){
                criteria.andEmployeeIdEqualTo(curriculum.getEmployeeId());
            }
            if (curriculum.getCourseId() != null){
                criteria.andCourseIdEqualTo(curriculum.getCourseId());
            }
            criteria.andDeletedEqualTo(isUse);
            List<Curriculum> CriteriaCurriculum = curriculumMapper.selectByExample(example);
            //课程表name集合
            List<CurriculumVO> ReturnCurriculumVO = new ArrayList<CurriculumVO>();
            for(Curriculum c : CriteriaCurriculum) {
                System.out.println("搜索出来的课程表ID" + c.toString());
                CurriculumVO vo = curriculumMapper.selectCurriculumByDateAndTeacherId(c);
                ReturnCurriculumVO.add(vo);
                System.out.println("返回的课程表name" + vo.toString());
            }
            return ReturnCurriculumVO ;
        }*/
    @Override
    public List<CurriculumVO> Transform(CurriculumWithCondition curriculumWithCondition){
        System.out.println("接收的课程表ID" + curriculumWithCondition.toString());
        List<CurriculumVO> cu = curriculumMapper.selectCurriculumNameByCondition(curriculumWithCondition);
        System.out.println("返回的课程表name" + cu.toString());
        return cu ;
    }

    // TODO 学期Id集合，教师id集合，课程id集合， 班级id集合
    // TODO 返回eA_curriculum 实体集合，校历id换取学期id
    @Override
    public List<Curriculum> selectCurriculumByCondition(List<Long> semesterId ,List<Long> employeeId ,List<Long> courseId ,List<Long> classId){
        Map<String,List<Long>> curriculumMap = new HashMap<String,List<Long>>();
        curriculumMap.put("semesterId",semesterId);
        curriculumMap.put("employeeId",employeeId);
        curriculumMap.put("courseId",courseId);
        curriculumMap.put("classId",classId);

/*        System.out.println("semesterId:" + curriculumMap.get("semesterId"));
        System.out.println("employeeId:" + curriculumMap.get("employeeId"));
        System.out.println("courseId:" + curriculumMap.get("courseId"));
        System.out.println("classId:" + curriculumMap.get("classId"));*/

        List<Curriculum> curriculumList = curriculumMapper.getCurriculumMsgList(curriculumMap);
        System.out.println(curriculumList);
        return curriculumList;
    }
    // TODO 根据学生ID和时间段获取该时间段该学生的授课安排表
    @Override
    public List<Long> selectCurriculumByUserAndCanlendar(Long studentId, String start, String end) {
        Map<String,Object> SearchMap = new HashMap<>();
        SearchMap.put("studentId",studentId);
        SearchMap.put("start",start);
        SearchMap.put("end",end);
        System.out.println("接收到的studentId" + SearchMap.get("studentId"));
        System.out.println("接收到的start" + SearchMap.get("start"));
        System.out.println("接收到的end" + SearchMap.get("end"));
        List<Long> curriculumIdList = curriculumMapper.getCurriculumIdByUserAndCanlendar(SearchMap);
        System.out.println(curriculumIdList);
        return curriculumIdList;
    }

    //通过学生ID或者教师ID查询当前学期当前周的课程
    @Override
    public List<CurriculumVO> getCurriculumByStudentOrEmployeeAndSemesterIDANDWeek(Long no, Long semesterId, String week) {
        Map<String,Object> map = new HashMap<>();
        map.put("no",no);
        map.put("semesterId",semesterId);
        map.put("week",week);
        System.out.println("week:" + week);
        List<Curriculum> curriculumList = curriculumMapper.getCurriculumByStudentOrEmployeeAndSemesterIDANDWeek(map);
        System.out.println(curriculumList.toString());
        List<CurriculumVO> curriculumVOList = new ArrayList<>();
        for(Curriculum cu : curriculumList){
            CurriculumWithCondition curriculumWithCondition = new CurriculumWithCondition();
            curriculumWithCondition.setId(cu.getId());
            curriculumWithCondition.setCanlendarId(cu.getCanlendarId());
            curriculumWithCondition.setTimeTableId(cu.getTimeTableId());
            curriculumWithCondition.setFieldId(cu.getFieldId());
            curriculumWithCondition.setClassId(cu.getClassId());
            curriculumWithCondition.setEmployeeId(cu.getEmployeeId());
            curriculumWithCondition.setCourseId(cu.getCourseId());
            curriculumWithCondition.setCanlendar(true);
            curriculumWithCondition.setTimetable(true);
            curriculumWithCondition.setField(true);
            curriculumWithCondition.setClass(true);
            curriculumWithCondition.setEmployee(true);
            curriculumWithCondition.setCourse(true);
            List<CurriculumVO> curriculumVO1 = curriculumMapper.selectCurriculumNameByCondition(curriculumWithCondition);
            for (CurriculumVO cv : curriculumVO1){
                curriculumVOList.add(cv);
            }
        }
        return curriculumVOList;
    }
    @Override
    public List<Integer> selectAllYear() {
        return curriculumMapper.selectAllYear();
    }
}
