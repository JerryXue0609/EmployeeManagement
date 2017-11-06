package com.lokey.student.web.mapper;



import com.lokey.student.web.model.App;
import com.lokey.student.web.model.UserLevel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "appMapper")
public interface AppMapper {
    int deleteByPrimaryKey(String id);

    int insert(App app);

    App selectByPrimaryKey(String id);

    int updateByPrimaryKey(App app);

    int updateByPrimaryKeySelective(App app);

    List<App> selectByPage(Map map);

    Integer selectCount(Map map);


}