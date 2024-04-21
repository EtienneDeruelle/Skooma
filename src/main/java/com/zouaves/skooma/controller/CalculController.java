package com.zouaves.skooma.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.zouaves.skooma.entity.Echeance;
import com.zouaves.skooma.service.CalculService;

@RestController
public class CalculController {
    
    private final CalculService calculService;
    
    @Autowired
    public CalculController(CalculService calculService){
        this.calculService = calculService;
    }

    @GetMapping("/calcul/lineaire")
    public List<Echeance> calculEmpruntLineaire(@RequestParam Long nbEcheance, BigDecimal montantEmprunt, BigDecimal taux){
        return calculService.calcul(nbEcheance, montantEmprunt, taux);
    }

}
