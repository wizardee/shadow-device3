package net.herit.ami.features.stat.job;

import java.util.List;

import org.quartz.JobExecutionContext;

import lombok.RequiredArgsConstructor;
import net.herit.ami.commons.job.QuartzJobBeans;
import net.herit.ami.commons.logger.call.log.CallLogger;
import net.herit.ami.commons.logger.oms.aop.LogTracer;
import net.herit.ami.commons.util.DateTimeUtil;
import net.herit.ami.features.stat.dto.entity.IotDeviceStatusHourStatisticEntity;

@RequiredArgsConstructor
public class HourStatisticsJob extends QuartzJobBeans{

	private final CallLogger call;
	private final DateTimeUtil dateTimeUtil;
	
	private final net.herit.ami.features.stat.repository.postgresql.IotDeviceStatusHourStatisticRepository postgresqlhourRepo;
	private final net.herit.ami.features.stat.repository.mysql.IotDeviceStatusHourStatisticRepository mysqlHourRepo;
	
	@Override
	@LogTracer(svcId = "ST001")
	protected void initJob(JobExecutionContext context) {
		
		call.info("Hourjob startTime: {}", dateTimeUtil.getNow_yyyyMMddHHmmss());
		
		//postgreSql 데이터 조회
		List<IotDeviceStatusHourStatisticEntity> list = postgresqlhourRepo.selectHourStatistics();
		
		call.debug("list size : {} ", list.size());
		
		int result = 0;
		
		//mysql insert
		if(!list.isEmpty()) {
			result = mysqlHourRepo.insertHourStatistics(list);
		}

		call.info("Hourjob endTime: {}, result : {} ", dateTimeUtil.getNow_yyyyMMddHHmmss(), result);
	}
}
