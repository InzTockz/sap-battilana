package com.battilana.sap.dto;

import java.time.LocalDate;

public record BorradoresResponse (
        Integer docEntry,
        String objType,
        LocalDate docDate,
        LocalDate createDate,
        String cardCode,
        String cardName,
        Integer slpCode,
        String fullNamesSlp,
        Integer ownerCode,
        String fullNamesOwner,
        String wddStatus,
        String comments,
        String docCur,
        Double vatSum,
        Double vatSumFc,
        Double docTotalFc,
        Double docTotal
){
}
