package com.demo.credits.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RequestCreditDto(
        @NotBlank(message = "Applicant name is required")
        @NotNull(message = "Applicant name is required")
        String applicantName,

        @Positive(message = "Loan amount must be greater than 0")
        Double loanAmount,

        @NotBlank(message = "Purpose is required")
        @NotNull(message = "Purpose is required")
        String purpose,

        @Positive(message = "Monthly income must be greater than 0")
        Double monthlyIncome,

        @Positive(message = "Credit score must be greater than 0")
        Integer creditScore
) {

}
