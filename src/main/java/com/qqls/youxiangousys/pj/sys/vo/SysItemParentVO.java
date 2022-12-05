package com.qqls.youxiangousys.pj.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qqls.youxiangousys.pj.sys.entity.SysCategory;
import lombok.Data;

import java.util.Date;

@Data
public class SysItemParentVO {
    private int id;
    private String itemName;
    private String itemImg;
    private String itemTitle;
    private String itemNote;
    private SysCategory sysCategory;
    private double itemPrice;
    private Integer itemState;
    private Integer itemSell;
    private double itemRebate;
    private Integer saleNum;
    private Integer itemNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;
}
