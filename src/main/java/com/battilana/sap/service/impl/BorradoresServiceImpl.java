package com.battilana.sap.service.impl;

import com.battilana.sap.dto.BorradoresResponse;
import com.battilana.sap.dto.PedidosDiaroResponse;
import com.battilana.sap.entity.*;
import com.battilana.sap.repository.*;
import com.battilana.sap.service.BorradoresService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorradoresServiceImpl implements BorradoresService {

    private final BorradoresRepository borradoresRepository;
    private final ClienteRepository clienteRepository;
    private final FacturasClienteRepository facturasClienteRepository;
    private final PagosRecibidosRepository pagosRecibidosRepository;
    private final TerminosPagoRepository terminosPagoRepository;

    public BorradoresServiceImpl(BorradoresRepository borradoresRepository, ClienteRepository clienteRepository, FacturasClienteRepository facturasClienteRepository, PagosRecibidosRepository pagosRecibidosRepository, TerminosPagoRepository terminosPagoRepository) {
        this.borradoresRepository = borradoresRepository;
        this.clienteRepository = clienteRepository;
        this.facturasClienteRepository = facturasClienteRepository;
        this.pagosRecibidosRepository = pagosRecibidosRepository;
        this.terminosPagoRepository = terminosPagoRepository;
    }

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
        return this.borradoresRepository.listado();
    }
}
