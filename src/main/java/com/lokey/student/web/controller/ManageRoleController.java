package com.lokey.student.web.controller;


import com.lokey.student.web.constant.RequestConstant;
import com.lokey.student.web.model.Role;
import com.lokey.student.web.service.ModelService;
import com.lokey.student.web.service.RoleService;
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
public class ManageRoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private ModelService modelService;


    @ResponseBody
    @RequestMapping(value = "/getbtfunction.json")
    public Map<String, Object> getBtfunction(HttpSession session){
        Map<String,Object> returnMap = new HashMap<>();
        String roleid = (String)session.getAttribute("roleid");
        //TODO：获取按钮权限
        returnMap = roleService.getManagerButtonFunction(roleid);
        return returnMap;
    }

    /*************************** 管理员管理 *************************/
    @ResponseBody
    @RequestMapping(value = "/getroletbfunction.json")
    public Map<String, Object> getRoleTbFunction(
            @RequestParam(value = "roleid", required = false, defaultValue = "") String roleid){
        Map<String, Object> returnMap = modelService.getUserTbfunction(roleid);
        return returnMap;
    }

    @RequestMapping(value = "rolemanage.html")
    public ModelAndView rolemanage(HttpSession session){
        Map<String, Object> returnMap = new HashMap<>();
        return new ModelAndView("rolemanage", returnMap);
    }

    @ResponseBody
    @RequestMapping(value = "/rolelist.json")
    public Map<String, Object> rolelist(
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
        Map<String, Object> returnMap = roleService.getRoleList(querymap);
        return returnMap;
    }


    @ResponseBody
    @RequestMapping(value = "/saverolefunction.json")
    public Map<String, Object> saveRoleFunction(@RequestBody Map map){
        Map returnMap = new HashMap<>();
        try {
            returnMap = roleService.editManagerFunction(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap= RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/saverole.json")
    public Map saverole(@RequestBody Role role){
        Map returnMap = new HashMap<>();
        try {
            if(role!=null){
                if (role.getId()==null||role.getId().equals("")) {
                    returnMap = roleService.addRole(role);
                    return returnMap;
                }else {
                    returnMap = roleService.editRole(role);
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
    @RequestMapping(value = "/getroleinfo.json")
    public Map getManagerInfo(
            @RequestParam(value = "roleid", required = false, defaultValue = "") String roleid){
        Map returnMap = new HashMap<>();
        try {
            returnMap = roleService.getRoleInfo(roleid);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap= RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }


    @ResponseBody
    @RequestMapping(value = "/delrole.json")
    public Map delrole(
            @RequestParam(value = "id", required = false, defaultValue = "") String id){
        Map returnMap = new HashMap<>();
        try {
            returnMap = roleService.delRole(id);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap= RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }

}
