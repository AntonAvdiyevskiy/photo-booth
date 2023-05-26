package com.codesample.service.impl;

import com.codesample.dto.tax.TaxDTO;
import com.codesample.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static com.codesample.constants.Constants.Tax.SALES_TAX_RATE;

class TaxServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    private TaxServiceImpl taxService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taxService = new TaxServiceImpl(orderRepository);
    }

    @Test
    void calculateTaxPerMonth_shouldReturnTaxDTOWithCalculatedTax() {

        int month = 5;
        int year = 2023;
        double revenueByMonth = 5000.0;
        double expectedTax = (revenueByMonth / 100) * SALES_TAX_RATE;
        when(orderRepository.calculateRevenueByMonth(month, year)).thenReturn(revenueByMonth);

        TaxDTO taxDTO = taxService.calculateTaxPerMonth(month, year);

        assertEquals(expectedTax, taxDTO.getTax());
    }
}