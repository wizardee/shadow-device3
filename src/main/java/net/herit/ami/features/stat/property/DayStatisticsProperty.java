package net.herit.ami.features.stat.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix="quartz.day-statistics-property")
public class DayStatisticsProperty {
	private boolean enable;
	private String schedule;
}
