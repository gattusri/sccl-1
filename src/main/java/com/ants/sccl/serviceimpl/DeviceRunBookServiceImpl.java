package com.ants.sccl.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ants.sccl.model.DeviceRunBook;
import com.ants.sccl.repository.DeviceRunBookRepository;
import com.ants.sccl.service.DeviceRunBookService;


@Service
public class DeviceRunBookServiceImpl implements DeviceRunBookService {

	@Autowired
	DeviceRunBookRepository deviceRunBookRepository;
	
	public DeviceRunBook saveDeviceRunBook(DeviceRunBook deviceRunbook) {
		DeviceRunBook drb=deviceRunBookRepository.save(deviceRunbook);
		return drb;
	}

	@Override
	public DeviceRunBook findDevice(String deviceId) {
		return deviceRunBookRepository.findDevice(deviceId);
	}

	@Override
	public List<DeviceRunBook> findAllDeviceRunBook() {
		return deviceRunBookRepository.findAll();
	}
	
}
