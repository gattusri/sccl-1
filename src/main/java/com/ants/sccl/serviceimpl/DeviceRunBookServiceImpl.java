package com.ants.sccl.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	@Override
	public void updateDeviceParts(String sensorid) {
		deviceRunBookRepository.updateDeviceParts(sensorid);
	}
	@Override
	public List<DeviceRunBook> findAllDeviceRunBook(Integer pageNo, Integer pageSize, String sortBy) {
		
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<DeviceRunBook> drbl= deviceRunBookRepository.findAll(paging);
		System.out.println(drbl.getNumber()+"---------"+drbl.toString());
		if(drbl.hasContent()) {
			System.out.println("----one-----");
            return drbl.getContent();
        } else {
            return new ArrayList<DeviceRunBook>();
        }
	}
}
