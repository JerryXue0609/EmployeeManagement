package com.lokey.student.web.mapper;

import com.lokey.student.web.model.Model;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "modelMapper")
public interface ModelMapper {
    int deleteByPrimaryKey(String id);

    int insert(Model record);

    int insertSelective(Model record);

    Model selectByPrimaryKey(String id);

    List<Model> selectAll();

    int updateByPrimaryKeySelective(Model record);

    int updateByPrimaryKey(Model record);
}