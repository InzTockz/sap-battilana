package com.battilana.sap.dto;

import java.math.BigDecimal;

public record FacturasPorCobrarTopDiezResponse(
        String nombre,
        BigDecimal saldo
) {
}
