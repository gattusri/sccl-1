package com.ants.sccl.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ants.sccl.model.Device;
import com.ants.sccl.response.IoTResponse;



public interface DeviceService {
	

	public IoTResponse saveDeviceData(Device device);
}
