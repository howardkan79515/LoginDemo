package com.howard.smartbee.exception;



public class InvalidParameterException extends ApiException {
    private static final long serialVersionUID = 4509017585490690638L;

    public InvalidParameterException(String message) {
        super(ApiResult.InvalidParameter, message);
    }

}
 