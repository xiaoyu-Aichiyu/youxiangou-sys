package com.qqls.youxiangousys.pj.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private int itemState = 1;
    private int itemSell;
    private double itemRebate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysItem sysItem = (SysItem) o;
        return Objects.equals(itemName, sysItem.itemName) && Objects.equals(itemTitle, sysItem.itemTitle) && Objects.equals(itemImg, sysItem.itemImg) && Objects.equals(itemNote, sysItem.itemNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, itemTitle, itemImg, itemNote);
    }

}
