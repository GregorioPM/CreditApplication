package com.demo.credits.service.impl;


import com.demo.credits.dto.RequestCreditDto;
import com.demo.credits.dto.ResponseCreditDto;
import com.demo.credits.entity.Credit;
import com.demo.credits.enums.CreditScore;
import com.demo.credits.enums.CreditStatus;
import com.demo.credits.exception.CreditNotFoundException;
import com.demo.credits.mapper.CreditMapper;
import com.demo.credits.repository.CreditRepository;
import com.demo.credits.service.ICreditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class that implements the service methods for requesting and retrieving credit information.
 *
 * @author Jose Gregorio Perez Manosalva
 */
@Slf4j
@Service
@AllArgsConstructor
public class CreditServiceImpl implements ICreditService {

    private final CreditRepository creditRepository;
    private final CreditMapper creditMapper;
    private final ObjectMapper objectMapper;
    private static final int MONTHS_IN_YEAR = 12;
    private static final int LOAN_MULTIPLIER = 3;

    /**
     * Service that submits a credit request.
     *
     * @param creditDto object containing the credit information
     * @author Jose Gregorio Perez Manosalva
     * @return ResponseCreditDto
     */
    @Override
    public ResponseCreditDto save(RequestCreditDto creditDto) {
        Credit credit = creditMapper.creditDtoToCredit(creditDto);
        if (credit.getCreditScore() < CreditScore.REJECTED.getValue()) {
            log.info("{} - Credit rejected", credit.getApplicantName());
            credit.setStatus(CreditStatus.REJECTED.getValue());
        } else if (credit.getCreditScore() >= CreditScore.APPROVED.getValue() &&
                credit.getLoanAmount() <= (credit.getMonthlyIncome() * MONTHS_IN_YEAR) / LOAN_MULTIPLIER) {
            log.info("{} - Credit approved", credit.getApplicantName());
            credit.setStatus(CreditStatus.APPROVED.getValue());
        } else {
            log.info("{} - Credit pending", credit.getApplicantName());
            credit.setStatus(CreditStatus.PENDING.getValue());
        }
        return objectMapper.convertValue(creditRepository.save(credit), ResponseCreditDto.class);
    }

    /**
     * Service for retrieving credit information by its ID.
     *
     * @param id the credit identifier
     * @author Jose Gregorio Perez Manosalva
     * @return ResponseCreditDto
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseCreditDto findById(Long id) {
        return objectMapper.convertValue(creditRepository.findById(id)
                .orElseThrow(() -> new CreditNotFoundException("Credit not found by id: " + id)),
                ResponseCreditDto.class);
    }

}
