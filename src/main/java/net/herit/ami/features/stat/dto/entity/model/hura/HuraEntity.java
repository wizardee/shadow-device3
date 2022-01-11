package net.herit.ami.features.stat.dto.entity.model.hura;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HuraEntity {
    @JsonProperty(value = "sv")
    String sv;

    @JsonProperty(value = "n")
    String resourceUri;

    @JsonProperty(value = "ti")
    String ti;
}
