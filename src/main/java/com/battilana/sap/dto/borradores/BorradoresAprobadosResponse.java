package com.battilana.sap.dto.borradores;

import java.time.LocalDate;

public record BorradoresAprobadosResponse (
        Integer docEntry,
        String objType,
        LocalDate docDate,
        String cardCode,
        String cardName,
//        String docCur,
        Double docTotal,
        Double docTotalFc,
        String comments,
        Integer userSign
){
}
