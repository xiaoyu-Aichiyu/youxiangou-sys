package com.qqls.youxiangousys.mp.dao;

import com.qqls.youxiangousys.sys.entity.SysItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemDao {

    /**
     * 查找首页推荐栏的商品
     * @param sellState 推荐状态
     * @return
     */
    List<SysItem> findSellItem(@Param("sellState") Integer sellState);

    /**
     * 查找猜你喜欢栏的商品
     * @param likeItemType
     * @return
     */
    List<SysItem> findLikeItem(Integer likeItemType);
}
