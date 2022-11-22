package com.qqls.youxiangousys.sys.service.impl;//package com.qqls.youxiangousys.pj.admin.sys.service.impl;
//
//import com.qqls.youxiangousys.pj.admin.common.entity.Pagination;
//import com.qqls.youxiangousys.pj.admin.common.util.Assert;
//import com.qqls.youxiangousys.pj.admin.sys.dao.SysLogDao;
//import com.qqls.youxiangousys.pj.admin.sys.entity.SysItem;
//import com.qqls.youxiangousys.pj.admin.sys.entity.SysLog;
//import com.qqls.youxiangousys.pj.admin.sys.service.SysLogService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class SysLogServiceImpl implements SysLogService {
//
//    @Autowired
//    private SysLogDao logDao;
//
//    /**
//     * 查询所有日志+分页
//     * @param username
//     * @param curPage
//     * @param pageSize
//     * @return
//     */
//    public Pagination findAllItem(String username, Integer curPage, Integer pageSize) {
//        //1.验证参数
//        Assert.isEmpty(curPage == null || pageSize == null,"请选择当前页码或者每页条数！！");
//        //2.得到角色的总条数
//        Integer count = logDao.getCountLog(username);
//        //3.创建分页对象，算出所有属性
//        Pagination pageObj = new Pagination(curPage,count,pageSize);
//        curPage = pageObj.getCurPage();//得到当前页
//        pageSize = pageObj.getPageSize();//得到每页条数
//        //分页跳过条数=（当前页 - 1）*每页条数
//        Integer start = (curPage - 1) * pageSize;
//        //4.根据参数找角色
//        List<SysItem> list = logDao.findItemByUsername(username,start,pageSize);
//        pageObj.setPageData(list);
//        return pageObj;
//    }
//
//    /**
//     * 根据id删除日志信息
//     * @param ids
//     * @return
//     */
//    public Integer deleteLog(Integer[] ids) {
//        Assert.isEmpty(ids == null || ids.length == 0,"请选择要删除的数据！");
//        Integer n = logDao.deleteLogByIds(ids);
//        Assert.isEmpty(n == 0,"日志删除失败！");
//        return n;
//    }
//
//    /**
//     * 添加日志
//     * @param log
//     */
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Async//异步注解，交给另外一个线程去执行
//    public void insertLogData(SysLog log) {
//        logDao.insertLog(log);
//    }
//}
