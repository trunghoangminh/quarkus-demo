package com.tma.training.common.enumeration;

/**
 * Error code and exception message
 */
public enum ProductCode {

    PRODUCT_NOT_FOUND("0001", "Product %s not found");

    private String code;
    private String message;

    private ProductCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
