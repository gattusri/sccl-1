package com.ants.sccl.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ants.sccl.model.DevicePart;
import com.ants.sccl.repository.DevicePartRepository;
import com.ants.sccl.service.DevicePartService;

@Service
public class DevicePartServiceImpl implements DevicePartService {
	
	@Autowired
	DevicePartRepository devicePartRepository;
	
	public DevicePart deivePartReplace(DevicePart devicepart) {
		
		return devicePartRepository.save(devicepart);
		
	}
	
	public DevicePart deivePartCreate(DevicePart devicepart) {
		
		return devicePartRepository.save(devicepart);
		
	}
	

}
