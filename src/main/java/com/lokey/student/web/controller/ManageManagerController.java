package com.lokey.student.web.controller;


import com.lokey.student.web.constant.RequestConstant;
import com.lokey.student.web.model.Manager;
import com.lokey.student.web.service.ManagerService;
import com.lokey.student.web.service.ModelService;
import com.lokey.student.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lokey on 15/9/25.
 */
@Controller
@RequestMapping(value = "admin")
public class ManageManagerController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "managermanage.html")
    public ModelAndView managerManage(HttpSession session){
        Map<String, Object> returnMap = new HashMap<>();
        returnMap = managerService.getRoleList();
        return new ModelAndView("managermanage", returnMap);
    }

    @ResponseBody
    @RequestMapping(value = "/managerlist.json")
    public Map<String, Object> managerList(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows,
            @RequestParam(value = "seachName", required = false, defaultValue = "") String seachName){
        Map<String,Object> querymap = new HashMap<>();
        querymap.put("start", (page-1)*rows);
        querymap.put("end", rows);
        if (seachName.equals("null")) {
            querymap.put("searchName", null);
        } else {
            querymap.put("searchName", "%" + seachName + "%");
        }
        Map<String, Object> returnMap = managerService.getManagerList(querymap);
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/changePassword.json")
    public Map<String, Object> changePassword(HttpSession session, HttpServletResponse response,
            @RequestParam(value = "oldpassword", required = false, defaultValue = "") String oldpassword,
            @RequestParam(value = "password", required = false, defaultValue = "") String password){
        String managerId = (String)session.getAttribute("userid");
        if(managerId.equals("")||managerId==null){
            try {
                response.sendRedirect("/admin/login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> returnMap = managerService.changePassword(managerId,password,oldpassword);
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/savemanager.json")
    public Map saveManager(@RequestBody Manager manager){
        Map returnMap = new HashMap<>();
        try {
            if(manager!=null){
                if (manager.getId()==null||manager.getId().equals("")) {
                    returnMap = managerService.addManager(manager);
                    return returnMap;
                }else {
                    returnMap = managerService.editManager(manager);
                    return returnMap;
                }
            }
            returnMap= RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap= RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/getmanagerinfo.json")
    public Map getManagerInfo(
            @RequestParam(value = "userid", required = false, defaultValue = "") String userid){
        Map returnMap = new HashMap<>();
        try {
            returnMap = managerService.getManagerInfo(userid);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap= RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }


    @ResponseBody
    @RequestMapping(value = "/delmanager.json")
    public Map delManager(
            @RequestParam(value = "id", required = false, defaultValue = "") String id){
        Map returnMap = new HashMap<>();
        try {
            returnMap = managerService.delManager(id);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap= RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

}
