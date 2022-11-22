package com.qqls.youxiangousys.pj.admin.sys.service.impl;

import com.qqls.youxiangousys.pj.admin.common.entity.Pagination;
import com.qqls.youxiangousys.pj.admin.sys.dao.SysUserDao;
import com.qqls.youxiangousys.pj.admin.sys.dao.SysUserRoleDao;
import com.qqls.youxiangousys.pj.admin.sys.entity.SysUser;
import com.qqls.youxiangousys.pj.admin.sys.service.SysUserService;
import com.qqls.youxiangousys.pj.admin.common.util.Assert;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private SysUserRoleDao userRoleDao;

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
    public Integer updateValId(Integer id, Integer state) {
        Assert.isEmpty(id == null || id == 0,"请选择要修改的用户");
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
}
