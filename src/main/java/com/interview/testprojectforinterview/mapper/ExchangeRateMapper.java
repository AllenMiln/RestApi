package com.interview.testprojectforinterview.mapper;


import com.interview.testprojectforinterview.model.ExchangeRate;
import com.interview.testprojectforinterview.model.web.WebExchangeRate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {

    ExchangeRate map(WebExchangeRate webExchangeRate);
    List<ExchangeRate> mapWebExchangeRates(List<WebExchangeRate> webExchangeRates);

    WebExchangeRate map(ExchangeRate exchangeRate);
    List<WebExchangeRate> mapExchangeRates(List<ExchangeRate> exchangeRates);
}
