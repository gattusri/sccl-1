package com.ants.sccl.controller;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ants.sccl.model.Asset;
import com.ants.sccl.model.AssetCount;
import com.ants.sccl.model.AssetLocations;
import com.ants.sccl.model.AssetRegister;
import com.ants.sccl.model.Dashboard;
import com.ants.sccl.model.Device;
import com.ants.sccl.model.DeviceMapping;
import com.ants.sccl.model.DumperCount;
import com.ants.sccl.model.DumperDetails;
import com.ants.sccl.model.DumperTransaction;
import com.ants.sccl.model.LiveLocation;
import com.ants.sccl.model.UserDetails;
import com.ants.sccl.projections.AssetTrackingModel;
import com.ants.sccl.projections.DumperDetailsModel;
import com.ants.sccl.projections.DurationOfTripModel;
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
	/**	D1--Master API for store dumper Raw Data  */
	@PostMapping("/adddevicedetails")
	public  ResponseEntity<?>  dumper(@RequestBody Device device) {
		//System.out.println(device.toString()+"----");
		IoTResponse it=deviceServiceImpl.saveDeviceData(device);
				
			//return  ResponseEntity.status(HttpStatus.OK).body(new IoTResponse(it.getStatus(),it.getIgnition_status(),it.getSw_ver()));
			return  ResponseEntity.status(HttpStatus.OK).body(it);
	}
	
	
	
	/**	D2 -- API for store dumper trip count  */
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
	
	/** D3 -- Dumper Live Location API */
	@PostMapping("/dumperLiveLocation")
	public  ResponseEntity<?>  dumperLiveLocation(@RequestBody String dumperId) {
		//System.out.println(dumperId+"----");
			Optional<LiveLocation> dumperll=livelocationrepositery.checkLiveLocationExistOrNot(dumperId);
			if(dumperll.isPresent()) {
				//System.out.println("if condition+ ----");
			return  ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","Dumper live location getting successfully",dumperll));
		
			}
			else 	{
				//System.out.println("else condition+ ----");
				return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponse("false","Invalid DumperId",dumperll));
			}
	}
	
	/** duplicate for D3 -- Dumper Trip Count API */
	
	@PostMapping("/trip")
	public  ResponseEntity<?>  dumperCount(@RequestBody String dumperId) {
		//System.out.println(dumperId+"----");
			Optional<LiveLocation> dumperll=livelocationrepositery.checkLiveLocationExistOrNot(dumperId);
			if(dumperll.isPresent()) {
				//System.out.println("if condition+ ----");
			return  ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","trip getting successfully",dumperll));
		
			}
			else 	{
				System.out.println("else condition+ ----");
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid DumperId",dumperll));
			}
	}
	
	
	
	
	
	
	
	// A1 -- Asset tracking API along with live location update
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
	
	
	//* A2 -- getting all locations available in Asset_location_mapping Table */
	@GetMapping("/getlocations")
	public  ResponseEntity<?>  getLocations() {
		//System.out.println(device.toString()+"----");
			List<AssetLocations> locationsList= assetLocationRepository.findAll();
			//return  ResponseEntity.status(HttpStatus.OK).body(new IoTResponse(it.getStatus(),it.getIgnition_status(),it.getSw_ver()));
			return  ResponseEntity.status(HttpStatus.OK).body(locationsList);
	}
	
	/** A3 getting assets list based on location id */
	@PostMapping("/getassets")
	public  ResponseEntity<?>  getAssetBasedOnLocation(@RequestBody AssetLocations assetLocations) {
			List<AssetRegister> assetList=assetRegisterRepository.getAssetBasedonLocation(assetLocations.getLocationId());
			//return  ResponseEntity.status(HttpStatus.OK).body(new IoTResponse(it.getStatus(),it.getIgnition_status(),it.getSw_ver()));
			return  ResponseEntity.status(HttpStatus.OK).body(assetList);
	}
	
	/** A4 getting all Avalible assets in asset Table  */
	@GetMapping("/gettest")
	public  ResponseEntity<?>  getAsset() {
			List<Asset> assetList=assetRepository.getAsset();
			//return  ResponseEntity.status(HttpStatus.OK).body(new IoTResponse(it.getStatus(),it.getIgnition_status(),it.getSw_ver()));
			return  ResponseEntity.status(HttpStatus.OK).body(assetList);
	}
	
	/** A5 -- getting Asset count based on locations(rooms)  */
	@GetMapping("/getdevicecount")
	public  ResponseEntity<?>  getDeviceCount() {
			ArrayList<AssetCount> assetsList=(ArrayList<AssetCount>) assetRegisterRepository.getAssetCountBasedonLocationId();
			return  ResponseEntity.status(HttpStatus.OK).body(assetsList);
	}
	
	/** A6 -- getting Asset information  */
	@GetMapping("/assettrackingforui")
	public  ResponseEntity<?>  getAssetTracking() {
		try {
		 List<AssetTrackingModel> atm=assetRegisterRepository.getAssetTrackingDTO();
		 System.out.println(atm.get(0)+"---");
			return  ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","asset details getting successfully",atm));
		// return null;
		}catch(Exception e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid",e));
		}
	}
	
	
	

	/** A7 -- Adding Asset  */
	@PostMapping("/assetadding")
	public  ResponseEntity<?>  addAsset(@RequestBody AssetRegister assetRegister) throws DataAccessException {
		 AssetRegister ar = null;
		try {
		if(assetRegister!=null && assetRegister.getAssetCode().trim().length()>0) {
		    ar =assetRegisterRepository.save(assetRegister);
		   //return   ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","asset details added successfully",ar));
		}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid assset",e));
		}
			//ArrayList<AssetCount> assetsList=(ArrayList<AssetCount>) assetRegisterRepository.getAssetCountBasedonLocationId();
		return   ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","asset details added successfully",ar));
			
	}
	
