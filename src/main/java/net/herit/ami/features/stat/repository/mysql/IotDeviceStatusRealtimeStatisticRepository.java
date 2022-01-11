package net.herit.ami.features.stat.repository.mysql;

import java.util.List;

import org.springframework.stereotype.Component;

import net.herit.ami.commons.config.MysqlDataSourceMapper;
import net.herit.ami.features.stat.dto.entity.IotDeviceStatusRealtimeStatisticEntity;

@MysqlDataSourceMapper
@Component(value="mysqlIotDeviceStatusRealtimeStatisticRepository")
public interface IotDeviceStatusRealtimeStatisticRepository {

	int insertRealtimeStatistics(List<IotDeviceStatusRealtimeStatisticEntity> list);
}
