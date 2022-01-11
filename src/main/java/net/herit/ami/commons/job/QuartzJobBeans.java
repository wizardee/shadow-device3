package net.herit.ami.commons.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import net.herit.ami.commons.exception.ExceptionHandler;
import net.herit.ami.commons.logger.oms.aop.LogTrace;

public abstract class QuartzJobBeans extends QuartzJobBean {
	
	@Override
	@LogTrace
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			initJob(context);
		} catch(Exception e) {
			ExceptionHandler.handle(e);
		} 
	}
	
	protected abstract void initJob(JobExecutionContext context);
}
