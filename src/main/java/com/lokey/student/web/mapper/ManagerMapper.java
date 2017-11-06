package com.lokey.student.web.mapper;

import com.lokey.student.web.model.Manager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "managerMapper")
public interface ManagerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);

    Manager selectByUsername(String username);

    Manager selectByManagename(String username);

    List<Manager> selectByPage(Map map);

    Integer selectCount(Map map);
}