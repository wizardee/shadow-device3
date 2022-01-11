package net.herit.ami.commons.logger;

import java.util.Map;

import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import ch.qos.logback.core.util.CachingDateFormatter;

public class DefaultLayout extends LayoutBase<ILoggingEvent> {
	
	CachingDateFormatter cachingDateFormatter = new CachingDateFormatter("yyyy-MM-dd HH:mm:ss.SSS");
	ThrowableProxyConverter tpc = new ThrowableProxyConverter();
	
	@Override
	public void start() {
		tpc.start();
		super.start();
	}

	@Override
	public String doLayout(ILoggingEvent event) {
		if (!isStarted()) {
			return CoreConstants.EMPTY_STRING;
		}
		
		StringBuilder sb = new StringBuilder();
		
		Map<String, String> map = event.getMDCPropertyMap();
		
		sb.append(map.get("toLog"));
		sb.append(event.getFormattedMessage());
		sb.append(CoreConstants.LINE_SEPARATOR);

		IThrowableProxy tp = event.getThrowableProxy();

		if (tp != null) {
			String stackTrace = tpc.convert(event);
			sb.append(stackTrace);
		}

		return sb.toString();
	}

}
