package com.howard.smartbee.vo;

@SuppressWarnings("rawtypes")
public class ResultVo<T> {
	private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
	private static final String DEFAULT_FAIL_MESSAGE = "FAIL";
    private String code;
    private String message;
    private Object data;
    private Long totalElements;
    
    public static ResultVo<?> genSuccessResult() {
		final ResultVo<?> result = new ResultVo();
		result.setCode("0");
		result.setMessage(DEFAULT_SUCCESS_MESSAGE);
    	return result;
    }
    
    
    public static ResultVo<?> genSuccessResult(Object data) {
    	final ResultVo<?> result = new ResultVo();
		result.setCode("0");
		result.setMessage(DEFAULT_SUCCESS_MESSAGE);
		result.setData(data);
    	return result;
    }
    

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public static String getDefaultSuccessMessage() {
		return DEFAULT_SUCCESS_MESSAGE;
	}

	public static String getDefaultFailMessage() {
		return DEFAULT_FAIL_MESSAGE;
	}
    
}
