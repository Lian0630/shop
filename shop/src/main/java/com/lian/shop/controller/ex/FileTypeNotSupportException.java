package com.lian.shop.controller.ex;
/**
 * 上傳文件類型不支持的異常
 */
public class FileTypeNotSupportException extends FileUploadException {

	private static final long serialVersionUID = 1L;

	public FileTypeNotSupportException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileTypeNotSupportException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileTypeNotSupportException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileTypeNotSupportException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileTypeNotSupportException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
	
}
