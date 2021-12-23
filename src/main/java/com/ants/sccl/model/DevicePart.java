package com.ants.sccl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="Device_Part")
public class DevicePart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long id;
	
	@Column(name="Device_Name")
	private String deviceName;
	
	@Column(name="Part_Name")
	private String devicePartName;
	
	
	@Column(name="Replacement_Status")
	private String replacementStatus;
	
	
	//@JsonFormat(pattern = "HH:mm:ss")
	@Column(name="Run_Hours")
	private String runHours;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDevicePartName() {
		return devicePartName;
	}

	public void setDevicePartName(String devicePartName) {
		this.devicePartName = devicePartName;
	}

	public String getReplacementStatus() {
		return replacementStatus;
	}

	public void setReplacementStatus(String replacementStatus) {
		this.replacementStatus = replacementStatus;
	}
	
	

	public String getRunHours() {
		return runHours;
	}

	public void setRunHours(String runHours) {
		this.runHours = runHours;
	}
}
