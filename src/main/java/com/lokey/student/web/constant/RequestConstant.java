package com.lokey.student.web.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yinhaijin on 15/9/9.
 */
public class RequestConstant {

    public final static Map getRequestcodeDesc(Integer requestCode) {
        Map returnMap = new HashMap();
        returnMap.put("code", requestCode);
        switch (requestCode) {
            case 0:
                returnMap.put("msg", "请求成功!");
                break;
            case 1:
                returnMap.put("msg", "数据不存在!");
                break;
            case 100:
                returnMap.put("msg", "系统内部错误!");
                break;
            case 101:
                returnMap.put("msg", "新增课程类别失败!");
                break;
            case 111:
                returnMap.put("msg", "用户已存在!");
                break;
            case 115:
                returnMap.put("msg", "员工号已存在!");
                break;
            case 112:
                returnMap.put("msg", "登陆状态过期!");
                break;
            case 113:
                returnMap.put("msg", "用户密码错误!");
                break;
            case 121:
                returnMap.put("msg", "基础角色不可删除!");
                break;
            case 123:
                returnMap.put("msg", "本月工资记录已存在!");
                break;
            default:
                break;
        }
        return returnMap;
    }
}
