package com.lian.shop.controller.ex;
/**
 * 上傳文件超出限制的異常
 */
public class FileSizeOutOfLimitException extends FileUploadException {

	private static final long serialVersionUID = 1L;

	public FileSizeOutOfLimitException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileSizeOutOfLimitException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileSizeOutOfLimitException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileSizeOutOfLimitException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileSizeOutOfLimitException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
