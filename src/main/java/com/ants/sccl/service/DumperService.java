package com.ants.sccl.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.ants.sccl.model.DumperCount;
import com.ants.sccl.projections.DurationOfTripModel;

public interface DumperService {

	//public Boolean checkDumperAndBluetoothDevideExistornot(String dumperId,String bluetoothId);
	//public Boolean checkDumperAndBluetoothDeviseExistornot(String dumperId,String bluetoothId, String bluetoothDeviceType);
	public void dumperIdWithLoader(String dumperId, String bluetoothValue, Timestamp dateTime);
	public void dumperIdWithunLoader(String dumperId, String bluetoothValue, Timestamp dateTime);
	public void dumperIdWithNodevice(String dumperId);
	boolean checkWithDumperShovel(DumperCount dumperCount);
	boolean checkWithDumperUnload(DumperCount dumperCount);
	public List<DurationOfTripModel> getDurationOfTheTrip(Date fromDate,Date toDate);
}
