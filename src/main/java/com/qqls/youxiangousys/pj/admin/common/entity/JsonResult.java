package com.qqls.youxiangousys.pj.admin.common.entity;

import lombok.Data;

@Data
public class JsonResult {
    private Integer state = 1;//状态
    private Object data;//成功数据
    private String message;//异常文本描述
    public JsonResult(Throwable e) {
        this.state = 0;//状态码改为0
        message = e.getMessage();
    }

    public JsonResult(Object data) {
        this.data = data;
    }
}
