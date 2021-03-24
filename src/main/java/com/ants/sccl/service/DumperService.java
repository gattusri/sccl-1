package com.ants.sccl.service;

import java.sql.Timestamp;
import java.util.Date;

import com.ants.sccl.model.DumperCount;

public interface DumperService {

	//public Boolean checkDumperAndBluetoothDevideExistornot(String dumperId,String bluetoothId);
	//public Boolean checkDumperAndBluetoothDeviseExistornot(String dumperId,String bluetoothId, String bluetoothDeviceType);
	public void dumperIdWithLoader(String dumperId, String bluetoothValue, Timestamp dateTime);
	public void dumperIdWithunLoader(String dumperId, String bluetoothValue, Timestamp dateTime);
	public void dumperIdWithNodevice(String dumperId);
	void checkWithDumperShovel(DumperCount dumperCount);
	void checkWithDumperUnload(DumperCount dumperCount);
	
}
