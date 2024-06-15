package com.zouaves.skooma.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zouaves.skooma.entity.Echeance;
import com.zouaves.skooma.entity.Echeancier;
import com.zouaves.skooma.utils.FinancialCalculUtils;

@ExtendWith(MockitoExtension.class)
public class CalculLineaireServiceTest {
    
    @InjectMocks
    CalculLineaireService calculLineaireService;
    
    @Test
    public void itShouldCalculateMensuality() {
        Long nbEcheance = 240L;
        double capital = 140000;
        
        final Echeancier echeancier = calculLineaireService.calcul(nbEcheance, BigDecimal.valueOf(capital), BigDecimal.valueOf(1.44), FinancialCalculUtils.PERIODE.MENSUELLE);
        
        assertThat(echeancier.getEcheances()).hasSize(nbEcheance.intValue());
        assertThat(echeancier.getEcheances().stream().map(Echeance::loyer).collect(Collectors.toList())).allMatch((montant) -> montant.doubleValue() == 671.71);
        assertThat(echeancier.getTotalInteret()).isEqualTo(BigDecimal.valueOf(21209.60));
        //assertThat(echeances.stream().map(Echeance::capital).reduce(BigDecimal::add).get().doubleValue()).isEqualTo(capital);
    }
}
