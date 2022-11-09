package com.qqls.youxiangousys.pj.admin.controller;

import com.qqls.youxiangousys.pj.admin.service.SysItemService;
import com.qqls.youxiangousys.pj.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class SysItemController {

    @Autowired
    private SysItemService itemService;

    /**
     * 查找所有商品
     * @param itemName
     * @param curPage
     * @param pageSize
     * @return
     */
    public JsonResult findAllItem(String itemName,Integer curPage,Integer pageSize){
        Pagination pageObj = itemService.findAllItem(itemName,curPage,pageSize);
        return new JsonResult(pageObj);
    }

}
