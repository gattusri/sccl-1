package com.ants.sccl.projections;

import java.sql.Date;
import java.sql.Timestamp;

public interface DurationOfTripModel {
	String getDumperId();
	String getLoadDeviceValue();
	String getUnloadDeviceValue();
	Date getDate();
	Timestamp getLoadStartTime();
	Timestamp getUnloadEndTime();
	String getTimeDiff();

}
