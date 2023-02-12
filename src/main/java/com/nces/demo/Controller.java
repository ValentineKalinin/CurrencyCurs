package com.nces.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/test")
public class Controller {
    @Value("${allCurrencyUrl}")
    private String allCurrencyUrl;
    @Value("${curIdUrl}")
    private String curIdUrl;
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public Controller(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    // Получить список всех валют - http://localhost:8080/test/currencies
    @GetMapping(value = "/currencies", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Results> GetAllCurrency() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<Results[]> response = restTemplate.getForEntity(allCurrencyUrl, Results[].class);
        Results[] cur = response.getBody();
        // System.out.println("all currencues here");
        assert cur != null;
        return Arrays.stream(cur).toList();
    }

    // Получить валюту за период - http://localhost:8080/test/history?cur_id=290&startdate=2001-12-30&enddate=2050-01-01
    @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RateShort> GetHistoryCurrency(
            @RequestParam(value = "cur_id") int cur_id,
            @RequestParam(value = "startdate") @DateTimeFormat(pattern = "dd.MM.yyyy") String startdate,
            @RequestParam(value = "enddate") @DateTimeFormat(pattern = "dd.MM.yyyy") String enddate) throws IOException {
        Map<String, String> uriParams = new HashMap<>();
        // System.out.println("request done");
        uriParams.put("cur_id", String.valueOf(cur_id));
        uriParams.put("startdate", startdate);
        uriParams.put("enddate", enddate);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<RateShort[]> response = restTemplate.getForEntity(curIdUrl, RateShort[].class, uriParams);
        RateShort[] cur = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        File myFile = new File("src/main/resources/templates/historyOfCurrency.json");
        mapper.writeValue(myFile, cur);
        // System.out.println("file created");
        assert cur != null;
        //DrawPlot();
        return Arrays.stream(cur).toList();
    }

    // Нарисовать график валюты за период --
    public void DrawPlot() {

    }


    @GetMapping(value = "/currency", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Results> GetCurrencyByName() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<Results[]> response = restTemplate.getForEntity(allCurrencyUrl, Results[].class);
        Results[] cur = response.getBody();
        assert cur != null;
        return Arrays.stream(cur).toList();
    }
}
