package com.qqls.youxiangousys.pj.mp.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.sys.entity.SysItem;
import com.qqls.youxiangousys.pj.mp.dao.MpItemDao;
import com.qqls.youxiangousys.pj.mp.service.MpItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MpItemServiceImpl implements MpItemService {

    @Autowired
    private MpItemDao mpItemDao;

    public Pagination findSellItem(Integer sellCurPage, Integer sellPageSize, Integer sellState) {
        sellPageSize = sellPageSize == null || sellPageSize == 0 ? 3 : sellPageSize;
        Page<SysItem> page = PageHelper.startPage(sellCurPage, sellPageSize);
        List<SysItem> list = mpItemDao.findSellItem(sellState);
        Pagination pageObj = new Pagination(sellCurPage, (int) page.getTotal(), sellPageSize);
        pageObj.setPageData(list);
        return pageObj;
    }

    public Pagination findLikeItem(Integer likeCurPage, Integer likePageSize, Integer likeItemType) {
        likePageSize = likePageSize == null || likePageSize == 0 ? 6 : likePageSize;
        Page<Object> page = PageHelper.startPage(likeCurPage, likePageSize);
        List<SysItem> list = mpItemDao.findLikeItem(likeItemType);
        Pagination pageObj = new Pagination(likeCurPage, (int) page.getTotal(), likePageSize);
        pageObj.setPageData(list);
        return pageObj;
    }

    public SysItem findItemById(Integer id) {
        SysItem item = mpItemDao.findItemById(id);
        return item;
    }

}
