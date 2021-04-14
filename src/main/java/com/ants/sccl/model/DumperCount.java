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

	private String deviceId; 
	
	private String ble_pair_id;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp time_stamp;
	
	private byte ble_status;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getBle_pair_id() {
		return ble_pair_id;
	}

	public void setBle_pair_id(String ble_pair_id) {
		this.ble_pair_id = ble_pair_id;
	}

	public Timestamp getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(Timestamp time_stamp) {
		this.time_stamp = time_stamp;
	}

	public byte getBle_status() {
		return ble_status;
	}

	public void setBle_status(byte ble_status) {
		this.ble_status = ble_status;
	}

	@Override
	public String toString() {
		return "DumperCount [deviceId=" + deviceId + ", ble_pair_id=" + ble_pair_id + ", time_stamp=" + time_stamp
				+ ", ble_status=" + ble_status + "]";
	}
	
}
