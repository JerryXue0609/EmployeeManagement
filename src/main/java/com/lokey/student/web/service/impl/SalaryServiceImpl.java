package com.lokey.student.web.service.impl;


import com.lokey.student.web.constant.RequestConstant;
import com.lokey.student.web.mapper.ManagerMapper;
import com.lokey.student.web.mapper.SalaryMapper;
import com.lokey.student.web.mapper.UserLevelMapper;
import com.lokey.student.web.mapper.UserMapper;
import com.lokey.student.web.model.*;
import com.lokey.student.web.service.SalaryService;
import com.lokey.student.web.service.UserService;
import com.lokey.student.web.util.DateUtil;
import com.lokey.student.web.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Lokey on 17/6/30.
 */
@Component(value = "salaryService")
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public Map getSalaryList(Map map) {
        Map<String, Object> returnMap = new HashMap<>();
        List<Salary> salaries = salaryMapper.selectByPage(map);
        returnMap.put("rows",salaries);
        map.remove("start");
        map.remove("end");
        returnMap.put("total", salaryMapper.selectCount(map));
        return returnMap;
    }

    @Override
    public Map addSalary(Salary salary) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            Map requeryMap = new HashMap();
            requeryMap.put("userId",salary.getUserId());
            requeryMap.put("updateTime",DateUtil.getMouth());
            Salary chkSalary = salaryMapper.selectByUserAndDate(requeryMap);
            if(chkSalary!=null){
                returnMap = RequestConstant.getRequestcodeDesc(123);
                return returnMap;
            }
            salary.setId(UUID.randomUUID().toString());
            salary.setUpdateTime(DateUtil.getMouth());
            salaryMapper.insert(salary);

            returnMap = RequestConstant.getRequestcodeDesc(0);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            returnMap = RequestConstant.getRequestcodeDesc(100);
        }
        return returnMap;
    }



}
