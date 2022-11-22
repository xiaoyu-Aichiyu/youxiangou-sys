package com.qqls.youxiangousys.pj.sys.controller;

import com.qqls.youxiangousys.pj.sys.service.SysItemService;
import com.qqls.youxiangousys.pj.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class SysItemController {

    @Autowired
    private SysItemService itemService;

    /**
     * 查找所有商品
     * @param itemName
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("findAllItem")
    public JsonResult findAllItem(String itemName, Integer curPage, Integer pageSize, Integer itemState){
        Pagination pageObj = itemService.findAllItem(itemName,curPage,pageSize,itemState);
        return new JsonResult(pageObj);
    }

    /**
     * 修改用户启用禁用
     * @param id
     * @param state
     * @return
     */
    @RequestMapping("updateState")
    public JsonResult updateValid(Integer id,Integer state) {
        JsonResult jr = new JsonResult(itemService.updateState(id,state));
        jr.setMessage("状态修改成功！");
        return jr;
    }

    /**
     * 修改用户推荐状态
     * @param id
     * @param itemSell
     * @return
     */
    @RequestMapping("updateSell")
    public JsonResult updateSell(Integer id,Integer itemSell) {
        JsonResult jr = new JsonResult(itemService.updateSell(id, itemSell));
        jr.setMessage("推荐修改成功！");
        return jr;
    }

    /**
     * 根据id删除商品
     * @param ids
     * @return
     */
    @RequestMapping("deleteItem")
    public JsonResult deleteItemList(@RequestParam("ids[]") Integer[] ids){
        return new JsonResult(itemService.deleteItem(ids));
    }

}
