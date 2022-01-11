package net.herit.ami.features.stat.job;

import java.util.List;

import org.quartz.JobExecutionContext;

import lombok.RequiredArgsConstructor;
import net.herit.ami.commons.job.QuartzJobBeans;
import net.herit.ami.commons.logger.call.log.CallLogger;
import net.herit.ami.commons.logger.oms.aop.LogTracer;
import net.herit.ami.commons.util.DateTimeUtil;
import net.herit.ami.features.stat.dto.entity.IotDeviceStatusRealtimeStatisticEntity;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class RealtimeStatisticsJob extends QuartzJobBeans{

	private final CallLogger call;
	private final DateTimeUtil dateTimeUtil;
	
	private final net.herit.ami.features.stat.repository.postgresql.IotDeviceStatusRealtimeStatisticRepository postgresqlDayRepo;
	private final net.herit.ami.features.stat.repository.mysql.IotDeviceStatusRealtimeStatisticRepository mysqlDayRepo;

	@Value("${select-stat.realtime-interval-minute}")
    int selectInvterval;
	
	@Override
	@LogTracer(svcId = "ST000")
	protected void initJob(JobExecutionContext context) {
		// TODO Auto-generated method stub
		call.info("Realtimejob startTime: {}", dateTimeUtil.getNow_yyyyMMddHHmmss());
		
		//postgreSql 데이터 조회
		List<IotDeviceStatusRealtimeStatisticEntity> list = postgresqlDayRepo.selectRealtimeStatistics(selectInvterval);
		
		call.debug("list size : {} ", list.size());
		
		int result = 0;
		
		//mysql insert
		if(!list.isEmpty()) {
			result = mysqlDayRepo.insertRealtimeStatistics(list);
		}

		call.info("Realtimejob endTime: {}, result : {}", dateTimeUtil.getNow_yyyyMMddHHmmss(), result);
	}

	
	
}
