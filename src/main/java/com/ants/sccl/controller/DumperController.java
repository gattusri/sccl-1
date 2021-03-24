package com.ants.sccl.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ants.sccl.model.DumperCount;
import com.ants.sccl.model.DumperData;
import com.ants.sccl.model.DumperTransaction;
import com.ants.sccl.repository.DumperRepository;
import com.ants.sccl.response.MessageResponse;
import com.ants.sccl.service.DumperServiceImpl;

@RestController
@RequestMapping("/api/v1")
@Component
public class DumperController {

	
	@Autowired
	DumperRepository dumperRepo;
	
	@Autowired
	DumperServiceImpl dumperServiceImpl;
	
	@PostMapping("/dumper")
	public void dumper(@RequestBody DumperData dumperdata) {
		Boolean b;
		if("Loader".equalsIgnoreCase(dumperdata.getBluetoothType())) {
			dumperServiceImpl.dumperIdWithLoader(dumperdata.getDumperId(), dumperdata.getBluetoothValue(),dumperdata.getDateTime());
		}
			//b=dumperServiceImpl.checkDumperAndBluetoothDevideExistornot(dumperdata.getDumperId(), dumperdata.getBluetoothValue());
		if("Unloader".equalsIgnoreCase(dumperdata.getBluetoothType())) {
			dumperServiceImpl.dumperIdWithunLoader(dumperdata.getDumperId(), dumperdata.getBluetoothValue(),dumperdata.getDateTime());
		}
		if("No".equalsIgnoreCase(dumperdata.getBluetoothType())) {
			dumperServiceImpl.dumperIdWithNodevice(dumperdata.getDumperId());
		}
	}
	
	//getting live location of dumper
	@PostMapping("/dumper/live")
	public ResponseEntity<?> dumperTrack(@RequestBody String dumperId){
		Optional<?> dd= dumperRepo.findById(dumperId);
		if(dd.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("200","success",dd));
		} else {
				return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("201","dumper not found",""));
		       }
	}
	
	
	@PostMapping("/dumperone")
	public void DumperSecondAPI(@RequestBody DumperCount dumperCount ) {
		System.out.println(dumperCount.toString()+"----");
		if(dumperCount.getBluetooth_device_ID().equalsIgnoreCase("S1") || dumperCount.getBluetooth_device_ID().equalsIgnoreCase("S2") ||dumperCount.getBluetooth_device_ID().equalsIgnoreCase("S3"))
			{ 	 System.out.println(dumperCount.toString()+"--S1--");
			dumperServiceImpl.checkWithDumperShovel(dumperCount);
			}
		if(dumperCount.getBluetooth_device_ID().equalsIgnoreCase("U1") || dumperCount.getBluetooth_device_ID().equalsIgnoreCase("U2") ||dumperCount.getBluetooth_device_ID().equalsIgnoreCase("U3"))
			{dumperServiceImpl.checkWithDumperUnload(dumperCount);
				System.out.println(dumperCount.toString()+"--U1--");
			}
		
	}
}
