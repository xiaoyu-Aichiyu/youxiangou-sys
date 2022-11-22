package com.qqls.youxiangousys.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageUIController {

    @RequestMapping("indexUI")
    public String indexUI() {
        return "index";
    }

    @RequestMapping("loginUI")
    public String loginUI() {
        return "login";
    }

    @RequestMapping("{module}/{page}")
    public String moduleUI(@PathVariable("module") String module, @PathVariable("page") String page) {
        //System.out.println("module = " + module + ", page = " + page);
        return module + "/" + page;
    }

}
