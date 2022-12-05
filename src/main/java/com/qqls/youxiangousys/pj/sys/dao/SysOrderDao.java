package com.qqls.youxiangousys.pj.sys.dao;

import com.qqls.youxiangousys.pj.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysOrderDao {
    /**
     *统计订单总条数用来分页
     * @param orderNumber
     * @return
     */
    int countOrder(String orderNumber);

    /**
     * 根据订单号找订单
     * @param orderNumber
     * @param start
     * @param pageSize
     * @param state
     * @return
     */
    List<SysUser> findOrder(
            @Param("orderNumber") String orderNumber,
            @Param("start") int start,
            @Param("pageSize") Integer pageSize,
            @Param("state") Integer state);

}
