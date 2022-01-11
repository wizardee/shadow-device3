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
import net.herit.ami.features.stat.job.MonthStatisticsJob;
import net.herit.ami.features.stat.property.MonthStatisticsProperty;

@Service
@RequiredArgsConstructor
public class MonthStatisticsJobService implements JobService{

	private static final String NAME = "MonthStatistics";
	private static final String GROUP = "STAT";
	
	private final MonthStatisticsProperty property;
	private final Scheduler scheduler;
	private final CallLogger call;
	
	@Override
	@LogTracer(svcId = "ST003")
	public void initBatch() {

		JobDetail monthStatisticsJob = buildJobDetail(MonthStatisticsJob.class, NAME, GROUP, new HashMap<>());

		try {
			if(property.isEnable())
				scheduler.scheduleJob(monthStatisticsJob, buildJobTrigger(property.getSchedule()));
		} catch (SchedulerException e) {
			call.error("FileReaderService :: scheduler error :: {}", LocalDateTime.now());
		}
		
		
	}
}
