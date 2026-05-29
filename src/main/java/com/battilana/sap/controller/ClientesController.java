package com.battilana.sap.controller;

import ch.qos.logback.core.net.server.Client;
import com.battilana.sap.dto.clientes.ClienteDeudorResponse;
import com.battilana.sap.entity.Cliente;
import com.battilana.sap.service.ClientesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/clientes")
public class ClientesController {

    private final ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping()
    public ResponseEntity<List<Cliente>> listarClientes(){
        return ResponseEntity.status(HttpStatus.OK).body(this.clientesService.findAll());
    }

    @GetMapping("/{cardCode}")
    public ResponseEntity<Cliente> listarClientesId(@PathVariable String cardCode){
        return ResponseEntity.status(HttpStatus.OK).body(this.clientesService.findPorId(cardCode).orElseThrow());
    }

    @GetMapping("/vendedor/{idVendedor}")
    public ResponseEntity<List<Cliente>> listarClientesPorIdVendedor(@PathVariable Integer idVendedor){
        return ResponseEntity.status(HttpStatus.OK).body(this.clientesService.findClientesPorIdVendedor(idVendedor));
    }

    @GetMapping("/vendedor/{idVendedor}/nombres")
    public ResponseEntity<List<Cliente>> listarClientesPorVendedorYCardName(@PathVariable Integer idVendedor, @RequestParam("cardName") String cardName){
        return ResponseEntity.status(HttpStatus.OK).body(this.clientesService.findClientesPorVendedorYCardName(idVendedor, cardName));
    }

    @GetMapping("/deudor")
    public ResponseEntity<List<ClienteDeudorResponse>> buscarClientesDeudores(){
        return ResponseEntity.status(HttpStatus.OK).body(this.clientesService.buscarClientesDeudores());
    }

    @GetMapping("/deudor/vendedor")
    public ResponseEntity<List<ClienteDeudorResponse>> buscarClientesDeudoresPorVendedor(@RequestParam("idVendedor") Integer idVendedor){
        return ResponseEntity.status(HttpStatus.OK).body(this.clientesService.buscarClientesDeudoresPorVendedor(idVendedor));
    }
}
