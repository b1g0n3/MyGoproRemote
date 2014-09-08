package org.gopro.core.model;

public enum ENCameraReady {

	
	READY(1),
	NOT_READY(0);
	
	private Integer code;

	private ENCameraReady(Integer code){
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
