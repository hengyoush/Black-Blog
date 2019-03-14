package com.hengyh.blog2.controller;

import com.hengyh.blog2.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/admin/file/upload")
    @ResponseBody
    public String imageUpload(HttpServletRequest request,
                              @RequestParam("editormd-image-file") MultipartFile file) throws JSONException {
        JSONObject res = new JSONObject();
        try{
            String rootDirectory = request.getSession().getServletContext().getRealPath("/");
            Map<String, String> uploadMap =
                    fileUploadService.imageUpload(file.getInputStream(), rootDirectory);
            String code = uploadMap.get("code");
            if("success".equals(code)) {
                res.put("success", 1);
                res.put("message", "上传成功");
                res.put("url", uploadMap.get("url"));
            } else {
                res.put("success", 0);
                res.put("message", "上传失败" + uploadMap.get("error"));
            }
        }  catch (IOException|JSONException e2) {
            res.put("success", 0);
            res.put("message", "上传异常");
        }
        return res.toString();

    }
}
