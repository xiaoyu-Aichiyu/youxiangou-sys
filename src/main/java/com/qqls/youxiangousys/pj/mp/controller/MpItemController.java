package com.qqls.youxiangousys.pj.mp.controller;

import com.qqls.youxiangousys.pj.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.mp.service.MpItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("minProgram/item")
public class MpItemController {

    @Autowired
    private MpItemService mpItemService;

    /**
     * 查找首页推荐栏的商品
     * @param sellCurPage 当前页
     * @param sellPageSize 每页条数
     * @param sellState 推荐状态
     * @return
     */
    @RequestMapping("findSellItem")
    public JsonResult findSellItem(Integer sellCurPage, Integer sellPageSize, Integer sellState) {
        Pagination pageObj = mpItemService.findSellItem(sellCurPage, sellPageSize, sellState);
        return new JsonResult(pageObj);
    }

    /**
     * 查找喜欢栏的商品
     * @param likeCurPage 当前页
     * @param likePageSize 每页条数
     * @param likeItemType 商品类型id
     * @return
     */
    @RequestMapping("findLikeItem")
    public JsonResult findLikeItem(Integer likeCurPage, Integer likePageSize, Integer likeItemType) {
        Pagination pageObj = mpItemService.findLikeItem(likeCurPage, likePageSize, likeItemType);
        return new JsonResult(pageObj);
    }

    /**
     * 通过id查商品
     * @param id
     * @return
     */
    @RequestMapping("findItemById")
    public JsonResult findItemById (Integer id) {
        return new JsonResult(mpItemService.findItemById(id));
    }

}
