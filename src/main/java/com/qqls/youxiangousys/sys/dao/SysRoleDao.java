package com.qqls.youxiangousys.sys.dao;

import com.qqls.youxiangousys.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleDao {

     List<SysRole> findAllRole();
}
