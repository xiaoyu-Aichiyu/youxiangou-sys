package com.qqls.youxiangousys.pj.admin.common.web;

public class ServiceException extends RuntimeException {

	/**
	 * 序列化常量
	 */
	private static final long serialVersionUID = 2444658681172872019L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
