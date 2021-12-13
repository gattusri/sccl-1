package com.ants.sccl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ants.sccl.model.DeviceRunBook;


@Service
public interface DeviceRunBookService {
	
	public DeviceRunBook saveDeviceRunBook(DeviceRunBook deviceRunbook);

	public DeviceRunBook findDevice(String deviceId);
	
	public List<DeviceRunBook> findAllDeviceRunBook();

}
