package com.battilana.sap.controller;

import com.battilana.sap.dto.FacturasPorCobrarResponse;
import com.battilana.sap.dto.FacturasPorCobrarTopDiezResponse;
import com.battilana.sap.dto.facturas.ResumenCarteraResponse;
import com.battilana.sap.service.FacturasClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/facturas-cliente")
@RequiredArgsConstructor
public class FacturasClienteController {

    private final FacturasClienteService facturasClienteService;

    @GetMapping("/facturas-por-cobrar")
    public ResponseEntity<List<FacturasPorCobrarResponse>> buscarFacturasPorCobrar(){
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasClienteService.buscarFacturasPorCobrar());
    }

    @GetMapping("/facturas-por-cobrar/cliente/{ruc}")
    public ResponseEntity<List<FacturasPorCobrarResponse>> buscarFacturasPorCobrarPorCliente(@PathVariable String ruc){
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasClienteService.buscarFacturasPorCobrarPorCliente(ruc));
    }

    @GetMapping("/facturas-por-cobrar/vendedor/{slpCode}")
    public ResponseEntity<List<FacturasPorCobrarResponse>> buscarFacturasPorCobrarPorVendedor(@PathVariable Integer slpCode){
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasClienteService.buscarFacturasPorCobrarPorVendedor(slpCode));
    }

    @GetMapping("/facturas-por-cobrar/vendedor/{slpCode}/cliente/{ruc}")
    public ResponseEntity<List<FacturasPorCobrarResponse>> buscarFacturasPorVendedorYCliente(@PathVariable Integer slpCode, @PathVariable String ruc){
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasClienteService.buscarFacturasPorVendedorYCliente(slpCode, ruc));
    }

    @GetMapping("/facturas-por-cobrar/top-diez")
    public ResponseEntity<List<FacturasPorCobrarTopDiezResponse>> facturasPorCobrarTopDiez(){
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasClienteService.facturasPorCobrarTopDiez());
    }

    @GetMapping("/resumen-cartera")
    public ResponseEntity<List<ResumenCarteraResponse>> resumenCartera(){
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasClienteService.resumenCartera());
    }
}
