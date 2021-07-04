package com.howard.smartbee.exception;


public class InternalServerErrorException extends ApiException {
    private static final long serialVersionUID = -8421753051139068753L;

    public InternalServerErrorException(String message) {
        super(ApiResult.InternalServerError, message);
    }
}
