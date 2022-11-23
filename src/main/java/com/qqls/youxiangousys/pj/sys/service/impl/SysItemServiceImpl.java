package com.qqls.youxiangousys.pj.sys.service.impl;

import com.qqls.youxiangousys.pj.common.annotation.RequiredLog;
import com.qqls.youxiangousys.pj.sys.dao.SysItemDao;
import com.qqls.youxiangousys.pj.sys.entity.SysItem;
import com.qqls.youxiangousys.pj.sys.service.SysItemService;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.common.util.Assert;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
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
    @RequiredLog("查找商品")
    @RequiresPermissions("sys:item:view")
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
     * 修改商品上架下架
     * @param id
     * @param state
     * @return
     */
    @RequiredLog("修改商品状态")
    public Integer updateState(Integer id, Integer state) {
        Assert.isEmpty(id == null || id == 0, "请选择要修改状态的用户！");
        Assert.isEmpty(state == null, "操作有误！");
        Integer n = itemDao.updateState(id, state);
        Assert.isEmpty(n == 0, "状态修改失败！");
        return n;
    }

    /**
     * 修改商品推荐状态
     * @param id
     * @param itemSell
     * @return
     */
    @RequiredLog("修改商品推荐")
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
    @RequiredLog("删除商品")
    public Integer deleteItem(Integer[] ids,Integer itemState) {
        String message1 = "请选择要删除的数据！";
        String message2 = "商品删除失败！";
        if (itemState == 1){
            message1 = "请选择要上架的数据！";
            message2 = "商品上架失败！";
        }
        Assert.isEmpty(ids == null || ids.length == 0,message1);
        Integer n = itemDao.deleteItemByIds(ids,itemState);
        Assert.isEmpty(n == 0,message2);
        return n;
    }

    /**
     * 导出所有商品
     */
    @RequiredLog("导出商品")
    public void exportThisItem() {
        //创建XSSFWorkbook对象
        Workbook workbook = new XSSFWorkbook();
        //创建一个sheet
        Sheet sheet = workbook.createSheet("商品详情表");
        //创建行，从0开始
        Row row = sheet.createRow(0);
        //处理表头
        handlerRowTitle(row);
        //查询所有商品信息
        List<SysItem> list = itemDao.findExportThisItem();
        for (int i = 0; i < list.size(); i++) {
            Row rowCar = sheet.createRow(i + 1);
            //把每一个商品信息对象写入行中
            handlerRowCar(rowCar,list.get(i),i);
        }
        try (FileOutputStream fos = new FileOutputStream("D:/item.xlsx");){
            //把文件写出到指定路径
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handlerRowCar(Row rowCar, SysItem item, int i) {
        Cell cell0 = rowCar.createCell(0);
        cell0.setCellValue(i + 1);
        Cell cell1 = rowCar.createCell(1);
        cell1.setCellValue(item.getItemName());
        Cell cell2 = rowCar.createCell(2);
        cell2.setCellValue(item.getItemImg());
        Cell cell3 = rowCar.createCell(3);
        cell3.setCellValue(item.getItemTitle());
        Cell cell4 = rowCar.createCell(4);
        cell4.setCellValue(item.getItemPrice());
        Cell cell5 = rowCar.createCell(5);
        cell5.setCellValue(item.getItemState());
        Cell cell6 = rowCar.createCell(6);
        cell6.setCellValue(item.getItemSell());
        Cell cell7 = rowCar.createCell(7);
        System.out.println(item.getCreatedTime());
        SimpleDateFormat sdf = new SimpleDateFormat();
        cell7.setCellValue(sdf.format(item.getCreatedTime()));
    }

    public void handlerRowTitle(Row row) {
        String[] titles = {"编号","商品名","图片","分类","售价","售卖状态","商品推荐","最后操作时间"};
        for (int i = 0; i < titles.length; i++) {//遍历titles数组，拿到里面的值进行操作
            //创建一个单元格
            Cell cell = row.createCell(i);
            //设置单元格的值
            cell.setCellValue(titles[i]);
        }
    }
}
