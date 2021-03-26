package com.ants.sccl.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ants.sccl.model.Device;
import com.ants.sccl.model.LiveData;
import com.ants.sccl.model.LiveLocation;
import com.ants.sccl.repository.DeviceRepository;
import com.ants.sccl.repository.LiveLocationRepository;
import com.ants.sccl.response.IoTResponse;
import com.ants.sccl.service.DeviceService;


@Component
@Service
public class DeviceServiceImpl implements DeviceService{

	@Autowired
	DeviceRepository devicerepository;
	
	@Autowired
	LiveLocationRepository liveLocationRepository;
	
	@Autowired
	LiveLocation liveLocation;
	

	@Autowired
	Device devic;
	
	@Override
	public IoTResponse saveDeviceData(Device device) {
		IoTResponse iotresponse=new IoTResponse();
					
				devicerepository.save(device);
					liveLocation.setDeviceId(device.getDeviceId());
					liveLocation.setLatitude(device.getLatitude());
					liveLocation.setLongitude(device.getLongitude());
					liveLocation.setTime_stamp(device.getTime_stamp());
					//if("Dumper")
					liveLocation.setStatus("No-data");
					liveLocationRepository.save(liveLocation);	 
		//if(devic.getDeviceId().equalsIgnoreCase(device.getDeviceId())) {
			System.out.println(device.toString()+"---");
			iotresponse.setStatus("Ok");
			iotresponse.setIgnition_status(devic.getIgnition_status());
			iotresponse.setSw_ver("V"+devic.getSw_ver());
		return iotresponse;
//		}
//		else {
//			iotresponse.setStatus("No");
//			iotresponse.setIgnition_status(devic.getIgnition_status());
//			iotresponse.setSw_ver("V"+devic.getSw_ver());
//			return iotresponse;
//		}
		
	}
	
	
}
