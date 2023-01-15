package com.tma.training.interceptor.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import lombok.extern.jbosslog.JBossLog;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@JBossLog
@Provider
public class ResponseLoggingFilter implements ContainerResponseFilter {

    @Context
    HttpServerResponse httpResponse;

    @Context
    HttpServerRequest httpRequest;

    @Inject
    ObjectMapper mapper;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        log.infof("Request %s %s | Data: %s", httpRequest.method(), httpRequest.path(), mapper.writeValueAsString(responseContext.getEntity()));
    }
}