package com.tma.training.product.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class JsonParser {
    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    public static <T> T convert(Object data, Class<T> clazz) {
        try {
            return mapper.readValue(mapper.writeValueAsString(data), clazz);
        } catch (Exception e) {
            log.error("Parse fail", e);
        }
        return null;
    }
}
