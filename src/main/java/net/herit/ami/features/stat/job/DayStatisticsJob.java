package net.herit.ami.features.stat.job;

import java.util.List;

import org.quartz.JobExecutionContext;

import lombok.RequiredArgsConstructor;
import net.herit.ami.commons.job.QuartzJobBeans;
import net.herit.ami.commons.logger.call.log.CallLogger;
import net.herit.ami.commons.logger.oms.aop.LogTracer;
import net.herit.ami.commons.util.DateTimeUtil;
import net.herit.ami.features.stat.dto.entity.IotDeviceStatusDayStatisticEntity;

@RequiredArgsConstructor
public class DayStatisticsJob extends QuartzJobBeans{

	private final CallLogger call;
	private final DateTimeUtil dateTimeUtil;
	
	private final net.herit.ami.features.stat.repository.postgresql.IotDeviceStatusDayStatisticRepository postgresqlDayRepo;
	private final net.herit.ami.features.stat.repository.mysql.IotDeviceStatusDayStatisticRepository mysqlDayRepo;
	
	@Override
	@LogTracer(svcId = "ST002")
	protected void initJob(JobExecutionContext context) {
		// TODO Auto-generated method stub
		call.info("Dayjob startTime: {}", dateTimeUtil.getNow_yyyyMMddHHmmss());
		
		//postgreSql 데이터 조회
		List<IotDeviceStatusDayStatisticEntity> list = postgresqlDayRepo.selectDayStatistics();
		
		call.debug("list size : {} ", list.size());		

		int result = 0;

		//mysql insert
		if(!list.isEmpty()) {
			result = mysqlDayRepo.insertDayStatistics(list);
		}		 

		call.info("Dayjob endTime: {}, result : {} ", dateTimeUtil.getNow_yyyyMMddHHmmss(), result);
	}

	
	
}