//	/** A8 -- update Asset status */
//	@PostMapping("/assetupdate")
//	public  ResponseEntity<?>  updateAsset(@RequestBody AssetRegister assetRegister) {
//		 AssetRegister ar = null;
//		try {
//		
//		    ar =assetRegisterRepository.save(assetRegister);
//		   //return   ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","asset details added successfully",ar));
//		
//		}catch(Exception e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid",e));
//		}
//			//ArrayList<AssetCount> assetsList=(ArrayList<AssetCount>) assetRegisterRepository.getAssetCountBasedonLocationId();
//		return   ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","asset details added successfully",ar));
//			
//	}
	
	/** A8 -- view Asset based on status */
	@GetMapping("/assetview")
	public  ResponseEntity<?>  viewAsset() {
		List<AssetRegister> av =null;
	try {
		
		  av =assetRegisterRepository.findAll();
				   
		 }catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid",e));
		}
			//ArrayList<AssetCount> assetsList=(ArrayList<AssetCount>) assetRegisterRepository.getAssetCountBasedonLocationId();
		return   ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","asset details added successfully",av));
			
	}
	
	/** A9 -- Asset Locations */
	@GetMapping("/assetlocationsview")
	public  ResponseEntity<?>  viewAssetLocations() {
		
		  List<AssetLocations> av = assetLocationRepository.findAll();
			//ArrayList<AssetCount> assetsList=(ArrayList<AssetCount>) assetRegisterRepository.getAssetCountBasedonLocationId();
		return   ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","asset Locations getting successfully",av));
			
	}
	/** A9 -- Asset Locations */
	@PostMapping("/assetlocationsadding")
	public  ResponseEntity<?>  assetLocationAdding(@RequestBody AssetLocations assetlocation) {
		 AssetLocations al =null;
		try {
		 al = assetLocationRepository.save(assetlocation);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid",e));
		}
			//ArrayList<AssetCount> assetsList=(ArrayList<AssetCount>) assetRegisterRepository.getAssetCountBasedonLocationId();
		return   ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","asset Location saved successfully",al));
			
	}
	
	@PostMapping("/useradding")
	public ResponseEntity<?> userRegister(@RequestBody UserDetails user ){
		UserDetails ud=null;
		try {
			System.out.println(Instant.now()+"---1------");
		ud=userDetailsRepository.save(user);
		System.out.println(Instant.now()+"----1-----");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","User Registered Successfully",e));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","User Registered Successfully",ud));
		
	}
	
	
	/** L1 -- Login API  */
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
	
	/** D4 -- API for getting dumper_Count, shovel_Count , trip_Count in DashBoard */
	@PostMapping("/dashboard")
	//public  ResponseEntity<?>  getShovalDumperTrips(@RequestParam(name = "fromDate")Date fromDate,@RequestParam(name = "toDate")Date toDate) {
		public  ResponseEntity<?>  getShovalDumperTrips(@RequestBody DumperDetails dumperDetails) {
		//System.out.println("API Calling working");
		if(dumperDetails.getFromDate()==null || dumperDetails.getToDate()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid Date",dumperDetails)) ;
		}else {
		try {
		ArrayList<Dashboard> db=deviceServiceImpl.getThreeData(dumperDetails.getFromDate(), dumperDetails.getToDate());
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","fetching count of doumper Count ,shovel count total trips successfully",db)) ;
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dumperDetails) ;
			}
			}
	
		}
	
	/** D5-- API for getting Recent Trips in DashBoard */
		@GetMapping("/recenttrips")
		public  ResponseEntity<?>  getRecentTrips() {
		try {
		List<DumperTransaction> recentTripsData = deviceServiceImpl.getRecentTripsData();
			return ResponseEntity.status(HttpStatus.OK).body(recentTripsData) ;
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid ",e)) ;
			}
		
		}
	
		/** D6 -- API for getting dumper_Count, shovel_Count */
		@GetMapping("/countofshovelsdumpers")
		public  ResponseEntity<?>  getCountSovelsDumpers() {
		try {
		List<?> deviceCount = deviceMappingRepository.getCountofDumperShovels();
			return ResponseEntity.status(HttpStatus.OK).body(deviceCount) ;
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid ",e)) ;
			}
		
		}
	
		/** D7 -- API for getting dumper_Id's */
		@GetMapping("/alldumperids")
		public  ResponseEntity<?>  getDumpers() {
		try {
		List<DeviceMapping> deviceCount = deviceMappingRepository.getAllDumpers();
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true"," Dumpers fetch successfully",deviceCount)) ;
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid",e)) ;
			}
		
		}
		
		/** D8 -- API for Dumper Deails Count*/
		@PostMapping("/dumperdetailscount")
		public  ResponseEntity<?>  getDumperdetailsCount(@RequestBody DumperDetails dumperDetails) {
		try {
			//System.out.println("-----");
			List<DumperDetailsModel> dumperDetailsCount = dumperServiceImpl.getDumperDetailsCount(dumperDetails.getFromDate(), dumperDetails.getToDate());
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","Dumper details count fetch successfully ",dumperDetailsCount)) ;
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid ",e)) ;
			}
		
		}
		
		/** D9 -- API for duration of trip*/
		@PostMapping("/durationofthetrip")
		public  ResponseEntity<?>  getDurationOfTheTrip(@RequestBody DumperDetails dumperDetails) {
		try {
			//System.out.println(dumperDetails.getFromDate()+"-DOTT--"+dumperDetails.getToDate());
			List<DurationOfTripModel> DTM = dumperServiceImpl.getDurationOfTheTrip(dumperDetails.getFromDate(), dumperDetails.getToDate());
			//System.out.println(DTM.size());
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","Duration of Trip details fetch successfully success ",DTM)) ;
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","Invalid ",e)) ;
			}
		
		}
		
	}
