package com.qqls.youxiangousys.pj.mp.sys.service.Impl;

import com.qqls.youxiangousys.pj.admin.sys.entity.SysItem;
import com.qqls.youxiangousys.pj.mp.sys.dao.ItemDao;
import com.qqls.youxiangousys.pj.mp.sys.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    public List<SysItem> findSellItem(Integer sellCurPage, Integer sellPageSize, Integer sellState) {
        List<SysItem> list = itemDao.findSellItem(sellCurPage, sellPageSize, sellState);
        return list;
    }

}
