package com.battilana.sap.dto;

import java.math.BigDecimal;

public record FacturasPorCobrarResponse(
        String ruc,
        String nombre,
        Integer documento,
        String comprobante,
        String emision,
        String vencimiento,
        String moneda,
        BigDecimal importe,
        BigDecimal saldo,
        String vendedor,
        BigDecimal lc
)
{}