package com.interview.testprojectforinterview.dao;

import com.interview.testprojectforinterview.model.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ExchangeRatesDaoImpl extends JdbcDaoSupport implements ExchangeRatesDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExchangeRatesDaoImpl(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.setDataSource(dataSource);
    }

    public List<ExchangeRate> getAllExchangeRatesPerDay(String date) {
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            java.util.Date finalParsedDate = format.parse(date);
            exchangeRates = jdbcTemplate.query("SELECT * FROM EXCHANGE_RATE WHERE DATE = ?",
                    ps -> ps.setDate(1, new Date(finalParsedDate.getTime())),
                    (rs, rowNum) -> ExchangeRate.builder()
                            .numCode(rs.getString("NUM_CODE"))
                            .charCode(rs.getString("CHAR_CODE"))
                            .nominal(rs.getString("NOMINAL"))
                            .name(rs.getString("NAME"))
                            .value(rs.getString("VALUE"))
                            .build()
            );
        } catch (ParseException e) {
            e.getMessage();
        }

        return exchangeRates;
    }

    @Override
    public void saveExchangeRates(List<ExchangeRate> exchangeRates, String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            java.util.Date finalParsedDate = format.parse(date);
            jdbcTemplate.batchUpdate(
                    "INSERT INTO EXCHANGE_RATE (NUM_CODE, CHAR_CODE, NOMINAL, NAME, VALUE, DATE) VALUES (?, ?, ?, ? ,? ,?)",
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, exchangeRates.get(i).getNumCode());
                            ps.setString(2, exchangeRates.get(i).getCharCode());
                            ps.setString(3, exchangeRates.get(i).getNominal());
                            ps.setString(4, exchangeRates.get(i).getName());
                            ps.setString(5, exchangeRates.get(i).getValue());
                            ps.setDate(6, new Date(finalParsedDate.getTime()));
                        }

                        @Override
                        public int getBatchSize() {
                            return exchangeRates.size();
                        }
                    });
        } catch (ParseException e) {
            e.getMessage();
        }
    }
}
