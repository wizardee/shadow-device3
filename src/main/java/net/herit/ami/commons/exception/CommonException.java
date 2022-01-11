package net.herit.ami.commons.exception;

import org.quartz.JobExecutionException;

import net.herit.ami.commons.util.BeanUtil;

public class CommonException extends RuntimeException {

	private static final long serialVersionUID = -6079553227811321648L;
	
	public final String message;
	public final ExceptionType type;
	public final boolean doExceptionJob;
	
	public CommonException(ExceptionType type) {
		this(type, type.isExceptionJob());
	}

	/**
	 * ExceptionType에 isExceptionJob이 있더라도, 
	 * 해당 메서드의 동작을 원하지 않는경우를 위하여 doExceptionJob을
	 * 추가 매개 변수로 생성
	 * @param type
	 * @param doExceptionJob
	 */
	public CommonException(ExceptionType type, boolean doExceptionJob) {
		this.type = type;
		this.message = null;
		this.doExceptionJob = doExceptionJob;
	}

	public CommonException(String message) {
		this.message = message;
		this.type = null;
		doExceptionJob = false;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
	public ExceptionType getType() {
		return this.type;
	}
	
	public void errorJob() throws JobExecutionException {
		Object obj = BeanUtil.getBean("commonExceptionHandler");
		CommonExceptionHandler commonExceptionHandler = null;
		
		if (obj instanceof CommonExceptionHandler) {
			commonExceptionHandler = (CommonExceptionHandler) obj;

			if (message != null) {
				commonExceptionHandler.errorJob(message);
			} else {
				commonExceptionHandler.errorJob(type, doExceptionJob);
			}
		} else {
			throw new JobExecutionException();
		}
		
	}

}
