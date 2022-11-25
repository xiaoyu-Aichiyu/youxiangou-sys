package com.qqls.youxiangousys.pj.sys.controller;

import com.qqls.youxiangousys.pj.common.util.Assert;
import com.qqls.youxiangousys.pj.sys.entity.saveExcelCarObj;
import com.qqls.youxiangousys.pj.sys.service.SysItemService;
import com.qqls.youxiangousys.pj.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public JsonResult deleteItemList(@RequestParam("ids[]") Integer[] ids,Integer itemState){
        return new JsonResult(itemService.deleteItem(ids,itemState));
    }

    /**
     * 导出所有商品
     */
    @RequestMapping("exportThisItem")
    public JsonResult exportThisItem(){
        itemService.exportThisItem();
        return new JsonResult("商品数据导出成功！！");
    }

    /**
     * 导入所有商品
     * @return
     */
    @RequestMapping("saveExportItem")
    public JsonResult saveExportItem(MultipartFile file){
        Assert.isEmpty(file == null,"上传的文件不存在！！");
        return new JsonResult(itemService.saveExportItem(file));
    }
}
