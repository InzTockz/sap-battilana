package com.battilana.sap.dto;

public record StockAlmacenResponse (
        double stock,
        double comprometido,
        double stockTotal,
        String unidadMedida,
        String codigoArticulo
){
}
