package net.herit.ami.features.stat.dto.entity.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DeviceProperties {
    @JsonProperty(value = "name")
    String name;

    @JsonProperty(value = "url")
    String url;

    @JsonProperty(value = "devices")
    List<DeviceProperty> deviceDataConfigs = new ArrayList<>();
}
