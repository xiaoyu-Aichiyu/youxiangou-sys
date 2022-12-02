package com.qqls.youxiangousys.pj.sys.service.impl;

import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.common.util.Assert;
import com.qqls.youxiangousys.pj.sys.dao.SysRoleDao;
import com.qqls.youxiangousys.pj.sys.entity.SysRole;
import com.qqls.youxiangousys.pj.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao roleDao;

    public List<SysRole> findAllRole() {
        List<SysRole> list = roleDao.findAllRole();
        return list;
    }

    /**
     * 初始加载分页数据
     * @param name
     * @param curPage
     * @param pageSize
     * @return
     */
    public Pagination findRole(String name, Integer curPage, Integer pageSize) {
        //1.验证curPage,pageSzie是否有值
        Assert.isEmpty(curPage == null || pageSize == null, "请选择当前页马或每页条数");
        //2.得到角色的总条数
        int count = roleDao.countRole(name);
        //3.创建分页对象，算出所有属性
        Pagination pageObj = new Pagination(curPage, count, pageSize);
        curPage = pageObj.getCurPage();//得到当前页
        pageSize = pageObj.getPageSize();//得到每页条数
        int start = (curPage -1 ) * pageSize;//分页跳过多少条=(当前页 - 1 ) * 每页条数
        //4.根据参数找角色
        List<SysRole> list = roleDao.findRole(name, start,pageSize);
        //5.验证第4步的结果是否为null
        Assert.isEmpty(list == null || list.size() == 0, "数据或已被删除");
        pageObj.setPageData(list);//设置分页数据
        return pageObj;
    }
}
