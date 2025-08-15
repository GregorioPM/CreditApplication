package com.demo.credits.enums;

import lombok.Getter;

@Getter
public enum CreditScore {

    REJECTED (600),
    APPROVED(750);

    private final Integer value;

    CreditScore(int value) {
        this.value = value;
    }

}
