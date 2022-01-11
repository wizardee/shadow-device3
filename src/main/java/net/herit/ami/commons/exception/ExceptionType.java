package net.herit.ami.commons.exception;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 에러 로그를 쉽게 찍기위한 ExceptionType
 */
public enum ExceptionType {
	SUCCESS(0, "success", false), 
	DEFAULT_EXCEPTION(1, "default exception", true), 
	LOG_TRACER_IS_NOT_EXIST(2, "LogTracer is not exist", false)
	;
	
	private int code;
	private String message;
	
	/**
	 * 예외 발생시 별도로 수행할 동작이 필요한 경우 true
	 */
	private boolean isExceptionJob;

	private static final Map<Integer, ExceptionType> findExceptionTypeByCode;
	
	static {
		findExceptionTypeByCode = new ConcurrentHashMap<>();
		for (ExceptionType status : ExceptionType.values()) {
			findExceptionTypeByCode.put(status.getCode(), status);
		}
	}
	
	ExceptionType(int code, String message, boolean isExceptionJob) {
		this.code = code;
		this.message = message;
		this.isExceptionJob = isExceptionJob;
	}
	
	public String getMessage() {
		return message;
	}
	
	public int getCode() {
		return code;
	}
	
	public boolean isExceptionJob() {
		return isExceptionJob;
	}

}