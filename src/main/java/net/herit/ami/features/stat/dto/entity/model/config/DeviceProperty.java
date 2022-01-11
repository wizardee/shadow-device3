package net.herit.ami.features.stat.dto.entity.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DeviceProperty {
    @JsonProperty(value = "use")
    String use;

    @JsonProperty(value = "device_id")
    String deviceId;

    @JsonProperty(value = "vdevice_id")
    String vDdeviceId;

    @JsonProperty(value = "instance_ids")
    List<String> instanceIds = new ArrayList<>();

    @JsonProperty(value = "object_ids")
    List<String> objectIds = new ArrayList<>();

    @JsonProperty(value = "default_value")
    String defaultValue;
}
