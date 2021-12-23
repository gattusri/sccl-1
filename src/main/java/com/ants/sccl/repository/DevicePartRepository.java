package com.ants.sccl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ants.sccl.model.DevicePart;

@Repository
public interface DevicePartRepository extends JpaRepository<DevicePart, String> {

//	@Query(value="select * from Device_Runbook where End IS NULL and Device_Sensor_Id=?1", nativeQuery = true)
//	void saveRecord(DevicePart devicepart);

}
