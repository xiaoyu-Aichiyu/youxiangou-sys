package com.qqls.youxiangousys.pj.common.entity;

import lombok.Data;

@Data
public class JsonResult {
	private Integer state = 1;//状态 1是ok 0是no 默认是ok
	private Object data;//成功数据
	private String message;//异常文本描述信息
	public JsonResult(Object data) {
		this.data = data;
	}
	public JsonResult(Throwable e) {
		this.state = 0;//状态码改为0
		this.message = e.getMessage();
	}
	
}
