package com.lokey.student.web.service;

import com.lokey.student.web.model.Result;
import com.lokey.student.web.model.User;

import java.util.Map;

/**
 * Created by lokey on 15/12/10.
 */
public interface UserService {
    //µÇÂ¼
    Map login(String username, String password,Integer isUser);

    Map getLevelList();

    Map getUserList(Map map);

    Map addUser(User user);

    Map getUserInfo(String userid);

    Map editUser(User user);

    Map delUser(String userid);

    Result excelIn(String url);

}
