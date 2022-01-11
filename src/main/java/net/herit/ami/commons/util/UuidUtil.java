package net.herit.ami.commons.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UuidUtil {
    
    public String getUuid() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "").substring(0, 9);
		return uuid;
    }
}
