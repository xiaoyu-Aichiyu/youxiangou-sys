package com.qqls.youxiangousys.pj.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SysCoupon {
    private Integer id;
    private String couponName;
    private Integer couponMoney;
    private double couponRebate;
    private Integer couponTerm;
    private Integer couponState = 1;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}
