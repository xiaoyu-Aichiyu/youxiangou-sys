package com.qqls.youxiangousys.pj.mp.sys.service;

import com.qqls.youxiangousys.pj.admin.common.entity.Pagination;
import com.qqls.youxiangousys.pj.admin.sys.entity.SysItem;

import java.util.List;

public interface ItemService {


    /**
     * 查找首页推荐栏的商品
     * @param sellCurPage 当前页
     * @param sellPageSize 每页条数
     * @param sellState 推荐状态
     * @return
     */
    Pagination findSellItem(Integer sellCurPage, Integer sellPageSize, Integer sellState);

    /**
     * 查找首页猜你喜欢栏的商品
     * @param likeCurPage
     * @param likePageSize
     * @param likeItemType
     * @return
     */
    Pagination findLikeItem(Integer likeCurPage, Integer likePageSize, Integer likeItemType);
}
