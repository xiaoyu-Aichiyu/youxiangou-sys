package com.qqls.youxiangousys.pj.sys.service.impl;

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
}
