package net.herit.ami.commons.config;

import net.herit.ami.features.stat.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class QuartzStarter {
	private final RealtimeStatisticsJobService realtimeJobService;
	private final HourStatisticsJobService hourJobService;
	private final DayStatisticsJobService dayJobService; 
	private final MonthStatisticsJobService monthJobService;
	private final ShadowDeviceJobService shadowDeviceJobService;

	@Bean
	public void startBatch() {
		realtimeJobService.initBatch();
		hourJobService.initBatch();
		dayJobService.initBatch();
		monthJobService.initBatch();
		shadowDeviceJobService.initBatch();
	}

}
