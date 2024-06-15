package com.zouaves.skooma.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zouaves.skooma.entity.Echeancier;
import com.zouaves.skooma.service.CalculService;
import com.zouaves.skooma.utils.FinancialCalculUtils;

@RestController
public class CalculController {
    
    private final CalculService calculService;
    
    @Autowired
    public CalculController(CalculService calculService) {
        this.calculService = calculService;
    }
    
    @GetMapping("/api/calcul/lineaire")
    public Echeancier calculEmpruntLineaire(@RequestParam Long nbEcheance, BigDecimal montantEmprunt, BigDecimal taux, FinancialCalculUtils.PERIODE periodicite) {
        return calculService.calcul(nbEcheance, montantEmprunt, taux, periodicite);
    }
    
}
