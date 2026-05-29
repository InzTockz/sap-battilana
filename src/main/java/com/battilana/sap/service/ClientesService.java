package com.battilana.sap.service;

import com.battilana.sap.dto.clientes.ClienteDeudorResponse;
import com.battilana.sap.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClientesService {

    List<Cliente> findAll();
    Optional<Cliente> findPorId(String cardCode);
    List<Cliente> findClientesPorIdVendedor(Integer idVendedor);
    List<Cliente> findClientesPorVendedorYCardName(Integer idVendedor, String cardName);
    List<ClienteDeudorResponse> buscarClientesDeudores();
    List<ClienteDeudorResponse> buscarClientesDeudoresPorVendedor(Integer idVendedor);
}
