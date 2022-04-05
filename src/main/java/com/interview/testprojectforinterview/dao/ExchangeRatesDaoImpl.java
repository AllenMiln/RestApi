package com.interview.testprojectforinterview.dao;

import com.interview.testprojectforinterview.model.ExchangeRate;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ExchangeRatesDaoImpl implements ExchangeRatesDao{

    private final JdbcTemplate jdbcTemplate;

    public ExchangeRatesDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ExchangeRate> getAllExchangeRatesPerDay(String date) {
        List<ExchangeRate> exchangeRates = new ArrayList<>();

        exchangeRates = jdbcTemplate.query("SELECT * FROM EXCHANGE_RATE WHERE DATE = ?",
                new PreparedStatementSetter() {
                    @SneakyThrows
                    @Override
                    public void setValues(PreparedStatement ps) {
                        ps.setDate(1, ExchangeRatesDaoImpl.this.parseDate(date));
                    }
                },
                (rs, rowNum) -> ExchangeRate.builder()
                        .numCode(rs.getString("NUM_CODE"))
                        .charCode(rs.getString("CHAR_CODE"))
                        .nominal(rs.getString("NOMINAL"))
                        .name(rs.getString("NAME"))
                        .value(rs.getString("VALUE"))
                        .build()
        );

        return exchangeRates;
    }

    @Override
    public void saveExchangeRates(List<ExchangeRate> exchangeRates, String date) {
        jdbcTemplate.batchUpdate(
                "INSERT INTO EXCHANGE_RATE (NUM_CODE, CHAR_CODE, NOMINAL, NAME, VALUE, DATE) VALUES (?, ?, ?, ? ,? ,?)",
                new BatchPreparedStatementSetter() {
                    @SneakyThrows
                    @Override
                    public void setValues(PreparedStatement ps, int i) {
                        ps.setString(1, exchangeRates.get(i).getNumCode());
                        ps.setString(2, exchangeRates.get(i).getCharCode());
                        ps.setString(3, exchangeRates.get(i).getNominal());
                        ps.setString(4, exchangeRates.get(i).getName());
                        ps.setString(5, exchangeRates.get(i).getValue());
                        ps.setDate(6, parseDate(date));
                    }

                    @Override
                    public int getBatchSize() {
                        return exchangeRates.size();
                    }
                });
    }

    private Date parseDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date finalParsedDate = format.parse(date);
        return new Date(finalParsedDate.getTime());
    }
}
