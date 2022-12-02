package com.qqls.youxiangousys.pj.sys.dao;

import com.qqls.youxiangousys.pj.sys.entity.SysItem;
import com.qqls.youxiangousys.pj.sys.entity.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    List<SysLog> findItemByUsername(@Param("username") String username, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

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
    Integer insertLog(SysLog log);

    /**
     * 导出所有日志信息
     * @return
     */
    @Select("select * from t_log")
    List<SysLog> findExportThisLog();
}
