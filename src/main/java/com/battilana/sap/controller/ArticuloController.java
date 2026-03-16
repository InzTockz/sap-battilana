package com.battilana.sap.controller;

import com.battilana.sap.dto.PreciosEspecialesResponse;
import com.battilana.sap.dto.StockAlmacenResponse;
import com.battilana.sap.entity.articulos.Articulos;
import com.battilana.sap.entity.articulos.PreciosEspeciales;
import com.battilana.sap.entity.articulos.UnidadMedidaGrupal;
import com.battilana.sap.entity.articulos.UnidadMedidaMaestra;
import com.battilana.sap.service.ArticulosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/articulos")
public class ArticuloController {

    private final ArticulosService articulosService;

    public ArticuloController(ArticulosService articulosService) {
        this.articulosService = articulosService;
    }

    @GetMapping("/{idAlmacen}")
    public ResponseEntity<List<Articulos>> listarArticulosPorAlmacen(@PathVariable String idAlmacen){
        return ResponseEntity.status(HttpStatus.OK).body(this.articulosService.findArticulosPorAlmacen(idAlmacen));
    }

    @GetMapping("/stock/articulo/{idArticulo}/almacen/{idAlmacen}")
    public ResponseEntity<StockAlmacenResponse> stockPorArticuloYAlmacen(@PathVariable String idArticulo, @PathVariable String idAlmacen){
        return ResponseEntity.status(HttpStatus.OK).body(this.articulosService.stockPorProductoYAlmacen(idArticulo, idAlmacen));
    }

    @GetMapping("/articulos/{idAlmacen}")
    public ResponseEntity<List<Articulos>> listarArticulosPorAlmacenYNombre(@PathVariable String idAlmacen, @RequestParam("nombre") String nombre){
        return ResponseEntity.status(HttpStatus.OK).body(this.articulosService.findArticulosPorAlmacenYNombre(idAlmacen, nombre));
    }

    //UNIDAD DE MEDIDA GRUPO Y MAESTROS
    @GetMapping("/umg")
    public ResponseEntity<List<UnidadMedidaGrupal>> findAllUmg(){
        return ResponseEntity.status(HttpStatus.OK).body(this.articulosService.findAllUmg());
    }

    @GetMapping("/umm")
    public ResponseEntity<List<UnidadMedidaMaestra>> findAllUmm(){
        return ResponseEntity.status(HttpStatus.OK).body(this.articulosService.findAllUmm());
    }

    /*SECCION DE PRECIOS ESPECIALES*/
    @GetMapping("/precios-especiales")
    public ResponseEntity<PreciosEspecialesResponse> buscarPreciosEspeciales(
            @RequestParam("cardCode") String cardCode,
            @RequestParam("itemCode") String itemCode,
            @RequestParam("priceList") Integer priceList){
        return ResponseEntity.status(HttpStatus.OK).body(this.articulosService.buscarPreciosEspeciales(cardCode, itemCode, priceList));
    }
}
