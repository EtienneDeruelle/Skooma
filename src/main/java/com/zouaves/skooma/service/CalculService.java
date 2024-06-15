package com.zouaves.skooma.service;

import java.math.BigDecimal;
import java.util.List;

import com.zouaves.skooma.entity.Echeance;
import com.zouaves.skooma.entity.Echeancier;
import com.zouaves.skooma.utils.FinancialCalculUtils;

public interface CalculService {
    
    Echeancier calcul(Long nbEcheance, BigDecimal montantEmprunt, BigDecimal taux, FinancialCalculUtils.PERIODE periode);
}
