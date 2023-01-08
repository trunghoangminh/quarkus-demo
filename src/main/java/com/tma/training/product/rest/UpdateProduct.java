package com.tma.training.product.rest;

import com.tma.training.product.entity.Product;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import java.math.BigDecimal;

public interface UpdateProduct {

    @Data
    @RegisterForReflection
    public class RequestVO {
        private String name;
        private String description;
        private BigDecimal price;
        private String manufacturer;
    }

    @Data
    @RegisterForReflection
    public class ResponseVO {
        private String id;
        private String name;
        private String description;
        private BigDecimal price;
        private String manufacturer;
    }

    public static ResponseVO toVO(Product entity) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setId(entity.getId().toHexString());
        responseVO.setName(entity.getName());
        responseVO.setDescription(entity.getDescription());
        responseVO.setPrice(entity.getPrice());
        responseVO.setManufacturer(entity.getManufacturer());
        return responseVO;
    }
}
