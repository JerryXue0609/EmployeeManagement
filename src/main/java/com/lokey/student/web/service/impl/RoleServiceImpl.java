package com.lokey.student.web.service.impl;


import com.lokey.student.web.constant.RequestConstant;
import com.lokey.student.web.mapper.*;
import com.lokey.student.web.model.Btfunction;
import com.lokey.student.web.model.Managerfunction;
import com.lokey.student.web.model.Role;
import com.lokey.student.web.service.RoleService;
import com.lokey.student.web.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by yinhaijin on 15/9/12.
 */

@Component(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private BtfunctionMapper btfunctionMapper;

    @Autowired
    private ManagerfunctionMapper managerfunctionMapper;


    @Override
    public Map getRoleList(Map map) {
        Map<String, Object> returnMap = new HashMap<>();
        List<Role> roles = roleMapper.selectByPage(map);
        returnMap.put("rows",roles);
        map.remove("start");
        map.remove("end");
        returnMap.put("total", roleMapper.selectCount(map));
        return returnMap;
    }

    @Override
    public Map addRole(Role role) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            role.setDeleteFlag(0);
            role.setId(UUID.randomUUID().toString());
            role.setUpdateTime(DateUtil.getNowDateTime());
            roleMapper.insert(role);
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map getRoleInfo(String id) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            Role role = roleMapper.selectByPrimaryKey(id);
            if(role==null){
                returnMap = RequestConstant.getRequestcodeDesc(107);
                return returnMap;
            }
            returnMap = RequestConstant.getRequestcodeDesc(0);
            returnMap.put("id", role.getId());
            returnMap.put("isEnabled",role.getIsEnabled());
            returnMap.put("name", role.getName());
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map editRole(Role role) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            roleMapper.updateByPrimaryKeySelective(role);
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }



    @Override
    public Map delRole(String id) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            Role role = roleMapper.selectByPrimaryKey(id);
            if (role.getId().equals("-1")){
                returnMap = RequestConstant.getRequestcodeDesc(121);
                return returnMap;
            }
            if (role!=null){
                role.setDeleteFlag(1);
                roleMapper.updateByPrimaryKey(role);
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
    public Map getManagerButtonFunction(String roleid) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            List<Managerfunction> userfunctionList = managerfunctionMapper.selectByRoleId(roleid);
            List<String> userBtfunctionIdList = new ArrayList<>();
            for(Managerfunction userfunction : userfunctionList){
                userBtfunctionIdList.add(userfunction.getBtfunctionid());
            }
            returnMap = RequestConstant.getRequestcodeDesc(0);
            returnMap.put("data", userBtfunctionIdList);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    /**
     * 修改用户权限
     * @param map
     * @return
     */
    @Override
    public Map editManagerFunction(Map map) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            String roleid = (String)map.get("roleid");
            List<Managerfunction> userfunctionList = managerfunctionMapper.selectByRoleId(roleid);//此用户现在已经开通的权限
            List<Btfunction> btfunctionList = btfunctionMapper.selectAll();//所有的按钮
            List<String> btFunctionidList=new ArrayList<>();
            List<String> userBtFunctionidList=new ArrayList<>();
            List<Map> saveFunctionMapList = (List<Map>)map.get("data"); //将要保存的权限
            List<String> saveFunctionidList=new ArrayList<>();
            for (Map saveFunctionMap : saveFunctionMapList){
                saveFunctionidList.add((String)saveFunctionMap.get("id"));  //将要保存的权限放在一个权限list里
            }
            for (Managerfunction userfunction : userfunctionList){
                userBtFunctionidList.add(userfunction.getBtfunctionid());    //之前的权限放在一个权限list里
            }

            for (Btfunction btfunction : btfunctionList){
                String btfunctionId = btfunction.getId();
                if(saveFunctionidList.contains(btfunctionId)){      //将要开通的权限按钮是否存在所有的权限按钮中
                    if(!userBtFunctionidList.contains(btfunctionId)){      //之前的权限按钮是否存有了，有则不插入，没有则插入一个新的权限记录
                        Managerfunction userfunction = new Managerfunction();
                        userfunction.setId(UUID.randomUUID().toString());
                        userfunction.setRoleid(roleid);
                        userfunction.setModelid(btfunction.getModelid());
                        userfunction.setFunctionid(btfunction.getFunctionid());
                        userfunction.setBtfunctionid(btfunctionId);
                        managerfunctionMapper.insert(userfunction);
                    }
                }else {
                    if(userBtFunctionidList.contains(btfunctionId)){  //删除不需要保存的权限
                        Map delUserBtFunctionMap = new HashMap();
                        delUserBtFunctionMap.put("roleid", roleid);
                        delUserBtFunctionMap.put("btfunctionid",btfunctionId);
                        managerfunctionMapper.deleteByRoleBtfunctionid(delUserBtFunctionMap);
                    }
                }
            }
            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }



}
