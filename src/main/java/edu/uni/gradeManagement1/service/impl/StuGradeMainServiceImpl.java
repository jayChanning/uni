package edu.uni.gradeManagement1.service.impl;

import edu.uni.gradeManagement1.bean.StuGradeMain;
import edu.uni.gradeManagement1.mapper.StuGradeMainMapper;
import edu.uni.gradeManagement1.service.StuGradeMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 蔡政堂
 * create 2019/4/29
 * modified 2019/4/29
 * description TODO
 */
@Service
public class StuGradeMainServiceImpl implements StuGradeMainService {
    @Autowired
    private StuGradeMainMapper sgmMapper;

    /**
     * 录入成绩到学生成绩主表
     *
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

    @Override
    public StuGradeMain select(Long id) {
        return sgmMapper.selectByPrimaryKey(id);
    }
}
