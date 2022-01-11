package net.herit.ami.features.stat.dto.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IotDeviceStatusHourLastStatisticEntity implements StatisticEntity {

	private String deviceId;
	
	private String resourceUri;

	private String resourceName;
	
	private String statType;
	
	private String startValue;
	
	private String previousValue;
	
	private String statValue;
	
	private String statDate;
	
	private String createTime;
	
	private String updateTime;
}
