package com.qqls.youxiangousys.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String itemName;
    private String itemTitle;
    private String itemImg;
    private String itemNote;
    private double itemPrice;
    private int itemNum;
    private int saleNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
    private int typeId;
    private Integer itemState = 1;
    private Integer itemSell;
}
