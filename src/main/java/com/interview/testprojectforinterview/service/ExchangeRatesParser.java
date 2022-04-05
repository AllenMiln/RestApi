package com.interview.testprojectforinterview.service;

import com.interview.testprojectforinterview.model.ExchangeRate;

import java.util.List;

public interface ExchangeRatesParser {

    List<ExchangeRate> parseExchangeRates(String exchangeRates);
}
