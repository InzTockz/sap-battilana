package com.battilana.sap.controller;

import com.battilana.sap.dto.BorradoresResponse;
import com.battilana.sap.entity.Borradores;
import com.battilana.sap.entity.DetalleBorradores;
import com.battilana.sap.service.BorradoresService;
import com.battilana.sap.service.DetalleBorradoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v2/borradores")
public class BorradoresController {

    private final BorradoresService borradoresService;
    private final DetalleBorradoresService detalleBorradoresService;

    public BorradoresController(BorradoresService borradoresService, DetalleBorradoresService detalleBorradoresService) {
        this.borradoresService = borradoresService;
        this.detalleBorradoresService = detalleBorradoresService;
    }

    @GetMapping("/vendedor/{idVendedor}")
    ResponseEntity<List<Borradores>> listarBorradoresPorVendedor(
            @PathVariable Integer idVendedor,
            @RequestParam("fechaInicio") LocalDate fechaInicio,
            @RequestParam("fechaFin") LocalDate fechaFin
            ){
        return ResponseEntity.status(HttpStatus.OK).body(this.borradoresService.listarDrafts(idVendedor, fechaInicio, fechaFin));
    }

    @GetMapping("/id/{docEntryId}")
    ResponseEntity<BorradoresResponse> buscarBorradoresPorDocEntry(@PathVariable Integer docEntryId){
        return ResponseEntity.status(HttpStatus.OK).body(this.borradoresService.buscarDraftPorDocEntry(docEntryId));
    }

    @GetMapping("/detalle/{docEntryId}")
    public ResponseEntity<List<DetalleBorradores>> buscarDetalleBorradoresPorDocEntry(@PathVariable Integer docEntryId){
        return ResponseEntity.status(HttpStatus.OK).body(this.detalleBorradoresService.buscarDetalleDraftPorDocEntry(docEntryId));
    }
}
