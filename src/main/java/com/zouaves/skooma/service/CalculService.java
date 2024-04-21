package com.zouaves.skooma.service;

import java.math.BigDecimal;
import java.util.List;

import com.zouaves.skooma.entity.Echeance;

public interface CalculService {
    
    List<Echeance> calcul(Long nbEcheance, BigDecimal montantEmprunt, BigDecimal taux);
}
