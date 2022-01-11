package net.herit.ami.features.stat.job;

import java.util.List;

import org.quartz.JobExecutionContext;

import lombok.RequiredArgsConstructor;
import net.herit.ami.commons.job.QuartzJobBeans;
import net.herit.ami.commons.logger.call.log.CallLogger;
import net.herit.ami.commons.logger.oms.aop.LogTracer;
import net.herit.ami.commons.util.DateTimeUtil;
import net.herit.ami.features.stat.dto.entity.IotDeviceStatusMonthStatisticEntity;

@RequiredArgsConstructor
public class MonthStatisticsJob extends QuartzJobBeans{
	
	private final CallLogger call;
	private final DateTimeUtil dateTimeUtil;
	
	private final net.herit.ami.features.stat.repository.postgresql.IotDeviceStatusMonthStatisticRepository postgresqlMonthRepo;
	private final net.herit.ami.features.stat.repository.mysql.IotDeviceStatusMonthStatisticRepository mysqlMonthRepo;

	@Override
	@LogTracer(svcId = "ST003")
	protected void initJob(JobExecutionContext context) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			call.info("Monthjob startTime: {}", dateTimeUtil.getNow_yyyyMMddHHmmss());

			//postgreSql 데이터 조회
			List<IotDeviceStatusMonthStatisticEntity> list = postgresqlMonthRepo.selectMonthStatistics();
			call.debug("list size : {} ", list.size());

			//mysql insert
			if(!list.isEmpty()) {
				result = mysqlMonthRepo.insertMonthStatistics(list);
			}
		}catch(Exception e) {
		    result = -1;
			call.error("Monthjob error: {}", e);
		}finally {
			call.info("Monthjob endTime: {}, result : {}", dateTimeUtil.getNow_yyyyMMddHHmmss(), result);
		}
	}

}
