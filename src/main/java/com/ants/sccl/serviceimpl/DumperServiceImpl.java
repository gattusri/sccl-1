package com.ants.sccl.serviceimpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ants.sccl.model.DumperCount;
import com.ants.sccl.model.DumperData;
import com.ants.sccl.model.DumperTransaction;
import com.ants.sccl.repository.DumperRepository;
import com.ants.sccl.repository.DumperTransactionrepositery;
import com.ants.sccl.service.DumperService;
@Component
@Service
public class DumperServiceImpl implements DumperService {
	
	@Autowired
	DumperRepository dumperRepositery;
	
	@Autowired
	DumperData dumperdata;
	
	@Autowired
	DumperTransaction dumperTransaction;
	@Autowired
	DumperTransactionrepositery dumpertransactionRepositery;
	
	/* Loader API */
	@Override
	public void dumperIdWithLoader(String dumperId, String bluetoothValue, Timestamp dateTime) {
	
		// here we getting only loader values
		 String loading="loading";
		 Optional<DumperTransaction> check =dumpertransactionRepositery.checkDumperWithLoader(dumperId,bluetoothValue,loading);
		  
		 System.out.println(check.isPresent());
		 DumperTransaction dTransaction=new DumperTransaction();
	
			  if(!check.isPresent()) {
				  dTransaction.setDumperId(dumperId);
				  dTransaction.setLoadDeviceValue(bluetoothValue);
				  dTransaction.setLoadStartTime(dateTime);
				  dTransaction.setUnloadDeviceValue("notYet");
				  dTransaction.setStatus(loading);
			  
				  System.out.println("dumperIdWithLoader"+check.isPresent());
				  dumpertransactionRepositery.save(dTransaction);
			  
			  } else { 
				  check.get().setLoadEndTime(dateTime);
				  dumpertransactionRepositery.save(check.get());
				  System.out.println(dumperTransaction.toString()+"-----");
				  System.out.println("dumperIdWithLoader else condition");
			    
			  }
			 
	}
	
	/* UnLoader API */
	@Override
	public void dumperIdWithunLoader(String dumperId, String bluetoothValue, Timestamp dateTime) {
		 String loaded="loaded";
		 Optional<DumperTransaction> checkStatusLoaded =dumpertransactionRepositery.checkDumperWithUnLoader(dumperId,loaded);
		  
		 System.out.println(checkStatusLoaded.isPresent());
	
			  if(checkStatusLoaded.isPresent()) {
				  checkStatusLoaded.get().setUnloadDeviceValue(bluetoothValue);
				  checkStatusLoaded.get().setUnloadStartTime(dateTime);
				  checkStatusLoaded.get().setStatus("unloading");
			  
				 // System.out.println("dumperIdWithLoader"+check.isPresent());
				  dumpertransactionRepositery.save(checkStatusLoaded.get());
			  
			  }else
			  {
				  Optional<DumperTransaction> checkStatusUnloading =dumpertransactionRepositery.checkDumperWithUnLoader(dumperId,"unloading");
				  if(checkStatusUnloading.isPresent()) {
					  checkStatusUnloading.get().setUnloadEndTime(dateTime);
				  dumpertransactionRepositery.save(checkStatusUnloading.get());
				  }
			  }
		
	}
	
	/* Status chaining API */
	@Override
	public void dumperIdWithNodevice(String dumperId) {
		Optional<DumperTransaction> dt=dumpertransactionRepositery.getStatusofrecord(dumperId);
		if(dt.isPresent()) {
				if("loading".equalsIgnoreCase(dt.get().getStatus())) {
					dt.get().setStatus("Loaded");
					dumpertransactionRepositery.save(dt.get());
				}
				if("unloading".equalsIgnoreCase(dt.get().getStatus())) {
					dt.get().setStatus("TripCompleted");
					dumpertransactionRepositery.save(dt.get());
					System.out.println(dt.toString()+"dumperwithno device");
				}
		}
	}

	@Override
	public void checkWithDumperShovel(DumperCount dumperCount) {
		Optional<DumperTransaction> dtrans =dumpertransactionRepositery.checkWithDumperShovelRecord(dumperCount.getDumper_ID(),dumperCount.getBluetooth_device_ID(),"loading");
		if(dtrans.isPresent()) {
			dtrans.get().setLoadEndTime(dumperCount.getTimeStamp());
			dtrans.get().setStatus("loaded");
			dumpertransactionRepositery.save(dtrans.get());
		}else {
			DumperTransaction dt=new DumperTransaction();
			dt.setDumperId(dumperCount.getDumper_ID());
			dt.setLoadDeviceValue(dumperCount.getBluetooth_device_ID());
			dt.setLoadStartTime(dumperCount.getTimeStamp());
			dt.setStatus("loading");
			dumpertransactionRepositery.save(dt);
			
		}
	}

	
	@Override
	public void checkWithDumperUnload(DumperCount dumperCount) {
		if(dumperCount.getStatus()==1) {
		Optional<DumperTransaction> dtrans =dumpertransactionRepositery.checkWithDumperShovelRecord(dumperCount.getDumper_ID(),"loaded");
		if(dtrans.isPresent()) {
			dtrans.get().setUnloadDeviceValue(dumperCount.getBluetooth_device_ID());
			dtrans.get().setUnloadStartTime(dumperCount.getTimeStamp());
			dtrans.get().setStatus("unloading");
			dumpertransactionRepositery.save(dtrans.get());
		}
		}else {
		Optional<DumperTransaction> dtransct =dumpertransactionRepositery.checkWithDumperUnloadRecord(dumperCount.getDumper_ID(),dumperCount.getBluetooth_device_ID(),"unloading");
		if(dtransct.isPresent()) {
			//dtransct.get().setDumperId(dumperCount.getDumper_ID());
			//dtransct.get().setLoadDeviceValue(dumperCount.getBluetooth_device_ID());
			dtransct.get().setUnloadEndTime(dumperCount.getTimeStamp());
			dtransct.get().setStatus("TripCompleted");
			dumpertransactionRepositery.save(dtransct.get());
			
		}
		}
		
	}
	
	



	

}
