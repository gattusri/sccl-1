package com.ants.sccl.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ants.sccl.model.Asset;
import com.ants.sccl.model.AssetLocations;
import com.ants.sccl.model.AssetRegister;
import com.ants.sccl.model.Device;
import com.ants.sccl.model.LiveLocation;
import com.ants.sccl.repository.AssetLocationsRepository;
import com.ants.sccl.repository.AssetRegisterRepository;
import com.ants.sccl.repository.AssetRepository;
import com.ants.sccl.repository.DumperRepository;
import com.ants.sccl.repository.LiveLocationRepository;
import com.ants.sccl.response.IoTResponse;
import com.ants.sccl.response.MessageResponse;
import com.ants.sccl.serviceimpl.DeviceServiceImpl;
import com.ants.sccl.serviceimpl.DumperServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class DeviceController {
	
	@Autowired
	DumperRepository dumperRepo;
	
	@Autowired
	DeviceServiceImpl deviceServiceImpl;
	
	@Autowired
	DumperServiceImpl dumperServiceImpl;
	
	@Autowired
	LiveLocationRepository livelocationrepositery;
	
	@Autowired
	AssetRepository assetRepository;
	
	@Autowired
	AssetLocationsRepository assetLocationRepository;
	
	@Autowired
	AssetRegisterRepository assetRegisterRepository;
	
//	@Autowired
//	LocationForAssetRepository locationForAssetRepository;
	
//	@Autowired
//	AssetRegisterRepository arRepository;
	
	
//	@PostMapping("/dumper")
//	public void dumper(@RequestBody DumperData dumperdata) {
//		System.out.println("dumper Api calling...");
//		Boolean b;
//		if("Loader".equalsIgnoreCase(dumperdata.getBluetoothType())) {
//			dumperServiceImpl.dumperIdWithLoader(dumperdata.getDumperId(), dumperdata.getBluetoothValue(),dumperdata.getDateTime());
//		}
//			//b=dumperServiceImpl.checkDumperAndBluetoothDevideExistornot(dumperdata.getDumperId(), dumperdata.getBluetoothValue());
//		if("Unloader".equalsIgnoreCase(dumperdata.getBluetoothType())) {
//			dumperServiceImpl.dumperIdWithunLoader(dumperdata.getDumperId(), dumperdata.getBluetoothValue(),dumperdata.getDateTime());
//		}
//		if("No".equalsIgnoreCase(dumperdata.getBluetoothType())) {
//			dumperServiceImpl.dumperIdWithNodevice(dumperdata.getDumperId());
//		}
//	}
//	
	//getting live location of dumper
//	@PostMapping("/dumper/live")
//	public ResponseEntity<?> dumperTrack(@RequestBody String dumperId){
//		Optional<?> dd= dumperRepo.findById(dumperId);
//		if(dd.isPresent()) {
//			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("200","success",dd));
//		} else {
//				return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("201","dumper not found",""));
//		       }
//	}
//	
//	
//	@PostMapping("/dumperone")
//	public void DumperSecondAPI(@RequestBody DumperCount dumperCount ) {
//		System.out.println(dumperCount.toString()+"----");
//		if(dumperCount.getBluetooth_device_ID().equalsIgnoreCase("S1") || dumperCount.getBluetooth_device_ID().equalsIgnoreCase("S2") ||dumperCount.getBluetooth_device_ID().equalsIgnoreCase("S3"))
//			{ 	 System.out.println(dumperCount.toString()+"--S1--");
//			dumperServiceImpl.checkWithDumperShovel(dumperCount);
//			}
//		if(dumperCount.getBluetooth_device_ID().equalsIgnoreCase("U1") || dumperCount.getBluetooth_device_ID().equalsIgnoreCase("U2") ||dumperCount.getBluetooth_device_ID().equalsIgnoreCase("U3"))
//			{dumperServiceImpl.checkWithDumperUnload(dumperCount);
//				System.out.println(dumperCount.toString()+"--U1--");
//			}
//		
//	}
	
		//	Master API's
	@PostMapping("/adddevicedetails")
	public  ResponseEntity<?>  dumper(@RequestBody Device device) {
		//System.out.println(device.toString()+"----");
		IoTResponse it=deviceServiceImpl.saveDeviceData(device);
			//return  ResponseEntity.status(HttpStatus.OK).body(new IoTResponse(it.getStatus(),it.getIgnition_status(),it.getSw_ver()));
			return  ResponseEntity.status(HttpStatus.OK).body(it);
	}
	
	@PostMapping("/dumperLiveLocation")
	public  ResponseEntity<?>  dumperLiveLocation(@RequestBody String dumperId) {
		System.out.println(dumperId+"----");
			Optional<LiveLocation> dumperll=livelocationrepositery.checkLiveLocationExistOrNot(dumperId);
			if(dumperll.isPresent()) {
				System.out.println("if condition+ ----");
			return  ResponseEntity.status(HttpStatus.OK).body(dumperll);
		
			}
			else 	{
				System.out.println("else condition+ ----");
			
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid DumperId",dumperll));
			}
	}
	// Asset tracking API
	@PostMapping("/assettracking")
	public ResponseEntity<?> assetTracking1(@RequestBody Asset asset) {
		System.out.println(asset.toString()+"------");
			Asset res = assetRepository.save(asset);
			System.out.println(res.toString()+"-----");
				
		
			AssetLocations assetLocations = assetLocationRepository.getLocationBasedOnParameters(res.getrFIDReaderCode(),res.getcH_Antenna(),res.getSubch());
			
			//Optional<LocationsForAsset> lfar=locationForAssetRepository.getLocation(res.getrFIDReaderCode(),res.getcH_Antenna(),res.getSubch());
				
			
			System.out.println(assetLocations.getLocationId()+"---location id getting");
			System.out.println(res.getAssetCode()+"------assetcode");
				//lfar.get().getLocationID()

				Optional<AssetRegister> aRegister =assetRegisterRepository.getAssetBasedonAssetCode(res.getAssetCode());
				
				System.out.println(aRegister.get().toString()+"---asset register object");
				System.out.println("----------------------");
				System.out.println("");
			
				if(aRegister.isPresent()) {
				AssetRegister aRegisterOne=aRegister.get();
					aRegisterOne.setLocationId(assetLocations.getLocationId());
					assetRegisterRepository.save(aRegisterOne);
					System.out.println("---asset IF Condition check");
				}
				System.out.println("----------------------");
				
			if(res.getAssetCode()!=null) {
				System.out.println("if condition+ ----");
			return  ResponseEntity.status(HttpStatus.OK).body(res);
		
			}
			else 	{
				System.out.println("else condition+ ----");
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
	}
	
//	@GetMapping("/getdetails")
//	public  ResponseEntity<?>  device() {
//		//System.out.println(device.toString()+"----");
//		IoTResponse it=deviceServiceImpl.saveDeviceData(device);
//			//return  ResponseEntity.status(HttpStatus.OK).body(new IoTResponse(it.getStatus(),it.getIgnition_status(),it.getSw_ver()));
//			return  ResponseEntity.status(HttpStatus.OK).body(it);
//	}
	
	
	
	}
