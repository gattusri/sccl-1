package com.ants.sccl.projections;

import java.sql.Date;

public interface DumperDetailsModel {
	
	String getDumperId();
	String getLoadDeviceValue();
	String getUnloadDeviceValue();
	Date getDate();
	 String getStatus();
	
}
