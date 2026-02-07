package com.battilana.sap.service;

import com.battilana.sap.entity.Vendedor;

import java.util.List;
import java.util.Optional;

public interface VendedorService {

    List<Vendedor> findAllVendedores();
    Optional<Vendedor> findById(int slpCode);
}
