package net.herit.ami.commons.exception;

import org.springframework.stereotype.Component;

import net.herit.ami.commons.exception.job.CommonExceptionJobs;
import net.herit.ami.commons.logger.call.log.CallLogger;
import net.herit.ami.commons.logger.oms.log.OmsLogger;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommonExceptionHandler {
	private final OmsLogger omsLogger;
	private final CallLogger callLogger;
	private final CommonExceptionJobs commonExceptionJobs;
	
	public void errorJob(String message) {
		callLogger.error(message);
	}
	
	public void errorJob(ExceptionType type, boolean doExceptionType) {
		omsLogger.setResultCode(type.getCode());
		
		if (doExceptionType && type.isExceptionJob()) {
			commonExceptionJobs.commonExceptionJob(type);
		} else {
			callLogger.error(type.getMessage());
		}
	}
}
