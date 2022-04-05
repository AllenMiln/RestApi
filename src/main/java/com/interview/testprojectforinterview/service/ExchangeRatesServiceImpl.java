package com.interview.testprojectforinterview.service;

import com.interview.testprojectforinterview.dao.ExchangeRatesDao;
import com.interview.testprojectforinterview.mapper.ExchangeRateMapper;
import com.interview.testprojectforinterview.model.ExchangeRate;
import com.interview.testprojectforinterview.model.web.WebExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private final CbrRequester cbrRequester;
    private final ExchangeRatesParser exchangeRatesParser;
    private final ExchangeRateMapper exchangeRateMapper;
    private final ExchangeRatesDao exchangeRatesDao;

    @Value("${cbr.url}")
    private String CBR_URL;

    public ExchangeRatesServiceImpl(CbrRequester cbrRequester, ExchangeRatesParser exchangeRatesParser, ExchangeRateMapper exchangeRateMapper, ExchangeRatesDao exchangeRatesDao) {
        this.cbrRequester = cbrRequester;
        this.exchangeRatesParser = exchangeRatesParser;
        this.exchangeRateMapper = exchangeRateMapper;
        this.exchangeRatesDao = exchangeRatesDao;
    }

    @Override
    public String getTestExchangeRates() {
        return "Test string for Exchange Rates";
    }

    @Override
    public List<WebExchangeRate> getAllExchangeRatesPerDay(String date) {

        List<ExchangeRate> exchangeRatesPerDay = exchangeRatesDao.getAllExchangeRatesPerDay(date);
        if (exchangeRatesPerDay.isEmpty()) {
            String exchangeRates = cbrRequester.getRatesAsXml(CBR_URL, date);
            exchangeRatesPerDay = exchangeRatesParser.parseExchangeRates(exchangeRates);
            exchangeRatesDao.saveExchangeRates(exchangeRatesPerDay, date);
        }
        return exchangeRateMapper.mapExchangeRates(exchangeRatesPerDay);
    }

    @Override
    public WebExchangeRate getExchangeRatePerDay(String date, String charCode) {
        return null;
    }
}
