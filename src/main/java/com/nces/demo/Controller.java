package com.nces.demo;

import org.jfree.data.general.Dataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.JFrame;
import java.util.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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
        assert cur != null;
        return Arrays.stream(cur).toList();
    }


    // Получить валюту по айди за период - http://localhost:8080/test/history?cur_id=290&startdate=2001-12-30&enddate=2050-01-01
    @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RateShort> GetHistoryCurrency(
            @RequestParam(value = "cur_id") int cur_id,
            @RequestParam(value = "startdate") @DateTimeFormat(pattern="dd.MM.yyyy") String startdate,
            @RequestParam(value = "enddate") @DateTimeFormat(pattern="dd.MM.yyyy") String enddate) {
        Map<String, String> uriParams = new HashMap<String, String>();
        uriParams.put("cur_id", String.valueOf(cur_id));
        uriParams.put("startdate", startdate);
        uriParams.put("enddate", enddate);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<RateShort[]> response = restTemplate.getForEntity(curIdUrl, RateShort[].class, uriParams);
        RateShort[] cur = response.getBody();
        assert cur != null;
        //DrawPlot();
        return Arrays.stream(cur).toList();
    }

    public void DrawPlot() {
        XYDataset dataset = new XYSeriesCollection();
        JFreeChart chart = ChartFactory.createXYLineChart("", "", "", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
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
