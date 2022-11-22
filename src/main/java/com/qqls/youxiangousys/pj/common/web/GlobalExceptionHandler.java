package com.qqls.youxiangousys.pj.common.web;

import com.qqls.youxiangousys.pj.common.entity.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//全局异常处理类
//@ControllerAdvice
@RestControllerAdvice//相当于 @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {
	
	//统一处理运行时异常
	@ExceptionHandler(RuntimeException.class)
	//@ResponseBody//把返回值当数据处理
	public JsonResult handlerRuntimeException(RuntimeException e) {
		e.printStackTrace();//控制台的打印
		e = new RuntimeException("服务器异常，请稍后重试！");
		return new JsonResult(e);
	}
	
	@ExceptionHandler(ServiceException.class)
	public JsonResult handlerServiceException(ServiceException e) {
		return new JsonResult(e);
	}
}
