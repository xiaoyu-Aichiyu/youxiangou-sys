package com.qqls.youxiangousys.pj.sys.service;

import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.sys.entity.SysLog;

public interface SysLogService {

    /**
     * 查询所有日志+分页
     * @param username
     * @param curPage
     * @param pageSize
     * @return
     */
    Pagination findAllItem(String username, Integer curPage, Integer pageSize);

    /**
     * 根据id删除日志信息
     * @param ids
     * @return
     */
    Integer deleteLog(Integer[] ids);

    /**
     * 添加日志
     * @param log
     */
    void insertLogData(SysLog log);

    /**
     * 导出所有日志信息
     */
    void exportLogThis();
}
