package com.zouaves.skooma.entity;

import java.math.BigDecimal;

public record Echeance(int numero, BigDecimal loyer, BigDecimal capital, BigDecimal interet, BigDecimal capitalRestantDu) {

}
