package net.herit.ami.features.stat.repository.postgresql;

import java.util.List;

import net.herit.ami.commons.config.PostgreSqlDataSourceMapper;
import net.herit.ami.features.stat.dto.entity.IotDeviceStatusRealtimeStatisticEntity;
import org.apache.ibatis.annotations.Param;

@PostgreSqlDataSourceMapper
public interface IotDeviceStatusRealtimeStatisticRepository {

	List<IotDeviceStatusRealtimeStatisticEntity> selectRealtimeStatistics(
			@Param("selectInterval") int selectInterval);
}
