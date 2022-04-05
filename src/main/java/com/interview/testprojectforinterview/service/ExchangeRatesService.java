package com.interview.testprojectforinterview.service;

import com.interview.testprojectforinterview.model.web.WebExchangeRate;

import java.util.List;

public interface ExchangeRatesService {

    /**
     * Получить данные для теста
     *
     * @return данные для теста
     */
    String getTestExchangeRates();

    /**
     * Получить все данные с сайта ЦБ за день
     *
     * @param date дата. Согласно контракту необходимо использовать дату вида "dd/MM/yyyy"
     * @return данные ЦБ за день
     */
    List<WebExchangeRate> getAllExchangeRatesPerDay(String date);

    /**
     * Получить данные по валюте с сайта ЦБ за день
     *
     * @param date дата. Согласно контракту необходимо использовать дату вида "dd/MM/yyyy"
     * @param charCode код валюты
     * @return данные по валюте за день
     */
    WebExchangeRate getExchangeRatePerDay(String date, String charCode);
}
