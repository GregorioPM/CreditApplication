package com.demo.credits.enums;

import lombok.Getter;

@Getter
public enum CreditStatus {

    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    PENDING("PENDING");


    private final String value;

    CreditStatus(String value) {
        this.value = value;
    }

}
