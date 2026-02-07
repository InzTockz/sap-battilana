package com.battilana.sap.service;

import com.battilana.sap.dto.BorradoresResponse;
import com.battilana.sap.entity.Borradores;

import java.time.LocalDate;
import java.util.List;

public interface BorradoresService {
    List<Borradores> listarDrafts(Integer idVendedor, LocalDate fechaInicio, LocalDate fechaFin);
    BorradoresResponse buscarDraftPorDocEntry(Integer docEntryId);
}
