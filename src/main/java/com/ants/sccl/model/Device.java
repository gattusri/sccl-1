package com.ants.sccl.model;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="`Raw_Data`")
@Component
public class Device {
	
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;

	@Column(name="Device_Id")
	private String deviceId;
	

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="Date_Time")
	private Timestamp time_stamp;
	
	@Column(name="BLE_Status")
	private int ble_status;
	
	@Column(name="BLE_Pair_Id")
	private String ble_pair_id;

	
	@Column(name="Ignition_Status")
	private String ignition_status;
	
	@Column(name="Engine_RPM")
	private int engine_rpm;
	
	@Column(name="Engine_Temp1")
	private float engine_temp1;
	
	@Column(name="Engine_Temp2")
	private float engine_temp2;
	
	@Column(name="Fuel_Level")
	private int fuel_level;
	
	@Column(name="System_Volt")
	private float sys_volt;
	
	@Column(name="Battery_Volt")
	private float bat_olt;
	
	@Column(name="Latitude")
	private String latitude;
	
	@Column(name="Longitude")
	private String longitude;
	
	@Column(name="Software_Version")
	private float sw_ver;
	
	@Column(name="Type_Of_Load_Unload")
	private String type_of_Load_or_unload;
	
	@Column(name="Point_Of_Loading_Unloading")
	private  String  point_of_loading_unloading;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Timestamp getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(Timestamp time_stamp) {
		this.time_stamp = time_stamp;
	}

	public int getBle_status() {
		return ble_status;
	}

	public void setBle_status(int ble_status) {
		this.ble_status = ble_status;
	}

	public String getBle_pair_id() {
		return ble_pair_id;
	}

	public void setBle_pair_id(String ble_pair_id) {
		this.ble_pair_id = ble_pair_id;
	}

	public String getIgnition_status() {
		return ignition_status;
	}

	public void setIgnition_status(String ignition_status) {
		this.ignition_status = ignition_status;
	}

	public int getEngine_rpm() {
		return engine_rpm;
	}

	public void setEngine_rpm(int engine_rpm) {
		this.engine_rpm = engine_rpm;
	}

	public float getEngine_temp1() {
		return engine_temp1;
	}

	public void setEngine_temp1(float engine_temp1) {
		this.engine_temp1 = engine_temp1;
	}

	public float getEngine_temp2() {
		return engine_temp2;
	}

	public void setEngine_temp2(float engine_temp2) {
		this.engine_temp2 = engine_temp2;
	}

	public int getFuel_level() {
		return fuel_level;
	}

	public void setFuel_level(int fuel_level) {
		this.fuel_level = fuel_level;
	}

	public float getSys_volt() {
		return sys_volt;
	}

	public void setSys_volt(float sys_volt) {
		this.sys_volt = sys_volt;
	}

	public float getBat_olt() {
		return bat_olt;
	}

	public void setBat_olt(float bat_olt) {
		this.bat_olt = bat_olt;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public float getSw_ver() {
		return sw_ver;
	}

	public void setSw_ver(float sw_ver) {
		this.sw_ver = sw_ver;
	}

	public String getType_of_Load_or_unload() {
		return type_of_Load_or_unload;
	}

	public void setType_of_Load_or_unload(String type_of_Load_or_unload) {
		this.type_of_Load_or_unload = type_of_Load_or_unload;
	}

	public String getPoint_of_loading_unloading() {
		return point_of_loading_unloading;
	}

	public void setPoint_of_loading_unloading(String point_of_loading_unloading) {
		this.point_of_loading_unloading = point_of_loading_unloading;
	}

}
