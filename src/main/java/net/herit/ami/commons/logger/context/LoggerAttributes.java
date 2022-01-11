package net.herit.ami.commons.logger.context;

import java.util.EnumMap;
import java.util.Map;

import net.herit.ami.commons.logger.LogObject;
import net.herit.ami.commons.logger.LoggerType;
import net.herit.ami.commons.logger.call.log.CallObject;
import net.herit.ami.commons.logger.oms.field.OmsLogField;
import net.herit.ami.commons.logger.oms.log.OmsObject;

public class LoggerAttributes {
    private Map<LoggerType, LogObject> txAttribMap = new EnumMap<>(LoggerType.class);

    public LoggerAttributes() {
    	OmsObject omsObject = new OmsObject();
    	CallObject callObject = new CallObject();
    	
    	callObject.setLogId(omsObject.getOmsData(OmsLogField.LOG_ID));
    	
		put(LoggerType.OMS_LOGGER, omsObject);
		put(LoggerType.CALL_LOGGER, callObject);
    }
    
    public Object get(LoggerType key) {
        if(txAttribMap.containsKey(key)) {
            return txAttribMap.get(key);
        }else {
            return null;
        }
    }

    public LoggerAttributes put(LoggerType key, LogObject value) {
        txAttribMap.put(key,value);
        return this;
    }

}
