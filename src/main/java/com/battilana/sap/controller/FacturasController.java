package com.battilana.sap.controller;

import com.battilana.sap.entity.FacturasProveedor;
import com.battilana.sap.service.FacturasProveedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v2/facturas")
public class FacturasController {

    private final FacturasProveedorService facturasProveedorService;

    public FacturasController(FacturasProveedorService facturasProveedorService) {
        this.facturasProveedorService = facturasProveedorService;
    }

    @GetMapping()
    public ResponseEntity<List<FacturasProveedor>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasProveedorService.listarTodo());
    }

    @GetMapping("/imp")
    public ResponseEntity<List<FacturasProveedor>> listarPorInicialesImp() {
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasProveedorService.listarPorInicialesIMP());
    }

    @GetMapping("/imp/fechas")
    public ResponseEntity<List<FacturasProveedor>> listarPorFechasInicialesImp(@RequestParam("fecha1") String fecha1, @RequestParam("fecha2") String fecha2) throws ParseException {
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasProveedorService.listarPorFechasInicialesIMP(fecha1, fecha2));
    }

    @GetMapping("/p")
    public ResponseEntity<List<FacturasProveedor>> listarPorInicialesP() {
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasProveedorService.listarPorInicialesP());
    }

    @GetMapping("/p/fechas")
    public ResponseEntity<List<FacturasProveedor>> listarPorFechasInicialesP(@RequestParam("fecha1") String fecha1, @RequestParam("fecha2") String fecha2) throws ParseException {
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasProveedorService.listarPorFechasInicialesP(fecha1, fecha2));
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<FacturasProveedor>> listarPorFechas(@RequestParam("fecha1") String fecha1, @RequestParam("fecha2") String fecha2) {
        return ResponseEntity.status(HttpStatus.OK).body(this.facturasProveedorService.buscarPorFechasVencimiento(fecha1, fecha2));
    }
}
