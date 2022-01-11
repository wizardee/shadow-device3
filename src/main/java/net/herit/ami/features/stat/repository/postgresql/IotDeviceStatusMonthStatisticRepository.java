package net.herit.ami.features.stat.repository.postgresql;

import java.util.List;

import net.herit.ami.commons.config.PostgreSqlDataSourceMapper;
import net.herit.ami.features.stat.dto.entity.IotDeviceStatusMonthStatisticEntity;

@PostgreSqlDataSourceMapper
public interface IotDeviceStatusMonthStatisticRepository {

	List<IotDeviceStatusMonthStatisticEntity> selectMonthStatistics();
}
