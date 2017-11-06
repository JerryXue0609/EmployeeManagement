package com.lokey.student.web.util;

import com.alibaba.druid.util.StringUtils;
import com.lokey.student.web.constant.RequestConstant;
import com.lokey.student.web.mapper.ManagerMapper;
import com.lokey.student.web.mapper.UserMapper;
import com.lokey.student.web.model.Manager;
import com.lokey.student.web.model.Result;
import com.lokey.student.web.model.User;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExcelUtil {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public static Result ExcelIn(String url, UserMapper userMapper, ManagerMapper managerMapper){
        Result result = new Result();
        List<User> userList = new ArrayList<>();
        result.setCode(1);
        int i = 1;  //����
        Sheet sheet;
        Workbook book;
        try {
            book = Workbook.getWorkbook(new File(url));
            sheet = book.getSheet(0);
            //��ȡ���Ͻǵĵ�Ԫ��
            i = 0;
            while (true) {
                //��ȡÿһ�еĵ�Ԫ��
                Cell cell1 = null;
                Cell cell2 = null;
                Cell cell3 = null;
                Cell cell4 = null;
                try {
                    cell1 = sheet.getCell(0, i);//���У��У�
                    cell2 = sheet.getCell(1, i);//���У��У�
                    cell3 = sheet.getCell(2, i);//���У��У�
                    cell4 = sheet.getCell(3, i);//���У��У�
                    System.out.println(cell1.getContents());
                    System.out.println(cell2.getContents());
                    System.out.println(cell3.getContents());
                    System.out.println(cell4.getContents());
                    if (StringUtils.isEmpty(cell1.getContents())){
                        result.setMsg("һ��"+ i +"������");
                        break;
                    }
                }catch (Exception e) {
                    System.out.println("have " + i + " rows");
                    break;
                }
                String num = cell1.getContents();
                String name = cell2.getContents();
                String level = cell3.getContents();
                String startTime = cell4.getContents();
                if(!StringUtils.isEmpty(num)&&!StringUtils.isEmpty(name)
                        &&!StringUtils.isEmpty(level)&&!StringUtils.isEmpty(startTime)){
                       User user1 = new User();
                       user1.setId(UUID.randomUUID().toString());
                       user1.setNum(num);
                       user1.setName(name);
                       user1.setLevel(level);
                       user1.setStartTime(startTime);
                       userMapper.insert(user1);
                        userList.add(user1);
                    Manager manager = new Manager();
                    manager.setId(user1.getId());
                    manager.setIsUser(1);
                    manager.setPassword("123456");
                    manager.setRoleid("-1");
                    manager.setUsername(user1.getNum());
                    manager.setUpdatetime(DateUtil.getDate());
                    managerMapper.insert(manager);
                        i++;
                    }else {
                        result.setMsg("��"+ i +"��ݔ�����`");
                        return result;
                    }
                }

            book.close();
            result.setCode(0);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(1);
        }

        if(result.getCode()==0){
            for(User user:userList){
                User checkUser = userMapper.selectByUsernum(user.getNum());
                if(checkUser!=null){
                    result.setCode(1);
                    result.setMsg("����ɹ���");
                    return result;
                }
                userMapper.insert(user);
            }
        }
        return result;
    }


}