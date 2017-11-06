package com.lokey.student.web.mapper;

import com.lokey.student.web.model.Btfunction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "btfunctionMapper")
public interface BtfunctionMapper {

    List<Btfunction> selectAll();

    int deleteByPrimaryKey(String id);

    int insert(Btfunction record);

    int insertSelective(Btfunction record);

    Btfunction selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Btfunction record);

    int updateByPrimaryKey(Btfunction record);

    List<Btfunction> selectByFunctionId(String functionid);
}