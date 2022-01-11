package net.herit.ami.features.stat.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix="quartz.hour-statistics-property")
public class HourStatisticsProperty {
    private boolean enable;
    private String schedule;
}
