package com.battilana.sap.controller;

import com.battilana.sap.entity.Facturas;
import com.battilana.sap.service.FacturasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ClientInfoStatus;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v2/facturas")
public class FacturasController {

    private final FacturasService facturasService;

    public FacturasController(FacturasService facturasService) {
        this.facturasService = facturasService;
    }

    @GetMapping()
    public ResponseEntity<List<Facturas>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasService.listarTodo());
    }

    @GetMapping("/imp")
    public ResponseEntity<List<Facturas>> listarPorInicialesImp() {
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasService.listarPorInicialesIMP());
    }

    @GetMapping("/imp/fechas")
    public ResponseEntity<List<Facturas>> listarPorFechasInicialesImp(@RequestParam("fecha1") String fecha1, @RequestParam("fecha2") String fecha2) throws ParseException {
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasService.listarPorFechasInicialesIMP(fecha1, fecha2));
    }

    @GetMapping("/p")
    public ResponseEntity<List<Facturas>> listarPorInicialesP() {
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasService.listarPorInicialesP());
    }

    @GetMapping("/p/fechas")
    public ResponseEntity<List<Facturas>> listarPorFechasInicialesP(@RequestParam("fecha1") String fecha1, @RequestParam("fecha2") String fecha2) throws ParseException {
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasService.listarPorFechasInicialesP(fecha1, fecha2));
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<Facturas>> listarPorFechas(@RequestParam("fecha1") String fecha1, @RequestParam("fecha2") String fecha2) {
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasService.buscarPorFechasVencimiento(fecha1, fecha2));
    }
}
