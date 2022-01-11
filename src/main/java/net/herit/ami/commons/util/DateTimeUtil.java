package net.herit.ami.commons.util;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import net.herit.ami.commons.dto.DateTimeFormatterObject;

@Component
public class DateTimeUtil {
    
	public String generateTimeStamp(String format) {
		return generateTimeStamp(LocalDateTime.now(), format);
	}
    
	public String generateTimeStamp(LocalDate localDate, String format) {
		return localDate.format(DateTimeFormatter.ofPattern(format));
	}
    
	public String generateTimeStamp(LocalDateTime localDateTime, String format) {
		return localDateTime.format(DateTimeFormatter.ofPattern(format));
	}

	public long getExecuteSecond(LocalDateTime startTime, LocalDateTime endTime) {
		Duration duration = Duration.between(startTime, endTime);
		return duration.getSeconds();
	}
	
    public String getNow_yyyyMMddHHmmssSSS() {
        return get_yyyyMMddHHmmssSSS(LocalDateTime.now());
    }

    public String getNow_yyyyMMddHHmmss() {
        return get_yyyyMMddHHmmss(LocalDateTime.now());
    }
    
    public String getNow_yyyyMMddHHmm() {
        return get_yyyyMMddHHmmss(LocalDateTime.now());
    }
    
    public String getNow_yyyyMMdd() {
    	return get_yyyyMMdd(LocalDate.now());
    }

    public String get_yyyyMMddHHmmssSSS(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatterObject.yyyyMMddHHmmssSSS);
    }

    public String get_yyyyMMddHHmmss(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatterObject.yyyyMMddHHmmss);
    }

    public String get_yyyyMMddHHmm(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatterObject.yyyyMMddHHmm);
    }

    public String get_yyyyMMdd(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatterObject.yyyyMMdd);
    }

    public String get_yyyyMMdd(LocalDate localDate) {
        return localDate.format(DateTimeFormatterObject.yyyyMMdd);
    }

    public long getNow_epochMillis() {
        return Instant.now().toEpochMilli();
    }

    public long getNow_epochNano() {
        return System.nanoTime();
    }
    
    public String getNow_yyyyMMddHHmmssSSSnnn() {
        return LocalDateTime.now().format(DateTimeFormatterObject.yyyyMMddHHmmssSSSnnn);
    }
}
