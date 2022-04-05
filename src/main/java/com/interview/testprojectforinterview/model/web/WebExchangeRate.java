package com.interview.testprojectforinterview.model.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WebExchangeRate {

    /**
     * Числовой код валюты
     */
    String numCode;

    /**
     * Символьный код валюты
     */
    String charCode;

    /**
     * Номинал валюты
     */
    String nominal;

    /**
     * Наименование валюты
     */
    String name;

    /**
     * Номинал валюты
     */
    String value;
}
