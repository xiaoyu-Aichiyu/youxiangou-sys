package com.qqls.youxiangousys.pj.admin.sys.controller;

import com.qqls.youxiangousys.pj.admin.sys.service.SysItemService;
import com.qqls.youxiangousys.pj.admin.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.admin.common.entity.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public JsonResult findAllItem(String itemName,Integer curPage,Integer pageSize){
        Pagination pageObj = itemService.findAllItem(itemName,curPage,pageSize);
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

    @RequestMapping("updateTj")
    public JsonResult updateTj(Integer id,Integer itemTj) {
        JsonResult jr = new JsonResult(itemService.updateTj(id, itemTj));
        jr.setMessage("推荐修改成功！");
        return jr;
    }

}
