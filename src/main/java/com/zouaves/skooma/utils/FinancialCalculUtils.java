package com.zouaves.skooma.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class  FinancialCalculUtils {
    
    public enum PERIODE {
        
        MENSUELLE(12, 1),
        TRIMESTRIELLE(4, 3),
        SEMESTRIELLE(2, 6),
        ANNUELLE(1, 12);
        
        final int numberPeriodByYear;
        final int numberOfMonth;
        
        private PERIODE(int numberPeriodByYear, int numberOfMonth) {
            this.numberOfMonth = numberOfMonth;
            this.numberPeriodByYear = numberPeriodByYear;
        }
        
    }
    
    /**
     * Calcul le 1er loyer
     * @param amount montant de base pour calculer
     * @param rate taux (exemple : 0.014 pour un taux de 1.4%)
     * @param numberOfPeriod nombre de mois si mensuelle, nombre de trimestre si trimestriel, etc ...
     */
    public static BigDecimal calculateFirstRent(double amount, double rate, int numberOfPeriod, PERIODE periode) {
        return BigDecimal.valueOf((amount * rate / periode.numberPeriodByYear)
                / (1 - Math.pow(1 + rate / periode.numberPeriodByYear, -numberOfPeriod))).setScale(2, RoundingMode.HALF_DOWN);
    }
    
    public static BigDecimal calculateInteretPart(double amount, double rate, PERIODE periode) {
        return BigDecimal.valueOf(amount * rate / periode.numberPeriodByYear).setScale(2, RoundingMode.HALF_DOWN);
    }
    
    public static BigDecimal calculateCapitalPart(double rent, double interetPart) {
        return BigDecimal.valueOf(rent - interetPart).setScale(2, RoundingMode.HALF_DOWN);
    }
}
