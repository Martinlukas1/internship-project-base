package com.virtuslab.internship.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String s) {
        super("Product: " + s + " not found");
    }
}
