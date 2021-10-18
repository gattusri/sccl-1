package com.ants.sccl.mqtttest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ants.sccl.response.ResponseObject;


@RestController
@RequestMapping("/mqtt/api")
public class MQTTTestController {
	
	@Autowired
	MQTTService mqttService;
	
	@PostMapping("ac")
	public ResponseEntity<?> acpusblisher(@RequestBody MQTTModel mqtt) {
	//	System.out.println(mc.toString());
	//	return "hello";
		if(mqtt.getDeviceId().length()>0 && mqtt.getDeviceTopic().length()>0 && mqtt.getMessage().length()>0) {
		mqttService.mqtt(mqtt);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","published Success"));
		}
		else
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("201","published failed"));
		
	}

}
