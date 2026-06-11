package com.company.controller;

import com.company.dto.TextToSqlRequest;
import com.company.dto.TextToSqlResponse;
import com.company.service.TextToSqlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class TextToSqlController {

    private final TextToSqlService textToSqlService;

    @PostMapping("/text-to-sql")
    public TextToSqlResponse textToSql(@RequestBody TextToSqlRequest request) {

        return textToSqlService.handle(request);
    }
}