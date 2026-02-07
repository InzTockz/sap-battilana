package com.battilana.sap.service.impl;

import com.battilana.sap.dto.StockAlmacenResponse;
import com.battilana.sap.entity.articulos.Articulos;
import com.battilana.sap.entity.articulos.UnidadMedidaGrupal;
import com.battilana.sap.entity.articulos.UnidadMedidaMaestra;
import com.battilana.sap.repository.articulos.ArticulosRepository;
import com.battilana.sap.repository.articulos.StockAlmacenRepository;
import com.battilana.sap.repository.articulos.UmgRepository;
import com.battilana.sap.repository.articulos.UmmRepository;
import com.battilana.sap.service.ArticulosService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticulosServiceImpl implements ArticulosService {

    private final ArticulosRepository articulosRepository;
    private final StockAlmacenRepository stockAlmacenRepository;
    private final UmmRepository ummRepository;
    private final UmgRepository umgRepository;

    public ArticulosServiceImpl(ArticulosRepository articulosRepository, StockAlmacenRepository stockAlmacenRepository, UmmRepository ummRepository, UmgRepository umgRepository) {
        this.articulosRepository = articulosRepository;
        this.stockAlmacenRepository = stockAlmacenRepository;
        this.ummRepository = ummRepository;
        this.umgRepository = umgRepository;
    }

    @Override
    public List<Articulos> findArticulosPorAlmacen(String idAlmacen) {
        return this.articulosRepository.findArticulosPorAlmacen(idAlmacen);
    }

    @Override
    public StockAlmacenResponse stockPorProductoYAlmacen(String idItemCode, String idAlmacen) {
        return this.stockAlmacenRepository.stockPorProductoYAlmacen(idItemCode, idAlmacen);
    }

    @Override
    public List<Articulos> findArticulosPorAlmacenYNombre(String idAlmacen, String nombre) {
        Pageable page = PageRequest.of(0, 20);
        return this.articulosRepository.findArticulosPorNombres(idAlmacen, nombre, page);
    }

    @Override
    public List<UnidadMedidaGrupal> findAllUmg() {
        return this.umgRepository.findAll();
    }

    @Override
    public List<UnidadMedidaMaestra> findAllUmm() {
        return this.ummRepository.findAll();
    }
}
