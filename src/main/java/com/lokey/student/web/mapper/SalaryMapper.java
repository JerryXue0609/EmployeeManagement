package com.lokey.student.web.mapper;


import com.lokey.student.web.model.Manager;
import com.lokey.student.web.model.Salary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "salaryMapper")
public interface SalaryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Salary salary);

    Salary selectByPrimaryKey(String id);

    int updateByPrimaryKey(Salary salary);

    List<Salary> selectByPage(Map map);

    Salary selectByUserAndDate(Map map);

    Integer selectCount(Map map);


}