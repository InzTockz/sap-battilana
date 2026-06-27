package com.battilana.sap.dto;

import java.math.BigDecimal;

public record FacturasPorCobrarResponse(
        String ruc, //NUMERO RUC DEL CLIENTE
        String nombre, //RAZON SOCIAL DEL CLIENTE
        Integer documento, //NRO DOCUMENTO DE SAP
        String comprobante, //NRO DE LETRA O FATURA
        String emision, //FECHA EN LA QUE FUE GENERADO
        String vencimiento, //FECHA EN LA QUE SE VENCE LA FACTURA Y/O LETRA
        String moneda, //MONEDA SE REPRESENTA EN 'US$'
        BigDecimal importe,  //EL MONTO ORIGINAL DE LA FACTURA
        BigDecimal saldo, //EL MONTO PENDIENTE QUE QUEDA POR PAGAR
        String vendedor, //NOMBRE DEL VENDEDOR
        BigDecimal lc //LINEA DE CREDITO
)
{}