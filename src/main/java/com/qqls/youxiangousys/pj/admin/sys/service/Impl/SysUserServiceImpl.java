package com.qqls.youxiangousys.pj.admin.sys.service.Impl;

import com.qqls.youxiangousys.pj.admin.common.entity.Pagination;
import com.qqls.youxiangousys.pj.admin.sys.dao.SysUserDao;
import com.qqls.youxiangousys.pj.admin.sys.entity.SysUser;
import com.qqls.youxiangousys.pj.admin.sys.service.SysUserService;
import com.qqls.youxiangousys.pj.admin.common.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao userDao;

    public Pagination findUser(String name, Integer curPage, Integer pageSize) {
        //验证curPage和pageSize是否有值
        Assert.isEmpty(curPage == null || pageSize == null,"请选择当前页码或每页条数");
        //得到用户的总条数
        int count = userDao.countUser(name);
        //创建分页对象，计算出所有属性
        Pagination pageObj = new Pagination(curPage,count,pageSize);
        curPage = pageObj.getCurPage();//得到当前页
        pageSize = pageObj.getPageSize();//得到每页条数
        int start =(curPage - 1) * pageSize;
        List<SysUser> list =userDao.findUser(name,start,pageSize);//根据用户找角色
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
    public int updateValId(Integer id, Integer state) {
        Assert.isEmpty(id == null || id == 0,"请选择要修改的用户");
        Assert.isEmpty(state == null, "操作有误");
        int n = userDao.updateValId(id,state);
        Assert.isEmpty(n == 0,"修改失败");
        return n;
    }
}
