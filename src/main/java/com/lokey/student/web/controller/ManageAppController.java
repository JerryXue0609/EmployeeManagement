package com.lokey.student.web.controller;


import com.lokey.student.web.constant.RequestConstant;
import com.lokey.student.web.model.App;
import com.lokey.student.web.model.UserLevel;
import com.lokey.student.web.service.UserAppService;
import com.lokey.student.web.service.UserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lokey on 17/6/25.
 */
@Controller
@RequestMapping(value = "admin")
public class ManageAppController {

    @Autowired
    private UserAppService userAppService;

    @RequestMapping(value = "userappmanage.html")
    public ModelAndView userappmanage(HttpSession session){
        Map<String, Object> returnMap = new HashMap<>();
        return new ModelAndView("userappmanage", returnMap);
    }

    @RequestMapping(value = "appmanage.html")
    public ModelAndView appmanage(HttpSession session){
        Map<String, Object> returnMap = new HashMap<>();
        return new ModelAndView("appmanage", returnMap);
    }

    @ResponseBody
    @RequestMapping(value = "/userapplist.json")
    public Map<String, Object> userapplist(
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
        Map<String, Object> returnMap = userAppService.getAppList(querymap);
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/applist.json")
    public Map<String, Object> applist(HttpSession session,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows,
            @RequestParam(value = "seachName", required = false, defaultValue = "") String seachName){
        Map<String,Object> querymap = new HashMap<>();
        querymap.put("start", (page-1)*rows);
        querymap.put("end", rows);
        querymap.put("userId",session.getAttribute("userid").toString());
        if (seachName.equals("null")) {
            querymap.put("searchName", null);
        } else {
            querymap.put("searchName", "%" + seachName + "%");
        }
        Map<String, Object> returnMap = userAppService.getAppList(querymap);
        return returnMap;
    }


    @ResponseBody
    @RequestMapping(value = "/saveuserapp.json")
    public Map saveuserapp(@RequestBody App app,HttpSession httpSession){
        Map returnMap = new HashMap<>();
        try {
            if(app!=null){
                if (app.getId()==null||app.getId().equals("")) {
                    app.setUserId(httpSession.getAttribute("userid").toString());
                    returnMap = userAppService.addApp(app);
                    return returnMap;
                }else {
                    returnMap = userAppService.editApp(app);
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
    @RequestMapping(value = "/getuserappinfo.json")
    public Map getuserappinfo(
            @RequestParam(value = "appid", required = false, defaultValue = "") String appid){
        Map returnMap = new HashMap<>();
        try {
            returnMap = userAppService.getAppInfo(appid);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap= RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }


    @ResponseBody
    @RequestMapping(value = "/deluserapp.json")
    public Map deluserapp(
            @RequestParam(value = "id", required = false, defaultValue = "") String id){
        Map returnMap = new HashMap<>();
        try {
            returnMap = userAppService.delApp(id);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap= RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

}
