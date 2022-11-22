package com.qqls.youxiangousys.mp.controller;

import com.qqls.youxiangousys.common.entity.JsonResult;
import com.qqls.youxiangousys.common.entity.Pagination;
import com.qqls.youxiangousys.mp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Pagination pageObj = itemService.findSellItem(sellCurPage, sellPageSize, sellState);
        return new JsonResult(pageObj);
    }

    @RequestMapping("findLikeItem")
    public JsonResult findLikeItem(Integer likeCurPage, Integer likePageSize, Integer likeItemType) {
        Pagination pageObj = itemService.findLikeItem(likeCurPage, likePageSize, likeItemType);
        return new JsonResult(pageObj);
    }

}
