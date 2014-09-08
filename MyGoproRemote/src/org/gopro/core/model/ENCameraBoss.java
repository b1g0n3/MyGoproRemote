package org.gopro.core.model;

/**
 * 
 * The value returned by go pro when is ready to receive url cmd(start record...)
 * 
 * @author gonella
 *
 */
public enum ENCameraBoss {
	
	READY_TO_RECEIVE_CMD(1),
	NOT_READY_TO_RECEIVE_CMD(0);
	
	private Integer code;

	private ENCameraBoss(Integer code){
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
