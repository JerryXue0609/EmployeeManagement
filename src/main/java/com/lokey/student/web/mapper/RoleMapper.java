package com.lokey.student.web.mapper;


import com.lokey.student.web.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "roleMapper")
public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByPage(Map map);

    Integer selectCount(Map map);
}