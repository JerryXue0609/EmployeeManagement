package com.lokey.student.web.mapper;

import com.lokey.student.web.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "userMapper")
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User user);

    User selectByPrimaryKey(String id);

    List<User> selectByPage(Map map);

    User selectByUsernum(String num);

    int updateByPrimaryKeySelective(User record);

    Integer selectCount(Map map);

    int updateByPrimaryKey(User user);


}