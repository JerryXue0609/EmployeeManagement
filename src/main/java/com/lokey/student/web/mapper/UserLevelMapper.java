package com.lokey.student.web.mapper;

import com.lokey.student.web.model.User;
import com.lokey.student.web.model.UserLevel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "userLevelMapper")
public interface UserLevelMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserLevel userLevel);

    UserLevel selectByPrimaryKey(String id);

    int updateByPrimaryKey(UserLevel userLevel);

    int updateByPrimaryKeySelective(UserLevel record);

    List<UserLevel> selectByPage(Map map);

    Integer selectCount(Map map);


}