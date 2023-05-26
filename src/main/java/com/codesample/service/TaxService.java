package com.codesample.service;

import com.codesample.dto.tax.TaxDTO;

public interface TaxService {

    TaxDTO calculateTaxPerMonth(int month, int year);
}
