package com.ants.sccl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ants.sccl.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
	
	@Query(value="select * from User_Details where User_Email=?1",nativeQuery = true)
	Optional<UserDetails> getUserDetailsBasedOnEmail(String userEmail);
	
}
