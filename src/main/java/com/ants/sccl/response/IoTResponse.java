package com.ants.sccl.response;

public class IoTResponse {
	private String status;
	private String ignition_status;
	private String sw_ver;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIgnition_status() {
		return ignition_status;
	}
	public void setIgnition_status(String ignition_status) {
		this.ignition_status = ignition_status;
	}
	public String getSw_ver() {
		return sw_ver;
	}
	public void setSw_ver(String sw_ver) {
		this.sw_ver = sw_ver;
	}
	public IoTResponse(String status, String ignition_status, String sw_ver) {
		super();
		this.status = status;
		this.ignition_status = ignition_status;
		this.sw_ver = sw_ver;
	}
	public IoTResponse() {
		
	}
	
	
		
}
