package net.herit.ami.features.stat.dto.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IotDeviceStatusRealtimeLastStatisticEntity implements StatisticEntity {

	private String deviceId;
	
	private String resourceUri;
	
	private String objectId;
	
	private String instanceId;
	
	private String resourceId;
	
	private String resourceName;
	
	private String statType;
	
	private String startValue;
	
	private String previousValue;
	
	private String statValue;
	
	private String statDate;
	
	private String createTime;
	
	private String updateTime;
}
