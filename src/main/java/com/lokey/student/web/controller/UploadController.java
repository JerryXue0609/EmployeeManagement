package com.lokey.student.web.controller;

/**
 * Created by Lokey on 2016/7/4.
 */


import com.lokey.student.web.model.Result;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "file")
public class UploadController {
    private final static Logger logger = LoggerFactory.getLogger(UploadController.class);

    /**
     * 日期格式化
     */
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    /**
     * 文件上传接口
     *
     * @param request
     * @param response
     * @param file     上传的文件
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadImage(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("file") MultipartFile file) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        Map returnMap = new HashMap();

        int code = 1;
        System.out.println("file" + file);

        //判断文件是否为空
        if (!file.isEmpty() && file.getSize() / 1024 / 1024 <= 2) {
            String path = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/views/upload";
            if (!new File(path + "/" + dateFormat.format(new Date())).exists()) {
                new File(path + "/" + dateFormat.format(new Date())).mkdirs();
            }
            String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            String fileName = "";//文件存放路径
            if (prefix.equals(".mp3")) {
                if (!new File(path + "/" + dateFormat.format(new Date()) + "/mp3").exists()) {
                    new File(path + "/" + dateFormat.format(new Date()) + "/mp3").mkdirs();
                }
                fileName = "/" + dateFormat.format(new Date()) + "/mp3/" + new Date().getTime() + prefix;
            } else if (prefix.equals(".png") || prefix.equals(".jpg") || prefix.equals(".JPG") || prefix.equals(".gif") || prefix.equals(".jpeg") || prefix.equals(".swf")) {
                if (!new File(path + "/" + dateFormat.format(new Date()) + "/picture").exists()) {
                    new File(path + "/" + dateFormat.format(new Date()) + "/picture").mkdirs();
                }
                fileName = "/" + dateFormat.format(new Date()) + "/picture/" + new Date().getTime() + prefix;
            } else if (prefix.equals(".bmp")) {
                if (!new File(path + "/" + dateFormat.format(new Date()) + "/bmp").exists()) {
                    new File(path + "/" + dateFormat.format(new Date()) + "/bmp").mkdirs();
                }
                fileName = "/" + dateFormat.format(new Date()) + "/bmp/" + new Date().getTime() + prefix;
            } else {
                if (!new File(path + "/" + dateFormat.format(new Date()) + "/other").exists()) {
                    new File(path + "/" + dateFormat.format(new Date()) + "/other").mkdirs();
                }
                fileName = "/" + dateFormat.format(new Date()) + "/other/" + new Date().getTime() + prefix;
            }

            File targetFile = new File(path, fileName);
            logger.debug("上传文件的绝对路径：" + path);
            logger.debug("上传文件的路径：" + fileName);
            logger.debug("上传文件的名字：" + file.getOriginalFilename());
            //保存
            try {
                file.transferTo(targetFile);
                code = 0;
            } catch (Exception e) {
                e.printStackTrace();
            }

            returnMap.put("code", code);

            if (code == 1) {
                returnMap.put("msg", "上传失败");
            } else {
                returnMap.put("msg", "上传成功");
            }
            //  result.setResultData(ConvertFormat.convertUrl(request.getContextPath() + "/upload" + fileName));
            returnMap.put("filePath", "../upload" + fileName);
            outWriter.write(mapper.writeValueAsString(returnMap));
            return;
        } else {
            returnMap.put("msg", "文件为空！");
        }

        returnMap.put("code", code);
        returnMap.put("filePath", "");
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }

    /**
     * excel文件上传接口
     *
     * @param request
     * @param response
     * @param file     上传的文件
     * @throws IOException
     */
    @RequestMapping(value = "excelUpload", method = RequestMethod.POST)
    public void excelUpload(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("file") MultipartFile file) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Result result = new Result();
        int code = 1;

        //判断文件是否为空
        if (!file.isEmpty() && file.getSize() / 1024 / 1024 <= 2) {
            String path = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/views/upload";
            if (!new File(path + "/" + dateFormat.format(new Date())).exists()) {
                new File(path + "/" + dateFormat.format(new Date())).mkdirs();
            }
            String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            String fileName = "";//文件存放路径
            if (prefix.equals(".mp3")) {
                if (!new File(path + "/" + dateFormat.format(new Date()) + "/mp3").exists()) {
                    new File(path + "/" + dateFormat.format(new Date()) + "/mp3").mkdirs();
                }
                fileName = "/" + dateFormat.format(new Date()) + "/mp3/" + new Date().getTime() + prefix;
            } else if (prefix.equals(".png") || prefix.equals(".jpg") || prefix.equals(".JPG") || prefix.equals(".gif") || prefix.equals(".jpeg") || prefix.equals(".swf")) {
                if (!new File(path + "/" + dateFormat.format(new Date()) + "/picture").exists()) {
                    new File(path + "/" + dateFormat.format(new Date()) + "/picture").mkdirs();
                }
                fileName = "/" + dateFormat.format(new Date()) + "/picture/" + new Date().getTime() + prefix;
            } else if (prefix.equals(".xls")) {
                if (!new File(path + "/" + dateFormat.format(new Date()) + "/xls").exists()) {
                    new File(path + "/" + dateFormat.format(new Date()) + "/xls").mkdirs();
                }
                fileName = "/" + dateFormat.format(new Date()) + "/xls/" + new Date().getTime() + prefix;
            } else {
                if (!new File(path + "/" + dateFormat.format(new Date()) + "/other").exists()) {
                    new File(path + "/" + dateFormat.format(new Date()) + "/other").mkdirs();
                }
                fileName = "/" + dateFormat.format(new Date()) + "/other/" + new Date().getTime() + prefix;
            }
            File targetFile = new File(path, fileName);
            logger.debug("上传文件的名字：" + file.getOriginalFilename());
            //保存
            try {
                file.transferTo(targetFile);
                code = 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            result.setCode(code);

            if (code == 1) {
                result.setMsg("上传失败");
            } else {
                result.setMsg("上传成功");
            }
            result.setData(convertUrl(path+fileName));
            outWriter.write(mapper.writeValueAsString(result));
            return;
        } else {
            result.setMsg("文件为空！");
        }

        result.setCode(code);
        if (code == 1) {
            result.setMsg("上传失败");
        }
        result.setData(convertUrl(""));
        outWriter.write(mapper.writeValueAsString(result));
        return;
    }

    //转换url(文件上传的服务器地址)
    private HashMap<String, Object> convertUrl(String url) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("filePath", url);
        return map;
    }
}