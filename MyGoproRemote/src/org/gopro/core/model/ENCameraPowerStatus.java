package org.gopro.core.model;

public enum ENCameraPowerStatus {

	
	POWERON(1),
	POWEROFF(0);
	
	private Integer code;

	private ENCameraPowerStatus(Integer code){
		this.setCode(code);		
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String toString(){
		return String.valueOf(code);
	}
	
}
