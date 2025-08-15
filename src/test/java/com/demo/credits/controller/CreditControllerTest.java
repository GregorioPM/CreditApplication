package com.demo.credits.controller;


import com.demo.credits.dto.RequestCreditDto;
import com.demo.credits.dto.ResponseCreditDto;
import com.demo.credits.entity.Credit;
import com.demo.credits.service.ICreditService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(CreditController.class)
class CreditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ICreditService creditService;

    @Test
    void postCreatedCredit() throws Exception {
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


        when(creditService.save(dto)).thenReturn(responseCreditDto);

        mockMvc.perform(post("/api/v1/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.applicantName").value("Jose Perez"))
                .andExpect(jsonPath("$.loanAmount").value(20000.0))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    void getCredit() throws Exception {
        Long creditId = 1L;

        ResponseCreditDto responseCreditDto = new ResponseCreditDto(
                creditId,
                "Jose Perez",
                20000.0,
                "buy house",
                4000.0,
                750,
                "PENDING"
        );

        when(creditService.findById(creditId)).thenReturn(responseCreditDto);

        mockMvc.perform(get("/api/v1/applications/{id}", creditId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.applicantName").value("Jose Perez"))
                .andExpect(jsonPath("$.loanAmount").value(20000.0))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

}