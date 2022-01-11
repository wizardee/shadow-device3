package net.herit.ami.commons.logger.oms.log;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import net.herit.ami.commons.logger.context.LoggerContext;
import net.herit.ami.commons.logger.oms.field.OmsLogField;
import net.herit.ami.commons.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OmsLogger {
	private final DateTimeUtil dateTimeUtil;

    public void writeLog() {
        OmsObject oms = LoggerContext.getTxLogger();
    	log.info(oms.getLogString());
    }
    
    public void setResultCode(int resultCode) {
        addOmsData(OmsLogField.RESULT_CODE, String.valueOf(resultCode));
    }

    public void setLogTime() {
        addOmsData(OmsLogField.LOG_TIME, dateTimeUtil.getNow_yyyyMMddHHmmssSSS());
    }

    public void setLogTime(LocalDateTime date) {
        addOmsData(OmsLogField.LOG_TIME, dateTimeUtil.get_yyyyMMddHHmmssSSS(date));
    }

    public void setReqTime() {
        addOmsData(OmsLogField.REQ_TIME, dateTimeUtil.getNow_yyyyMMddHHmmssSSS());
    }

    public void setReqTime(LocalDateTime date) {
        addOmsData(OmsLogField.REQ_TIME, dateTimeUtil.get_yyyyMMddHHmmssSSS(date));
    }
    
    public void setResTime(LocalDateTime date) {
        addOmsData(OmsLogField.RES_TIME, dateTimeUtil.get_yyyyMMddHHmmssSSS(date));
    }
    
    public void setSvcId(String svcId) {
        addOmsData(OmsLogField.SVC_ID, svcId);
    }
    
    private void addOmsData(OmsLogField key, String value) {
        OmsObject oms = LoggerContext.getTxLogger();
        oms.setOmsData(key, value);
    }
    
    public String getOmsData(OmsLogField key) {
        OmsObject oms = LoggerContext.getTxLogger();
        return oms.getOmsData(key);
    }
    
    
    
}
