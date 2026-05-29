package com.battilana.sap.service.impl;

import com.battilana.sap.dto.clientes.ClienteDeudorResponse;
import com.battilana.sap.entity.Cliente;
import com.battilana.sap.repository.ClienteRepository;
import com.battilana.sap.service.ClientesService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesServiceImpl implements ClientesService {

    private final ClienteRepository clienteRepository;

    public ClientesServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> findAll() {
        return this.clienteRepository.findClientes();
    }

    @Override
    public Optional<Cliente> findPorId(String cardCode) {
        return this.clienteRepository.findById(cardCode);
    }

    @Override
    public List<Cliente> findClientesPorIdVendedor(Integer idVendedor) {
        return this.clienteRepository.findClientesPorIdVendedor(idVendedor);
    }

    @Override
    public List<Cliente> findClientesPorVendedorYCardName(Integer idVendedor, String cardName) {
        Pageable pageable = PageRequest.of(0, 20);
        return this.clienteRepository.findClientesPorVendedorYCliente(idVendedor, cardName, pageable);
    }

    @Override
    public List<ClienteDeudorResponse> buscarClientesDeudores() {
        return this.clienteRepository.buscarClientesDeudores();
    }

    @Override
    public List<ClienteDeudorResponse> buscarClientesDeudoresPorVendedor(Integer idVendedor) {
        return this.clienteRepository.buscarClientesDeudoresPorVendedor(idVendedor);
    }
}
