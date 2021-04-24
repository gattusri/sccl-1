package com.ants.sccl.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ants.sccl.model.DumperTransaction;
import com.ants.sccl.projections.DumperDetailsModel;
import com.ants.sccl.projections.DurationOfTripModel;

@Repository
@Transactional
public interface DumperTransactionrepositery extends JpaRepository<DumperTransaction, String> {
	
	@Query(value="select * from Dumper_Transaction where dumper_id=?1 and load_device_value=?2 and status=?3",nativeQuery = true)
	Optional<DumperTransaction> checkDumperWithLoader(String dumperId, String bluetoothValue, String string);
	
	@Query(value="select * from Dumper_Transaction where id=( SELECT MAX(ID) FROM Dumper_Transaction where dumper_id=?1)",nativeQuery=true)
	Optional<DumperTransaction> getStatusofrecord(String dumperId);

	@Query(value="select * from Dumper_Transaction where dumper_id=?1 and status=?2",nativeQuery = true)
	Optional<DumperTransaction> checkDumperWithUnLoader(String dumperId, String string);

	// new API for dumper trip count
	
	@Query(value="select * from Dumper_Transaction where dumper_id=?1 and load_device_value=?2 and status=?3",nativeQuery = true)
	Optional<DumperTransaction> checkWithDumperShovelRecord(String dumper_ID, String bluetooth_device_ID, String string);

	@Query(value="select * from Dumper_Transaction where dumper_id=?1 and status=?2",nativeQuery = true)
	Optional<DumperTransaction> checkWithDumperShovelRecord(String dumper_ID, String string);
	
	@Query(value="select * from Dumper_Transaction where dumper_id=?1 and unload_device_value=?2 and status=?3",nativeQuery = true)
	Optional<DumperTransaction> checkWithDumperUnloadRecord(String dumper_ID, String bluetooth_device_ID, String string);


	@Query(value="select * from Dumper_Transaction where dumper_id=?1 and  (status='loading' or status='loaded' or status='unloading')",nativeQuery = true)
	Optional<DumperTransaction> checkWithDumperRecordWithOutTripCompleted(String dumper_ID);
	
	@Query(value="select * from \r\n"
			+ "(select  Date(Unload_End_Time) as Date,\r\n"
			+ "       count(distinct(Dumper_Id)) as Dumper_Count,\r\n"
			+ "       count(distinct(Load_Device_Value)) as Shovel_Count,\r\n"
			+ "       sum(case when Dumper_Transaction.Status = \"TripCompleted\" then 1 else 0 end) as Trip_Count \r\n"
			+ "                              from Dumper_Transaction\r\n"
			+ "                               where Date(Unload_End_Time) between ?1 And ?2\r\n"
			+ "         Group by Date(Unload_End_Time)) TripCount;",nativeQuery = true)
	List<?> getDataWithFromDateToDate(Date fromDate,Date toDate);
	
	@Query(value="select \r\n"
			+ "(select T2.Mapping_Id from SCCL_Demo.Dumper_Device_Mapping T2\r\n"
			+ "where T2.Device_Id = T1.Dumper_Id) Dumper_Id,\r\n"
			+ "(Select T2.Mapping_Id from SCCL_Demo.Dumper_Device_Mapping T2\r\n"
			+ "where T2.Device_Id =  T1.Load_Device_Value) load_device_value,\r\n"
			+ "(Select T2.Mapping_Id from SCCL_Demo.Dumper_Device_Mapping T2\r\n"
			+ "where T2.Device_Id = T1.Unload_Device_Value) unload_device_value, T1.Load_Start_Time, T1.Unload_End_Time\r\n"
			+ "from SCCL_Demo.Dumper_Transaction T1\r\n"
			+ "where T1.Status = 'TripCompleted' order by T1.Unload_End_Time desc limit 5;",nativeQuery=true)
	List<?> getRecentTrips();
	
	
	
	@Query(value="select DISTINCT\r\n"
			+ "(select T2.Mapping_Id from SCCL_Demo.Dumper_Device_Mapping T2\r\n"
			+ "where T2.Device_Id = T1.Dumper_Id) dumperId,\r\n"
			+ "(Select T2.Mapping_Id from SCCL_Demo.Dumper_Device_Mapping T2\r\n"
			+ "where T2.Device_Id = T1.Load_Device_Value) loadDeviceValue,\r\n"
			+ "(Select T2.Mapping_Id from SCCL_Demo.Dumper_Device_Mapping T2\r\n"
			+ "where T2.Device_Id = T1.Unload_Device_Value) unloadDeviceValue, Date(T1.Unload_End_Time) as Date,\r\n"
			+ "count(*) over(Partition by T1.Dumper_Id, T1.Load_Device_Value, T1.Unload_Device_Value,Date(T1.Unload_End_Time)) as status\r\n"
			+ "from SCCL_Demo.Dumper_Transaction T1\r\n"
			+ "where T1.Status = 'TripCompleted' and Date(T1.Unload_End_Time) between ?1 and ?2\r\n"
			+ "order by Date(T1.Unload_End_Time) desc",nativeQuery=true)
    List<DumperDetailsModel> getDumperDetailsCount(Date fromDate,Date toDate);
	
	@Query(value="select\r\n"
			+ "(select T2.Mapping_Id from SCCL_Demo.Dumper_Device_Mapping T2\r\n"
			+ "where T2.Device_Id = T1.Dumper_Id) dumperId,\r\n"
			+ "(Select T2.Mapping_Id from SCCL_Demo.Dumper_Device_Mapping T2\r\n"
			+ "where T2.Device_Id = T1.Load_Device_Value) loadDeviceValue,\r\n"
			+ "(Select T2.Mapping_Id from SCCL_Demo.Dumper_Device_Mapping T2\r\n"
			+ "where T2.Device_Id = T1.Unload_Device_Value) unloadDeviceValue, Date(T1.Unload_End_Time) as date,\r\n"
			+ "T1.Load_Start_Time as loadStartTime , T1.Unload_End_Time as unloadEndTime,\r\n"
			+ "(SELECT (TIMEDIFF((T1.Unload_End_Time),(T1.Load_Start_Time)))) as timeDiff\r\n"
			+ "from SCCL_Demo.Dumper_Transaction T1\r\n"
			+ "where T1.Status = 'TripCompleted' and Date(T1.Unload_End_Time) between ?1 and ?2\r\n"
			+ "order by T1.Unload_End_Time desc;\r\n"
			+ "" ,nativeQuery = true)
	List<DurationOfTripModel> getDurationOfTheTripDTO(Date fromDate, Date toDate);
}
