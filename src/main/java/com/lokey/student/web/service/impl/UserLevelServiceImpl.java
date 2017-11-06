package com.lokey.student.web.service.impl;

import com.lokey.student.web.constant.RequestConstant;
import com.lokey.student.web.mapper.UserLevelMapper;
import com.lokey.student.web.model.Role;
import com.lokey.student.web.model.UserLevel;
import com.lokey.student.web.service.UserLevelService;
import com.lokey.student.web.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lokey on 2017/6/30.
 */
@Component(value = "userlevelService")
public class UserLevelServiceImpl implements UserLevelService{

    @Autowired
    private UserLevelMapper userLevelMapper;
    @Override
    public Map getLevelList(Map map) {
        Map<String, Object> returnMap = new HashMap<>();
        List<UserLevel> userLevelList = userLevelMapper.selectByPage(map);
        returnMap.put("rows",userLevelList);
        map.remove("start");
        map.remove("end");
        returnMap.put("total", userLevelMapper.selectCount(map));
        return returnMap;
    }

    @Override
    public Map addLevel(UserLevel userLevel) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            userLevel.setId(UUID.randomUUID().toString());
            userLevel.setUpdateTime(DateUtil.getNowDateTime());
            userLevelMapper.insert(userLevel);
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }
    @Override
    public Map getLevelInfo(String id) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            UserLevel userLevel = userLevelMapper.selectByPrimaryKey(id);
            if(userLevel==null){
                returnMap = RequestConstant.getRequestcodeDesc(107);
                return returnMap;
            }
            returnMap = RequestConstant.getRequestcodeDesc(0);
            returnMap.put("id", userLevel.getId());
            returnMap.put("name",userLevel.getName());
            returnMap.put("salary", userLevel.getSalary());
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map editLevel(UserLevel userLevel) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            userLevelMapper.updateByPrimaryKeySelective(userLevel);
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map delLevel(String id) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            userLevelMapper.deleteByPrimaryKey(id);
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }
}
