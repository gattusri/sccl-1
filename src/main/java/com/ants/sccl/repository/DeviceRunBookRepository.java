package com.ants.sccl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ants.sccl.model.DeviceRunBook;



@Repository
public interface DeviceRunBookRepository extends JpaRepository<DeviceRunBook, Integer> {

	@Query(value="select * from Device_Runbook where End IS NULL and Device_Sensor_Id=?1", nativeQuery = true)
	DeviceRunBook findDevice(String deviceId);

}
