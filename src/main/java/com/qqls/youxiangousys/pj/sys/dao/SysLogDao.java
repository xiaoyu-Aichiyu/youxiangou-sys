package com.qqls.youxiangousys.pj.sys.dao;

import com.qqls.youxiangousys.pj.sys.entity.SysItem;
import com.qqls.youxiangousys.pj.sys.entity.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysLogDao {

    /**
     * 统计日志条数，用来分页
     * @param username
     * @return
     */
    Integer getCountLog(String username);

    /**
     * 查询所有日志
     * @param username
     * @param start
     * @param pageSize
     * @return
     */
    List<SysItem> findItemByUsername(@Param("username") String username, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    /**
     * 根据id删除日志信息
     * @param ids
     * @return
     */
    Integer deleteLogByIds(Integer[] ids);

    /**
     * 添加日志
     * @param log
     */
    @Insert("insert into sys_logs value(null,#{username},#{operation},#{method},#{params},#{time},#{ip},#{createdTime})")
    void insertLog(SysLog log);
}
