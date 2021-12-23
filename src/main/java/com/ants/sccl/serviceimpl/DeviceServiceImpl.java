package com.ants.sccl.serviceimpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ants.sccl.model.Dashboard;
import com.ants.sccl.model.Device;
import com.ants.sccl.model.DeviceMapping;
import com.ants.sccl.model.DumperTransaction;
import com.ants.sccl.model.LiveLocation;
import com.ants.sccl.repository.DeviceMappingRepository;
import com.ants.sccl.repository.DeviceRepository;
import com.ants.sccl.repository.DumperTransactionrepositery;
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
	DeviceMappingRepository deviceMappingRepository;
	
	@Autowired
	DumperTransactionrepositery dumpertransactionRepositery;
	
	@Autowired
	Device devi;
	
	@Override
	public IoTResponse saveDeviceData(Device device) {
		
		IoTResponse iotresponse=new IoTResponse();
		try {
					devi=new Device();
					devi.setBat_olt(device.getBat_olt());
					devi.setBle_pair_id(device.getBle_pair_id());
					devi.setBat_olt(device.getBat_olt());
					devi.setDeviceId(device.getDeviceId());	
					devi.setBle_status(device.getBle_status());
					devi.setEngine_rpm(device.getEngine_rpm());
					devi.setEngine_temp1(device.getEngine_temp1());
					devi.setEngine_temp2(device.getEngine_temp2());
					devi.setFuel_level(device.getFuel_level());
					devi.setIgnition_status(device.getIgnition_status());
					devi.setLatitude(device.getLatitude());
					devi.setLongitude(device.getLongitude());
					devi.setPoint_of_loading_unloading(device.getPoint_of_loading_unloading());
					devi.setType_of_Load_or_unload(device.getType_of_Load_or_unload());
					devi.setTime_stamp(device.getTime_stamp());
					devi.setSys_volt(device.getSys_volt());
					devi.setSw_ver(device.getSw_ver());
		
				devicerepository.save(devi);
		}catch(Exception E) {
			iotresponse.setStatus("No");
			iotresponse.setIgnition_status(devi.getIgnition_status());
			iotresponse.setSw_ver("V"+devi.getSw_ver());
			return iotresponse;
		}
		try {
					Optional<LiveLocation> ll=liveLocationRepository.checkLiveLocationExistOrNot(device.getDeviceId());
				
					if(!ll.isPresent()) {
						LiveLocation liveLocation=new LiveLocation();
						liveLocation.setDeviceId(device.getDeviceId());
						liveLocation.setLatitude(device.getLatitude());
						liveLocation.setLongitude(device.getLongitude());
						liveLocation.setTime_stamp(device.getTime_stamp());
						
						liveLocation.setStatus("Loading");
						liveLocationRepository.save(liveLocation);
						
						
					}else {
						DeviceMapping dm=deviceMappingRepository.getOne(device.getDeviceId());
						if(dm!=null && dm.getDeviceCategory().equalsIgnoreCase("Dumper")) {
					
						
					ll.get().setLatitude(device.getLatitude());
					ll.get().setLongitude(device.getLongitude());
					ll.get().setTime_stamp(device.getTime_stamp());
					
					
					if(device.getBle_pair_id()=="") {
						if(ll.get().getStatus().equalsIgnoreCase("Unloading") && device.getBle_pair_id()=="") 
							ll.get().setStatus("tripend");
						else if(ll.get().getStatus().equalsIgnoreCase("Loading") && device.getBle_pair_id()=="") 
							ll.get().setStatus("Transit");
					}
					else {
						DeviceMapping dm1=deviceMappingRepository.getOne(device.getBle_pair_id());
						if(dm1.getDeviceCategory().equalsIgnoreCase("Shovel"))
							ll.get().setStatus("Loading");
						else if(dm1.getDeviceCategory().equalsIgnoreCase("Unloading"))
							ll.get().setStatus("Unloading");
					
					}
					
					liveLocationRepository.save(ll.get());	 
					
						}
					}
		}catch (Exception e) {
			System.out.println("Issue in updating live location."+ e);
		}
				
		if(devi.getDeviceId().equalsIgnoreCase(device.getDeviceId())) {
			
			iotresponse.setStatus("Ok");
			iotresponse.setIgnition_status(devi.getIgnition_status());
			iotresponse.setSw_ver("V"+devi.getSw_ver());
		return iotresponse;
		}
		else {
			iotresponse.setStatus("No");
			iotresponse.setIgnition_status(devi.getIgnition_status());
			iotresponse.setSw_ver("V"+devi.getSw_ver());
			return iotresponse;
		}
		
	}

	@Override
	public ArrayList<Dashboard> getThreeData(Date fromDate, Date toDate) {
		
		ArrayList<Dashboard> op=(ArrayList<Dashboard>) dumpertransactionRepositery.getDataWithFromDateToDate(fromDate, toDate);
		return op;
	}

	public List<DumperTransaction> getRecentTripsData() {
		
		List<DumperTransaction> dumperTransaction=(List<DumperTransaction>) dumpertransactionRepositery.getRecentTrips();
		return dumperTransaction;
	}
	
	
	
}
