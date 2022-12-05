package com.qqls.youxiangousys.pj.sys.controller;

import com.qqls.youxiangousys.pj.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.sys.service.SysOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单明细
 */
@RequestMapping("order")
@RestController
public class SysOrderController {

    @Autowired
    private SysOrderService orderService;

    /**
     * 根据订单号查订单
     * @param orderNumber
     * @param curPage
     * @param pageSize
     * @param state
     * @return
     */
    @RequestMapping("findOrder")
    public JsonResult findOrder(String orderNumber,Integer curPage,Integer pageSize,Integer state){
        Pagination pagination = orderService.findOrder(orderNumber,curPage,pageSize,state);
        return new JsonResult(pagination);
    }
}
