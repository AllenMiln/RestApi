package com.interview.testprojectforinterview.dao;

import com.interview.testprojectforinterview.model.ExchangeRate;

import java.util.List;

public interface ExchangeRatesDao {

    List<ExchangeRate> getAllExchangeRatesPerDay(String date);

    void saveExchangeRates(List<ExchangeRate> exchangeRates, String date);


}
