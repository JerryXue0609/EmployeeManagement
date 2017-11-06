package com.lokey.student.web.service;




import com.lokey.student.web.model.App;

import java.util.Map;

/**
 * Created by lokey on 16/7/12.
 */
public interface UserAppService {

    Map getAppList(Map map);

    Map addApp(App app);

    Map getAppInfo(String id);

    Map editApp(App app);

    Map delApp(String id);
}
