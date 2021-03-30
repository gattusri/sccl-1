package com.ants.sccl.model;


import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="`Dumper_data`")
@Component
public class DumperData {
	
	
	@Id
	@Column(name="`Id`")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name="sensor_id")
//	private String sensorId;
	
	@Column(name="dumper_id")
	private String dumperId;
	
	@Column(name="date_time")
	private Timestamp DateTime;
	
//	@Column(name="d_time")
//	private Time d_Time;
	
	@Column(name="gps_lat")
	private Double GPSlat;
	
	@Column(name="gps_lon")
	private Double GPSlon;
	
//	@Column(name="b_time")
//	private Time B_Time;
	

	@Column(name="b_value")
	private String bluetoothValue;
	
	@Column(name="b_type")
	private String bluetoothType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDumperId() {
		return dumperId;
	}

	public void setDumperId(String dumperId) {
		this.dumperId = dumperId;
	}

	public Timestamp getDateTime() {
		return DateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		DateTime = dateTime;
	}

	public Double getGPSlat() {
		return GPSlat;
	}

	public void setGPSlat(Double gPSlat) {
		GPSlat = gPSlat;
	}

	public Double getGPSlon() {
		return GPSlon;
	}

	public void setGPSlon(Double gPSlon) {
		GPSlon = gPSlon;
	}

	public String getBluetoothValue() {
		return bluetoothValue;
	}

	public void setBluetoothValue(String bluetoothValue) {
		this.bluetoothValue = bluetoothValue;
	}

	public String getBluetoothType() {
		return bluetoothType;
	}

	public void setBluetoothType(String bluetoothType) {
		this.bluetoothType = bluetoothType;
	}

	@Override
	public String toString() {
		return "DumperData [id=" + id + ", dumperId=" + dumperId + ", DateTime=" + DateTime + ", GPSlat=" + GPSlat
				+ ", GPSlon=" + GPSlon + ", bluetoothValue=" + bluetoothValue + ", bluetoothType=" + bluetoothType
				+ "]";
	}


	
	
}
