package com.lokey.student.web.service;




import com.lokey.student.web.model.Role;

import java.util.Map;

/**
 * Created by lokey on 16/7/12.
 */
public interface RoleService {

    Map getRoleList(Map map);

    Map addRole(Role role);

    Map getRoleInfo(String id);

    Map editRole(Role role);

    Map delRole(String id);

    Map getManagerButtonFunction(String roleid);

    Map editManagerFunction(Map map);

}
