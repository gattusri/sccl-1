package com.ants.sccl.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ants.sccl.model.DumperData;
import com.ants.sccl.model.DumperTransaction;
@Repository
@Transactional
public interface DumperRepository extends JpaRepository<DumperData, String> {
	
	@Query(value="select * from dumper_transaction where dumper_id=?1 and load_device_value=?2 and status=?3",nativeQuery = true)
	Optional<?> existsByDumperId(String dumperId,String deviceId,String status);

			

		//createDumperWithloader(String dumperId, String bluetoothValue, Date dateTime, String loading);

	//void updateDumperWithloader(String dumperId, String bluetoothValue, Date dateTime, String loading);

	
	
}
