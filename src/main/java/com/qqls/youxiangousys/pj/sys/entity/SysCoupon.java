package com.qqls.youxiangousys.pj.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SysCoupon {
    private Integer id;
    private String couponName;
    private Integer couponMoney;
    private Integer couponTerm;
    private Integer couponState = 1;
    private String startTime;
    private String endTime;
}
