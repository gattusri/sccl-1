package com.ants.sccl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="`Dumper_Device_Mapping`")
@Component
public class DeviceMapping {
	
	@Id
	@Column(name="device_Id")
	private String deviceId;
	
	@Column(name="category")
	private String deviceCategory;
	
	@Column(name="mapping_Id")
	private String deviceMappingID;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceCategory() {
		return deviceCategory;
	}

	public void setDeviceCategory(String deviceCategory) {
		this.deviceCategory = deviceCategory;
	}

	public String getDeviceMappingID() {
		return deviceMappingID;
	}

	public void setDeviceMappingID(String deviceMappingID) {
		this.deviceMappingID = deviceMappingID;
	}
	
	
	
}
