package com.tma.training.exception;

import com.tma.training.common.enumeration.ProductCode;
import lombok.Data;

/**
 * Product exception
 */
@Data
public class ProductException extends RuntimeException {

    private String code;

    public ProductException(ProductCode productCode, Object... params) {
        super(String.format(productCode.getMessage(), params));
        this.code = productCode.getCode();
    }
}

