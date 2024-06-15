package com.zouaves.skooma.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zouaves.skooma.entity.Echeance;
import com.zouaves.skooma.entity.Echeancier;
import com.zouaves.skooma.utils.FinancialCalculUtils;

@Service
public class CalculLineaireService implements CalculService {
    
    @Override
    public Echeancier calcul(final Long nbEcheance, final BigDecimal montantEmprunt, final BigDecimal taux, final FinancialCalculUtils.PERIODE periodicite) {
        final Echeancier echeancier = new Echeancier();
        final double rate = taux.divide(BigDecimal.valueOf(100)).doubleValue();
        final BigDecimal premierEcheance = FinancialCalculUtils.calculateFirstRent(montantEmprunt.doubleValue(), rate, nbEcheance.intValue(), periodicite);
                /*(montantEmprunt.multiply(BigDecimal.valueOf(taux.doubleValue()/100))
                        .divide(BigDecimal.valueOf(12))).divide(BigDecimal.valueOf(1-Math.pow(1+taux.doubleValue()/100.0/12, -nbEcheance)), RoundingMode.HALF_DOWN).setScale(2, RoundingMode.HALF_DOWN);*/
        
        echeancier.setEcheances(buildEcheanceList(montantEmprunt, premierEcheance, nbEcheance.intValue(), rate, periodicite));
        final BigDecimal totalInteret = echeancier.getEcheances()
                .stream()
                .map(Echeance::interet)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        echeancier.setTotalInteret(totalInteret);
        
        return echeancier;
    }
    
    private List<Echeance> buildEcheanceList(BigDecimal totalAmount, BigDecimal firstRent, int nbEcheance, double rate, FinancialCalculUtils.PERIODE periode) {
        final List<Echeance> echeancier = new ArrayList<>();
        BigDecimal capitalRestantDu = totalAmount;
        
        for (int i = 0; i < nbEcheance; i++) {
            final BigDecimal interetPart = FinancialCalculUtils.calculateInteretPart(capitalRestantDu.doubleValue(), rate, periode);
            final BigDecimal capitalPart = FinancialCalculUtils.calculateCapitalPart(firstRent.doubleValue(), interetPart.doubleValue());
            capitalRestantDu = capitalRestantDu.subtract(capitalPart).setScale(2, RoundingMode.HALF_DOWN);
            echeancier.add(new Echeance(i + 1, firstRent, capitalPart, interetPart, capitalRestantDu));
        }
        
        return echeancier;
    }
    
}
