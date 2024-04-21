package com.zouaves.skooma.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zouaves.skooma.entity.Echeance;
import com.zouaves.skooma.utils.FinancialCalculUtils;

@Service
public class CalculLineaireService implements CalculService {
    
    @Override
    public List<Echeance> calcul(final Long nbEcheance, final BigDecimal montantEmprunt, final BigDecimal taux) {
        
        
        final BigDecimal premierEcheance = FinancialCalculUtils.calculateFirstRent(montantEmprunt.doubleValue(), taux.doubleValue(), nbEcheance.intValue(), FinancialCalculUtils.PERIODE.MENSUELLE);
                /*(montantEmprunt.multiply(BigDecimal.valueOf(taux.doubleValue()/100))
                        .divide(BigDecimal.valueOf(12))).divide(BigDecimal.valueOf(1-Math.pow(1+taux.doubleValue()/100.0/12, -nbEcheance)), RoundingMode.HALF_DOWN).setScale(2, RoundingMode.HALF_DOWN);*/
        
                
        return buildEcheanceList(montantEmprunt, premierEcheance, nbEcheance.intValue(), taux.doubleValue());
    }
    
    private List<Echeance> buildEcheanceList(BigDecimal totalAmount, BigDecimal firstRent, int nbEcheance, double rate) {
        final List<Echeance> echeancier = new ArrayList<>();
        double capitalRestantDu = totalAmount.doubleValue();
        
        for(int i = 0; i < nbEcheance; i++) {
            final BigDecimal interetPart = FinancialCalculUtils.calculateInteretPart(capitalRestantDu, rate, FinancialCalculUtils.PERIODE.MENSUELLE);
            final BigDecimal capitalPart = FinancialCalculUtils.calculateCapitalPart(firstRent.doubleValue(), interetPart.doubleValue());
            echeancier.add(new Echeance(i+1, firstRent, capitalPart, interetPart));
            capitalRestantDu = BigDecimal.valueOf(capitalRestantDu).subtract(capitalPart).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
        }
        
        return echeancier;
    }
    
    
}
