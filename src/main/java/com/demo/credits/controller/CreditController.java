package com.demo.credits.controller;

import com.demo.credits.dto.RequestCreditDto;
import com.demo.credits.dto.ResponseCreditDto;
import com.demo.credits.entity.Credit;
import com.demo.credits.service.ICreditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("${app.context-api}")
@AllArgsConstructor
@Tag(name = "Credits", description = "Controller for credits")
public class CreditController {

    private final ICreditService creditService;

    /**
     * Service that returns the information of a credit.
     *
     * @param id credit ID
     * @return ResponseCreditDto
     * @author Jose Gregorio Perez Manosalva
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve a credit application by ID",
            description = """
                    Fetches the details of an existing credit application using its unique identifier.
                    """
    )
    @ApiResponse(
            responseCode = "200"
    )
    public ResponseEntity<ResponseCreditDto> getCredit(@PathVariable("id") Long id) {
        log.info("GET: credit {}", id);
        return ResponseEntity.ok(creditService.findById(id));
    }

    /**
     * Service that allows requesting a credit.
     *
     * @param credit information about the requested credit
     * @return ResponseCreditDto
     * @author Jose Gregorio Perez Manosalva
     */
    @PostMapping
    @Operation(
            summary = "Create a new credit application",
            description = """
                    Receives credit application details, automatically evaluates the application based on the credit
                     score and monthly income versus loan amount, assigns the status (APPROVED, REJECTED, or PENDING),
                     and saves it to the database.
                    """
    )
    @ApiResponse(
            responseCode = "201"
    )
    public ResponseEntity<ResponseCreditDto> createCredit (@Valid @RequestBody RequestCreditDto credit) {
        log.info("POST: credit for {}", credit.applicantName());
        return ResponseEntity.status(HttpStatus.CREATED).body(creditService.save(credit));
    }

}
