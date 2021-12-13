package com.ants.sccl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ants.sccl.model.PilferageDetectionModel;

@Repository
public interface PilferageDetectionRepository extends JpaRepository<PilferageDetectionModel, String> {

}
