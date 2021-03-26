package com.ants.sccl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ants.sccl.model.LiveLocation;

public interface LiveLocationRepository extends JpaRepository<LiveLocation, String> {

}
