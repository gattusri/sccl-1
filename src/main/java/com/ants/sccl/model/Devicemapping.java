package com.ants.sccl.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Devicemapping {
	
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
