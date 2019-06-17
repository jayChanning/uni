package edu.uni.place.service;

import com.github.pagehelper.PageInfo;
import edu.uni.place.bean.Field;
import edu.uni.place.dto.FDIDto;

import java.util.List;

public interface FieldService {

    List<Field> selectFieldBySchoolarea(Long said);

}
