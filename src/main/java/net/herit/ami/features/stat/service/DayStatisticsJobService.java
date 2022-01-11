package net.herit.ami.features.stat.service;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.herit.ami.commons.logger.call.log.CallLogger;
import net.herit.ami.commons.logger.oms.aop.LogTracer;
import net.herit.ami.commons.service.JobService;
import net.herit.ami.features.stat.job.DayStatisticsJob;
import net.herit.ami.features.stat.property.DayStatisticsProperty;

@Service
@RequiredArgsConstructor
public class DayStatisticsJobService implements JobService{

	private static final String NAME = "DayStatistics";
	private static final String GROUP = "STAT";
	
	private final DayStatisticsProperty property;
	private final Scheduler scheduler;
	private final CallLogger call;
	
	@Override
	@LogTracer(svcId = "ST002")
	public void initBatch() {

		JobDetail dayStatisticsJob = buildJobDetail(DayStatisticsJob.class, NAME, GROUP, new HashMap<>());

		try {
			if(property.isEnable())
				scheduler.scheduleJob(dayStatisticsJob, buildJobTrigger(property.getSchedule()));
		} catch (SchedulerException e) {
			call.error("FileReaderService :: scheduler error :: {}", LocalDateTime.now());
		}
		
		
	}
}
