package com.qqls.youxiangousys.pj.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysItem implements Serializable {

    private int id;
    private String itemName;
    private String itemTitle;
    private String itemImg;
    private String itemNote;
    private double itemPrice;
    private int itemNum;
    private int saleNum;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
    private int typeId;
    private int state;
}
