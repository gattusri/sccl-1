package com.ants.sccl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Device_Mapping")
public class DeviceMappingModel {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="Device_Sensor_Id")
	private String deviceSensorId;
	
	@Column(name="Device_Name")
	private String deviceName;
	
	@Column(name="Device_Model")
	private String deviceModel;
	
	@Column(name="Device_Type")
	private String deviceType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeviceSensorId() {
		return deviceSensorId;
	}

	public void setDeviceSensorId(String deviceSensorId) {
		this.deviceSensorId = deviceSensorId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	public String toString() {
		return "DeviceMappingModel [id=" + id + ", deviceSensorId=" + deviceSensorId + ", deviceName=" + deviceName
				+ ", deviceModel=" + deviceModel + ", deviceType=" + deviceType + "]";
	}
}
