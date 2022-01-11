package net.herit.ami.features.stat.repository.mysql;

import net.herit.ami.commons.config.MysqlDataSourceMapper;
import net.herit.ami.features.stat.dto.entity.IotDeviceStatusHourLastStatisticEntity;
import net.herit.ami.features.stat.dto.entity.IotDeviceStatusRealtimeLastStatisticEntity;
import net.herit.ami.features.stat.dto.entity.IotDeviceStatusRealtimeStatisticEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@MysqlDataSourceMapper
@Component(value="mysqlIotDeviceStatusRealtimeLastStatisticRepository")
public interface IotDeviceStatusRealtimeLastStatisticRepository {
	IotDeviceStatusRealtimeLastStatisticEntity selectRealtimeLastStatistics(String deviceId, String resourceUri);
	IotDeviceStatusHourLastStatisticEntity selectHourLastStatistics(String deviceId, String resourceUri);
}
