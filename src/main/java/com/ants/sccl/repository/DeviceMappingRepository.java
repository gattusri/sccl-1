package com.ants.sccl.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ants.sccl.model.DeviceMapping;

public interface DeviceMappingRepository extends JpaRepository<DeviceMapping, String> {

	@Query(value="select Category from dumper_device_mapping where Device_Id=?1 ",nativeQuery = true)
	DeviceMapping getBlePair(String ble_pair_Id);
	
	@Query(value="select * from \r\n"
			+ "(select  \r\n"
			+ "       sum(case when Dumper_Device_Mapping.Category = \"Dumper\" then 1 else 0 end) as Total_Dumpers,\r\n"
			+ "       sum(case when Dumper_Device_Mapping.Category = \"Shovel\" then 1 else 0 end) as Total_Shovels,\r\n"
			+ "       sum(case when Dumper_Device_Mapping.Category = \"Unloading\" then 1 else 0 end) as Total_Unloading_points\r\n"
			+ "                              from Dumper_Device_Mapping\r\n"
			+ "        )  TotalCount\r\n"
			+ "",nativeQuery=true)
	List<?> getCountofDumperShovels();
	

	@Query(value="select * from Dumper_Device_Mapping where Dumper_Device_Mapping.Category='Dumper'",nativeQuery = true)
	List<DeviceMapping> getAllDumpers();
	
	
}
