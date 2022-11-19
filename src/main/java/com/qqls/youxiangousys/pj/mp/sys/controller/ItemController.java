package com.qqls.youxiangousys.pj.mp.sys.controller;

import com.qqls.youxiangousys.pj.admin.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.admin.sys.entity.SysItem;
import com.qqls.youxiangousys.pj.mp.sys.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("minProgram/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 查找首页推荐栏的商品
     * @param sellCurPage 当前页
     * @param sellPageSize 每页条数
     * @param sellState 推荐状态
     * @return
     */
    @RequestMapping("findSellItem")
    public JsonResult findSellItem(Integer sellCurPage, Integer sellPageSize, Integer sellState) {
        List<SysItem> list = itemService.findSellItem(sellCurPage, sellPageSize, sellState);
        return new JsonResult(list);
    }

}
