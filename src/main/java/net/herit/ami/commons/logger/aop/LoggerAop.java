package net.herit.ami.commons.logger.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;

import net.herit.ami.commons.logger.call.log.CallObject;
import net.herit.ami.commons.logger.context.LoggerContext;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggerAop {

	@Pointcut("within(net.herit.ami.commons.logger.call.log.CallLogger) || within(net.herit.ami.commons.logger.error.log.ErrorLogger)")
	public void DefaultLogger() {};

	@Before("DefaultLogger()")
	public void beforeLog(JoinPoint joinPoint) {
		CallObject callObj = LoggerContext.getCallLogger();
		
		String level = joinPoint.getSignature().getName();
		
		if (callObj == null) return;
		callObj.setLogLevel(level);
		MDC.put("toLog", callObj.getLogString());
	}
	

}
