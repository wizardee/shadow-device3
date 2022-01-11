package net.herit.ami.features.stat.dto.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class IotDeviceStatusMonthStatisticEntity {

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
