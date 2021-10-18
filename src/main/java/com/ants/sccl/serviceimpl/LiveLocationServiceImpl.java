package com.ants.sccl.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ants.sccl.repository.LiveLocationRepository;
import com.ants.sccl.service.LiveLocationService;

public class LiveLocationServiceImpl implements LiveLocationService {

	@Autowired
	LiveLocationRepository liveLocationRepository;
	
	
	
}
