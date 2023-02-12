package com.nces.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

public class RateShort {
    @JsonProperty(value = "Date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    String currencyDate;
    @JsonProperty(value = "Cur_OfficialRate")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    String currencyRate;

    public RateShort(String currencyDate, String currencyRate) {
        this.currencyDate = currencyDate;
        this.currencyRate = currencyRate;
    }
}
