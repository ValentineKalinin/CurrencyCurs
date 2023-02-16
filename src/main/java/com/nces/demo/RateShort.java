package com.nces.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RateShort {
    @JsonProperty(value = "Date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date currencyDate;
    @JsonProperty(value = "Cur_OfficialRate")
    private Double currencyRate;

    public RateShort() {}

    public RateShort(Date currencyDate, Double currencyRate) {
        this.currencyDate = currencyDate;
        this.currencyRate = currencyRate;
    }

    public Date getCurrencyDate() {
        return currencyDate;
    }

    public void setCurrencyDate(Date currencyDate) {
        this.currencyDate = currencyDate;
    }

    public Double getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(Double currencyRate) {
        this.currencyRate = currencyRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RateShort rateShort)) return false;

        if (!getCurrencyDate().equals(rateShort.getCurrencyDate())) return false;
        return getCurrencyRate().equals(rateShort.getCurrencyRate());
    }

    @Override
    public int hashCode() {
        int result = getCurrencyDate().hashCode();
        result = 31 * result + getCurrencyRate().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RateShort{" +
                "currencyDate=" + currencyDate +
                ", currencyRate='" + currencyRate + '\'' +
                '}';
    }
}
