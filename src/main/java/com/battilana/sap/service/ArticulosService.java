package com.battilana.sap.service;

import com.battilana.sap.dto.StockAlmacenResponse;
import com.battilana.sap.entity.articulos.Articulos;
import com.battilana.sap.entity.articulos.UnidadMedidaGrupal;
import com.battilana.sap.entity.articulos.UnidadMedidaMaestra;

import java.util.List;

public interface ArticulosService {

    List<Articulos> findArticulosPorAlmacen(String idAlmacen);
    StockAlmacenResponse stockPorProductoYAlmacen(String idItemCode, String idAlmacen);
    List<Articulos> findArticulosPorAlmacenYNombre(String idAlmacen, String nombre);

    List<UnidadMedidaGrupal> findAllUmg();
    List<UnidadMedidaMaestra> findAllUmm();
}
