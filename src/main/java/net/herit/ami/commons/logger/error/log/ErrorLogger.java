package net.herit.ami.commons.logger.error.log;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ErrorLogger {
	public void error(String msg) {
		log.error(msg);
	}

	public void error(String format, Object arg) {
		log.error(format, arg);
	}

	public void error(String format, Object arg1, Object arg2) {
		log.error(format, arg1, arg2);
	}

	public void error(String format, Object... arguments) {
		log.error(format, arguments);
	}

	public void error(String msg, Throwable t) {
		log.error(msg, t);
	}
}
