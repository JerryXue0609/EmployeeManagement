package com.lokey.student.web.service;




import com.lokey.student.web.model.Manager;

import java.util.Map;

/**
 * Created by lokey on 16/7/12.
 */
public interface ManagerService {

    Map getRoleList();

    Map getManagerList(Map map);

    Map addManager(Manager manager);

    Map getManagerInfo(String userid);

    Map editManager(Manager manager);

    Map changePassword(String userid, String password, String oldPassword);

    Map delManager(String userid);
}
