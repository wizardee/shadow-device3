package net.herit.ami.features.stat.repository.mysql;

import java.util.List;

import org.springframework.stereotype.Component;

import net.herit.ami.commons.config.MysqlDataSourceMapper;
import net.herit.ami.features.stat.dto.entity.IotDeviceStatusDayStatisticEntity;

@MysqlDataSourceMapper
@Component(value="mysqlIotDeviceStatusDayStatisticRepository")
public interface IotDeviceStatusDayStatisticRepository {

	int insertDayStatistics(List<IotDeviceStatusDayStatisticEntity> list);
}
