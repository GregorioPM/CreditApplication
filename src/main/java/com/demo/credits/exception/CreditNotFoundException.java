package com.demo.credits.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreditNotFoundException extends RuntimeException {

    private final String message;

    public String getMessage() {
        return this.message;
    }

}
