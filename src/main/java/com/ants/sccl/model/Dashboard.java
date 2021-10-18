package com.ants.sccl.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class Dashboard {
	private Date date;
	private int Dumper_Count;
	private int Shovel_Count;
	private int Trip_Count;
	private Date fromDate;
	private Date toDate;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getDumper_Count() {
		return Dumper_Count;
	}
	public void setDumper_Count(int dumper_Count) {
		Dumper_Count = dumper_Count;
	}
	public int getShovel_Count() {
		return Shovel_Count;
	}
	public void setShovel_Count(int shovel_Count) {
		Shovel_Count = shovel_Count;
	}
	public int getTrip_Count() {
		return Trip_Count;
	}
	public void setTrip_Count(int trip_Count) {
		Trip_Count = trip_Count;
	}
		
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	@Override
	public String toString() {
		return "Dashboard [date=" + date + ", Dumper_Count=" + Dumper_Count + ", Shovel_Count=" + Shovel_Count
				+ ", Trip_Count=" + Trip_Count + "]";
	}
	
}
