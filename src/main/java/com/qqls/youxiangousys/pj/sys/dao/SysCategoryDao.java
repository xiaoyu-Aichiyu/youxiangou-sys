package com.qqls.youxiangousys.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysCategoryDao {

    public List<Map<String, Object>> doFindCategory() ;

}
