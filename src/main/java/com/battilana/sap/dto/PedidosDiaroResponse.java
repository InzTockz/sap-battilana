package com.battilana.sap.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PedidosDiaroResponse(
        Integer docEntry,
        Integer docTime,
        LocalDateTime fechaCreacionPedido,
        String cardCode,
        String cardName,
        String pymntGroup, //CONDICION DE PAGO
        BigDecimal docTotalFC, //MONTO TOTAL DEL PEDIDO
        BigDecimal creditLine, //LIMITE DE CREDITO
        LocalDateTime docDate, // ULTIMO PAGO RECIBIDO
        Long facturasVencidas, //NUMERO DE FACTURAS VENCIDAS
        BigDecimal montoVencido, //MONTO TOTAL VENCIDO EN DOLARES
        BigDecimal montoPorVencer, //MONTO POR VENCER
        LocalDateTime fechaVencida //FECHA DEL DOCUMENTO MAS VENCIDO
) {
}
