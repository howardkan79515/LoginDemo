package com.howard.smartbee.exception;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.howard.smartbee.vo.ErrorVo;





@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    private ErrorVo handleException(HttpServletRequest req, Exception e) {
        ApiException ae = wrapToApiException(e);
        ErrorVo result = new ErrorVo();
        result.setCode(ae.getResultCode());
        result.setMessage(ae.getMessage());
        result.setPath(req.getRequestURI());
        result.setTimestamp(new Date());
        result.setData(ae.getData());
        return result;
    }
    
    private ApiException wrapToApiException(Exception e) {
        if(e instanceof ConstraintViolationException) {
            return new InvalidParameterException(((ConstraintViolationException) e)
                    .getConstraintViolations().iterator().next().getMessage()); 
        }
        else if(e instanceof IllegalArgumentException ||
                e instanceof MethodArgumentTypeMismatchException ||
                e instanceof MissingServletRequestParameterException) {
            return new InvalidParameterException(e.getMessage()); 
        }
        else if(e instanceof NoHandlerFoundException) {
        	
            return new ServiceUnsupportedException(e.getMessage()); 
        }
        else if(e instanceof HttpRequestMethodNotSupportedException) {
            return new RequestMethodNotSupportedException(e.getMessage()); 
        }
        else if(e instanceof InvalidParameterException) { 
            return (ApiException) e;
        } else {
            e.printStackTrace();
            return new InternalServerErrorException(e.getMessage());
        }
    }
}
