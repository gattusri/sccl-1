package com.ants.sccl.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ants.sccl.repository.AssetRepository;

@Component
@Service
public class AssetService {
	@Autowired
	AssetRepository assetRepository;
	
	
	
	
}
