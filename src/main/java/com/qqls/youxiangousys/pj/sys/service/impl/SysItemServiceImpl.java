package com.qqls.youxiangousys.pj.sys.service.impl;

import com.qqls.youxiangousys.pj.common.annotation.RequiredLog;
import com.qqls.youxiangousys.pj.common.web.ServiceException;
import com.qqls.youxiangousys.pj.sys.dao.SysItemDao;
import com.qqls.youxiangousys.pj.sys.entity.SysItem;
import com.qqls.youxiangousys.pj.sys.entity.saveExcelItemObj;
import com.qqls.youxiangousys.pj.sys.service.SysItemService;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.common.util.Assert;
import com.qqls.youxiangousys.pj.sys.vo.SysItemParentVO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
        List<SysItemParentVO> list = itemDao.findItemByItemName(itemName,start,pageSize,itemState);
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
        Assert.isEmpty(id == null || id == 0, "请选择要修改状态的商品！");
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
     * 根据id批量删除商品信息
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

    /**
     * 导入所有商品
     * @param file
     * @return
     */
    @RequiredLog("导入商品")
    public saveExcelItemObj saveExportItem(MultipartFile file) {
        saveExcelItemObj sec = new saveExcelItemObj();
        try {
            //获取输入流
            InputStream is = file.getInputStream();
            Map<String, List<?>> map = getListCarExcel(is);
            System.out.println(map.get("itemData"));
            Integer n = 0;
            if (map.get("itemData").size() != 0) {
                n = itemDao.insertItemExcel((List<SysItem>)map.get("itemData"));
            }
            if (n == 0) {
                throw new ServiceException("数据导入失败或无数据！");
            }
            sec.setSuccessNumber(n);
            sec.setErrorNumber((List<Integer>) map.get("numList"));
        } catch (Exception e) {
            throw new ServiceException("数据导入失败或无数据！");
        }
        return sec;
    }

    /**
     * 根据id删除一行商品数据
     * @param id
     * @param itemState
     * @return
     */
    @RequiredLog("删除商品")
    public Integer deleteItemById(Integer id, Integer itemState) {
        Assert.isEmpty(id == null,"请选择要删除的一条数据");
        Integer n = itemDao.deleteItemById(id,itemState);
        Assert.isEmpty(n == 0,"商品数据删除失败！！");
        return n;
    }

    /**
     * 添加商品数据
     * @param item
     * @return
     */
    @RequiredLog("新增商品")
    public Integer insertItemData(SysItem item) {
        Assert.isEmpty(item == null,"请将表单信息填写完整！！");
        Integer n = itemDao.insertItemData(item);
        Assert.isEmpty(n == 0,"商品数据添加失败！！");
        return n;
    }

    /**
     * 修改商品数据
     * @param item
     * @return
     */
    @RequiredLog("修改商品")
    public Integer updateItemData(SysItem item) {
        Assert.isEmpty(item == null,"请将表单信息填写完整！！");
        Integer n = itemDao.updateItemData(item);
        Assert.isEmpty(n == 0,"商品数据修改失败！！");
        return n;
    }

    /**
     * 返回重复行号集合
     * @param is
     * @return
     * @throws IOException
     */
    public Map<String, List<?>> getListCarExcel(InputStream is) throws IOException {
        //获取Workbook对象
        Workbook workbook = new XSSFWorkbook(is);
        //获取有效sheet的数量
        int sheetNumber = workbook.getNumberOfSheets();
        List<SysItem> itemList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < sheetNumber; i++) {
            //获取sheet对象
            Sheet sheet = workbook.getSheetAt(i);
            //如果sheet表为空则跳过
            if (sheet == null) {
                continue;
            }
            //获取初始行号
            int startRowNum = sheet.getFirstRowNum();
            //获取结束行号
            int endRowNum = sheet.getLastRowNum();
            for (int j = startRowNum + 1; j <= endRowNum; j++) {
                //获取行
                Row row = sheet.getRow(j);
                Cell cell0 = row.getCell(0);
                //getNumericCellValue:获取double数字
                int id = (int)cell0.getNumericCellValue();
                Cell cell1 = row.getCell(1);
                String itemName = cell1.getStringCellValue();
                Cell cell2 = row.getCell(2);
                String itemTitle = cell2.getStringCellValue();
                Cell cell3 = row.getCell(3);
                String itemImg = cell3.getStringCellValue();
                Cell cell4 = row.getCell(4);
                String itemNote = cell4.getStringCellValue();
                Cell cell5 = row.getCell(5);
                int itemPrice = (int)cell5.getNumericCellValue();
                Cell cell6 = row.getCell(6);
                int itemNum = (int)cell6.getNumericCellValue();
                Cell cell7 = row.getCell(7);
                int saleNum = (int)cell7.getNumericCellValue();
                Cell cell8 = row.getCell(8);
                Date createdTime = cell8.getDateCellValue();
                Cell cell9 = row.getCell(9);
                Date modifiedTime = cell9.getDateCellValue();
                Cell cell10 = row.getCell(10);
                String createdUser = cell10.getStringCellValue();
                Cell cell11 = row.getCell(11);
                String modifiedUser = cell11.getStringCellValue();
                Cell cell12 = row.getCell(12);
                int typeId = (int)cell12.getNumericCellValue();
                Cell cell13 = row.getCell(13);
                int itemState = (int)cell13.getNumericCellValue();
                Cell cell14 = row.getCell(14);
                int itemSell = (int)cell14.getNumericCellValue();
                Cell cell15 = row.getCell(15);
                int itemRebate = (int)cell15.getNumericCellValue();
                SysItem item = new SysItem(id,itemName,itemTitle,itemImg,itemNote,itemPrice,itemNum,saleNum,
                        createdTime,modifiedTime,createdUser,modifiedUser,typeId,itemState,itemSell,itemRebate);
                SysItem c = itemDao.findCarByNameAndNote(itemName,itemNote);
                if (c == null && !itemList.contains(item)) {
                    itemList.add(item);
                }else{
                    numList.add(j + 1);//重复的数据的行号
                }
            }
        }
        Map<String,List<?>> map = new HashMap<>();
        map.put("itemData", itemList);//需要存入数据库的Car集合
        map.put("numList", numList);//重复行号集合
        return map;
    }

    /**
     * 导出加载主体数据
     * @param rowCar
     * @param item
     * @param i
     */
    public void handlerRowCar(Row rowCar, SysItem item, int i) {
        Cell cell0 = rowCar.createCell(0);
        cell0.setCellValue(i + 1);
        Cell cell1 = rowCar.createCell(1);
        cell1.setCellValue(item.getItemName());
        Cell cell2 = rowCar.createCell(2);
        cell2.setCellValue(item.getItemTitle());
        Cell cell3 = rowCar.createCell(3);
        cell3.setCellValue(item.getItemImg());
        Cell cell4 = rowCar.createCell(4);
        cell4.setCellValue(item.getItemNote());
        Cell cell5 = rowCar.createCell(5);
        cell5.setCellValue(item.getItemPrice());
        Cell cell6 = rowCar.createCell(6);
        cell6.setCellValue(item.getItemNum());
        Cell cell7 = rowCar.createCell(7);
        cell7.setCellValue(item.getSaleNum());
        Cell cell8 = rowCar.createCell(8);
        cell8.setCellValue(item.getCreatedTime());
        Cell cell9 = rowCar.createCell(9);
        cell9.setCellValue(item.getModifiedTime());
        Cell cell10 = rowCar.createCell(10);
        cell10.setCellValue(item.getCreatedUser());
        Cell cell11 = rowCar.createCell(11);
        cell11.setCellValue(item.getModifiedUser());
        Cell cell12 = rowCar.createCell(12);
        cell12.setCellValue(item.getTypeId());
        Cell cell13 = rowCar.createCell(13);
        cell13.setCellValue(item.getItemState());
        Cell cell14 = rowCar.createCell(14);
        cell14.setCellValue(item.getItemSell());
        Cell cell15 = rowCar.createCell(15);
        cell15.setCellValue(item.getItemRebate());
    }

    /**
     * 导出处理表头部
     * @param row
     */
    public void handlerRowTitle(Row row) {
        String[] titles = {"编号","商品名","商品标题","图片","商品详情","价格","商品库存","售卖数量","创建时间","修改时间","创建用户","修改用户","类型id","商品 状态","商品推荐","商品折扣"};
        for (int i = 0; i < titles.length; i++) {//遍历titles数组，拿到里面的值进行操作
            //创建一个单元格
            Cell cell = row.createCell(i);
            //设置单元格的值
            cell.setCellValue(titles[i]);
        }
    }
}
