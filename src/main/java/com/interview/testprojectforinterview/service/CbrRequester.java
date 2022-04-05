package com.interview.testprojectforinterview.service;

public interface CbrRequester {

    /**
     * Получить данные с сайта цб
     *
     * @param url сайт цб
     * @param date дата
     * @return нераспарсенные данные с сайта цб за день
     */
    String getRatesAsXml(String url, String date);
}
