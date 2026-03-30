package com.battilana.sap.repository;

import com.battilana.sap.dto.BorradoresResponse;
import com.battilana.sap.dto.PedidosDiaroResponse;
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
//            "AND B.docStatus = 'O' " +
            "AND (B.canceled = 'N' OR B.canceled is null) " +
            "AND B.slpCode=:idVendedor " +
            "AND (B.createDate BETWEEN :fechaInicio AND :fechaFin) " +
            "ORDER BY B.createDate DESC, B.wddStatus DESC")
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
            "CONCAT(E.firstName, ' ', E.lastName), " +
            "B.docStatus," +
            "B.wddStatus," +
            "B.comments, " +
            "B.docCur, " +
            "B.vatSum, " +
            "B.vatSumFc, " +
            "B.docTotalFc, " +
            "B.docTotal) " +
            "FROM Borradores B " +
            "INNER JOIN Vendedor V ON B.slpCode = V.slpCode " +
            "LEFT JOIN Empleados E ON B.ownerCode = E.empId " +
            "WHERE B.docEntry=:docEntryId")
    BorradoresResponse buscarBorradorPorDocEntry(@Param("docEntryId") Integer docEntryId);

//    @Query("SELECT new com.battilana.sap.dto.PedidosDiaroResponse(" +
//            "T0.docEntry, " +
//            "T0.cardCode, " +
//            "T0.cardName, " +
//            "T2.pymntGroup, " +
//            "T0.docTotalFc, " +
//            "T1.creditLine, " +
//            "T3.docDate, " +
//            "T4.facturaVencida, " +
//            "T4.montoVencido, " +
//            "T4.montoPorVencer, " +
//            "'') " +
//            "FROM Borradores T0 " +
//            "INNER JOIN Cliente T1 ON T0.cardCode = T1.cardCode " +
//            "INNER JOIN TerminosPago T2 ON T1.groupNum = T2.groupNum " +
//            "LEFT JOIN ( " +
//            "SELECT X FROM (SELECT O.cardCode, O.docDate, ROW_NUMBER() OVER (PARTITION BY O.cardCode ORDER BY O.docDate DESC) AS FILA FROM PagosRecibidos O) X " +
//            "WHERE X.FILA = 1" +
//            ") T3 ON T0.cardCode = T3.cardCode " +
//            "LEFT JOIN ( " +
//            "SELECT FC.cardCode, " +
//            "COUNT(CASE WHEN FC.docDueDate < CURRENT_DATE THEN 1 END) AS facturaVencida, " +
//            "SUM(CASE WHEN FC.docDueDate < CURRENT_DATE THEN (FC.docTotalFC - FC.paidFC) ELSE 0 END) AS montoVencido, " +
//            "SUM(CASE WHEN FC.docDueDate >= CURRENT_DATE THEN (FC.docTotalFC - FC.paidFC) ELSE 0 END) AS montoPorVencer, " +
//            "MIN(CASE WHEN FC.docDueDate < CURRENT_DATE THEN FC.docDueDate END) AS fechaVencida " +
//            "FROM FacturasCliente FC " +
//            "WHERE FC.docStatus = 'O' " +
//            "AND (FC.docTotalFC - FC.paidFC) > 0 " +
//            "GROUP BY FC.cardCode" +
//            ") T4 ON T1.cardCode = T4.cardCode " +
//            "WHERE T0.wddStatus = 'W' " +
//            "AND T0.cardCode LIKE 'C%' AND T1.frozenFor = 'N' " +
//            "ORDER BY T3.docDate DESC")
//    List<PedidosDiaroResponse> pedidosDiario();

    @Query(value = "SELECT T0.\"DocEntry\" AS \"docEntry\", T0.\"CardCode\" AS \"cardCode\", T0.\"CardName\" AS \"cardName\", T2.\"PymntGroup\" AS \"pymntGroup\", T0.\"DocTotalFC\" AS \"docTotalFC\"," +
            "T1.\"CreditLine\" AS \"creditLine\", T3.\"DocDate\" AS \"docDate\", T4.\"FacturasVencidas\" AS \"facturasVencidas\", T4.\"MontoVencido\" AS \"montoVencido\", T4.\"MontoPorVencer\" AS \"montoPorVencer\", " +
            "T4.\"FechaVencida\" AS \"fechaVencida\" " +
            "FROM B1H_BATT_DESA_DE.\"ODRF\" T0 " +
            "INNER JOIN B1H_BATT_DESA_DE.\"OCRD\" T1 ON T0.\"CardCode\" = T1.\"CardCode\" " +
            "INNER JOIN B1H_BATT_DESA_DE.\"OCTG\" T2 ON T1.\"GroupNum\" = T2.\"GroupNum\" " +
            "LEFT JOIN (" +
            "SELECT * FROM (SELECT O.\"CardCode\", O.\"DocDate\", ROW_NUMBER() OVER (PARTITION BY O.\"CardCode\" ORDER BY O.\"DocDate\" DESC) AS \"Fila\" " +
            "FROM B1H_BATT_DESA_DE.\"ORCT\" O " +
            ") X " +
            "WHERE X.\"Fila\" = 1" +
            ") T3 ON T0.\"CardCode\" = T3.\"CardCode\" " +
            "LEFT JOIN ( " +
            "SELECT FC.\"CardCode\", " +
            "COUNT(CASE WHEN FC.\"DocDueDate\" < CURRENT_DATE THEN 1 END) AS \"FacturasVencidas\", " +
            "SUM(CASE WHEN FC.\"DocDueDate\" < CURRENT_DATE THEN (FC.\"DocTotalFC\" - FC.\"PaidFC\") ELSE 0 END) AS \"MontoVencido\", " +
            "SUM(CASE WHEN FC.\"DocDueDate\" >= CURRENT_DATE THEN (FC.\"DocTotalFC\" - FC.\"PaidFC\") ELSE 0 END) AS \"MontoPorVencer\", " +
            "MIN(CASE WHEN FC.\"DocDueDate\" < CURRENT_DATE THEN FC.\"DocDueDate\" END) AS \"FechaVencida\" " +
            "FROM B1H_BATT_DESA_DE.\"OINV\" FC " +
            "WHERE FC.\"DocStatus\" = 'O' " +
            "AND (FC.\"DocTotalFC\" - FC.\"PaidFC\") > 0 " +
            "GROUP BY FC.\"CardCode\" " +
            ") T4 ON T1.\"CardCode\" = T4.\"CardCode\" " +
            "WHERE T0.\"WddStatus\" = 'W' " +
            "AND T1.\"CardCode\" LIKE 'C%' AND T1.\"frozenFor\" = 'N' " +
            "ORDER BY T3.\"DocDate\" DESC"
            , nativeQuery = true)
    List<PedidosDiaroResponse> pedidosDiarios();
}
