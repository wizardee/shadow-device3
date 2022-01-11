package net.herit.ami.commons.logger.call.log;

import java.util.EnumMap;
import java.util.Map;

import net.herit.ami.commons.logger.LogObject;
import net.herit.ami.commons.logger.call.field.CallLogField;
import net.herit.ami.commons.util.DateTimeUtil;

public class CallObject implements LogObject{

	Map<CallLogField, String> callData;
    DateTimeUtil dateTimeUtil;
    
    public CallObject() {
    	callData = new EnumMap<>(CallLogField.class);
    	dateTimeUtil = new DateTimeUtil();
    }
    
    public String getCallData(CallLogField key) {
    	return callData.get(key);
    }
    
    protected void setCallData(CallLogField key, String value) {
    	callData.put(key, value);
    }

    public void setLogId(String logId) {
    	callData.put(CallLogField.LOG_ID, logId);
    }
    
    public void setLogLevel(String logLevel) {
    	callData.put(CallLogField.LOG_LEVEL, logLevel);
    }

    public String getLogString() {
    	callData.put(CallLogField.LOG_TIME, dateTimeUtil.getNow_yyyyMMddHHmmssSSS());
        
        StringBuilder sb = new StringBuilder();
        
        for (CallLogField callLogField : CallLogField.values()) {
        	sb.append("[").append(callData.get(callLogField)).append("]");
        }
        
        return sb.toString();
    }
    
}
