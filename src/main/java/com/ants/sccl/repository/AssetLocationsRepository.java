package com.ants.sccl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ants.sccl.model.AssetLocations;

public interface AssetLocationsRepository extends JpaRepository<com.ants.sccl.model.AssetLocations, String> {

	@Query(value="select * from Asset_Location_Mapping where RFID_Reader_Code=?1 and  CH_Antenna=?2 and  SUB_CH=?3",nativeQuery = true)
	AssetLocations getLocationBasedOnParameters(String getrFIDReaderCode, int getcH_Antenna, int subch);

}
