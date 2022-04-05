package com.interview.testprojectforinterview.controller;

import com.interview.testprojectforinterview.model.web.WebExchangeRate;
import com.interview.testprojectforinterview.service.ExchangeRatesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/exchange-rates")
public class ExchangeRatesController {

    private static final String TEST = "/test";
    private static final String GET_ALL_EXCHANGE_RATES_PER_DAY = "/getAllPerDay";
    private static final String GET_EXCHANGE_RATES_PER_DAY = "/getPerDay";

    private final ExchangeRatesService exchangeRatesService;

    public ExchangeRatesController(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping(path = TEST)
    public String getTestExchangeRates() {
        return exchangeRatesService.getTestExchangeRates();
    }

    @GetMapping(path = GET_ALL_EXCHANGE_RATES_PER_DAY)
    public List<WebExchangeRate> getAllExchangeRatesPerDay(@RequestParam(value = "date_req") String date) {
        return exchangeRatesService.getAllExchangeRatesPerDay(date);
    }

    @GetMapping(path = GET_EXCHANGE_RATES_PER_DAY)
    public WebExchangeRate getExchangeRatesPerDay(
            @RequestParam(value = "date_req") String date,
            @RequestParam(value = "code") String charCode
    ) {
        return exchangeRatesService.getExchangeRatePerDay(date, charCode);
    }
}
