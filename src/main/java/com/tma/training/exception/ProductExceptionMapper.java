package com.tma.training.exception;

import com.tma.training.common.Result;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ProductExceptionMapper implements ExceptionMapper<ProductException> {

    @Override
    public Response toResponse(ProductException exception) {
        return Response.ok(new Result<>(exception.getCode(), exception.getMessage())).build();
    }
}
