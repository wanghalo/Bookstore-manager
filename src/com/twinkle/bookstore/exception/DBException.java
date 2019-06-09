package com.twinkle.bookstore.exception;

/**
 * @Name com.twinkle.bookstore.exception/DBException.java
 * @Description: 自定义数据库操作异常
 *
 * @VersionInformation: Twinkle 2019年5月19日 V1.0
 */
@SuppressWarnings("serial")
public class DBException extends RuntimeException {

	public DBException() {
		super();
	}

	public DBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBException(String message) {
		super(message);
	}

	public DBException(Throwable cause) {
		super(cause);
	}

}
