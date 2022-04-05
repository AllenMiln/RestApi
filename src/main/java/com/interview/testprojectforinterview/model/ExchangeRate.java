package com.interview.testprojectforinterview.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExchangeRate {

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
