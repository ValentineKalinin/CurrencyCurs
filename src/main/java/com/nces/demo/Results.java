package com.nces.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Results implements Serializable {
    @JsonProperty(value = "Cur_ID")
    String currencyID;
    @JsonProperty(value = "Cur_Abbreviation")
    String currencyAbbreviation;
    @JsonProperty(value = "Cur_DateStart")
    String currencyStartDate;
    @JsonProperty(value = "Cur_DateEnd")
    String currencyEndDate;

    public Results(String currencyID, String currencyAbbreviation, String currencyStartDate, String currencyEndDate) {
        this.currencyID = currencyID;
        this.currencyAbbreviation = currencyAbbreviation;
        this.currencyStartDate = currencyStartDate;
        this.currencyEndDate = currencyEndDate;
    }
}
