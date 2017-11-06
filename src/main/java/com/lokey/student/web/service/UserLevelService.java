package com.lokey.student.web.service;




import com.lokey.student.web.model.UserLevel;

import java.util.Map;

/**
 * Created by lokey on 16/7/12.
 */
public interface UserLevelService {

    Map getLevelList(Map map);

    Map addLevel(UserLevel userLevel);

    Map getLevelInfo(String id);

    Map editLevel(UserLevel userLevel);

    Map delLevel(String id);
}
