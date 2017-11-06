package com.lokey.student.web.service;

import java.util.Map;

/**
 * Created by lokey on 16/7/12
 */
public interface ModelService {
    Map<String, Object> getUserModel(String roleId);

    Map getUserFunction(String roleId);

    Map getUserTbfunction(String roleId);
}
