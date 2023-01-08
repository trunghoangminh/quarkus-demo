package com.tma.training.interceptor.filter;

import io.quarkus.runtime.BlockingOperationControl;
import io.vertx.core.http.HttpServerRequest;
import lombok.extern.jbosslog.JBossLog;
import org.apache.commons.io.IOUtils;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
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
            String body = IOUtils.toString(requestContext.getEntityStream(), StandardCharsets.UTF_8);
            InputStream in = IOUtils.toInputStream(body, StandardCharsets.UTF_8);
            requestContext.setEntityStream(in);
            log.infof("Path: %s | Method: %s | Data: %s", httpRequest.path(), httpRequest.method(), body);
        } else {
            // Log non-blocking request
            httpRequest.body(buffer -> {
                log.infof("Path: %s | Method: %s | Data: %s", httpRequest.path(), httpRequest.method(), buffer.result());
            });
        }
    }
}
