package com.qqls.youxiangousys.pj.mp.sys.dao;

import com.qqls.youxiangousys.pj.admin.sys.entity.SysItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemDao {

    /**
     * 查找首页推荐栏的商品
     * @param sellCurPage 当前页
     * @param sellPageSize 每页条数
     * @param sellState 推荐状态
     * @return
     */
    List<SysItem> findSellItem(
            @Param("sellCurPage") Integer sellCurPage, Integer sellPageSize, Integer sellState);

}
