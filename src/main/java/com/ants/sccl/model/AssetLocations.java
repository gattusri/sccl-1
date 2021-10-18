package com.ants.sccl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="`Asset_Location_Mapping`")
@Component
public class AssetLocations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Location_Id")
	private int locationId;

	@Column(name="Location_Description")
	private String locationDescription;
	
	@Column(name="RFID_Reader_Code")
	private String rFIDReaderCode;
	
	@Column(name="CH_Antenna")
	private int chAntenna;
	
	@Column(name="SUB_CH")
	private int subch;



	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public String getrFIDReaderCode() {
		return rFIDReaderCode;
	}

	public void setrFIDReaderCode(String rFIDReaderCode) {
		this.rFIDReaderCode = rFIDReaderCode;
	}

	public int getChAntenna() {
		return chAntenna;
	}

	public void setChAntenna(int chAntenna) {
		this.chAntenna = chAntenna;
	}

	public int getSubch() {
		return subch;
	}

	public void setSubch(int subch) {
		this.subch = subch;
	}

	@Override
	public String toString() {
		return "AssetLocations [ locationId=" + locationId + ", locationDescription="
				+ locationDescription + ", rFIDReaderCode=" + rFIDReaderCode + ", chAntenna=" + chAntenna + ", subch="
				+ subch + "]";
	}
	

}
