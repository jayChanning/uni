package edu.uni.educateAffair.service.impl;

import edu.uni.educateAffair.bean.*;
import edu.uni.educateAffair.mapper.CanlendarMapper;
import edu.uni.educateAffair.mapper.CurriculumMapper;
import edu.uni.educateAffair.mapper.SemesterMapper;
import edu.uni.educateAffair.service.CanlendarService;
import edu.uni.utils.DayInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CanlendarServiceImpl implements CanlendarService {

    private final int isUse = 0;
    private final int notUse = 1;

    @Autowired
    private CanlendarMapper canlendarMapper;
    @Autowired
    private SemesterMapper semesterMapper;
    @Autowired
    private CurriculumMapper curriculumMapper;

    @Override
    public boolean insertCanlendar(List<Canlendar> canlendar) {
        Integer id = 0;
        for (Canlendar c : canlendar){
           id = canlendarMapper.insert(c);
        }
        {
            return id > 0 ? true : false;
        }
    }

    @Override
    public String inputWeek(Long id, Date theDate) throws SQLException{

        Date start = null;
        String week = null;

        SemesterExample example = new SemesterExample();
        SemesterExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andDeletedEqualTo(isUse);
        List<Semester> semester = semesterMapper.selectByExample(example);
        if(semester.size() > 0){
            start = semester.get(0).getStart();
            DayInterval dayInterval = new DayInterval(start,theDate);
            week = String.valueOf(dayInterval.DayLimit()/7 + 1);
        }else{
            throw new SQLException("学期不存在");
        }
        return week;
    }

    @Override
    public String inputDate(Date theDate) {
        String sweek = null;
        if(theDate != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(theDate);
            int iweek = calendar.get(Calendar.DAY_OF_WEEK);
            switch (iweek){
                case 1:
                    sweek="日";
                    break;
                case 2:
                    sweek="一";
                    break;
                case 3:
                    sweek="二";
                    break;
                case 4:
                    sweek="三";
                    break;
                case 5:
                    sweek="四";
                    break;
                case 6:
                    sweek="五";
                    break;
                case 7:
                    sweek="六";
                    break;
            }
        }
        return sweek;
    }

    @Override
    //c为校历ID + 是否为假期holiday + 描述describe
    public boolean updateCanlendar(List<Canlendar> c) throws SQLException {
        List<Canlendar> NewCanlendarList = new ArrayList<Canlendar>();
        //创建数据副本并将delete设为notUse
        for(Canlendar canlendar : c){
            List<Canlendar> c1 = new ArrayList<Canlendar>();
            CanlendarExample example = new CanlendarExample();
            CanlendarExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(canlendar.getId());
            criteria.andDeletedEqualTo(isUse);
            c1 = canlendarMapper.selectByExample(example);
            c1.get(0).setDeleted(notUse);
            System.out.println(c1.get(0));
            NewCanlendarList.add(c1.get(0));
        }
        List<Curriculum> curriculumList = new ArrayList<Curriculum>();
        for(Canlendar canlendar : c){
            CurriculumExample example1 = new CurriculumExample();
            CurriculumExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andCanlendarIdEqualTo(canlendar.getId());
            curriculumList = curriculumMapper.selectByExample(example1);
            if (curriculumList.size() > 0) {
                //创建数据副本并置为notUse
                for (Curriculum cu : curriculumList) {
                    cu.setDeleted(notUse);
                }
                System.out.println("课表数据副本" + curriculumList);
                curriculumMapper.insertCurriculumBatch(curriculumList);
                Curriculum updateCurriculum = new Curriculum();
                updateCurriculum.setStatus(1);
                updateCurriculum.setDatetime(Calendar.getInstance().getTime());
                criteria1.andDeletedEqualTo(isUse);
                curriculumMapper.updateByExampleSelective(updateCurriculum, example1);
            }
        }
        if(canlendarMapper.insertBatch(NewCanlendarList) <= 0){
            throw new SQLException();
        }
        //修改原数据
        for(Canlendar canlendar : c){
            CanlendarExample example = new CanlendarExample();
            CanlendarExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(canlendar.getId());

            if(canlendarMapper.updateByExampleSelective(canlendar,example) <= 0){
                throw new SQLException();
            }
        }
        return true;
    }

    @Override
    public List<Canlendar> selectAll() {
        CanlendarExample example = new CanlendarExample();
        CanlendarExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(isUse);
        List<Canlendar> AllCanlendar = canlendarMapper.selectByExample(example);
        return AllCanlendar;
    }

    @Override
    public List<Canlendar> selectBySemesterId(Long sid) {
        CanlendarExample example = new CanlendarExample();
        CanlendarExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(isUse);
        criteria.andSemesterIdEqualTo(sid);
        List<Canlendar> canlendarList = canlendarMapper.selectByExample(example);
        return canlendarList;
    }

    @Override
    public boolean delete(Long id) {
        boolean success;
        CanlendarExample example = new CanlendarExample();
        CanlendarExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);

        Canlendar DeleteCanlendar = new Canlendar();
        DeleteCanlendar.setDeleted(notUse);

        if(canlendarMapper.updateByExampleSelective(DeleteCanlendar,example) > 0){
            success = true;
        }else{
            success = false;
        }
        return success;
    }

    @Override
    public List<String> selectWeekBySemester(Long sid) {
        return canlendarMapper.selectWeekBySemester(sid);
    }
}

