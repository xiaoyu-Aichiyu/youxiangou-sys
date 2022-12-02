package com.qqls.youxiangousys.pj.sys.service.impl;

import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.common.util.Assert;
import com.qqls.youxiangousys.pj.sys.dao.SysLogDao;
import com.qqls.youxiangousys.pj.sys.entity.SysItem;
import com.qqls.youxiangousys.pj.sys.entity.SysLog;
import com.qqls.youxiangousys.pj.sys.service.SysLogService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao logDao;

    /**
     * 查询所有日志+分页
     * @param username
     * @param curPage
     * @param pageSize
     * @return
     */
    public Pagination findAllItem(String username, Integer curPage, Integer pageSize) {
        //1.验证参数
        Assert.isEmpty(curPage == null || pageSize == null,"请选择当前页码或者每页条数！！");
        //2.得到角色的总条数
        Integer count = logDao.getCountLog(username);
        //3.创建分页对象，算出所有属性
        Pagination pageObj = new Pagination(curPage,count,pageSize);
        curPage = pageObj.getCurPage();//得到当前页
        pageSize = pageObj.getPageSize();//得到每页条数
        //分页跳过条数=（当前页 - 1）*每页条数
        Integer start = (curPage - 1) * pageSize;
        //4.根据参数找角色
        List<SysLog> list = logDao.findItemByUsername(username,start,pageSize);
        pageObj.setPageData(list);
        return pageObj;
    }

    /**
     * 根据id删除日志信息
     * @param ids
     * @return
     */
    public Integer deleteLog(Integer[] ids) {
        Assert.isEmpty(ids == null || ids.length == 0,"请选择要删除的数据！");
        Integer n = logDao.deleteLogByIds(ids);
        Assert.isEmpty(n == 0,"日志删除失败！");
        return n;
    }

    /**
     * 添加日志
     * @param log
     */
    public void insertLogData(SysLog log) {
        logDao.insertLog(log);
    }

    /**
     * 导出所有日志信息
     */
    public void exportLogThis() {
        //创建XSSFWorkbook对象
        Workbook workbook = new XSSFWorkbook();
        //创建一个sheet
        Sheet sheet = workbook.createSheet("商品详情表");
        //创建行，从0开始
        Row row = sheet.createRow(0);
        //处理表头
        handlerRowTitle(row);
        //查询所有商品信息
        List<SysLog> list = logDao.findExportThisLog();
        for (int i = 0; i < list.size(); i++) {
            Row rowCar = sheet.createRow(i + 1);
            //把每一个商品信息对象写入行中
            handlerRowCar(rowCar,list.get(i),i);
        }
        try (FileOutputStream fos = new FileOutputStream("D:/log.xlsx");){
            //把文件写出到指定路径
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handlerRowCar(Row rowCar, SysLog log, int i) {
        Cell cell0 = rowCar.createCell(0);
        cell0.setCellValue(i + 1);
        Cell cell1 = rowCar.createCell(1);
        cell1.setCellValue(log.getUsername());
        Cell cell2 = rowCar.createCell(2);
        cell2.setCellValue(log.getOperation());
        Cell cell3 = rowCar.createCell(3);
        cell3.setCellValue(log.getMethod());
        Cell cell4 = rowCar.createCell(4);
        cell4.setCellValue(log.getParams());
        Cell cell5 = rowCar.createCell(5);
        cell5.setCellValue(log.getTime());
        Cell cell6 = rowCar.createCell(6);
        cell6.setCellValue(log.getIp());
        Cell cell7 = rowCar.createCell(7);
        cell7.setCellValue(log.getCreatedTime());
    }

    /**
     * 导出处理表头部
     * @param row
     */
    public void handlerRowTitle(Row row) {
        String[] titles = {"编号","用户名","操作","请求方法","参数","执行时长","IP","最后操作时间"};
        for (int i = 0; i < titles.length; i++) {//遍历titles数组，拿到里面的值进行操作
            //创建一个单元格
            Cell cell = row.createCell(i);
            //设置单元格的值
            cell.setCellValue(titles[i]);
        }
    }
}
