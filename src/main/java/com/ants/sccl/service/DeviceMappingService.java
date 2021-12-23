package com.ants.sccl.service;

import java.util.List;

import com.ants.sccl.model.DeviceMapping;

public interface DeviceMappingService {
	
	public List<DeviceMapping> getDevices(String deviceType);

}
