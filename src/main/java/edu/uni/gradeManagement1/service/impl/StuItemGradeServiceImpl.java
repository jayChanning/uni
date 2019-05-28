package edu.uni.gradeManagement1.service.impl;

import edu.uni.gradeManagement1.bean.StuItemGrade;
import edu.uni.gradeManagement1.mapper.StuItemGradeMapper;
import edu.uni.gradeManagement1.service.StuItemGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 蔡政堂
 * create 2019/4/29
 * modified 2019/4/29
 * description TODO
 */
@Service
public class StuItemGradeServiceImpl implements StuItemGradeService {

    @Autowired
//    @Resource
    private StuItemGradeMapper sigMapper;   //用@Resource不爆红，@Autowired爆红

        /*@Override
    public boolean AddStuItemGrade(StuItemGrade sig) {
        if (sigMapper.insert(sig)>0){
            return true;
        }else {
            return false;
        }

    }*/

    @Override
    public boolean insert(StuItemGrade stuItemGrade) {

        if (sigMapper.insertSelective(stuItemGrade)>0){
            return true;
        }else {
            return false;
        }
    }
}
