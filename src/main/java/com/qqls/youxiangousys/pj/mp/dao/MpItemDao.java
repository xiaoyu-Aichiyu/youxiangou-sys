package com.qqls.youxiangousys.pj.mp.dao;

import com.qqls.youxiangousys.pj.sys.entity.SysItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MpItemDao {

    /**
     * 查找首页推荐栏的商品
     * @param sellState 推荐状态
     * @return
     */
    List<SysItem> findSellItem(Integer sellState);

    /**
     * 查找猜你喜欢栏的商品
     * @param likeItemType 商品类型的id
     * @return
     */
    List<SysItem> findLikeItem(Integer likeItemType);

    SysItem findItemById(Integer id);
}
