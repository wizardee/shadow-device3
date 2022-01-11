package net.herit.ami.features.stat.repository.mysql;

import java.util.List;

import org.springframework.stereotype.Component;

import net.herit.ami.commons.config.MysqlDataSourceMapper;
import net.herit.ami.features.stat.dto.entity.IotDeviceStatusMonthStatisticEntity;

@MysqlDataSourceMapper
@Component(value="mysqlIotDeviceStatusMonthStatisticRepository")
public interface IotDeviceStatusMonthStatisticRepository {
	
	int insertMonthStatistics(List<IotDeviceStatusMonthStatisticEntity> list);
}

