package com.lokey.student.web.service.impl;


import com.lokey.student.web.constant.RequestConstant;
import com.lokey.student.web.mapper.ManagerMapper;
import com.lokey.student.web.mapper.UserLevelMapper;
import com.lokey.student.web.mapper.UserMapper;
import com.lokey.student.web.model.*;
import com.lokey.student.web.service.UserService;
import com.lokey.student.web.util.DateUtil;
import com.lokey.student.web.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Lokey on 17/6/30.
 */
@Component(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserLevelMapper userLevelMapper;

    @Override
    public Map login(String username, String password,Integer isUser) {
        Map returnMap = new HashMap();
        try {
            Manager manager = null;
            if(isUser==1) {
                manager =  managerMapper.selectByUsername(username);
            }else {
                manager =  managerMapper.selectByManagename(username);
            }
            if (manager == null) {
                returnMap = RequestConstant.getRequestcodeDesc(107);
                return returnMap;
            }
            if (password.equals(manager.getPassword())) {
                returnMap = RequestConstant.getRequestcodeDesc(0);
                returnMap.put("userid", manager.getId());
                returnMap.put("roleid", manager.getRoleid());
                returnMap.put("isUser", 0);
                return returnMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map getLevelList() {
        Map<String, Object> returnMap = new HashMap<>();
        List<UserLevel> userLevelList = userLevelMapper.selectByPage(null);
        returnMap.put("levelList",userLevelList);
        return returnMap;
    }

    @Override
    public Map getUserList(Map map) {
        Map<String, Object> returnMap = new HashMap<>();
        List<User> users = userMapper.selectByPage(map);
        List<User> showusers = new ArrayList<>();
        for(User user : users){
                UserLevel userLevel = userLevelMapper.selectByPrimaryKey(user.getLevel());
                if (userLevel ==null){
                    user.setLevelName("数据不存在");
                    user.setBaseSalary(0);
                }else {
                    user.setLevelName(userLevel.getName());
                    user.setBaseSalary(userLevel.getSalary());
                }
               showusers.add(user);
        }
        returnMap.put("rows",showusers);
        map.remove("start");
        map.remove("end");
        returnMap.put("total", userMapper.selectCount(map));
        return returnMap;
    }

    @Override
    public Map addUser(User user) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            User chkUser = userMapper.selectByUsernum(user.getNum());
            if(chkUser!=null){
                returnMap = RequestConstant.getRequestcodeDesc(115);
                return returnMap;
            }
            user.setId(UUID.randomUUID().toString());
            user.setStartTime(DateUtil.getDate());
            user.setUpdateTime(DateUtil.getDate());
            userMapper.insert(user);

            Manager manager = new Manager();
            manager.setId(user.getId());
            manager.setIsUser(1);
            manager.setPassword("123456");
            manager.setRoleid("-1");
            manager.setUsername(user.getNum());
            manager.setUpdatetime(DateUtil.getDate());
            managerMapper.insert(manager);

            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map getUserInfo(String userid) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            User user = userMapper.selectByPrimaryKey(userid);
            if(user==null){
                returnMap = RequestConstant.getRequestcodeDesc(107);
                return returnMap;
            }
            returnMap = RequestConstant.getRequestcodeDesc(0);
            returnMap.put("id", user.getId());
            returnMap.put("num", user.getNum());
            returnMap.put("name", user.getName());
            returnMap.put("sex", user.getSex());
            returnMap.put("edu", user.getEdu());
            returnMap.put("level", user.getLevel());
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map editUser(User user) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            userMapper.updateByPrimaryKeySelective(user);
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map delUser(String userid) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            userMapper.deleteByPrimaryKey(userid);
            managerMapper.deleteByPrimaryKey(userid);
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Result excelIn(String url) {
        Map<String, Object> returnMap = new HashMap<>();
        Result result = new Result();
        try {
            result =  ExcelUtil.ExcelIn(url,userMapper,managerMapper);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(1);
            result.setMsg("无法读取文件！");
        }
        return result;
    }


}
