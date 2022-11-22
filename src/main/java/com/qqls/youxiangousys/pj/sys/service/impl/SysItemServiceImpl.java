package com.qqls.youxiangousys.pj.sys.service.impl;

import com.qqls.youxiangousys.pj.sys.dao.SysItemDao;
import com.qqls.youxiangousys.pj.sys.entity.SysItem;
import com.qqls.youxiangousys.pj.sys.service.SysItemService;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.common.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysItemServiceImpl implements SysItemService {

    @Autowired
    private SysItemDao itemDao;

    /**
     * 根据商品名找商品
     * @param itemName
     * @param curPage
     * @param pageSize
     * @return
     */
    public Pagination findAllItem(String itemName, Integer curPage, Integer pageSize, Integer itemState) {
        //1.验证参数
        Assert.isEmpty(curPage == null || pageSize == null,"请选择当前页码或者每页条数！！");
        //2.得到角色的总条数
        Integer count = itemDao.getCountItem(itemName, itemState);
        //3.创建分页对象，算出所有属性
        Pagination pageObj = new Pagination(curPage,count,pageSize);
        curPage = pageObj.getCurPage();//得到当前页
        pageSize = pageObj.getPageSize();//得到每页条数
        //分页跳过条数=（当前页 - 1）*每页条数
        Integer start = (curPage - 1) * pageSize;
        //4.根据参数找角色
        List<SysItem> list = itemDao.findItemByItemName(itemName,start,pageSize,itemState);
        pageObj.setPageData(list);
        return pageObj;
    }

    /**
     * 修改用户启用禁用
     * @param id
     * @param state
     * @return
     */
    public Integer updateState(Integer id, Integer state) {
        Assert.isEmpty(id == null || id == 0, "请选择要修改状态的用户！");
        Assert.isEmpty(state == null, "操作有误！");
        Integer n = itemDao.updateState(id, state);
        Assert.isEmpty(n == 0, "状态修改失败！");
        return n;
    }

    /**
     * 修改用户推荐状态
     * @param id
     * @param itemSell
     * @return
     */
    public Integer updateSell(Integer id, Integer itemSell) {
        Assert.isEmpty(id == null || id == 0, "请选择要修改状态的用户！");
        Assert.isEmpty(itemSell == null, "操作有误！");
        Integer n = itemDao.updateSell(id, itemSell);
        Assert.isEmpty(n == 0,"推荐状态修改失败！");
        return n;
    }

    /**
     * 根据id删除商品信息
     * @param ids
     * @return
     */
    public Integer deleteItem(Integer[] ids) {
        Assert.isEmpty(ids == null || ids.length == 0,"请选择要删除的数据！");
        Integer n = itemDao.deleteItemByIds(ids);
        Assert.isEmpty(n == 0,"商品状态删除失败！");
        return n;
    }
}
