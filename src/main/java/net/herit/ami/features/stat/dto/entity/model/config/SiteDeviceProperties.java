package net.herit.ami.features.stat.dto.entity.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SiteDeviceProperties {
    @JsonProperty(value = "sites")
    List<DeviceProperties> deviceProperties = new ArrayList<>();
}
