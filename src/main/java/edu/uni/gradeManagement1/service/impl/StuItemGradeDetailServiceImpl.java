package edu.uni.gradeManagement1.service.impl;

import edu.uni.gradeManagement1.bean.StuItemGradeDetail;
import edu.uni.gradeManagement1.mapper.StuItemGradeDetailMapper;
import edu.uni.gradeManagement1.service.StuItemGradeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 蔡政堂
 * create 2019/4/29
 * modified 2019/4/29
 * description TODO
 */
@Service
public class StuItemGradeDetailServiceImpl implements StuItemGradeDetailService {
    @Autowired
    private StuItemGradeDetailMapper sigdMapper;

    /**
     * 录入成绩到成绩项明细表
     *
     * @param sigd
     * @return 是否成功（true or false）
     */

    @Override
    public boolean insert(StuItemGradeDetail sigd) {
        if (sigdMapper.insertSelective(sigd)>0){
            return true;
        }else {
            return false;
        }
    }
}
