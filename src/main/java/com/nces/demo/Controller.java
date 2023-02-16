package com.nces.demo;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/nces")
public class Controller {
    @Value("${allCurrencyUrl}")
    private String allCurrencyUrl;
    @Value("${curIdUrl}")
    private String curIdUrl;
    RestTemplateBuilder restTemplateBuilder;
    ObjectMapper mapper = new ObjectMapper();
    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    public Controller(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    // Получить список всех валют - http://localhost:8080/nces/currencies
    @GetMapping(value = "/currencies", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Results> GetAllCurrency() throws IOException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<Results[]> response = restTemplate.getForEntity(allCurrencyUrl, Results[].class);
        Results[] cur = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        File allCurrency = new File("src/main/resources/templates/allCurrency.json");
        mapper.writeValue(allCurrency, cur);
        // System.out.println("All currencies received");
        assert cur != null;
        return Arrays.stream(cur).toList();
    }

    // Получить данные о сроке действия валют - http://localhost:8080/nces/currency
    @GetMapping(value = "/currency", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Results> GetCurrencyByName() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<Results[]> response = restTemplate.getForEntity(allCurrencyUrl, Results[].class);
        Results[] cur = response.getBody();
        assert cur != null;
        return Arrays.stream(cur).toList();
    }

    // Получить валюту за период - http://localhost:8080/nces/history?cur_id=290&startdate=2001-12-30&enddate=2050-01-01
    @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RateShort> GetHistoryCurrency(
            @RequestParam(value = "cur_id") int cur_id,
            @RequestParam(value = "startdate") @DateTimeFormat(pattern = "dd.MM.yyyy") String startdate,
            @RequestParam(value = "enddate") @DateTimeFormat(pattern = "dd.MM.yyyy") String enddate) throws IOException {
        Map<String, String> uriParams = new HashMap<>();
        //System.out.println("Request done");
        uriParams.put("cur_id", String.valueOf(cur_id));
        uriParams.put("startdate", startdate);
        uriParams.put("enddate", enddate);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<RateShort[]> response = restTemplate.getForEntity(curIdUrl, RateShort[].class, uriParams);
        RateShort[] cur = response.getBody();
        mapper.setDateFormat(df);
        File historyOfCurrency = new File("src/main/resources/templates/historyOfCurrency.json");
        if (!historyOfCurrency.exists()) {
            historyOfCurrency.createNewFile();
        }
        mapper.writeValue(historyOfCurrency, cur);
        // JavaType objectType = mapper.getTypeFactory().constructCollectionType(List.class, RateShort.class);
        // List<RateShort> rates = mapper.readValue(new File("src/main/resources/templates/historyOfCurrency.json"), objectType);
        browse.browser("http://localhost:63342/demo/static/plot.html");
        // System.out.println("file created");
        assert cur != null;
        return Arrays.stream(cur).toList();
    }
}
