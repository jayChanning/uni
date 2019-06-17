package edu.uni.gradeManagement1.service.impl;

import edu.uni.gradeManagement1.bean.StuGradeMain;
import edu.uni.gradeManagement1.mapper.StuGradeMainMapper;
import edu.uni.gradeManagement1.service.StuGradeMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 蔡政堂，林晓峰，陈少鑫
 * create: 2019.4.26
 * modified: 2019.4.26
 * 功能：此实现类实现接口StuGradeMainService
 */

@Service
public class StuGradeMainServiceImpl implements StuGradeMainService {

    @Resource
    private StuGradeMainMapper stuGradeMainMapper;
    @Autowired
    private StuGradeMainMapper sgmMapper;

    /**
     * 录入成绩到学生成绩主表
     * author 蔡政堂
     * @param sgm StuGradeMain对象
     * @return 是否成功true或false
     */
    @Override
    public boolean insert(StuGradeMain sgm) {
        if (sgmMapper.insertSelective(sgm)>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * author 蔡政堂
     * @param id
     * @return
     */
    @Override
    public StuGradeMain select(Long id) {
        return sgmMapper.selectByPrimaryKey(id);
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
        StuGradeMain stuGradeMain = new StuGradeMain();
        stuGradeMain.setId(id);
        if (effective)
            stuGradeMain.setDeleted(Byte.valueOf("0"));
        else
            stuGradeMain.setDeleted(Byte.valueOf("1"));
        stuGradeMainMapper.updateByPrimaryKeySelective(stuGradeMain);
        return true;
    }
    /**
     * 修改表stu_grade_main的Score字段
     * author 陈少鑫
     * @param newScore 新得分
     * @param id 主键id
     * @return 如果成功返回true
     */
    @Override
    public boolean uploadScore(double newScore, Long id) {
        StuGradeMain stuGradeMain = new StuGradeMain();
        stuGradeMain.setId(id);
        stuGradeMain.setScore(newScore);
        stuGradeMainMapper.updateByPrimaryKeySelective(stuGradeMain);
        return true;
    }

    public void f(){
    }

}
