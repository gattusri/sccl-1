package com.ants.sccl.service;

import org.springframework.stereotype.Service;

import com.ants.sccl.model.DevicePart;

@Service
public interface DevicePartService {
	
	public DevicePart deivePartReplace(DevicePart devicepart);
	
	public DevicePart deivePartCreate(DevicePart devicepart);

}
