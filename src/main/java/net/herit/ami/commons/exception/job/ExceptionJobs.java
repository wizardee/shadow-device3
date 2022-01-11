package net.herit.ami.commons.exception.job;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

import net.herit.ami.commons.logger.call.log.CallLogger;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExceptionJobs {
	private final CallLogger callLogger;
	
	public boolean exceptionJob(Exception e) {
		
		Method[] methodArr = ExceptionJobs.class.getDeclaredMethods();
		
		for (Method method : methodArr) {
			if (method.getParameterTypes()[0] == e.getClass()) {
				try {
					Object obj = method.invoke(this, e);
					
					if (obj instanceof Boolean) {
						return ((Boolean)obj).booleanValue();
					}
					
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				}
			}
		}
		e.printStackTrace();
		return false;
	}
	
	@SuppressWarnings("unused")
	private boolean nullPointerException(NullPointerException e) {
		callLogger.info("null pointer exception Test!");
		
		
		return true;
	}
	
	
	
	
	
}
