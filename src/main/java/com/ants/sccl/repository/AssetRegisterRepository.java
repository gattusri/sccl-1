package com.ants.sccl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ants.sccl.model.AssetCount;
import com.ants.sccl.model.AssetRegister;
import com.ants.sccl.projections.AssetTrackingModel;

public interface AssetRegisterRepository extends JpaRepository<AssetRegister, String> {

	@Query(value="select * from Asset_Registration where asset_code=?1 and Status=1",nativeQuery = true)
	Optional<AssetRegister> getAssetBasedonAssetCode(String assetCode);
	
	@Query(value="select * from Asset_Registration where Location_Id=?1  and status=1",nativeQuery = true)
	List<AssetRegister> getAssetBasedonLocation(int locationId);

//	@Query(value="select Location_Id as locationId, count(asset_code) as assetCount from Asset_Registration group by Location_Id order by Location_Id",nativeQuery=true)
//	List<?> getAssetCountBasedonLocationId();
	
	@Query(value="SELECT L.Location_Id, count(AR.Asset_Code) as AssetCount FROM SCCL_Demo.Asset_Registration AR right join Asset_Location_Mapping L USING(Location_Id) group by L.Location_Id order by L.Location_Id",nativeQuery=true)
	List<?> getAssetCountBasedonLocationId();

	@Query(value="select AR.Asset_Code, AR.Asset_Description, ALM.Location_Id as Room_Id, ALM.Location_Description as Room\r\n"
			+ "from SCCL_Demo.Asset_Registration AR\r\n"
			+ "join SCCL_Demo.Asset_Location_Mapping ALM on\r\n"
			+ "AR.Location_Id = ALM.Location_Id and Status=1\r\n"
			+ "",nativeQuery=true)
	List<AssetTrackingModel> getAssetTrackingDTO();
	
	@Query(value="select * from Asset_Registration where Status=?1",nativeQuery=true)
	List<AssetRegister> getAssetBasedonStatus(byte status);
	
	
	
}
