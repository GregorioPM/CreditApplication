package com.demo.credits.service;


import com.demo.credits.dto.RequestCreditDto;
import com.demo.credits.dto.ResponseCreditDto;
import com.demo.credits.entity.Credit;

/**
 * Class that implements the service methods for requesting and retrieving credit information.
 *
 * @author Jose Gregorio Perez Manosalva
 */
public interface ICreditService {

    ResponseCreditDto save(RequestCreditDto creditDto);

    ResponseCreditDto findById(Long id);

}
