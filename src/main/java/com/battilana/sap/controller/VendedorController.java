package com.battilana.sap.controller;

import com.battilana.sap.entity.Vendedor;
import com.battilana.sap.service.VendedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping()
    public List<Vendedor> listarVendedores(){
        return vendedorService.findAllVendedores();
    }

    @GetMapping("/{slpCode}")
    public ResponseEntity<Vendedor> listarVendedoresId(@PathVariable Integer slpCode){
        return ResponseEntity.status(HttpStatus.OK).body(this.vendedorService.findById(slpCode).orElse(null));
    }
}
