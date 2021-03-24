package com.ants.sccl.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ants.sccl.model.DumperTransaction;

@Repository
@Transactional
public interface DumperTransactionrepositery extends JpaRepository<DumperTransaction, String> {
	
	/*
	 * @Query(
	 * value="UPDATE dumper_transaction SET Load_End_time=?3 where dumper_id=?1 and load_device_value=?2 and status=?3 and status=?4"
	 * ,nativeQuery = true) void updateDumperWithloader(String dumperId, String
	 * bluetoothValue, Date dateTime, String loading);
	 */
	@Query(value="select * from dumper_transaction where dumper_id=?1 and load_device_value=?2 and status=?3",nativeQuery = true)
	Optional<DumperTransaction> checkDumperWithLoader(String dumperId, String bluetoothValue, String string);
	
	@Query(value="select * from sccl.dumper_transaction where id=( SELECT MAX(ID) FROM sccl.dumper_transaction where dumper_id=?1)",nativeQuery=true)
	Optional<DumperTransaction> getStatusofrecord(String dumperId);

	@Query(value="select * from dumper_transaction where dumper_id=?1 and status=?2",nativeQuery = true)
	Optional<DumperTransaction> checkDumperWithUnLoader(String dumperId, String string);

	// new API for dumper trip count
	
	@Query(value="select * from dumper_transaction where dumper_id=?1 and load_device_value=?2 and status=?3",nativeQuery = true)
	Optional<DumperTransaction> checkWithDumperShovelRecord(String dumper_ID, String bluetooth_device_ID, String string);

	@Query(value="select * from dumper_transaction where dumper_id=?1 and status=?2",nativeQuery = true)
	Optional<DumperTransaction> checkWithDumperShovelRecord(String dumper_ID, String string);
	
	@Query(value="select * from dumper_transaction where dumper_id=?1 and unload_device_value=?2 and status=?3",nativeQuery = true)
	Optional<DumperTransaction> checkWithDumperUnloadRecord(String dumper_ID, String bluetooth_device_ID, String string);
}
