package com.company.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UiConfigController {

    @Value("${app.api.text-to-sql-url}")
    private String textToSqlUrl;

    @GetMapping("/api/ui-config")
    public Map<String, String> getUiConfig() {
        return Map.of("textToSqlUrl", textToSqlUrl);
    }
}