package com.lokey.student.web.service;

import com.lokey.student.web.model.Result;
import com.lokey.student.web.model.Salary;
import com.lokey.student.web.model.User;

import java.util.Map;

/**
 * Created by Jerry on 15/12/10.
 */
public interface SalaryService {

    Map getSalaryList(Map map);

    Map addSalary(Salary salary);


}
