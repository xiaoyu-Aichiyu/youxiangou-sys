package com.qqls.youxiangousys.pj.admin.common.entity;

import lombok.Data;

import java.util.List;

//分页
@Data
public class Pagination {
    private int curPage;//当前页
    private int pageSize;//每页条数
    private int countSize;//总条数
    private int countPage;//总页数
    private List<?> PageData;//分页数据

    public Pagination(int curPage,int countSize,int pageSize){
        this.pageSize = pageSize <=0 ? 10 : pageSize;
        this.pageSize = this.pageSize >= 50 ?50 : this.pageSize;
        this.countSize = countSize;
        this.countPage = (this.pageSize + this.countSize - 1) / this.pageSize;
        this.curPage = curPage < 1 ? 1 : curPage;
        this.curPage = this.curPage > this.countPage ? this.countPage : this.curPage;
    }
}
