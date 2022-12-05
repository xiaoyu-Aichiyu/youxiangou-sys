package com.qqls.youxiangousys.pj.sys.service;

import com.qqls.youxiangousys.pj.common.entity.Pagination;

public interface SysOrderService {
    /**
     * 根据订单号查订单
     * @param orderNumber
     * @param curPage
     * @param pageSize
     * @param orderState
     * @return
     */
    Pagination findOrder(String orderNumber, Integer curPage, Integer pageSize, Integer orderState);
}
