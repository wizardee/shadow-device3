package net.herit.ami.features.stat.service;

import java.time.LocalDateTime;
import java.util.HashMap;

import net.herit.ami.features.stat.property.HourStatisticsProperty;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.herit.ami.commons.logger.call.log.CallLogger;
import net.herit.ami.commons.logger.oms.aop.LogTracer;
import net.herit.ami.commons.service.JobService;
import net.herit.ami.features.stat.job.HourStatisticsJob;
import net.herit.ami.features.stat.property.ShadowDeviceProperty;

@Service
@RequiredArgsConstructor
public class HourStatisticsJobService implements JobService{

	private static final String NAME = "HourStatistics";
	private static final String GROUP = "STAT";
	
	private final HourStatisticsProperty property;
	private final Scheduler scheduler;
	private final CallLogger call;
	
	@Override
	@LogTracer(svcId = "ST001")
	public void initBatch() {

		JobDetail hourStatisticsJob = buildJobDetail(HourStatisticsJob.class, NAME, GROUP, new HashMap<>());

		try {
			if(property.isEnable())
				scheduler.scheduleJob(hourStatisticsJob, buildJobTrigger(property.getSchedule()));
		} catch (SchedulerException e) {
			call.error("FileReaderService :: scheduler error :: {}", LocalDateTime.now());
		}
		
		
	}
}
