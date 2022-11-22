package com.qqls.youxiangousys.pj.sys.controller;

import com.qqls.youxiangousys.pj.common.util.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping("upload")
    public String upload(MultipartFile file, String test) {
        String uploadPath = FileUtils.uploadImg(file);
        return uploadPath;
    }

}
