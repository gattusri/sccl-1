package com.ants.sccl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ants.sccl.model.LiveLocation;

public interface LiveLocationRepository extends JpaRepository<LiveLocation, String> {

	@Query(value="SELECT * FROM sccl_demo.live_location where Device_Id=?1", nativeQuery = true)
	Optional<LiveLocation> checkLiveLocationExistOrNot(String deviceId);

}
