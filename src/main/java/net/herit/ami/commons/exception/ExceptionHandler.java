package net.herit.ami.commons.exception;

import org.quartz.JobExecutionException;

import net.herit.ami.commons.exception.job.ExceptionJobs;
import net.herit.ami.commons.util.BeanUtil;

public class ExceptionHandler {
	
	private ExceptionHandler() {
		throw new IllegalAccessError();
	}
	
	public static void handle(Exception e) throws JobExecutionException {
		if (e instanceof CommonException) {
			CommonException ce = (CommonException) e;
			ce.errorJob();
		} else {
			Object obj = BeanUtil.getBean("exceptionJobs");
			
			if (obj instanceof ExceptionJobs) {
				ExceptionJobs exceptionJobs = (ExceptionJobs) obj;
				exceptionJobs.exceptionJob(e);
			}
		}
	}

	

}
