package com.battilana.sap.repository;

import com.battilana.sap.dto.BorradoresResponse;
import com.battilana.sap.dto.PedidosDiaroResponse;
import com.battilana.sap.dto.borradores.BorradoresAprobadosResponse;
import com.battilana.sap.entity.Borradores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BorradoresRepository extends JpaRepository<Borradores, Integer> {

    /**
     RECORDAR QUE EL TABLERO DE LEYENDA PARA LOS ESTADOS DE LOS PEDIDOS DIARIOS SON:
     ================================================================================
     Estado = Cerrado (C) y Status de Autorizacion = Sln (-)  => Significa que el pedido ya cumplio con todas las fases.
     Estado = Abierto (O) y Status de Autorizacion = Cancelado (C) => Significa que el pedido fue cancelado.
     Estado = Abierto (O) y Status de Autorizacion = Sln (-) => Significa que el pedido se encuentra a la espera de ser aceptado por el personal de facturacion.
     Estado = Abierto (O) y Status de Autorizacion = Autorizado (Y) => Significa que el pedido fue aprobado por algun Gerente.
     */

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

    @Query(value = "SELECT " +
            "X.\"docEntry\", " +
            "X.\"docTime\", " +
            "X.\"fechaCreacionPedido\", " +
            "X.\"cardCode\", " +
            "X.\"cardName\", " +
            "X.\"pymntGroup\", " +
            "X.\"docTotalFC\", " +
            "X.\"creditLine\", " +
            "X.\"docDate\", " +
            "X.\"facturasVencidas\", " +
            "X.\"montoVencido\", " +
            "X.\"montoPorVencer\", " +
            "X.\"fechaVencida\" " +
            "FROM (SELECT T0.\"DocEntry\" AS \"docEntry\", T0.\"UpdateTS\" AS \"docTime\", T0.\"UpdateDate\" as \"fechaCreacionPedido\", T0.\"CardCode\" AS \"cardCode\", T0.\"CardName\" AS \"cardName\", T2.\"PymntGroup\" AS \"pymntGroup\", T0.\"DocTotalFC\" AS \"docTotalFC\", " +
            "T1.\"CreditLine\" AS \"creditLine\", T3.\"DocDate\" AS \"docDate\", (IFNULL(T4.\"FacturasVencidas\", 0) + IFNULL(T5.\"FacturasVencidas\", 0)) AS \"facturasVencidas\", " +
            "(IFNULL(T4.\"MontoVencido\", 0) + IFNULL(T5.\"AsientoVencido\", 0)) AS \"montoVencido\", (IFNULL(T4.\"MontoPorVencer\", 0) + IFNULL(T5.\"AsientoPorVencer\", 0)) AS \"montoPorVencer\", " +
            "T4.\"FechaVencida\" AS \"fechaVencida\", ROW_NUMBER() OVER (PARTITION BY T0.\"CardCode\" ORDER BY T0.\"DocTotalFC\" DESC ) AS \"RN\" " +
            "FROM B1H_BATT_PROD2.\"ODRF\" T0 " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T1 ON T0.\"CardCode\" = T1.\"CardCode\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OCTG\" T2 ON T1.\"GroupNum\" = T2.\"GroupNum\" " +
            "LEFT JOIN (" +
            "SELECT * FROM (SELECT O.\"CardCode\", O.\"DocDate\", ROW_NUMBER() OVER (PARTITION BY O.\"CardCode\" ORDER BY O.\"DocDate\" DESC) AS \"Fila\" " +
            "FROM B1H_BATT_PROD2.\"ORCT\" O " +
            ") X " +
            "WHERE X.\"Fila\" = 1" +
            ") T3 ON T0.\"CardCode\" = T3.\"CardCode\" " +
            /* CALCULANDO FACTURAS DE LOS ASIENTOS CONTABLES */
            "LEFT JOIN ( " +
            "SELECT A.\"U_SYP_INFOPE01\", COUNT(CASE WHEN A.\"DueDate\"<CURRENT_DATE THEN 1 ELSE NULL END) AS \"FacturasVencidas\", " +
            "SUM(CASE WHEN A.\"DueDate\" > CURRENT_DATE THEN A.\"BalFcDeb\" ELSE 0 END) AS \"AsientoPorVencer\", " +
            "SUM(CASE WHEN A.\"DueDate\" < CURRENT_DATE THEN A.\"BalFcDeb\" ELSE 0 END) AS \"AsientoVencido\" " +
            "FROM B1H_BATT_PROD2.\"JDT1\" A " +
            "WHERE A.\"Account\" IN ('12142001', '12122002', '12132002', '12342001') " +
            "AND A.\"BalFcDeb\" > 0 " +
            "GROUP BY A.\"U_SYP_INFOPE01\" " +
            ") T5 ON T1.\"CardCode\" = T5.\"U_SYP_INFOPE01\" " +
            /* FACTURAS VENCIDAS Y MONTOS */
            "LEFT JOIN ( " +
            "SELECT FC.\"CardCode\", " +
            "COUNT(CASE WHEN FC.\"DocDueDate\" < CURRENT_DATE THEN 1 END) AS \"FacturasVencidas\", " +
            "SUM(CASE WHEN FC.\"DocDueDate\" < CURRENT_DATE THEN ((FC.\"DocTotalFC\" - FC.\"PaidFC\") - FC.\"WTSumFC\") ELSE 0 END) AS \"MontoVencido\", " +
            "SUM(CASE WHEN FC.\"DocDueDate\" >= CURRENT_DATE THEN (FC.\"DocTotalFC\" - FC.\"PaidFC\") ELSE 0 END) AS \"MontoPorVencer\", " +
            "MIN(CASE WHEN FC.\"DocDueDate\" < CURRENT_DATE THEN FC.\"DocDueDate\" END) AS \"FechaVencida\" " +
            "FROM B1H_BATT_PROD2.\"OINV\" FC " +
            "WHERE FC.\"DocStatus\" = 'O' " +
            "AND (FC.\"DocTotalFC\" - FC.\"PaidFC\") > 0 " +
            "GROUP BY FC.\"CardCode\" " +
            ") T4 ON T1.\"CardCode\" = T4.\"CardCode\" " +
            "WHERE T0.\"WddStatus\" = 'W' " +
            "AND T1.\"CardCode\" LIKE 'C%' AND T1.\"frozenFor\" = 'N' " +
            ") X " +
            "WHERE X.\"RN\" = 1 ", nativeQuery = true)
    List<PedidosDiaroResponse> pedidosDiarios();

    @Query("SELECT B.docEntry, B.objType, B.docDate, B.cardCode, B.cardName, B.docTotal, B.docTotalFc," +
            "B.comments, B.userSign " +
            "FROM Borradores B " +
            "WHERE B.wddStatus = 'Y' AND B.objType='17'")
    List<BorradoresAprobadosResponse> listadoBorradoresAprobados();
}
