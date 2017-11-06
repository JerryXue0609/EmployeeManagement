package com.lokey.student.web.controller;


import com.lokey.student.web.constant.RequestConstant;
import com.lokey.student.web.model.Role;
import com.lokey.student.web.model.UserLevel;
import com.lokey.student.web.service.ModelService;
import com.lokey.student.web.service.RoleService;
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
public class ManageUserLevelController {

    @Autowired
    private UserLevelService userLevelService;

    @RequestMapping(value = "userlevelmanage.html")
    public ModelAndView userlevelmanage(HttpSession session){
        Map<String, Object> returnMap = new HashMap<>();
        return new ModelAndView("userlevelmanage", returnMap);
    }

    @ResponseBody
    @RequestMapping(value = "/userlevellist.json")
    public Map<String, Object> userlevellist(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows,
            @RequestParam(value = "seachName", required = false, defaultValue = "") String seachName){
        Map<String,Object> querymap = new HashMap<>();
        querymap.put("start", (page-1)*rows);
        querymap.put("end", rows);
        querymap.put("deleteFlag",0);
        if (seachName.equals("null")) {
            querymap.put("searchName", null);
        } else {
            querymap.put("searchName", "%" + seachName + "%");
        }
        Map<String, Object> returnMap = userLevelService.getLevelList(querymap);
        return returnMap;
    }


    @ResponseBody
    @RequestMapping(value = "/saveuserlevel.json")
    public Map saveuserlevel(@RequestBody UserLevel userLevel){
        Map returnMap = new HashMap<>();
        try {
            if(userLevel!=null){
                if (userLevel.getId()==null||userLevel.getId().equals("")) {
                    returnMap = userLevelService.addLevel(userLevel);
                    return returnMap;
                }else {
                    returnMap = userLevelService.editLevel(userLevel);
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
    @RequestMapping(value = "/getuserlevelinfo.json")
    public Map getUserLevelInfo(
            @RequestParam(value = "levelid", required = false, defaultValue = "") String levelid){
        Map returnMap = new HashMap<>();
        try {
            returnMap = userLevelService.getLevelInfo(levelid);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap= RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }


    @ResponseBody
    @RequestMapping(value = "/deluserlevel.json")
    public Map deluserlevel(
            @RequestParam(value = "id", required = false, defaultValue = "") String id){
        Map returnMap = new HashMap<>();
        try {
            returnMap = userLevelService.delLevel(id);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap= RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

}
