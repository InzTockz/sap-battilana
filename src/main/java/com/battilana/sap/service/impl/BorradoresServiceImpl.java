package com.battilana.sap.service.impl;

import com.battilana.sap.dto.BorradoresResponse;
import com.battilana.sap.entity.Borradores;
import com.battilana.sap.repository.BorradoresRepository;
import com.battilana.sap.service.BorradoresService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorradoresServiceImpl implements BorradoresService {

    private final BorradoresRepository borradoresRepository;

    public BorradoresServiceImpl(BorradoresRepository borradoresRepository) {
        this.borradoresRepository = borradoresRepository;
    }

    @Override
    public List<Borradores> listarDrafts(Integer idVendedor, LocalDate fechaInicio, LocalDate fechaFin) {
        return this.borradoresRepository.findBorradores(idVendedor, fechaInicio, fechaFin);
    }

    @Override
    public BorradoresResponse buscarDraftPorDocEntry(Integer docEntryId) {
        return this.borradoresRepository.buscarBorradorPorDocEntry(docEntryId);
    }
}
