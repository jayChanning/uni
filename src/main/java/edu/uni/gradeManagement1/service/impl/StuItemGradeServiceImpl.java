package edu.uni.gradeManagement1.service.impl;

import edu.uni.gradeManagement1.bean.StuItemGrade;
import edu.uni.gradeManagement1.mapper.StuItemGradeMapper;
import edu.uni.gradeManagement1.service.StuItemGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@Service
public class StuItemGradeServiceImpl implements StuItemGradeService {

    @Resource
    private StuItemGradeMapper stuItemGradeMapper;

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

    /**
     * 蔡政堂
     * @param stuItemGrade
     * @return
     */
    @Override
    public boolean insert(StuItemGrade stuItemGrade) {

        if (sigMapper.insertSelective(stuItemGrade)>0){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 修改表stu_item_grade的指定id的deleted字段
     * author 陈少鑫
     * @param effective true-有效 false-无效
     * @param id stu_item_grade.id
     * @return 如果成功返回true
     */
    @Override
    public boolean changState(Long id, boolean effective) {
        StuItemGrade stuItemGrade = new StuItemGrade();
        stuItemGrade.setId(id);
        if (effective)
            stuItemGrade.setDeleted(Byte.valueOf("0"));
        else
            stuItemGrade.setDeleted(Byte.valueOf("1"));
        stuItemGradeMapper.updateByPrimaryKeySelective(stuItemGrade);
        return true;
    }

    /**
     *
     * 获取表stu_item_grade所有stu_grade_main_id字段值为id的记录
     * author 陈少鑫
     * @param id stu_grade_main_id
     * @return List<StuItemGrade>
     */
    @Override
    public List<HashMap> selectByStuMId(Long id) {
        return stuItemGradeMapper.scoreAndRate(id);
    }

    /**
     * 修改表stu_item_grade的score字段
     * author 陈少鑫
     * @param newScore 新得分
     * @param id 主键id
     * @return 如果成功返回true
     */
    @Override
    public boolean uploadScore(double newScore, Long id) {
        StuItemGrade stuItemGrade = new StuItemGrade();
        stuItemGrade.setId(id);
        stuItemGrade.setScore(newScore);
        stuItemGradeMapper.updateByPrimaryKeySelective(stuItemGrade);
        return true;
    }
}
