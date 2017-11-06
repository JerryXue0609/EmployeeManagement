package com.lokey.student.web.mapper;

import com.lokey.student.web.model.Function;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "functionMapper")
public interface FunctionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Function record);

    int insertSelective(Function record);

    Function selectByPrimaryKey(String id);

    List<Function> selectAll();

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);

    List<Function> selectByMap(Map<String, Object> map);

    List<Function> selectByModelId(String modelid);
}