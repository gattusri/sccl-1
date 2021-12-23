package com.ants.sccl.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeviceRunBookInputModel {
	
	private String deviceSensorId;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp timeStamp;
	
	
	private String longitude;
	
	private String latitude;
	
	private int flag;

	
	public String getDeviceSensorId() {
		return deviceSensorId;
	}

	public void setDeviceSensorId(String deviceSensorId) {
		this.deviceSensorId = deviceSensorId;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
	

}
