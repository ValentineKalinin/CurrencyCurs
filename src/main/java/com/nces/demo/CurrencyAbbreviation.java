package com.nces.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyAbbreviation {
    @JsonProperty(value = "Cur_Abbreviation")
    String currencyAbbreviation;
}
