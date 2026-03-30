package com.battilana.sap.repository;

import com.battilana.sap.entity.PagosRecibidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PagosRecibidosRepository extends JpaRepository<PagosRecibidos, Integer> {
}
