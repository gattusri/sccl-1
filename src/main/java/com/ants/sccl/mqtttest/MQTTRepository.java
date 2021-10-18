package com.ants.sccl.mqtttest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MQTTRepository extends JpaRepository<MQTTModel, Integer>{

}
