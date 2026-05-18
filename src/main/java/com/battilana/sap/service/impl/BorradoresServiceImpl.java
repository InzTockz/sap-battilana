package com.battilana.sap.service.impl;

import com.battilana.sap.dto.BorradoresResponse;
import com.battilana.sap.dto.PedidosDiaroResponse;
import com.battilana.sap.entity.*;
import com.battilana.sap.repository.*;
import com.battilana.sap.service.BorradoresService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorradoresServiceImpl implements BorradoresService {

    private final BorradoresRepository borradoresRepository;

    @Override
    public List<Borradores> listarDrafts(Integer idVendedor, LocalDate fechaInicio, LocalDate fechaFin) {
        return this.borradoresRepository.findBorradores(idVendedor, fechaInicio, fechaFin);
    }

    @Override
    public BorradoresResponse buscarDraftPorDocEntry(Integer docEntryId) {
        return this.borradoresRepository.buscarBorradorPorDocEntry(docEntryId);
    }

    @Override
    public List<PedidosDiaroResponse> buscarPedidosDiarios() {
        return this.borradoresRepository.pedidosDiarios();
    }
}
