package com.nces.demo;

import java.io.Serializable;
import java.util.Date;

public class Currency implements Serializable {
    String id;
    Date dateFrom;
    Date dateTo;

    public Currency() {
    }

    public Currency(String id, Date dateFrom, Date dateTo) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
