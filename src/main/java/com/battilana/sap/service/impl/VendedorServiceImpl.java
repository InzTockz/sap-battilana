package com.battilana.sap.service.impl;

import com.battilana.sap.entity.Vendedor;
import com.battilana.sap.repository.VendedorRepository;
import com.battilana.sap.service.VendedorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorServiceImpl implements VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorServiceImpl(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Override
    public List<Vendedor> findAllVendedores() {
        return this.vendedorRepository.findVendedores();
    }

    @Override
    public Optional<Vendedor> findById(int slpCode) {
        return this.vendedorRepository.findById(slpCode);
    }
}
