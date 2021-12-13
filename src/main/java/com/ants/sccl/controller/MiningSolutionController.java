package com.ants.sccl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ants.sccl.model.DeviceRunBook;
import com.ants.sccl.model.DeviceRunBookInputModel;
import com.ants.sccl.model.PilferageDetectionModel;
import com.ants.sccl.repository.PilferageDetectionRepository;
import com.ants.sccl.response.MessageResponse;

import com.ants.sccl.service.DeviceRunBookService;



@RestController
@RequestMapping("/")
public class MiningSolutionController {
	
	@Autowired
	DeviceRunBookService deviceRunBookService;
	
	@Autowired
	PilferageDetectionRepository pilferageDetectionRepository;
	
	/* device-runbook API used for save/update Device RunBook data in Device_Runbook Table ---- */
	@PostMapping("/device-runbook")
	public ResponseEntity<?> saveDeviceRunBook(@RequestBody DeviceRunBookInputModel deviceRunBookInputModel) {
		DeviceRunBook drb=new DeviceRunBook();
		
		if(deviceRunBookInputModel.getFlag()==1) {
			try {
			drb.setDevice(deviceRunBookInputModel.getDevice());
			drb.setStartTime(deviceRunBookInputModel.getTimeStamp());
			drb.setLatitude(deviceRunBookInputModel.getLatitude());
			drb.setLongitude(deviceRunBookInputModel.getLongitude());
			drb=deviceRunBookService.saveDeviceRunBook(drb);
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","data stored success",drb));
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("false","data stored failed",e));
			}
			
			
		}
		else if(deviceRunBookInputModel.getFlag()==0) {
			try {
			DeviceRunBook drbOne=deviceRunBookService.findDevice(deviceRunBookInputModel.getDevice());
				//drbOne.setDevice(deviceRunBookInputModel.getDevice());
			drbOne.setEndTime(deviceRunBookInputModel.getTimeStamp());
			drbOne.setLatitude(deviceRunBookInputModel.getLatitude());
			drbOne.setLongitude(deviceRunBookInputModel.getLongitude());
			drbOne=deviceRunBookService.saveDeviceRunBook(drbOne);
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","data stored success",drbOne));
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("true","data stored failed",e));
			}
			
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("false","data stored failed",""));		
	}
	
//	@PostMapping("/device-runbook")
//	public ResponseEntity<?> saveDeviceRunBook(@RequestBody DeviceRunBook deviceRunBook) {
//		
//		
//		DeviceRunBook drb=deviceRunBookService.saveDeviceRunBook(deviceRunBook);
//		if(drb!=null)		
//		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,drb,"data stored success"));
//		else
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(false,drb,"data stored failed"));
//			
//	}
	
	
	/* get-device-runbook API used for get all device runbook data*/
	@GetMapping("/get-device-runbook")
	public ResponseEntity<?> deviceRunBook() {
		List<DeviceRunBook> drb=deviceRunBookService.findAllDeviceRunBook();
		if(!drb.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","success", drb)) ;
		else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("false","failed",drb)) ;
	}
	
	
	/* Device Prilferage detection API */
	@PostMapping("/priferagedetection")
	public ResponseEntity<?>  priferageDetection(@RequestBody PilferageDetectionModel pilferageDetectionModel) {
		if(pilferageDetectionModel.getPilferageIndicator()==false) {
			PilferageDetectionModel tdm=pilferageDetectionRepository.save(pilferageDetectionModel);
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("true","success",tdm)) ;
		}
		else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("false","failed", pilferageDetectionModel)) ;
	}

	
}
