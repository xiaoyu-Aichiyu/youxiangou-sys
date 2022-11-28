package com.qqls.youxiangousys.pj.sys.service.impl;

import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.common.web.ServiceException;
import com.qqls.youxiangousys.pj.sys.dao.SysUserDao;
import com.qqls.youxiangousys.pj.sys.dao.SysUserRoleDao;
import com.qqls.youxiangousys.pj.sys.entity.SysItem;
import com.qqls.youxiangousys.pj.sys.entity.SysUser;
import com.qqls.youxiangousys.pj.sys.entity.saveExcelUserObj;
import com.qqls.youxiangousys.pj.sys.service.SysUserService;
import com.qqls.youxiangousys.pj.common.util.Assert;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private SysUserRoleDao userRoleDao;

    public Pagination findUser(String name, Integer curPage, Integer pageSize,Integer state) {
        //验证curPage和pageSize是否有值
        Assert.isEmpty(curPage == null || pageSize == null,"请选择当前页码或每页条数");
        //得到用户的总条数
        int count = userDao.countUser(name);
        //创建分页对象，计算出所有属性
        Pagination pageObj = new Pagination(curPage,count,pageSize);
        curPage = pageObj.getCurPage();//得到当前页
        pageSize = pageObj.getPageSize();//得到每页条数
        int start =(curPage - 1) * pageSize;
        List<SysUser> list =userDao.findUser(name,start,pageSize,state);//根据用户找角色
        Assert.isEmpty(list==null || list.size()==0,"数据已被删除");
        pageObj.setPageData(list);
        return pageObj;
    }


    /**
     * 通过用户id修改禁用启用功能
     * @param id
     * @param state
     * @return
     */
    public Integer updateValId(Integer id, Integer state) {
        Assert.isEmpty(id == null || id == 0,"请先选择用户");
        Assert.isEmpty(state == null, "操作有误");
        int n = userDao.updateValId(id,state);
        Assert.isEmpty(n == 0,"修改失败");
        return n;
    }

    /**
     * 添加用户
     * @param user
     * @param roleIds
     * @return
     */
    public Integer insertUser(SysUser user, Integer[] roleIds) {
        Assert.isEmpty(user == null || user.getUsername() == null,"请填写用户信息");
        String salt = UUID.randomUUID().toString();
        //加密方式，原密码，盐，加密次数
        SimpleHash sh = new SimpleHash("MD5",user.getPassword(),salt,1);
        String password = sh.toHex();
        user.setPassword(password);
        user.setSalt(salt);
        SysUser userByName = userDao.findUserByName(user.getUsername());//查找用户是否存在
        Assert.isEmpty(userByName != null,"用户已存在");
        int i = userDao.insertUser(user);
        Assert.isEmpty(i == 0,"添加失败");
        userRoleDao.insertUserRole(user.getId(),roleIds);//添加用户角色关系数据
        return i;
    }

    /**
     * 通过用户id修改用户
     * @param user
     * @param Ids
     * @return
     */
    public Integer updateUser(SysUser user, Integer[] Ids) {
        Assert.isEmpty(user==null || user.getId() == 0,"请选择要修改的数据");
        Assert.isEmpty(Ids == null || Ids.length == 0,"请至少选择一个角色");
        int n = userDao.updateUser(user);
        userRoleDao.deleteUserRoleByUserId(user.getId());
        userRoleDao.insertUserRole(user.getId(),Ids);
        Assert.isEmpty(n == 0,"修改失败");
        return n;
    }

    /**
     * 通过用户id找角色id
     * @param userId
     * @return
     */
    public List<Integer> findRoleUserById(Integer userId) {
        Assert.isEmpty(userId==null || userId == 0,"请选择要修改的用户");
        List<Integer> list =userRoleDao.findRoleUserById(userId);
        return list;
    }

    /**
     * 根据ID删除用户
     * @param ids
     * @return
     */
    public Integer deleteUser(Integer[] ids) {
        Assert.isEmpty(ids == null || ids.length == 0,"请选择要删除的用户");
        Integer n =  userDao.deleteUser(ids);
        Assert.isEmpty(n == 0,"用户删除失败");
        System.out.println(ids);
        return n;
    }

    /**
     * 导出所有
     */
    public void exportAll() {
        //创建XSSFWorkbook对象
        @SuppressWarnings("resource")
        Workbook workbook = new XSSFWorkbook();
        //创建一个sheet
        Sheet sheet = workbook.createSheet("用户数据详情表");
        //创建行，从0开始
        Row row = sheet.createRow(0);
        //处理表头
        handlerRowTitle(row);
        List<SysUser> list = userDao.findAllUser();
        for (int i = 0; i < list.size(); i++) {
            Row rowUser = sheet.createRow(i + 1);
            //把每一个用户信息对象写入行中
            handlerRowUser(rowUser,list.get(i));
        }
        try(FileOutputStream fos = new FileOutputStream("D:/user.xlsx");){
            workbook.write(fos);//把文件写出到指定路径
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入所有用户
     * @param file
     * @return
     */
    /*public saveExcelUserObj saveExportUser(MultipartFile file) {
        saveExcelUserObj seu = new saveExcelUserObj();
        try{
            InputStream ins = file.getInputStream();//获取输入流
            Map<String,List<?>> map = getListUserExcel(ins);
            System.out.println(map.get("userData"));
            Integer n = 0;
            if (map.get("userData").size() != 0){
                n = userDao.insertUserExcel((List<SysUser>)map.get("userData"));
            }
            Assert.isEmpty(n == 0,"数据导入失败!");
            seu.setSuccessNumber(n);
            seu.setErrorNumber((List<Integer>) map.get("numList"));
        }catch (Exception e){
            throw new ServiceException("数据导入失败或无数据！");
        }
        return seu;
    }
    private Map<String, List<?>> getListUserExcel(InputStream ins) throws Exception {
        //获取Workbook对象
        Workbook workbook = new XSSFWorkbook(ins);
        //获取有效sheet的数量
        int sheetNumber = workbook.getNumberOfSheets();
        List<SysUser> userList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < sheetNumber; i++){
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
                String name = cell1.getStringCellValue();
                Cell cell2 = row.getCell(2);
                String username = cell2.getStringCellValue();
                Cell cell3 = row.getCell(3);
                String password = cell3.getStringCellValue();
                Cell cell4 = row.getCell(4);
                String salt = cell4 == null ? "" : getCellValue(cell4);
                Cell cell5 = row.getCell(5);
                String userImg = getCellValue(cell5);
                Cell cell6 = row.getCell(6);
                String phone = cell6.getStringCellValue();
                Cell cell7 = row.getCell(7);
                String stateStr = getCellValue(cell7);
                int state = (int)Double.parseDouble(stateStr);
                Cell cell8 = row.getCell(8);
                int gender = (int)cell8.getNumericCellValue();
                Cell cell9 = row.getCell(9);
                Date createdTime = cell9.getDateCellValue();
                Cell cell10 = row.getCell(10);
                Date modifiedTime = cell10.getDateCellValue();
                SysUser user = new SysUser(id,name,username,password,salt,userImg,phone,state,gender,createdTime,modifiedTime);
                SysUser u = userDao.findUserByNameAnduserName(name,username);
                if (u == null && !userList.contains(user)){
                    userList.add(user);
                }else {
                    numList.add(j + 1);//重复数据的行号
                }
            }
        }
        Map<String,List<?>> map = new HashMap<>();
        map.put("userData",userList);//需要存入数据库的SysUser集合
        map.put("numLiat",numList);//重复行号集合
        return map;
    }

    public String getCellValue(Cell cell) {
        if (cell.getCellType() == cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
        } else if(cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
            return cell.getNumericCellValue()+"";
        } else {
            //如果是其它类型，自己找判断条件。比如时间日期判断
            return "......";
        }
    }*/

    /**
     * 把每一个用户信息对象写入行中
     * @param rowUser
     * @param sysUser
     */
    private void handlerRowUser(Row rowUser, SysUser sysUser) {
        Cell cell0 = rowUser.createCell(0);
        cell0.setCellValue(rowUser.getRowNum());//id
        Cell cell1 = rowUser.createCell(1);
        cell1.setCellValue(sysUser.getName());//用户姓名
        Cell cell2 = rowUser.createCell(2);
        cell2.setCellValue(sysUser.getUsername());//账号
        Cell cell3 = rowUser.createCell(3);
        cell3.setCellValue(sysUser.getUserImg());//头像
        Cell cell4 = rowUser.createCell(4);
        cell4.setCellValue(sysUser.getPhone());//电话
        Cell cell5 = rowUser.createCell(5);
        cell5.setCellValue(sysUser.getState());//状态
        Cell cell6 = rowUser.createCell(6);
        cell6.setCellValue(sysUser.getGender());//性别
        Cell cell7 = rowUser.createCell(7);
        cell7.setCellValue(sysUser.getCreatedTime());//创建时间
        Cell cell8 = rowUser.createCell(8);
        cell8.setCellValue(sysUser.getModifiedTime());//修改时间
    }

    /**
     * 数据导入导出的表头
     * @param row
     */
    private void handlerRowTitle(Row row) {
        String [] tities = {"ID","用户姓名","账号","头像","电话","状态","性别","创建时间","修改时间"};
        for (int i = 0; i < tities.length; i++) {
            //创建一个单元格
            Cell cell = row.createCell(i);
            //设置单元格的值
            cell.setCellValue(tities[i]);
        }
    }
}
