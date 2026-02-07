package com.battilana.sap.repository;

import com.battilana.sap.entity.Facturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FacturasRepository extends JpaRepository<Facturas, Integer> {

    @Query("SELECT F.docEntry, F.docNum, F.docType, F.canceled, F.docStatus, F.objType, F.docDate, " +
            "F.docDueDate, F.cardCode, F.cardName, F.numAtCard, F.docCur, F.docTotal, F.docTotalFC " +
            "FROM Facturas F " +
            "WHERE F.docStatus = 'O' " +
            "AND F.canceled = 'N' " +
            "AND F.docDueDate BETWEEN :fechaUno AND :fechaDos")
    List<Facturas> findPorDocDueDate(@Param("fechaUno") String fechaUno, @Param("fechaDos") String fechaDos);

    @Query("SELECT F " +
            "FROM Facturas F " +
            "WHERE F.canceled = 'N' " +
            "AND (F.cardCode LIKE 'PIMP%' " +
            "OR F.cardCode LIKE 'PXIMP%')")
    List<Facturas> findByImp();

    @Query("SELECT F " +
            "FROM Facturas F " +
            "WHERE F.canceled = 'N' " +
            "AND (F.cardCode LIKE 'PIMP%' OR F.cardCode LIKE 'PXIMP%') " +
            "AND F.docDueDate BETWEEN :fechaUno AND :fechaDos")
    List<Facturas> findPorDocDueDatePorImp(@Param("fechaUno") Date fechaUno, @Param("fechaDos") Date fechaDos);

    @Query("SELECT F " +
            "FROM Facturas F " +
            "WHERE F.canceled = 'N' " +
            "AND F.cardCode LIKE 'P%'" +
            "AND SUBSTRING(F.cardCode, 2, 1) BETWEEN '0' AND '9'")
    List<Facturas> findByP();

    @Query("SELECT F " +
            "FROM Facturas F " +
            "WHERE F.canceled = 'N' " +
            "AND F.cardCode LIKE 'P%' " +
            "AND SUBSTRING(F.cardCode, 2, 1) BETWEEN '0' AND '9' " +
            "AND F.docDueDate BETWEEN :fecha1 AND :fecha2")
    List<Facturas> findByDocDueDateByP(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);
}
