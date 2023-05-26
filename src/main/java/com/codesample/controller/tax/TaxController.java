package com.codesample.controller.tax;

import com.codesample.dto.screen.SentenceDTO;
import com.codesample.dto.tax.TaxDTO;
import com.codesample.service.TaxService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo-booth")
@RequiredArgsConstructor
public class TaxController {

    private final TaxService taxService;

    @GetMapping(value = "/tax/year/{year}/month/{monthNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Calculate tax per month"
    )
    public TaxDTO getTaxValue(@PathVariable int year, @PathVariable int monthNumber) {
        return taxService.calculateTaxPerMonth(monthNumber, year);
    }
}
