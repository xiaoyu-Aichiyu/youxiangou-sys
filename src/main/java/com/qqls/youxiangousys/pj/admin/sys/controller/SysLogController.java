package com.qqls.youxiangousys.pj.admin.sys.controller;

import com.qqls.youxiangousys.pj.admin.common.annotation.RequiredLog;
import com.qqls.youxiangousys.pj.admin.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.admin.common.entity.Pagination;
import com.qqls.youxiangousys.pj.admin.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
public class SysLogController {

    @Autowired
    private SysLogService logService;

    /**
     * 查询所有日志+分页
     * @param username
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequiredLog("查询方法")
    @RequestMapping("findAllLogList")
    public JsonResult findAllLogList(String username, Integer curPage, Integer pageSize, Model model){
        Pagination pageObj = logService.findAllItem(username,curPage,pageSize);
        model.addAttribute("list",pageObj);
        return new JsonResult(pageObj);
    }

    /**
     * 根据id删除日志信息
     * @param ids
     * @return
     */
    @RequestMapping("deleteLog")
    public JsonResult deleteLog(@RequestParam("ids[]") Integer[] ids){
        return new JsonResult(logService.deleteLog(ids));
    }
}
