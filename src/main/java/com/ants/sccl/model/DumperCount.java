package com.ants.sccl.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Component
public class DumperCount {

	private String dumper_ID; 
	
	private String bluetooth_device_ID;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp timeStamp;
	
	private byte status;

	public String getDumper_ID() {
		return dumper_ID;
	}

	public void setDumper_ID(String dumper_ID) {
		this.dumper_ID = dumper_ID;
	}

	public String getBluetooth_device_ID() {
		return bluetooth_device_ID;
	}

	public void setBluetooth_device_ID(String bluetooth_device_ID) {
		this.bluetooth_device_ID = bluetooth_device_ID;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DumperCount [dumper_ID=" + dumper_ID + ", bluetooth_device_ID=" + bluetooth_device_ID + ", timeStamp="
				+ timeStamp + ", status=" + status + "]";
	}
	
}
