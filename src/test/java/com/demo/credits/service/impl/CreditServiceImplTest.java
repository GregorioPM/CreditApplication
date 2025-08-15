package com.demo.credits.service.impl;

import com.demo.credits.dto.RequestCreditDto;
import com.demo.credits.dto.ResponseCreditDto;
import com.demo.credits.entity.Credit;
import com.demo.credits.enums.CreditStatus;
import com.demo.credits.exception.CreditNotFoundException;
import com.demo.credits.mapper.CreditMapper;
import com.demo.credits.repository.CreditRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditServiceImplTest {

    @Mock
    private CreditRepository creditRepository;

    @Mock
    private CreditMapper creditMapper;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CreditServiceImpl creditService;

    private RequestCreditDto dto;
    private Credit credit;

    @BeforeEach
    void setUp() {
        credit = new Credit();
        credit.setLoanAmount(10000.0);
        credit.setMonthlyIncome(4000.0);
    }

    @Test
    void rejectedCredit() {
        RequestCreditDto dto = new RequestCreditDto(
                "Jose Perez",
                10000.0,
                "Buy car",
                4000.0,
                500
        );

        ResponseCreditDto responseCreditDto = new ResponseCreditDto(
                1L,
                "Jose Perez",
                10000.0,
                "Buy car",
                4000.0,
                500,
                "REJECTED"
        );

        credit.setCreditScore(dto.creditScore());

        when(creditMapper.creditDtoToCredit(dto)).thenReturn(credit);
        when(creditRepository.save(credit)).thenReturn(credit);
        when(objectMapper.convertValue(any(), any(Class.class))).thenReturn(responseCreditDto);
        ResponseCreditDto result = creditService.save(dto);

        assertEquals(responseCreditDto, result);
        verify(creditRepository).save(credit);
    }

    @Test
    void creditApproved() {
        RequestCreditDto dto = new RequestCreditDto(
                "Jose Perez",
                10000.0,
                "Buy car",
                4000.0,
                750
        );

        ResponseCreditDto responseCreditDto = new ResponseCreditDto(
                1L,
                "Jose Perez",
                10000.0,
                "Buy car",
                4000.0,
                750,
                "APPROVED"
        );

        credit.setCreditScore(dto.creditScore());
        credit.setLoanAmount(dto.loanAmount());
        credit.setMonthlyIncome(dto.monthlyIncome());

        when(creditMapper.creditDtoToCredit(dto)).thenReturn(credit);
        when(creditRepository.save(credit)).thenReturn(credit);
        when(objectMapper.convertValue(any(), any(Class.class))).thenReturn(responseCreditDto);

        ResponseCreditDto result = creditService.save(dto);

        assertEquals(CreditStatus.APPROVED.getValue(), result.status());
        verify(creditRepository).save(credit);
    }

    @Test
    void returnPendingcreditScoreHigh() {
        RequestCreditDto dto = new RequestCreditDto(
                "Jose Perez",
                20000.0,
                "buy house",
                4000.0,
                750
        );

        ResponseCreditDto responseCreditDto = new ResponseCreditDto(
                1L,
                "Jose Perez",
                20000.0,
                "buy house",
                4000.0,
                750,
                "PENDING"
        );

        credit.setCreditScore(dto.creditScore());
        credit.setLoanAmount(dto.loanAmount());
        credit.setMonthlyIncome(dto.monthlyIncome());

        when(creditMapper.creditDtoToCredit(dto)).thenReturn(credit);
        when(creditRepository.save(credit)).thenReturn(credit);
        when(objectMapper.convertValue(any(), any(Class.class))).thenReturn(responseCreditDto);

        ResponseCreditDto result = creditService.save(dto);
        assertEquals(CreditStatus.PENDING.getValue(), result.status());
        verify(creditRepository).save(credit);
    }

    @Test
    void creditPending() {
        RequestCreditDto dto = new RequestCreditDto(
                "Jose Perez",
                20000.0,
                "buy house",
                4000.0,
                700
        );

        ResponseCreditDto responseCreditDto = new ResponseCreditDto(
                1L,
                "Jose Perez",
                20000.0,
                "buy house",
                4000.0,
                750,
                "PENDING"
        );

        credit.setCreditScore(dto.creditScore());
        credit.setLoanAmount(dto.loanAmount());
        credit.setMonthlyIncome(dto.monthlyIncome());

        when(creditMapper.creditDtoToCredit(dto)).thenReturn(credit);
        when(creditRepository.save(credit)).thenReturn(credit);
        when(objectMapper.convertValue(any(), any(Class.class))).thenReturn(responseCreditDto);


        ResponseCreditDto result = creditService.save(dto);

        assertEquals(CreditStatus.PENDING.getValue(), result.status());
        verify(creditRepository).save(credit);
    }


    @Test
    void creditFindByIdIfExists() {
        credit.setId(1L);
        ResponseCreditDto responseCreditDto = new ResponseCreditDto(
                1L,
                "Jose Perez",
                20000.0,
                "buy house",
                4000.0,
                750,
                "PENDING"
        );

        when(creditRepository.findById(1L)).thenReturn(java.util.Optional.of(credit));
        when(objectMapper.convertValue(any(), any(Class.class))).thenReturn(responseCreditDto);
        ResponseCreditDto result = creditService.findById(1L);

        assertEquals(responseCreditDto, result);
    }

    @Test
    void creditFindByIdNotFound() {
        when(creditRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        Exception exception = assertThrows(CreditNotFoundException.class,
                () -> creditService.findById(1L));

        assertTrue(exception.getMessage().contains("Credit not found by id: 1"));
    }

}