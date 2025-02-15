package com.affnine.auth.Util;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseUtils {

    public static <T>ResponseEntity<ServiceResponse<T>> makeResponse(HttpStatus httpStatus, String status, String message, T data){
        return ResponseEntity.status(httpStatus).body(new ServiceResponse<>(status,message,data));
    }

    public static <T>ResponseEntity<ServiceResponse<T>> successResponse(String message, T data){
        return makeResponse(HttpStatus.OK,ServiceResponse.SUCCESS,message,data);
    }

    public static <T>ResponseEntity<ServiceResponse<T>> badRequest(String message, T data){
        return makeResponse(HttpStatus.BAD_REQUEST, ServiceResponse.FAILED, message,data);
    }

    public static <T>ResponseEntity<ServiceResponse<T>> customSuccessResponse(String status, String message, T data){
        return makeResponse(HttpStatus.OK,status,message,data);
    }

    public static <T>ResponseEntity<ServiceResponse<T>> internalServerError(String message, T data){
        return makeResponse(HttpStatus.INTERNAL_SERVER_ERROR,ServiceResponse.FAILED,message,data);
    }
}