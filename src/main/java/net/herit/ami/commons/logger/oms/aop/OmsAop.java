package net.herit.ami.commons.logger.oms.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import net.herit.ami.commons.exception.CommonExceptionHandler;
import net.herit.ami.commons.exception.ExceptionType;
import net.herit.ami.commons.logger.context.LoggerContext;
import net.herit.ami.commons.logger.oms.field.OmsLogField;
import net.herit.ami.commons.logger.oms.log.OmsLogger;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class OmsAop {
	private final OmsLogger omsLogger;
	private final CommonExceptionHandler commonExceptionHandler;
	
	@Pointcut("@annotation(net.herit.ami.commons.logger.oms.aop.LogTrace)")
	public void LogTrace() {};
	
	@Around("LogTrace()")
	public Object omsAop(ProceedingJoinPoint pjp) throws Throwable {
		LoggerContext.set();
		
		Object obj = null;
		Method method = getMethod(pjp);
		
		if (method == null) {
			return logTracerIsNull(obj);
		}
		
		LogTracer logTracer = method.getAnnotation(LogTracer.class);
		
		if (logTracer == null) {
			return logTracerIsNull(obj);
		} else {
			omsLogger.setSvcId(logTracer.svcId());
		}
		
		obj = pjp.proceed();
		
		if (omsLogger.getOmsData(OmsLogField.RESULT_CODE).equals("")) {
			omsLogger.setResultCode(0);
		}
		
		return writeOms(obj);
	}
	
	private Method getMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException, SecurityException {
		Class<?> clz = pjp.getTarget().getClass();
		return clz.getDeclaredMethod("initJob", JobExecutionContext.class);
	}
	
	private Object logTracerIsNull(Object obj) {
		omsLogger.setResultCode(1);
		commonExceptionHandler.errorJob(ExceptionType.LOG_TRACER_IS_NOT_EXIST, false);
		omsLogger.setResultCode(ExceptionType.LOG_TRACER_IS_NOT_EXIST.getCode());
		return writeOms(obj);
	}
	
	private Object writeOms(Object obj) {
		omsLogger.writeLog();
		return obj;
	}

}
