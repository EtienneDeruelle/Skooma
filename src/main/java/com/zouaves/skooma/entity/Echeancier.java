package com.zouaves.skooma.entity;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Echeancier {
    
    BigDecimal totalInteret;
    
    List<Echeance> echeances;
}
