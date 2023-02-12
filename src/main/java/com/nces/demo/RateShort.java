package com.nces.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RateShort {
    @JsonProperty(value = "Cur_ID")
    String currencyID;
    @JsonProperty(value = "Date")
    String currencyDate;
    @JsonProperty(value = "Cur_OfficialRate")
    String currencyRate;

    public RateShort(String currencyID, String currencyDate, String currencyRate) {
        this.currencyID = currencyID;
        this.currencyDate = currencyDate;
        this.currencyRate = currencyRate;
    }
}
