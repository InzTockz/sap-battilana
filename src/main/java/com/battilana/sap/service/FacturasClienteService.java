package com.battilana.sap.service;

import com.battilana.sap.dto.FacturasPorCobrarResponse;
import com.battilana.sap.dto.FacturasPorCobrarTopDiezResponse;
import com.battilana.sap.dto.facturas.ResumenCarteraResponse;

import java.util.List;

public interface FacturasClienteService {

    List<FacturasPorCobrarResponse> buscarFacturasPorCobrar();
    List<FacturasPorCobrarResponse> buscarFacturasPorCobrarPorCliente(String ruc);
    List<FacturasPorCobrarResponse> buscarFacturasPorCobrarPorVendedor(Integer slpCode);
    List<FacturasPorCobrarTopDiezResponse> facturasPorCobrarTopDiez();
    List<ResumenCarteraResponse> resumenCartera();
}
