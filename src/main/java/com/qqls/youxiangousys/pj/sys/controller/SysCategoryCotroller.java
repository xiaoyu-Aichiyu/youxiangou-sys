package com.qqls.youxiangousys.pj.sys.controller;


import com.qqls.youxiangousys.pj.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.sys.service.SysCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categoty")
public class SysCategoryCotroller {

    @Autowired
    private SysCategoryService service;

    @RequestMapping("doFindCategoty")
    public JsonResult doFindCategory(){
        return new JsonResult(service.doFindCategory());
    }

}
