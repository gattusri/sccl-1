package com.ants.sccl.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ants.sccl.model.Dashboard;
import com.ants.sccl.model.Device;
import com.ants.sccl.model.DumperTransaction;
import com.ants.sccl.response.IoTResponse;



public interface DeviceService {
	

	public IoTResponse saveDeviceData(Device device);
	public ArrayList<Dashboard> getThreeData(Date fromDate, Date toDate);
	public List<DumperTransaction> getRecentTripsData();
}
