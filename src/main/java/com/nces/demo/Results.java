package com.nces.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

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

    public String getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(String currencyID) {
        this.currencyID = currencyID;
    }

    public String getCurrencyAbbreviation() {
        return currencyAbbreviation;
    }

    public void setCurrencyAbbreviation(String currencyAbbreviation) {
        this.currencyAbbreviation = currencyAbbreviation;
    }

    public String getCurrencyStartDate() {
        return currencyStartDate;
    }

    public void setCurrencyStartDate(String currencyStartDate) {
        this.currencyStartDate = currencyStartDate;
    }

    public String getCurrencyEndDate() {
        return currencyEndDate;
    }

    public void setCurrencyEndDate(String currencyEndDate) {
        this.currencyEndDate = currencyEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Results results)) return false;
        return getCurrencyID().equals(results.getCurrencyID()) && getCurrencyAbbreviation().equals(results.getCurrencyAbbreviation()) && getCurrencyStartDate().equals(results.getCurrencyStartDate()) && getCurrencyEndDate().equals(results.getCurrencyEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrencyID(), getCurrencyAbbreviation(), getCurrencyStartDate(), getCurrencyEndDate());
    }

    @Override
    public String toString() {
        return "Results{" +
                "currencyID='" + currencyID + '\'' +
                ", currencyAbbreviation='" + currencyAbbreviation + '\'' +
                ", currencyStartDate='" + currencyStartDate + '\'' +
                ", currencyEndDate='" + currencyEndDate + '\'' +
                '}';
    }
}
