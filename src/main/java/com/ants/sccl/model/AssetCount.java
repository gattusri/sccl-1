package com.ants.sccl.model;

import org.springframework.stereotype.Component;

@Component
public class AssetCount {
	
	private int locationId;
	private int assetCount;
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getAssetCount() {
		return assetCount;
	}
	public void setAssetCount(int assetCount) {
		this.assetCount = assetCount;
	}

}
