package com.lokey.student.web.controller;


import com.lokey.student.web.model.Result;
import com.lokey.student.web.model.Salary;
import com.lokey.student.web.service.SalaryService;
import com.lokey.student.web.service.UserSalaryService;
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
 * Created by lokey on 15/9/25.
 */
@Controller
@RequestMapping(value = "admin")
public class ManageSalaryController {

    @Autowired
    private SalaryService salaryService;


    @RequestMapping(value = "salarymanage.html")
    public ModelAndView salarymanage(HttpSession session){
        Map<String, Object> returnMap = new HashMap<>();
        return new ModelAndView("salarymanage", returnMap);
    }

    @ResponseBody
    @RequestMapping(value = "/salarylist.json")
    public Map<String, Object> salarylist(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows,
            @RequestParam(value = "userId", required = false, defaultValue = "") String userId){
        Map<String,Object> querymap = new HashMap<>();
        querymap.put("start", (page-1)*rows);
        querymap.put("end", rows);
        querymap.put("userId",userId);
        Map<String, Object> returnMap = salaryService.getSalaryList(querymap);
        return returnMap;
    }

    @RequestMapping(value = "usersalarymanage.html")
    public ModelAndView usersalarymanage(HttpSession session){
        Map<String, Object> returnMap = new HashMap<>();
        return new ModelAndView("usersalarymanage", returnMap);
    }

    @ResponseBody
    @RequestMapping(value = "/usersalarylist.json")
    public Map<String, Object> usersalarylist(HttpSession session,
                                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                              @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows){
        Map<String,Object> querymap = new HashMap<>();
        querymap.put("start", (page-1)*rows);
        querymap.put("end", rows);
        querymap.put("userId",session.getAttribute("userid"));
        Map<String, Object> returnMap = salaryService.getSalaryList(querymap);
        return returnMap;
    }


    @ResponseBody
    @RequestMapping(value = "/savesalary.json")
    public Map savesalary(@RequestBody Salary salary){
        Map returnMap = new HashMap<>();
        try {
            returnMap = salaryService.addSalary(salary);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnMap;
    }



}
