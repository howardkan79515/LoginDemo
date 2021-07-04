package com.howard.smartbee.exception;

public abstract class ApiException extends RuntimeException {
    private static final long serialVersionUID = 6662639634663633359L;
    
    private String resultCode;
    
    private Object object;

	public ApiException(String resultCode, String message) {
		super(message);
		this.resultCode = resultCode;
	}
	
	public ApiException(String resultCode, String message, Object obj) {
		super(message);
		this.resultCode = resultCode;
		this.object = obj;
	}
	
	public Object getData() {
		return this.object;
	}

	public String getResultCode() {
		return this.resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
}
