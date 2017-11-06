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
                returnMap.put("msg", "����ɹ�!");
                break;
            case 1:
                returnMap.put("msg", "���ݲ�����!");
                break;
            case 100:
                returnMap.put("msg", "ϵͳ�ڲ�����!");
                break;
            case 101:
                returnMap.put("msg", "�����γ����ʧ��!");
                break;
            case 111:
                returnMap.put("msg", "�û��Ѵ���!");
                break;
            case 115:
                returnMap.put("msg", "Ա�����Ѵ���!");
                break;
            case 112:
                returnMap.put("msg", "��½״̬����!");
                break;
            case 113:
                returnMap.put("msg", "�û��������!");
                break;
            case 121:
                returnMap.put("msg", "������ɫ����ɾ��!");
                break;
            case 123:
                returnMap.put("msg", "���¹��ʼ�¼�Ѵ���!");
                break;
            default:
                break;
        }
        return returnMap;
    }
}
