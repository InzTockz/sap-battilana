package com.battilana.sap.service.impl;

import com.battilana.sap.dto.FacturasPorCobrarResponse;
import com.battilana.sap.dto.FacturasPorCobrarTopDiezResponse;
import com.battilana.sap.dto.facturas.ResumenCarteraResponse;
import com.battilana.sap.repository.FacturasClienteRepository;
import com.battilana.sap.service.FacturasClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturasClienteServiceImpl implements FacturasClienteService {

    private final FacturasClienteRepository facturasClienteRepository;

    @Override
    public List<FacturasPorCobrarResponse> buscarFacturasPorCobrar() {
        return this.facturasClienteRepository.buscarFacturasPorCobrar();
    }

    @Override
    public List<FacturasPorCobrarResponse> buscarFacturasPorCobrarPorCliente(String ruc) {
        return this.facturasClienteRepository.buscarFacturasPorCobrarPorCliente(ruc);
    }

    @Override
    public List<FacturasPorCobrarResponse> buscarFacturasPorCobrarPorVendedor(Integer slpCode) {
        return this.facturasClienteRepository.buscarFacturasPorCobrarPorVendedor(slpCode);
    }

    @Override
    public List<FacturasPorCobrarTopDiezResponse> facturasPorCobrarTopDiez() {
        return this.facturasClienteRepository.facturasPorCobrarTopDiez();
    }

    @Override
    public List<ResumenCarteraResponse> resumenCartera() {
        return this.facturasClienteRepository.resumenCartera();
    }
}
