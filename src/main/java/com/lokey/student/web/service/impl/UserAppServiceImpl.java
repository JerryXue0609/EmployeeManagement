package com.lokey.student.web.service.impl;

import com.lokey.student.web.constant.RequestConstant;
import com.lokey.student.web.mapper.AppMapper;
import com.lokey.student.web.mapper.UserLevelMapper;
import com.lokey.student.web.model.App;
import com.lokey.student.web.model.UserLevel;
import com.lokey.student.web.service.UserAppService;
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
@Component(value = "userappService")
public class UserAppServiceImpl implements UserAppService{

    @Autowired
    private AppMapper appMapper;
    @Override
    public Map getAppList(Map map) {
        Map<String, Object> returnMap = new HashMap<>();
        List<App> appList = appMapper.selectByPage(map);
        returnMap.put("rows",appList);
        map.remove("start");
        map.remove("end");
        returnMap.put("total", appMapper.selectCount(map));
        return returnMap;
    }

    @Override
    public Map addApp(App app) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            app.setId(UUID.randomUUID().toString());
            app.setStatus(0);
            app.setUpdateTime(DateUtil.getNowDateTime());
            appMapper.insert(app);
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map getAppInfo(String id) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            App app = appMapper.selectByPrimaryKey(id);
            if(app==null){
                returnMap = RequestConstant.getRequestcodeDesc(107);
                return returnMap;
            }
            returnMap = RequestConstant.getRequestcodeDesc(0);
            returnMap.put("id", app.getId());
            returnMap.put("userName",app.getUserName());
            returnMap.put("cate",app.getCate());
            returnMap.put("content", app.getContent());
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map editApp(App app) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            appMapper.updateByPrimaryKeySelective(app);
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map delApp(String id) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            appMapper.deleteByPrimaryKey(id);
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }
}
