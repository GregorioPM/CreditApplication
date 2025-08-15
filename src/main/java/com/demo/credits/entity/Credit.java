package com.demo.credits.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;

/**
 * Entity Credit.
 *
 * @author Jose Gregorio Perez Manosalva
 */
@Entity
@Data
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "applicantName")
    String applicantName;

    @Column(name = "loanAmount")
    double loanAmount;

    @Column(name = "purpose")
    String purpose;

    @Column(name = "monthlyIncome")
    double monthlyIncome;

    @Column(name = "creditScore")
    int creditScore;

    @Column(name = "status")
    String status;

}
