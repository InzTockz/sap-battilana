package com.battilana.sap.dto;

public record PreciosEspecialesResponse(
        String cardCode,
        String itemCode,
        Double priceDiscount,
        Double discount,
        Double priceBase,
        Integer priceList
) {
}
