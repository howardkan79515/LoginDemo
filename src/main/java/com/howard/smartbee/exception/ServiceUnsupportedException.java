package com.howard.smartbee.exception;


public class ServiceUnsupportedException extends ApiException {
    private static final long serialVersionUID = 4962414512558721288L;

    public ServiceUnsupportedException(String message) {
        super(ApiResult.ServiceUnsupported, message);
    }
}
