package com.qqls.youxiangousys.pj.common.util;

import com.qqls.youxiangousys.pj.common.web.ServiceException;

//工具类，检测条件是否符合规则
public class Assert {
	
	//检测条件是否为空
	public static void isEmpty(boolean expression,String message) {
		if (expression) {
			throw new ServiceException(message);
		}
	}
}
