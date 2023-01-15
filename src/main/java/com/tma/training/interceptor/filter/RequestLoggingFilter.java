package com.tma.training.interceptor.filter;

import io.quarkus.runtime.BlockingOperationControl;
import io.vertx.core.http.HttpServerRequest;
import lombok.extern.jbosslog.JBossLog;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@JBossLog
@Provider
public class RequestLoggingFilter implements ContainerRequestFilter {

    @Context
    HttpServerRequest httpRequest;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Log blocking request
        if (BlockingOperationControl.isBlockingAllowed()) {
            InputStream is = requestContext.getEntityStream();
            byte[] data = is.readAllBytes();
            String body = new String(data, StandardCharsets.UTF_8);
            requestContext.setEntityStream(new ByteArrayInputStream(data));
            log.infof("Request %s %s | Data: %s", httpRequest.method(), httpRequest.path(), body);
        } else {
            // Log non-blocking request
            log.infof("Request %s %s | Data: %s", httpRequest.method(), httpRequest.path(), "");
//            httpRequest.body(buffer -> {
//                log.infof("Request %s %s | Data: %s", httpRequest.method(), httpRequest.path(), buffer.result());
//            });
        }
    }
}
