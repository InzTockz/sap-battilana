package com.battilana.sap.repository.articulos;

import com.battilana.sap.entity.articulos.UnidadMedidaGrupal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UmgRepository extends JpaRepository<UnidadMedidaGrupal, Integer> {
}
