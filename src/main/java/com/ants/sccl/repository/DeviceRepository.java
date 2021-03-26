package com.ants.sccl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ants.sccl.model.Device;

public interface DeviceRepository extends JpaRepository<Device, String> {

	
}
