package net.herit.ami.commons.exception.job;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

import net.herit.ami.commons.exception.ExceptionType;
import net.herit.ami.commons.logger.call.log.CallLogger;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommonExceptionJobs {
	private final CallLogger callLogger;
	
	public boolean commonExceptionJob(ExceptionType type) {
		
		Method[] methodArr = CommonExceptionJobs.class.getDeclaredMethods();
		
		String typeName = type.name().toLowerCase();
		
		for (Method method : methodArr) {
			try {
				if (typeName.equals(method.getName())) {
					Object obj = method.invoke(this);

					if (obj instanceof Boolean) {
						return ((Boolean) obj).booleanValue();
					}
					
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	@SuppressWarnings("unused")
	private boolean default_exception() {
		boolean result = true;

		callLogger.error("CommonExceptionJobs!!!");
		callLogger.error("exception is DEFAULT_EXCEPTION");
		
		return result;
	}
	

}
