package com.demo.credits.mapper;


import com.demo.credits.dto.RequestCreditDto;
import com.demo.credits.entity.Credit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditMapper {

    Credit creditDtoToCredit(RequestCreditDto creditDto);

}
