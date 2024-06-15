package com.zouaves.skooma.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zouaves.skooma.utils.FinancialCalculUtils;

@RestController
public class PeriodiciteController {
    
    @GetMapping("/api/periodicites")
    public List<FinancialCalculUtils.PERIODE> getPeriodicites() {
        return Arrays.stream(FinancialCalculUtils.PERIODE.values()).toList();
    }
}
