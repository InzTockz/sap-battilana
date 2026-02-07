package com.battilana.sap.repository.articulos;

import com.battilana.sap.entity.articulos.UnidadMedidaMaestra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UmmRepository extends JpaRepository<UnidadMedidaMaestra, Integer> {
}
