package com.lokey.student.web.util;

import java.util.Random;

/**
 * �����������
 * Created by yinhaijin on 15/4/30.
 */
public class VcodeUtil {

    /**
     * ���漴��
     * @return
     */
    public static String generateWord() {
        Random random =new Random();
        String result = "";
        result += (int)(Math.random()*9+1);
        for(int i = 0; i < 5; i++){
            result += (int)(Math.random()*10);
        }
        return result;
    }
}
