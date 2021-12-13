package com.ants.sccl.model;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="`asset`")
@Component
public class Asset {
	
	@Id
	@Column(name="`Id`")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="sensor_ID")
	private int sensor_ID;
	
	@Column(name="asset_code")
	private String assetCode;
	
	@Column(name="RFID_reader_code")
	private String rFIDReaderCode;
	
	@Column(name="ch_antenna")
	private int cH_Antenna;
	
	@Column(name="SUBCH")
	private int subch;
	
	@Column(name="gps_lat")
	private Double latitude;
	
	@Column(name="gps_lon")
	private Double longitude;
	
	//@DateTimeFormat(iso = ISO.DATE_TIME)
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="date_time")
	private Timestamp timeStamp;
	
	//private DateTim timeonw;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSensor_ID() {
		return sensor_ID;
	}

	public void setSensor_ID(int sensor_ID) {
		this.sensor_ID = sensor_ID;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getrFIDReaderCode() {
		return rFIDReaderCode;
	}

	public void setrFIDReaderCode(String rFIDReaderCode) {
		this.rFIDReaderCode = rFIDReaderCode;
	}

	public int getcH_Antenna() {
		return cH_Antenna;
	}

	public void setcH_Antenna(int cH_Antenna) {
		this.cH_Antenna = cH_Antenna;
	}

	public int getSubch() {
		return subch;
	}

	public void setSubch(int subch) {
		this.subch = subch;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Asset [id=" + id + ", sensor_ID=" + sensor_ID + ", assetCode=" + assetCode + ", rFIDReaderCode="
				+ rFIDReaderCode + ", cH_Antenna=" + cH_Antenna + ", subch=" + subch + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", timeStamp=" + timeStamp + "]";
	}


}
