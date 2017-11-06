package com.lokey.student.web.mapper;

import com.lokey.student.web.model.Managerfunction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "managerfunctionMapper")
public interface ManagerfunctionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Managerfunction record);

    int insertSelective(Managerfunction record);

    Managerfunction selectByPrimaryKey(String id);

    List<Managerfunction> selectByRoleId(String userid);

    int updateByPrimaryKeySelective(Managerfunction record);

    int updateByPrimaryKey(Managerfunction record);

    int deleteByRoleBtfunctionid(Map map);

    int deleteByRoleId(String roleid);
}