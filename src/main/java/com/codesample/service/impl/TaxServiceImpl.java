package com.codesample.service.impl;

import com.codesample.dto.tax.TaxDTO;
import com.codesample.repository.OrderRepository;
import com.codesample.service.TaxService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.codesample.constants.Constants.Tax.SALES_TAX_RATE;

@Service
@AllArgsConstructor
public class TaxServiceImpl implements TaxService {

    private OrderRepository orderRepository;

    @Override
    public TaxDTO calculateTaxPerMonth(int month, int year) {

        double revenueByMonth = orderRepository.calculateRevenueByMonth(month, year);

        double calculatedTax = (revenueByMonth / 100) * SALES_TAX_RATE;

        TaxDTO taxDTO = new TaxDTO(calculatedTax);

        return taxDTO;
    }
}
