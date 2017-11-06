package com.lokey.student.web.controller;

import com.lokey.student.web.model.ResponseModel;
import com.lokey.student.web.service.PermissionService;
import com.lokey.student.web.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lokey on 15/12/4.
 */
@Controller
@RequestMapping(value = "admin")
public class BaseController {

    @Autowired
    private HashMap userStatus;
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value = "login.html")
    public ModelAndView AdminLogin(HttpSession session){
        Map<String, Object> returnMap = new HashMap<>();
        return new ModelAndView("login", returnMap);
    }


    @RequestMapping(value = "index.html")
    public ModelAndView AdminIndex(HttpSession session){
        Map<String, Object> returnMap = new HashMap<>();
        return new ModelAndView("index", returnMap);
    }

    @RequestMapping(value = "logout.html")
    public ModelAndView AdminLogout(HttpSession session){
        Map<String, Object> returnMap = new HashMap<>();
        session.removeAttribute("username");
        return new ModelAndView("login", returnMap);
    }


    @ResponseBody
    @RequestMapping(value = "/test.action")
    public void test(HttpServletResponse response, String phone) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        ResponseModel responseModel = new ResponseModel();
        Map userMap = new HashMap();
        userMap.put("phone", phone);
        userMap.put("chktime", new Date());
        userStatus.put(phone,userMap);
        responseModel.setResult(userStatus);
        outWriter.write(mapper.writeValueAsString(responseModel));
        return;
    }

    @ResponseBody
    @RequestMapping(value = "/login.json")
    public Map<String, Object> managerLogin(HttpSession session,
                                            @RequestParam(value = "username", required = false, defaultValue = "") String username,
                                            @RequestParam(value = "indent", required = false, defaultValue = "") String indent,
                                            @RequestParam(value = "password", required = false, defaultValue = "") String password){
       Map<String,Object> returnMap = new HashedMap();
        if (StringUtils.isEmpty(indent)){
            return returnMap;
        }

        returnMap = userService.login(username, password,Integer.valueOf(indent));
        Integer code = Integer.valueOf(returnMap.get("code").toString());
        if (code == 0) {
           session.setAttribute("username", username);
           session.setAttribute("userid", returnMap.get("userid"));
           session.setAttribute("roleid", returnMap.get("roleid"));
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "menu.json")
    public void AdminMenu(HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        //TODO:通过session获取管理员的id
        String roleid = (String) session.getAttribute("roleid");
        if(roleid.equals("")||roleid==null){
            try {
                response.sendRedirect("/admin/login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map returnMap = permissionService.getRolePermission(roleid);
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }
}
