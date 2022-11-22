package com.qqls.youxiangousys.pj.admin.sys.dao;

import com.qqls.youxiangousys.pj.admin.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleDao {

     List<SysRole> findAllRole();
}
