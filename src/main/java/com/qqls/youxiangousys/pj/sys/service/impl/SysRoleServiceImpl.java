package com.qqls.youxiangousys.pj.sys.service.impl;

import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.common.util.Assert;
import com.qqls.youxiangousys.pj.sys.dao.SysRoleDao;
import com.qqls.youxiangousys.pj.sys.dao.SysRoleMenuDao;
import com.qqls.youxiangousys.pj.sys.dao.SysUserRoleDao;
import com.qqls.youxiangousys.pj.sys.entity.SysRole;
import com.qqls.youxiangousys.pj.sys.service.SysRoleService;
import com.qqls.youxiangousys.pj.sys.vo.SysRoleMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao roleDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

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

    /**
     * 添加角色，角色需要授权
     * @param role
     * @param ids
     * @return
     */
    public Integer insertRole(SysRole role, Integer[] ids) {
        //1.验证参数
        Assert.isEmpty(role == null || role.getName() == null || role.getName().equals(""),"不允许为空");
        Assert.isEmpty(ids == null || ids.length == 0 ,"必须为角色分配权限");
        //2.插入角色
        int n = roleDao.insertRole(role);
        //3.验证结果
        Assert.isEmpty(n == 0, "插入失败");
        //4.插入角色和菜单关系数据
        int m = sysRoleMenuDao.insertRoleMenu(role.getId(), ids);
        //5.验证结果
        Assert.isEmpty(m == 0, "插入失败");
        return n;
    }

    /**
     * 修改角色
     * @param vo
     * @return
     */
    public Integer updateRoleById(SysRoleMenuVO vo) {
        Assert.isEmpty(vo == null || vo.getId()==null, "请选择要修改角色");
        //根据角色id修改角色菜单关系数据
        //int n = rmdao.updateRoleMenuByRoleId(vo.getId(),vo.getMenuIds());
        //根据角色id删除角色菜单数据
        sysRoleMenuDao.deleteRoleMenuByMenuId(vo.getId());
        sysRoleMenuDao.insertRoleMenu(vo.getId(), vo.getMenuIds().toArray(new Integer[] {}));
        int n = roleDao.updateRoleById(vo);
        Assert.isEmpty(n == 0, "修改失败");
        return n;
    }

    /**
     * 通过角色id找角色和角色的菜单id
     * @param id
     * @return
     */
    public SysRoleMenuVO findRoleMenuIds(Integer id) {
        //验证参数
        Assert.isEmpty(id == null || id == 0, "请选择修改的角色");
        SysRoleMenuVO vo =	roleDao.findRoleMenuIds(id);
        Assert.isEmpty(vo == null , "角色不存在或已被删除");
        return vo;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public Integer deleteRoleById(Integer id) {
        //1.验证参数
        Assert.isEmpty(id==null || id==0, "请选择");
        //2.调用三个dao的与角色相关的删除方法
        int n = roleDao.deleteRoleById(id);
        sysRoleMenuDao.deleteRoleMenuByRoleId(id);
        sysUserRoleDao.deleteRoleMenuByRoleId(id);
        Assert.isEmpty(n==0, "已删除!");
        //3.验证结果
        return n;
    }
}
