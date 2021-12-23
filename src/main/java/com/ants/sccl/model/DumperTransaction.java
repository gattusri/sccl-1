package com.ants.sccl.model;


import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="`Dumper_Transaction`")
@Component
public class DumperTransaction {

	@Id
	@Column(name="`Id`")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="dumper_id")
	private String dumperId;

	@Column(name="load_device_value")
	private String loadDeviceValue;

	@Column(name="Load_Start_time")
	private Timestamp loadStartTime;

	@Column(name="Load_End_time")
	private Timestamp loadEndTime;

	@Column(name="unload_device_value")
	private String unloadDeviceValue;

	@Column(name="unload_start_time")
	private Timestamp unloadStartTime;

	@Column(name="unload_end_time")
	private Timestamp unloadEndTime;

	@Column(name="status")
	private String status;

	private Date date;


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

	public String getLoadDeviceValue() {
		return loadDeviceValue;
	}

	public void setLoadDeviceValue(String loadDeviceValue) {
		this.loadDeviceValue = loadDeviceValue;
	}

	public Timestamp getLoadStartTime() {
		return loadStartTime;
	}

	public void setLoadStartTime(Timestamp loadStartTime) {
		this.loadStartTime = loadStartTime;
	}


	public Timestamp getLoadEndTime() {
		return loadEndTime;
	}

	public void setLoadEndTime(Timestamp loadEndTime) {
		this.loadEndTime = loadEndTime;
	}

	public String getUnloadDeviceValue() {
		return unloadDeviceValue;
	}

	public void setUnloadDeviceValue(String unloadDeviceValue) {
		this.unloadDeviceValue = unloadDeviceValue;
	}

	public Timestamp getUnloadStartTime() {
		return unloadStartTime;
	}

	public void setUnloadStartTime(Timestamp unloadStartTime) {
		this.unloadStartTime = unloadStartTime;
	}

	public Timestamp getUnloadEndTime() {
		return unloadEndTime;
	}

	public void setUnloadEndTime(Timestamp unloadEndTime) {
		this.unloadEndTime = unloadEndTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public Date getDate() {
		return date;
	}


	@Override
	public String toString() {
		return "DumperTransaction [id=" + id + ", dumperId=" + dumperId + ", loadDeviceValue=" + loadDeviceValue
				+ ", loadStartTime=" + loadStartTime + ", loadEndTime=" + loadEndTime + ", unloadDeviceValue="
				+ unloadDeviceValue + ", unloadStartTime=" + unloadStartTime + ", unloadEndTime=" + unloadEndTime
				+ ", status=" + status + "]";
	}



}
