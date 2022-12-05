package com.qqls.youxiangousys.pj.sys.service.impl;

import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.common.util.Assert;
import com.qqls.youxiangousys.pj.sys.dao.SysOrderDao;
import com.qqls.youxiangousys.pj.sys.entity.SysUser;
import com.qqls.youxiangousys.pj.sys.service.SysOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysOrderServiceImpl implements SysOrderService {

    @Autowired
    private SysOrderDao orderDao;
    /**
     * 根据订单号查订单
     * @param orderNumber
     * @param curPage
     * @param pageSize
     * @param state
     * @return
     */
    public Pagination findOrder(String orderNumber, Integer curPage, Integer pageSize, Integer state) {
        //验证curPage和pageSize是否有值
        Assert.isEmpty(curPage == null || pageSize == null,"请选择当前页码和每页条数");
        //得到订单总条数
        int count = orderDao.countOrder(orderNumber);
        //创建分页对象，计算出所有属性
        Pagination pageObj = new Pagination(curPage,count,pageSize);
        curPage = pageObj.getCurPage();//得到当前页
        pageSize = pageObj.getPageSize();//得到每页条数
        int start =(curPage - 1) * pageSize;
        List<SysUser> list =orderDao.findOrder(orderNumber,start,pageSize,state);//根据订单号找订单
        Assert.isEmpty(list==null || list.size()==0,"数据已被删除");
        pageObj.setPageData(list);
        return pageObj;
    }
}
