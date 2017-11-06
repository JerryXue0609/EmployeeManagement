package com.lokey.student.web.service.impl;


import com.lokey.student.web.constant.RequestConstant;
import com.lokey.student.web.mapper.*;
import com.lokey.student.web.model.Btfunction;
import com.lokey.student.web.model.Manager;
import com.lokey.student.web.model.Managerfunction;
import com.lokey.student.web.model.Role;
import com.lokey.student.web.service.ManagerService;
import com.lokey.student.web.util.DateUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by lokey on 15/9/12.
 */

@Component(value = "managerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ManagerfunctionMapper managerfunctionMapper;


    @Override
    public Map getRoleList() {
        Map<String, Object> returnMap = new HashMap<>();
        Map map = new HashMap();
        map.put("isEnabled","0");
        map.put("deleteFlag","0");
        List<Role> roleList = roleMapper.selectByPage(map);
        returnMap.put("roleList",roleList);
        return returnMap;
    }

    @Override
    public Map getManagerList(Map map) {
        Map<String, Object> returnMap = new HashMap<>();
        List<Manager> managers = managerMapper.selectByPage(map);
        List<Manager> showmanagers = new ArrayList<>();
        for(Manager manager : managers){
            if(!manager.getId().equals("0")){
                Role role = roleMapper.selectByPrimaryKey(manager.getRoleid());
                if (role ==null){
                    manager.setRole("数据不存在");
                }else {
                    manager.setRole(role.getName());
                }
                showmanagers.add(manager);
            }
        }
        returnMap.put("rows",showmanagers);
        map.remove("start");
        map.remove("end");
        returnMap.put("total", managerMapper.selectCount(map));
        return returnMap;
    }

    @Override
    public Map addManager(Manager manager) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            Manager chkManager = managerMapper.selectByManagename(manager.getUsername());
            if(chkManager!=null){
                returnMap = RequestConstant.getRequestcodeDesc(111);
                return returnMap;
            }
            manager.setIsUser(0);
            manager.setId(UUID.randomUUID().toString());
            manager.setUpdatetime(DateUtil.getDate());
            managerMapper.insertSelective(manager);
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map getManagerInfo(String userid) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            Manager manager = managerMapper.selectByPrimaryKey(userid);
            if(manager==null){
                returnMap = RequestConstant.getRequestcodeDesc(107);
                return returnMap;
            }
            returnMap = RequestConstant.getRequestcodeDesc(0);
            returnMap.put("id", manager.getId());
            returnMap.put("username", manager.getUsername());
            returnMap.put("password", manager.getPassword());
            returnMap.put("roleid", manager.getRoleid());
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map editManager(Manager manager) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            managerMapper.updateByPrimaryKeySelective(manager);
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }


    @Override
    public Map changePassword(String userid, String password,String oldPassword) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            if (userid.equals("")) {
                returnMap = RequestConstant.getRequestcodeDesc(112);
                return returnMap;
            }
            Manager manager = managerMapper.selectByPrimaryKey(userid);
            if(manager.getPassword().equals(oldPassword)){
                manager.setPassword(password);
                managerMapper.updateByPrimaryKey(manager);
            }else {
                returnMap = RequestConstant.getRequestcodeDesc(113);
                return returnMap;
            }
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map delManager(String userid) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            if (userid.equals("0")) {
                returnMap = RequestConstant.getRequestcodeDesc(112);
                return returnMap;
            }
            managerfunctionMapper.deleteByRoleId(userid);
            managerMapper.deleteByPrimaryKey(userid);
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }


}
