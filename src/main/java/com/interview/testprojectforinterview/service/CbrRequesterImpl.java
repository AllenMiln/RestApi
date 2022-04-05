package com.interview.testprojectforinterview.service;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CbrRequesterImpl implements CbrRequester {

    public String getRatesAsXml(String url, String date) {
        try {

            //todo Переделать на что-то вменяемое, не используя параметры через "String.format()"
            var client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("%s?date_req=%s", url, date)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception ex) {
            if (ex instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new RuntimeException(ex);
        }
    }
}
