package com.lokey.student.web.service.impl;


import com.lokey.student.web.constant.RequestConstant;
import com.lokey.student.web.mapper.*;
import com.lokey.student.web.model.*;
import com.lokey.student.web.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinhaijin on 15/9/8.
 */
@Component(value = "modelService")
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FunctionMapper functionMapper;

    @Autowired
    private BtfunctionMapper btfunctionMapper;

    @Autowired
    private ManagerfunctionMapper managerfunctionMapper;

    @Override
    public Map<String, Object> getUserModel(String roleId) {
        Map returnMap = new HashMap();
        try {
            List<Managerfunction> userfunctionList = managerfunctionMapper.selectByRoleId(roleId);

            List<String> userModelIdList = new ArrayList<>();

            List<String> userFunctionIdList = new ArrayList<>();

            for(Managerfunction userfunction : userfunctionList){
                String modelid = userfunction.getModelid();
                String functionId = userfunction.getFunctionid();
                if(!userModelIdList.contains(modelid)){
                    userModelIdList.add(modelid);
                }

                if(!userFunctionIdList.contains(functionId)){
                    userFunctionIdList.add(functionId);
                }
            }

            List<Model> modelList = modelMapper.selectAll();
            List<Function> functionList = functionMapper.selectAll();
            List<Map> models = new ArrayList<>();
            for (Model model : modelList) {
                String modelid = model.getId();
                if(!userModelIdList.contains(modelid)){
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
                    if(!userFunctionIdList.contains(functionid)){
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
            returnMap.put("menus", models);
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map getUserFunction(String roleId) {
        Map returnMap = new HashMap();
        try {
            List<Model> modelList = modelMapper.selectAll();
            Map reqUserFunctionMap = new HashMap();
            reqUserFunctionMap.put("roleid", roleId);
            /** 获取用户权限列表 **/
            List<Function> userFunctionList = functionMapper.selectByMap(reqUserFunctionMap);
            List<String> userModelIdList = new ArrayList<>();
            List<String> userFunctionIdList = new ArrayList<>();
            for(Function userFunction : userFunctionList){
                if(!userModelIdList.contains(userFunction.getModelid())){
                    userModelIdList.add(userFunction.getModelid());
                }

                if(!userFunctionIdList.contains(userFunction.getId())){
                    userFunctionIdList.add(userFunction.getId());
                }
            }
            List<Map> modelMapList = new ArrayList<>();
            for(Model model : modelList){
                Map modelMap = new HashMap();
                modelMap.put("id", model.getId());
                modelMap.put("text", model.getName());
                if(userModelIdList.contains(model.getId())){
                    modelMap.put("checked", true);
                }else{
                    modelMap.put("checked", false);
                }

                List<Function> functionList = functionMapper.selectByModelId(model.getId());
                List<Map> functionMapList = new ArrayList<>();
                for(Function function : functionList){
                    Map functionMap = new HashMap();
                    functionMap.put("id", function.getId());
                    functionMap.put("text", function.getName());
                    if(userFunctionIdList.contains(function.getId())){
                        functionMap.put("checked", true);
                    }else{
                        functionMap.put("checked", false);
                    }
                    functionMapList.add(functionMap);
                }
                modelMap.put("children", functionMapList);
                modelMapList.add(modelMap);
            }
            returnMap.put("data", modelMapList);
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @Override
    public Map getUserTbfunction(String roleid) {
        Map returnMap = new HashMap();
        try {
            List<Managerfunction> userfunctionList = managerfunctionMapper.selectByRoleId(roleid);
            List<String> userBtfunctionidIdList = new ArrayList<>();

            for(Managerfunction userfunction : userfunctionList){
                String btfunctionid = userfunction.getBtfunctionid();

                /** 如果用户按钮ID列表中不包含按钮ID则添加到列表中 **/
                if(!userBtfunctionidIdList.contains(btfunctionid)){
                    userBtfunctionidIdList.add(btfunctionid);
                }

            }

            List<Map> modelMapList = new ArrayList<>();

            List<Model> modelList = modelMapper.selectAll();

            for(Model model : modelList) {

                Map modelMap = new HashMap();
                modelMap.put("id", model.getId());
                modelMap.put("text", model.getName());

                List<Function> functionList = functionMapper.selectByModelId(model.getId());
                List<Map> functionMapList = new ArrayList<>();

                for (Function function : functionList) {
                    Map functionMap = new HashMap();
                    functionMap.put("id", function.getId());
                    functionMap.put("text", function.getName());

                    List<Btfunction> btfunctionList = btfunctionMapper.selectByFunctionId(function.getId());
                    List<Map> btFunctionMapList = new ArrayList<>();
                    for(Btfunction btfunction : btfunctionList){
                        Map btFunctionMap = new HashMap();
                        btFunctionMap.put("id", btfunction.getId());
                        btFunctionMap.put("text", btfunction.getName());
                        if(userBtfunctionidIdList.contains(btfunction.getId())){
                            btFunctionMap.put("checked", true);
                        }else{
                            btFunctionMap.put("checked", false);
                        }
                        btFunctionMapList.add(btFunctionMap);
                    }

                    functionMap.put("children", btFunctionMapList);
                    functionMap.put("state", "closed");
                    functionMapList.add(functionMap);
                }
                modelMap.put("children", functionMapList);
                modelMap.put("state", "closed");

                modelMapList.add(modelMap);
            }
            returnMap = RequestConstant.getRequestcodeDesc(0);
            returnMap.put("data", modelMapList);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }
}
