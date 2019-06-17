package edu.uni.place.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.place.bean.*;
import edu.uni.place.dto.FDIDto;
import edu.uni.place.mapper.*;
import edu.uni.place.service.FieldService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author 潘绍豪
 * @Create 2019/4/30
 * @Description
 * @Since 1.0.0
 */
@Service
public class FieldServiceImpl implements FieldService {

    private final int isUse = 0;
    private final int notUse = 1;

    @Autowired
    private FieldMapper fieldMapper;

    @Override
    public List<Field> selectFieldBySchoolarea(Long said) {
        FieldExample example = new FieldExample();
        FieldExample.Criteria criteria = example.createCriteria();
        criteria.andAreaIdEqualTo(said);
        criteria.andDeletedEqualTo(isUse);
        return fieldMapper.selectByExample(example);
    }
}
