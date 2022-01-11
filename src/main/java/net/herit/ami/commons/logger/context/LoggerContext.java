package net.herit.ami.commons.logger.context;

import net.herit.ami.commons.logger.LoggerType;
import net.herit.ami.commons.logger.call.log.CallObject;
import net.herit.ami.commons.logger.oms.log.OmsObject;

public class LoggerContext {
    private static ThreadLocal<LoggerAttributes> threadLocal;
    
    static {
    	threadLocal = new InheritableThreadLocal<>();
    }
    
    private LoggerContext() {
    	throw new IllegalAccessError();
    }

    public static LoggerAttributes getTxAttributes() {
        return threadLocal.get();
    }

    public static OmsObject getTxLogger() {
        return (OmsObject) threadLocal.get().get(LoggerType.OMS_LOGGER);
    }

    public static CallObject getCallLogger() {
        return (CallObject) threadLocal.get().get(LoggerType.CALL_LOGGER);
    }
    
    public static void remove() {
        threadLocal.remove();
    }
    
    public static void set() {
    	threadLocal.set(new LoggerAttributes());
    }
    
}
