package com.ants.sccl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="`Asset_Registration`")
@Component
public class AssetRegister {
	
	@Id
	@Column(name="`Id`")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="asset_code")
	private String assetCode;
	
	@Column(name="Asset_Description")
	private String assetDescription;
	
	@Column(name="Location_Id")
	private int locationId;
	
	private byte status;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssetDescription() {
		return assetDescription;
	}

	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AssetRegister [id=" + id + ", assetCode=" + assetCode + ", assetDescription=" + assetDescription
				+ ", locationId=" + locationId + "]";
	}
}
