package com.demo.credits.dto;

public record ResponseCreditDto(
        Long id,

        String applicantName,

        Double loanAmount,

        String purpose,

        Double monthlyIncome,

        Integer creditScore,

        String status
) {
}
