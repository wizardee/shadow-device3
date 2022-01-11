package net.herit.ami.features.stat.dto.entity.model.hura;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HuraBodyMessage {
    @JsonProperty(value = "device_id")
    String deviceId;

    @JsonProperty(value = "sid")
    String sid;

    @JsonProperty(value = "msg")
    HuraEntityMessage huraEntityMessage;

    public static HuraBodyMessage getDummy() {
        HuraBodyMessage huraBodyMessage = new HuraBodyMessage();
        huraBodyMessage.deviceId = "deviceidddddd";
        huraBodyMessage.sid = String.valueOf(System.currentTimeMillis());

        huraBodyMessage.setHuraEntityMessage(HuraEntityMessage.getDummy());

        return huraBodyMessage;
    }

}
