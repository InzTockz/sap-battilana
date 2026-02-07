package com.battilana.sap.repository;

import com.battilana.sap.dto.BorradoresResponse;
import com.battilana.sap.entity.Borradores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BorradoresRepository extends JpaRepository<Borradores, Integer> {

    @Query("SELECT B " +
            "FROM Borradores B " +
            "WHERE B.objType = '17' " +
            "AND B.docStatus = 'O' " +
            "AND (B.canceled = 'N' OR B.canceled is null) " +
            "AND B.slpCode=:idVendedor " +
            "AND (B.createDate BETWEEN :fechaInicio AND :fechaFin) " +
            "ORDER BY B.createDate DESC")
    List<Borradores> findBorradores(
            @Param("idVendedor") Integer idVendedor,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );

    @Query("SELECT new com.battilana.sap.dto.BorradoresResponse(" +
            "B.docEntry," +
            "B.objType," +
            "B.docDate," +
            "B.createDate," +
            "B.cardCode," +
            "B.cardName," +
            "B.slpCode," +
            "V.slpName," +
            "B.ownerCode," +
            "CONCAT(E.firstName, ' ', E.lastName)," +
            "B.wddStatus," +
            "B.comments," +
            "B.docTotal) " +
            "FROM Borradores B " +
            "INNER JOIN Vendedor V ON B.slpCode = V.slpCode " +
            "INNER JOIN Empleados E ON B.ownerCode = E.empId " +
            "WHERE B.docEntry=:docEntryId")
    BorradoresResponse buscarBorradorPorDocEntry(@Param("docEntryId") Integer docEntryId);
}
