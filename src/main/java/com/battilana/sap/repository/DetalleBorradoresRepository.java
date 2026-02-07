package com.battilana.sap.repository;

import com.battilana.sap.entity.DetalleBorradores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleBorradoresRepository extends JpaRepository<DetalleBorradores, Integer> {

    @Query("SELECT DB " +
            "FROM DetalleBorradores DB " +
            "WHERE DB.docEntry=:docEntryId")
    List<DetalleBorradores> findDetalleBorradoresPorDocEntry(@Param("docEntryId") Integer docEntryId);
}
