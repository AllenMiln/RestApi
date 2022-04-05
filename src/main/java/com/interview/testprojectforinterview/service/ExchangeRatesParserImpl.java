package com.interview.testprojectforinterview.service;

import com.interview.testprojectforinterview.model.ExchangeRate;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ExchangeRatesParserImpl implements ExchangeRatesParser {

    @Override
    public List<ExchangeRate> parseExchangeRates(String exchangeRates) {
        ArrayList<ExchangeRate> rates;

        var dbf = DocumentBuilderFactory.newInstance();
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var db = dbf.newDocumentBuilder();

            try (var reader = new StringReader(exchangeRates)) {
                Document doc = db.parse(new InputSource(reader));
                doc.getDocumentElement().normalize();

                NodeList list = doc.getElementsByTagName("Valute");

                rates = IntStream.range(0, list.getLength()).mapToObj(list::item)
                        .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
                        .map(node -> (Element) node)
                        .map(element -> ExchangeRate.builder()
                                .numCode(element.getElementsByTagName("NumCode").item(0).getTextContent())
                                .charCode(element.getElementsByTagName("CharCode").item(0).getTextContent())
                                .nominal(element.getElementsByTagName("Nominal").item(0).getTextContent())
                                .name(element.getElementsByTagName("Name").item(0).getTextContent())
                                .value(element.getElementsByTagName("Value").item(0).getTextContent())
                                .build())
                        .collect(Collectors.toCollection(ArrayList::new));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return rates;
    }
}
