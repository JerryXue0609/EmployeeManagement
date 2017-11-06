package com.lokey.student.web.controller;


import com.lokey.student.web.constant.RequestConstant;
import com.lokey.student.web.model.Manager;
import com.lokey.student.web.model.Result;
import com.lokey.student.web.model.User;
import com.lokey.student.web.service.ManagerService;
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
public class ManageUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "usermanage.html")
    public ModelAndView managerManage(HttpSession session){
        Map<String, Object> returnMap = new HashMap<>();
        returnMap = userService.getLevelList();
        return new ModelAndView("usermanage", returnMap);
    }

    @ResponseBody
    @RequestMapping(value = "/userlist.json")
    public Map<String, Object> userlist(
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
        Map<String, Object> returnMap = userService.getUserList(querymap);
        return returnMap;
    }


    @ResponseBody
    @RequestMapping(value = "/saveuser.json")
    public Map saveUser(@RequestBody User user){
        Map returnMap = new HashMap<>();
        try {
            if(user!=null){
                if (user.getId()==null||user.getId().equals("")) {
                    returnMap = userService.addUser(user);
                    return returnMap;
                }else {
                    returnMap = userService.editUser(user);
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
    @RequestMapping(value = "/getuserinfo.json")
    public Map getUserInfo(
            @RequestParam(value = "userid", required = false, defaultValue = "") String userid){
        Map returnMap = new HashMap<>();
        try {
            returnMap = userService.getUserInfo(userid);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap= RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }


    @ResponseBody
      @RequestMapping(value = "/deluser.json")
    public Map delUser(@RequestParam(value = "id", required = false, defaultValue = "") String id){
        Map returnMap = new HashMap<>();
        try {
            returnMap = userService.delUser(id);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap= RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/exexlIn.json")
    public Result exexlIn(
            @RequestParam(value = "url", required = false, defaultValue = "") String url){
        Result result = new Result();
        try {
            result = userService.excelIn(url);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(1);
        }
        return result;
    }

}
