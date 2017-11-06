package com.lokey.student.web.service.impl;

import com.lokey.student.web.mapper.FunctionMapper;
import com.lokey.student.web.mapper.ManagerfunctionMapper;
import com.lokey.student.web.mapper.ModelMapper;
import com.lokey.student.web.model.Function;
import com.lokey.student.web.model.Managerfunction;
import com.lokey.student.web.model.Model;
import com.lokey.student.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinhaijin on 15/12/15.
 */
@Component(value = "permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private ManagerfunctionMapper managerfunctionMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FunctionMapper functionMapper;

    @Override
    public Map getRolePermission(String roleid) {
        Map returnMap = new HashMap();
        try {
            List<Managerfunction> managerfunctionList = managerfunctionMapper.selectByRoleId(roleid);
            List<String> managerModelIdList = new ArrayList<>();
            List<String> managerFunctionIdList = new ArrayList<>();
            for(Managerfunction managerfunction : managerfunctionList){
                String modelid = managerfunction.getModelid();
                String functionId = managerfunction.getFunctionid();
                if(!managerModelIdList.contains(modelid)){
                    managerModelIdList.add(modelid);
                }
                if(!managerFunctionIdList.contains(functionId)){
                    managerFunctionIdList.add(functionId);
                }
            }

            List<Model> modelList = modelMapper.selectAll();
            List<Function> functionList = functionMapper.selectAll();
            List<Map> models = new ArrayList<>();
            for (Model model : modelList) {
                String modelid = model.getId();
                if(!managerModelIdList.contains(modelid)){
                    continue;
                }
                Map modelMap = new HashMap();
                modelMap.put("id", model.getSortno());
                modelMap.put("name", model.getName());
                modelMap.put("icon", model.getIcon());

                List<Map> functions = new ArrayList<>();
                for(Function function:functionList){
                    String functionid = function.getId();
                    if(!function.getModelid().equals(modelid)){
                        continue;
                    }
                    if(!managerFunctionIdList.contains(functionid)){
                        continue;
                    }
                    Map functionMap = new HashMap();
                    functionMap.put("id", model.getSortno()*10+function.getSortno());
                    functionMap.put("name", function.getName());
                    functionMap.put("icon", function.getIcon());
                    functionMap.put("url", function.getUrl());
                    functions.add(functionMap);
                }
                modelMap.put("menus",functions);
                models.add(modelMap);
            }

            returnMap.put("code", 0);
            returnMap.put("msg", "");
            returnMap.put("menus", models);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("code", -1);
            returnMap.put("msg", "获取用户权限失败，系统异常！");
        }
        return returnMap;
    }
}
