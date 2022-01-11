package net.herit.ami.commons.logger.oms.log;

import java.util.EnumMap;
import java.util.Map;

import net.herit.ami.commons.logger.LogObject;
import net.herit.ami.commons.logger.oms.field.OmsLogField;
import net.herit.ami.commons.util.DateTimeUtil;

public class OmsObject implements LogObject {
	private static final String DELIM = "|";
	
	Map<OmsLogField, String> omsData;
    DateTimeUtil dateTimeUtil;
    
    public OmsObject() {
    	omsData = new EnumMap<>(OmsLogField.class);
    	dateTimeUtil = new DateTimeUtil();
    	
    	initOmsData();
    	
    	omsData.put(OmsLogField.REQ_TIME, dateTimeUtil.getNow_yyyyMMddHHmmssSSS());
    	omsData.put(OmsLogField.SVC_ID, "ROOKIE");
    }
    
    private void initOmsData() {
    	omsData.clear();
        for(OmsLogField field : OmsLogField.values()) {
            omsData.put(field, "");
        }
        
        omsData.put(OmsLogField.LOG_ID, "tx-" + dateTimeUtil.getNow_yyyyMMddHHmmssSSSnnn());
    }
    
    public String getOmsData(OmsLogField key) {
        return omsData.get(key);
    }
    
    protected void setOmsData(OmsLogField key, String data) {
    	omsData.put(key, data);
    }

    protected String getLogString() {
    	omsData.put(OmsLogField.RES_TIME, dateTimeUtil.getNow_yyyyMMddHHmmssSSS());
    	omsData.put(OmsLogField.LOG_TIME, dateTimeUtil.getNow_yyyyMMddHHmmssSSS());
        
        StringBuilder sb = new StringBuilder();
        
        for (OmsLogField txLogField : OmsLogField.values()) {
        	sb.append(omsData.get(txLogField)).append(DELIM);
        }
        
        return sb.toString();
    }

}
