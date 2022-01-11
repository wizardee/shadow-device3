package net.herit.ami.features.stat.dto.entity.model.hura;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HuraEntityMessage {
    @JsonProperty(value = "o")
    String operation = "n";

    @JsonProperty(value = "ti")
    String ti = String.valueOf(System.currentTimeMillis());

    @JsonProperty(value = "e")
    List<HuraEntity> huraEntities = new ArrayList<>();

    public static HuraEntityMessage getDummy() {
        List<HuraEntity> dummyEntities = new ArrayList<>();
        dummyEntities.add(
                HuraEntity.builder()
                        .resourceUri("/300/111222/0")
                        .sv("10")
                        .ti("1111")
                        .build()
        );

        HuraEntityMessage huraEntityMessage = new HuraEntityMessage();
        huraEntityMessage.operation = "n";
        huraEntityMessage.ti = String.valueOf(System.currentTimeMillis());
        huraEntityMessage.setHuraEntities(dummyEntities);
        return huraEntityMessage;
    }
}
