package com.howard.smartbee.exception;


public class RequestMethodNotSupportedException extends ApiException {
    private static final long serialVersionUID = 7236026700046422443L;

    public RequestMethodNotSupportedException(String message) {
        super(ApiResult.RequestMethodNotSupported, message);
    }
}
