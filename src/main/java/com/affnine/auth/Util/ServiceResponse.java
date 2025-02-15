package com.affnine.auth.Util;

import lombok.Getter;

public class ServiceResponse<T> {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    @Getter
    public final String status;
    public final String message;
    public final T data;

    public ServiceResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}