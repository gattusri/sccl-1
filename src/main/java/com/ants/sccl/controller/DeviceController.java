package com.ants.sccl.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ants.sccl.model.Asset;
import com.ants.sccl.model.AssetCount;
import com.ants.sccl.model.AssetLocations;
import com.ants.sccl.model.AssetRegister;
import com.ants.sccl.model.Dashboard;
import com.ants.sccl.model.Device;
import com.ants.sccl.model.DeviceMapping;
import com.ants.sccl.model.DumperCount;
import com.ants.sccl.model.DumperTransaction;
import com.ants.sccl.model.LiveLocation;
import com.ants.sccl.model.UserDetails;
import com.ants.sccl.repository.AssetLocationsRepository;
import com.ants.sccl.repository.AssetRegisterRepository;
import com.ants.sccl.repository.AssetRepository;
import com.ants.sccl.repository.DeviceMappingRepository;
import com.ants.sccl.repository.DumperRepository;
import com.ants.sccl.repository.LiveLocationRepository;
import com.ants.sccl.repository.UserDetailsRepository;
import com.ants.sccl.response.IoTResponse;
import com.ants.sccl.response.MessageResponse;
import com.ants.sccl.response.ResponseObject;
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
	
	
	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Autowired
	DeviceMappingRepository deviceMappingRepository;
	
	
	
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
	/**	Master API for store dumper Raw Data  */
	@PostMapping("/adddevicedetails")
	public  ResponseEntity<?>  dumper(@RequestBody Device device) {
		//System.out.println(device.toString()+"----");
		IoTResponse it=deviceServiceImpl.saveDeviceData(device);
				
			//return  ResponseEntity.status(HttpStatus.OK).body(new IoTResponse(it.getStatus(),it.getIgnition_status(),it.getSw_ver()));
			return  ResponseEntity.status(HttpStatus.OK).body(it);
	}
	
	
	
	/**	API for store dumper trip count  */
	@PostMapping("/tripcount")
	public  ResponseEntity<?>  tripCount(@RequestBody DumperCount dumperCount) {
		//System.out.println(dumperCount.getBle_pair_id()+"-----------");
		boolean flag=false;
		try {
			 DeviceMapping dm=	deviceMappingRepository.getOne(dumperCount.getBle_pair_id());
			 //System.out.println(dm.getDeviceCategory()+"-----------");
			
			 if(dm.getDeviceCategory()!=null) {
					//System.out.println(dumperCount.toString()+"----");
					if(dm.getDeviceCategory().equalsIgnoreCase("Shovel"))
						{ 	 //System.out.println(dumperCount.toString()+"--S1--");
						 flag=dumperServiceImpl.checkWithDumperShovel(dumperCount);
						}
					if(dm.getDeviceCategory().equalsIgnoreCase("Unloading") )
						{	flag=dumperServiceImpl.checkWithDumperUnload(dumperCount);
							//System.out.println(dumperCount.toString()+"--U1--");
						}
			 }else {
				 return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("201","UnSuccess"));
			 }
			 
		}catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("201","UnSuccess"));
		}
		if(flag)
			return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Success"));
		else
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("201","UnSuccess"));
	}
	
	
	@PostMapping("/dumperLiveLocation")
	public  ResponseEntity<?>  dumperLiveLocation(@RequestBody String dumperId) {
		//System.out.println(dumperId+"----");
			Optional<LiveLocation> dumperll=livelocationrepositery.checkLiveLocationExistOrNot(dumperId);
			if(dumperll.isPresent()) {
				//System.out.println("if condition+ ----");
			return  ResponseEntity.status(HttpStatus.OK).body(dumperll);
		
			}
			else 	{
				System.out.println("else condition+ ----");
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid DumperId",dumperll));
			}
	}
	
	/** Dumper Trip Count API */
	
	@PostMapping("/trip")
	public  ResponseEntity<?>  dumperCount(@RequestBody String dumperId) {
		//System.out.println(dumperId+"----");
			Optional<LiveLocation> dumperll=livelocationrepositery.checkLiveLocationExistOrNot(dumperId);
			if(dumperll.isPresent()) {
				//System.out.println("if condition+ ----");
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
//		System.out.println(!asset.getAssetCode().trim().isBlank());
		try {
		if(asset.getAssetCode()!=null) {
			//System.out.print(asset.getTimeStamp()+"------------");
				Asset res = assetRepository.save(asset);
				
				Optional<AssetRegister> aRegister =assetRegisterRepository.getAssetBasedonAssetCode(res.getAssetCode());
			
				if(aRegister.isPresent()) {
						AssetLocations assetLocations = assetLocationRepository.getLocationBasedOnParameters(res.getrFIDReaderCode(),res.getcH_Antenna(),res.getSubch());
						AssetRegister aRegisterOne=aRegister.get();
						aRegisterOne.setLocationId(assetLocations.getLocationId());
						assetRegisterRepository.save(aRegisterOne);
					}
												
			return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Success"));
		
		}
			else 	{
//				System.out.println("else condition+ ----");
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("201","UnSuccess"));
			}
		}catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("201","UnSuccess"));
		}
	}
	
	@GetMapping("/getlocations")
	public  ResponseEntity<?>  getLocations() {
		//System.out.println(device.toString()+"----");
			List<AssetLocations> locationsList= assetLocationRepository.findAll();
			//return  ResponseEntity.status(HttpStatus.OK).body(new IoTResponse(it.getStatus(),it.getIgnition_status(),it.getSw_ver()));
			return  ResponseEntity.status(HttpStatus.OK).body(locationsList);
	}
	
	@PostMapping("/getassets")
	public  ResponseEntity<?>  getAssetBasedOnLocation(@RequestBody AssetLocations assetLocations) {
			List<AssetRegister> assetList=assetRegisterRepository.getAssetBasedonLocation(assetLocations.getLocationId());
			//return  ResponseEntity.status(HttpStatus.OK).body(new IoTResponse(it.getStatus(),it.getIgnition_status(),it.getSw_ver()));
			return  ResponseEntity.status(HttpStatus.OK).body(assetList);
	}
	
	
	@GetMapping("/gettest")
	public  ResponseEntity<?>  getAsset() {
			List<Asset> assetList=assetRepository.getAsset();
			//return  ResponseEntity.status(HttpStatus.OK).body(new IoTResponse(it.getStatus(),it.getIgnition_status(),it.getSw_ver()));
			return  ResponseEntity.status(HttpStatus.OK).body(assetList);
	}
	
	
	@GetMapping("/getdevicecount")
	public  ResponseEntity<?>  getDeviceCount() {
			ArrayList<AssetCount> assetsList=(ArrayList<AssetCount>) assetRegisterRepository.getAssetCountBasedonLocationId();
			return  ResponseEntity.status(HttpStatus.OK).body(assetsList);
	}
	
	@PostMapping("/login")
	public  ResponseEntity<?>  loginCheck(@RequestBody UserDetails userDetails) {
		try {
		if(userDetails!=null) {
		  Optional<UserDetails> userObject=userDetailsRepository.getUserDetailsBasedOnEmail(userDetails.getUserEmail());
			
		  if(userDetails.getUserEmail().equalsIgnoreCase(userObject.get().getUserEmail())) {
			  if(userDetails.getUserPassword().equals(userObject.get().getUserPassword()) && userObject.get().getIsActive()==1){
				  userObject.get().setUserPassword("********");
				  return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","Login Succes",userObject));
			 
		  }
			  else {
				  userObject.get().setUserPassword("********");
				 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid Password / User not Active",userObject));
			  }
		  }else {
			  userObject.get().setUserPassword("********");
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid User EmailId",userObject));
		  }
		  //return  ResponseEntity.status(HttpStatus.OK).body(new IoTResponse(it.getStatus(),it.getIgnition_status(),it.getSw_ver()));
		//	return  ResponseEntity.status(HttpStatus.OK).body();
		}else {
				userDetails.setUserPassword("********");
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid User Details",userDetails));
		}
	}catch (Exception e) {
			userDetails.setUserPassword("********");
		  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid User Details",userDetails));
		}
	}
	
	/** API for getting dumper_Count, shovel_Count , trip_Count in DashBoard */
	@PostMapping("/dashboard")
	//public  ResponseEntity<?>  getShovalDumperTrips(@RequestParam(name = "fromDate")Date fromDate,@RequestParam(name = "toDate")Date toDate) {
		public  ResponseEntity<?>  getShovalDumperTrips(@RequestBody Dashboard dashBoard) {
		//System.out.println("API Calling working");
		if(dashBoard.getFromDate()==null || dashBoard.getToDate()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid Date",dashBoard)) ;
		}else {
		try {
		ArrayList<Dashboard> db=deviceServiceImpl.getThreeData(dashBoard.getFromDate(), dashBoard.getToDate());
			return ResponseEntity.status(HttpStatus.OK).body(db) ;
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dashBoard) ;
			}
			}
		}
	
	/** API for getting dumper_Count, shovel_Count , trip_Count in DashBoard */
		@GetMapping("/recenttrips")
		public  ResponseEntity<?>  getRecentTrips() {
		try {
		List<DumperTransaction> recentTripsData = deviceServiceImpl.getRecentTripsData();
			return ResponseEntity.status(HttpStatus.OK).body(recentTripsData) ;
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid ",e)) ;
			}
		
		}
	
	
	}
