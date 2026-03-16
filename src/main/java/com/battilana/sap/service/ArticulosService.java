package com.battilana.sap.service;

import com.battilana.sap.dto.PreciosEspecialesResponse;
import com.battilana.sap.dto.StockAlmacenResponse;
import com.battilana.sap.entity.articulos.Articulos;
import com.battilana.sap.entity.articulos.PreciosEspeciales;
import com.battilana.sap.entity.articulos.UnidadMedidaGrupal;
import com.battilana.sap.entity.articulos.UnidadMedidaMaestra;

import java.util.List;

public interface ArticulosService {

    /*SECCION DE ARTICULOS */
    List<Articulos> findArticulosPorAlmacen(String idAlmacen);
    List<Articulos> findArticulosPorAlmacenYNombre(String idAlmacen, String nombre);

    /*SECCION DE ALMACEN */
    StockAlmacenResponse stockPorProductoYAlmacen(String idItemCode, String idAlmacen);

    /*SECCION DE UNIDADES DE MEDIDA*/
    List<UnidadMedidaGrupal> findAllUmg();
    List<UnidadMedidaMaestra> findAllUmm();

    /*SECCIN DE PRECIOS ESPECIALES*/
    PreciosEspecialesResponse buscarPreciosEspeciales(String cardCode, String itemCode, Integer priceList);
}
