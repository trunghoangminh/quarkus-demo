package com.tma.training.common;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class Result<T> {
    private static final String SUCCESS = "Success";
    private static final String FAILURE = "Failure";

    private String code;
    private String message;
    private T data;

    public Result(T data) {
        if (data == null) {
            code = "9999";
            message = FAILURE;
            return;
        }
        this.code = "0000";
        this.message = SUCCESS;
        this.data = data;
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

