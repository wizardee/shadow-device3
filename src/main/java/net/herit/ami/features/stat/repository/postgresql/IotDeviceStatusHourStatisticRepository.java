package net.herit.ami.features.stat.repository.postgresql;

import java.util.List;

import net.herit.ami.commons.config.PostgreSqlDataSourceMapper;
import net.herit.ami.features.stat.dto.entity.IotDeviceStatusHourStatisticEntity;

@PostgreSqlDataSourceMapper
public interface IotDeviceStatusHourStatisticRepository {

	List<IotDeviceStatusHourStatisticEntity> selectHourStatistics();
}
