package net.herit.ami.commons.service;

import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import net.herit.ami.commons.job.QuartzJobBeans;

public interface JobService {

	void initBatch();
	
	default Trigger buildJobTrigger(String scheduleExp) {
		return TriggerBuilder.newTrigger()
				.withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
	}
	
	default JobDetail buildJobDetail(Class<? extends QuartzJobBeans> job, String name, String group, Map<String, ?> param) {
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.putAll(param);
		
		return JobBuilder.newJob(job).withIdentity(name, group).usingJobData(jobDataMap)
				.build();
	}
}
