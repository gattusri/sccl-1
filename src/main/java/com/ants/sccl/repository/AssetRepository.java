package com.ants.sccl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ants.sccl.model.Asset;


public interface AssetRepository extends JpaRepository<Asset, String> {
	@Query(value="select * from Asset",nativeQuery = true)
	List<Asset> getAsset();
}
