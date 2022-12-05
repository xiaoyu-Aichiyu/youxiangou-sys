package com.qqls.youxiangousys.pj.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 订单实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysOrder {
    private Integer id;//id
    private String orderNumber;//订单号
    private String state;//订单状态
    private String name;//用户名
    private String phone;//联系电话
    private String address;//地址
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderTime;//下单时间
    private Double collection;//实付金额
}
