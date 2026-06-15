package com.battilana.sap.repository;

import com.battilana.sap.dto.FacturasPorCobrarResponse;
import com.battilana.sap.dto.FacturasPorCobrarTopDiezResponse;
import com.battilana.sap.dto.facturas.FacturasPorCobrarTopDiezVencidosResponse;
import com.battilana.sap.dto.facturas.ResumenCarteraResponse;
import com.battilana.sap.entity.FacturasCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacturasClienteRepository extends JpaRepository<FacturasCliente, Integer> {

    @Query(value = "SELECT * FROM (" +
            "SELECT " +
            "T1.\"CardCode\" AS \"ruc\", " +
            "T1.\"CardName\" AS \"nombre\", " +
            "T1.\"DocNum\" AS \"documento\"," +
            "CASE WHEN T1.\"NumAtCard\" IS NOT NULL THEN CONCAT('FA-', T1.\"NumAtCard\") ELSE CONCAT('FA-', CAST(T1.\"DocNum\" AS NVARCHAR)) END AS \"comprobante\", " +
            "TO_VARCHAR(T1.\"DocDate\",    'YYYY-MM-DD') AS \"emision\", " +
            "TO_VARCHAR(T1.\"DocDueDate\", 'YYYY-MM-DD') AS \"vencimiento\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN 'US$' ELSE T1.\"DocCur\" END AS \"moneda\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN (T1.\"DocTotal\"/T1.\"SysRate\") ELSE T1.\"DocTotalFC\" END AS \"importe\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN ((T1.\"DocTotal\"-T1.\"PaidToDate\")/T1.\"SysRate\") ELSE ((T1.\"DocTotalFC\" - IFNULL(T1.\"PaidFC\", 0)) - IFNULL(T1.\"WTSumFC\", 0)) END AS \"saldo\", " +
            "T2.\"SlpName\" AS \"vendedor\", " +
            "T3.\"CreditLine\" AS \"lc\" " +
            "FROM B1H_BATT_PROD2.\"OINV\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OSLP\" T2 ON T1.\"SlpCode\"  =  T2.\"SlpCode\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T3 ON T1.\"CardCode\" = T3.\"CardCode\" " +
            "WHERE (T1.\"DocTotal\" - IFNULL(T1.\"PaidToDate\", 0)) > 0 " +
            "AND T1.\"CardCode\" NOT IN ('C40167525') " +
            "UNION ALL " +
            "SELECT " +
            "T1.\"ShortName\" AS \"ruc\", " +
            "T2.\"CardName\" AS \"nombre\", " +
            "T3.\"Number\" AS \"documento\", " +
            "CASE WHEN T1.\"Account\" IN ('12142001', '12122002', '12132002') THEN T1.\"Ref2\" ELSE CONCAT('LE-', T1.\"Ref2\") END AS \"comprobante\", " +
            "TO_VARCHAR(T1.\"RefDate\", 'YYYY-MM-DD') AS \"emision\", " +
            "TO_VARCHAR(T1.\"DueDate\", 'YYYY-MM-DD') AS \"vencimiento\", " +
            "T1.\"FCCurrency\" AS \"moneda\", " +
            "T1.\"FCDebit\" AS \"importe\", " +
            "T1.\"BalFcDeb\" AS \"saldo\", " +
            "T4.\"SlpName\" AS \"vendedor\", " +
            "T2.\"CreditLine\" AS \"lc\" " +
            "FROM B1H_BATT_PROD2.\"JDT1\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T2 ON T1.\"ShortName\" = T2.\"CardCode\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OJDT\" T3 ON T1.\"TransId\"   = T3.\"TransId\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OSLP\" T4 ON T2.\"SlpCode\"   = T4.\"SlpCode\" " +
            "WHERE T1.\"Account\" IN ('12142001', '12122002', '12132002', '12342001') " +
            "AND T1.\"BalFcDeb\" > 0 " +
            "AND T1.\"MthDate\" IS NULL " +
            ") AS \"SALDOS_UNIFICADOS\" " +
            "ORDER BY \"vendedor\" ASC, \"nombre\" ASC, \"emision\" ASC", nativeQuery = true)
    List<FacturasPorCobrarResponse> buscarFacturasPorCobrar();

    @Query(value = "SELECT * FROM ( " +
            "SELECT " +
            "T1.\"CardCode\"                                       AS \"ruc\", " +
            "T1.\"CardName\"                                       AS \"nombre\", " +
            "T1.\"DocNum\"                                         AS \"documento\", " +
            "CASE WHEN T1.\"NumAtCard\" IS NOT NULL THEN CONCAT('FA-', T1.\"NumAtCard\") ELSE CONCAT('FA-', CAST(T1.\"DocNum\" AS NVARCHAR)) END AS \"comprobante\", " +
            "TO_VARCHAR(T1.\"DocDate\",    'YYYY-MM-DD')           AS \"emision\", " +
            "TO_VARCHAR(T1.\"DocDueDate\", 'YYYY-MM-DD')           AS \"vencimiento\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN 'US$' ELSE T1.\"DocCur\" END AS \"moneda\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN (T1.\"DocTotal\"/T1.\"SysRate\") ELSE T1.\"DocTotalFC\" END AS \"importe\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN ((T1.\"DocTotal\"-T1.\"PaidToDate\")/T1.\"SysRate\") ELSE ((T1.\"DocTotalFC\" - IFNULL(T1.\"PaidFC\", 0)) - IFNULL(T1.\"WTSumFC\", 0)) END AS \"saldo\", " +
            "T2.\"SlpName\"                                        AS \"vendedor\", " +
            "T3.\"CreditLine\"                                     AS \"lc\" " +
            "FROM B1H_BATT_PROD2.\"OINV\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OSLP\" T2 ON T1.\"SlpCode\"  = T2.\"SlpCode\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T3 ON T1.\"CardCode\" = T3.\"CardCode\" " +
            "WHERE (T1.\"DocTotal\" - IFNULL(T1.\"PaidToDate\", 0)) > 0 " +
            "AND T1.\"CardCode\" NOT IN ('C40167525') " +
            "AND T1.\"CardCode\" =:ruc " +
            "UNION ALL " +
            "SELECT " +
            "T1.\"ShortName\"                                      AS \"ruc\", " +
            "T2.\"CardName\"                                       AS \"nombre\", " +
            "T3.\"Number\"                                         AS \"documento\", " +
            "CASE WHEN T1.\"Account\" IN ('12142001', '12122002', '12132002') THEN T1.\"Ref2\" ELSE CONCAT('LE-', T1.\"Ref2\") END AS \"comprobante\", " +
            "TO_VARCHAR(T1.\"RefDate\", 'YYYY-MM-DD')              AS \"emision\", " +
            "TO_VARCHAR(T1.\"DueDate\", 'YYYY-MM-DD')              AS \"vencimiento\", " +
            "T1.\"FCCurrency\"                                     AS \"moneda\", " +
            "T1.\"FCDebit\"                                        AS \"importe\", " +
            "T1.\"BalFcDeb\"                                       AS \"saldo\", " +
            "T4.\"SlpName\"                                        AS \"vendedor\", " +
            "T2.\"CreditLine\"                                     AS \"lc\" " +
            "FROM B1H_BATT_PROD2.\"JDT1\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T2 ON T1.\"ShortName\" = T2.\"CardCode\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OJDT\" T3 ON T1.\"TransId\"   = T3.\"TransId\" " +
            "LEFT JOIN B1H_BATT_PROD2.\"OINV\" INV ON T1.\"ShortName\" = INV.\"CardCode\" " +
            "AND SUBSTRING(INV.\"NumAtCard\", LOCATE(INV.\"NumAtCard\", '-') + 1) " +
            "= SUBSTRING(T1.\"Ref2\", LOCATE(T1.\"Ref2\", '-') + 1) " +
            "INNER JOIN B1H_BATT_PROD2.\"OSLP\" T4 ON IFNULL(INV.\"SlpCode\", T2.\"SlpCode\") = T4.\"SlpCode\" " +
            "WHERE T1.\"Account\" IN ('12142001', '12122002', '12132002', '12342001') " +
            "AND T1.\"BalFcDeb\" > 0  " +
            "AND T1.\"MthDate\" IS NULL " +
            "AND T1.\"ShortName\" =:ruc " +
            ") AS \"SALDOS_UNIFICADOS\" " +
            "ORDER BY \"vendedor\" ASC, \"emision\" ASC", nativeQuery = true)
    List<FacturasPorCobrarResponse> buscarFacturasPorCobrarPorCliente(@Param("ruc") String ruc);

    @Query(value = "SELECT * FROM (" +
            "SELECT " +
            "T1.\"CardCode\" AS \"ruc\", " +
            "T1.\"CardName\" AS \"nombre\", " +
            "T1.\"DocNum\" AS \"documento\"," +
            "CASE WHEN T1.\"NumAtCard\" IS NOT NULL THEN CONCAT('FA-', T1.\"NumAtCard\") ELSE CONCAT('FA-', CAST(T1.\"DocNum\" AS NVARCHAR)) END AS \"comprobante\", " +
            "TO_VARCHAR(T1.\"DocDate\",    'YYYY-MM-DD') AS \"emision\", " +
            "TO_VARCHAR(T1.\"DocDueDate\", 'YYYY-MM-DD') AS \"vencimiento\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN 'US$' ELSE T1.\"DocCur\" END AS \"moneda\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN (T1.\"DocTotal\"/T1.\"SysRate\") ELSE T1.\"DocTotalFC\" END AS \"importe\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN ((T1.\"DocTotal\"-T1.\"PaidToDate\")/T1.\"SysRate\") ELSE ((T1.\"DocTotalFC\" - IFNULL(T1.\"PaidFC\", 0)) - IFNULL(T1.\"WTSumFC\", 0)) END AS \"saldo\", " +
            "T2.\"SlpName\" AS \"vendedor\", " +
            "T3.\"CreditLine\" AS \"lc\" " +
            "FROM B1H_BATT_PROD2.\"OINV\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OSLP\" T2 ON T1.\"SlpCode\"  =  T2.\"SlpCode\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T3 ON T1.\"CardCode\" = T3.\"CardCode\" " +
            "WHERE (T1.\"DocTotal\" - IFNULL(T1.\"PaidToDate\", 0)) > 0 " +
            "AND T1.\"CardCode\" NOT IN ('C40167525') " +
            "AND T1.\"SlpCode\"=:slpCode " +
            "UNION ALL " +
            "SELECT " +
            "T1.\"ShortName\" AS \"ruc\", " +
            "T2.\"CardName\" AS \"nombre\", " +
            "T3.\"Number\" AS \"documento\", " +
            "CASE WHEN T1.\"Account\" IN ('12142001', '12122002', '12132002') THEN T1.\"Ref2\" ELSE CONCAT('LE-', T1.\"Ref2\") END AS \"comprobante\", " +
            "TO_VARCHAR(T1.\"RefDate\", 'YYYY-MM-DD') AS \"emision\", " +
            "TO_VARCHAR(T1.\"DueDate\", 'YYYY-MM-DD') AS \"vencimiento\", " +
            "T1.\"FCCurrency\" AS \"moneda\", " +
            "T1.\"FCDebit\" AS \"importe\", " +
            "T1.\"BalFcDeb\" AS \"saldo\", " +
            "T4.\"SlpName\" AS \"vendedor\", " +
            "T2.\"CreditLine\" AS \"lc\" " +
            "FROM B1H_BATT_PROD2.\"JDT1\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T2 ON T1.\"ShortName\" = T2.\"CardCode\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OJDT\" T3 ON T1.\"TransId\"   = T3.\"TransId\" " +
            "LEFT JOIN B1H_BATT_PROD2.\"OINV\" INV ON T1.\"ShortName\" = INV.\"CardCode\" " +
            "AND SUBSTRING(INV.\"NumAtCard\", LOCATE(INV.\"NumAtCard\", '-') + 1) " +
            "= SUBSTRING(T1.\"Ref2\", LOCATE(T1.\"Ref2\", '-') + 1) " +
            "INNER JOIN B1H_BATT_PROD2.\"OSLP\" T4 ON IFNULL(INV.\"SlpCode\", T2.\"SlpCode\") = T4.\"SlpCode\" " +
            "WHERE T1.\"Account\" IN ('12142001', '12122002', '12132002', '12342001') " +
            "AND T1.\"BalFcDeb\" > 0 " +
            "AND T1.\"MthDate\" IS NULL " +
            "AND IFNULL(INV.\"SlpCode\", T2.\"SlpCode\") =:slpCode " +
            ") AS \"SALDOS_UNIFICADOS\" " +
            "ORDER BY \"vendedor\" ASC, \"nombre\" ASC, \"emision\" ASC", nativeQuery = true)
    List<FacturasPorCobrarResponse> buscarFacturasPorCobrarPorVendedor(@Param("slpCode") Integer slpCode);

    @Query(value = "SELECT * FROM (" +
            "SELECT " +
            "T1.\"CardCode\" AS \"ruc\", " +
            "T1.\"CardName\" AS \"nombre\", " +
            "T1.\"DocNum\" AS \"documento\"," +
            "CASE WHEN T1.\"NumAtCard\" IS NOT NULL THEN CONCAT('FA-', T1.\"NumAtCard\") ELSE CONCAT('FA-', CAST(T1.\"DocNum\" AS NVARCHAR)) END AS \"comprobante\", " +
            "TO_VARCHAR(T1.\"DocDate\",    'YYYY-MM-DD') AS \"emision\", " +
            "TO_VARCHAR(T1.\"DocDueDate\", 'YYYY-MM-DD') AS \"vencimiento\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN 'US$' ELSE T1.\"DocCur\" END AS \"moneda\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN (T1.\"DocTotal\"/T1.\"SysRate\") ELSE T1.\"DocTotalFC\" END AS \"importe\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN ((T1.\"DocTotal\"-T1.\"PaidToDate\")/T1.\"SysRate\") ELSE ((T1.\"DocTotalFC\" - IFNULL(T1.\"PaidFC\", 0)) - IFNULL(T1.\"WTSumFC\", 0)) END AS \"saldo\", " +
            "T2.\"SlpName\" AS \"vendedor\", " +
            "T3.\"CreditLine\" AS \"lc\" " +
            "FROM B1H_BATT_PROD2.\"OINV\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OSLP\" T2 ON T1.\"SlpCode\"  =  T2.\"SlpCode\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T3 ON T1.\"CardCode\" = T3.\"CardCode\" " +
            "WHERE (T1.\"DocTotal\" - IFNULL(T1.\"PaidToDate\", 0)) > 0 " +
            "AND T1.\"CardCode\" NOT IN ('C40167525') " +
            "AND T1.\"SlpCode\" =:slpCode " +
            "AND T1.\"CardCode\" =:ruc " +
            "UNION ALL " +
            "SELECT " +
            "T1.\"ShortName\" AS \"ruc\", " +
            "T2.\"CardName\" AS \"nombre\", " +
            "T3.\"Number\" AS \"documento\", " +
            "CASE WHEN T1.\"Account\" IN ('12142001', '12122002', '12132002') THEN T1.\"Ref2\" ELSE CONCAT('LE-', T1.\"Ref2\") END AS \"comprobante\", " +
            "TO_VARCHAR(T1.\"RefDate\", 'YYYY-MM-DD') AS \"emision\", " +
            "TO_VARCHAR(T1.\"DueDate\", 'YYYY-MM-DD') AS \"vencimiento\", " +
            "T1.\"FCCurrency\" AS \"moneda\", " +
            "T1.\"FCDebit\" AS \"importe\", " +
            "T1.\"BalFcDeb\" AS \"saldo\", " +
            "T4.\"SlpName\" AS \"vendedor\", " +
            "T2.\"CreditLine\" AS \"lc\" " +
            "FROM B1H_BATT_PROD2.\"JDT1\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T2 ON T1.\"ShortName\" = T2.\"CardCode\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OJDT\" T3 ON T1.\"TransId\"   = T3.\"TransId\" " +
            "LEFT JOIN B1H_BATT_PROD2.\"OINV\" INV ON T1.\"ShortName\" = INV.\"CardCode\" " +
            "AND SUBSTRING(INV.\"NumAtCard\", LOCATE(INV.\"NumAtCard\", '-') + 1) " +
            "= SUBSTRING(T1.\"Ref2\", LOCATE(T1.\"Ref2\", '-') + 1) " +
            "INNER JOIN B1H_BATT_PROD2.\"OSLP\" T4 ON IFNULL(INV.\"SlpCode\", T2.\"SlpCode\") = T4.\"SlpCode\" " +
            "WHERE T1.\"Account\" IN ('12142001', '12122002', '12132002', '12342001') " +
            "AND T1.\"BalFcDeb\" > 0 " +
            "AND T1.\"MthDate\" IS NULL " +
            "AND IFNULL(INV.\"SlpCode\", T2.\"SlpCode\") =:slpCode " +
            "AND T1.\"ShortName\" =:ruc" +
            ") AS \"SALDOS_UNIFICADOS\" " +
            "ORDER BY \"vendedor\" ASC, \"nombre\" ASC, \"emision\" ASC", nativeQuery = true)
    List<FacturasPorCobrarResponse> buscarFacturasPorVendedorYCliente(@Param("slpCode") Integer slpCode, @Param("ruc") String ruc);

    @Query(value = "SELECT \"SALDOS_UNIFICADOS\".\"nombre\", SUM(\"SALDOS_UNIFICADOS\".\"saldo\") FROM (" +
            "SELECT " +
            "T1.\"CardCode\" AS \"ruc\", " +
            "T1.\"CardName\" AS \"nombre\", " +
            "T1.\"DocNum\" AS \"documento\"," +
            "CASE WHEN T1.\"NumAtCard\" IS NOT NULL THEN CONCAT('FA-', T1.\"NumAtCard\") ELSE CONCAT('FA-', CAST(T1.\"DocNum\" AS NVARCHAR)) END AS \"comprobante\", " +
            "TO_VARCHAR(T1.\"DocDate\",    'YYYY-MM-DD') AS \"emision\", " +
            "TO_VARCHAR(T1.\"DocDueDate\", 'YYYY-MM-DD') AS \"vencimiento\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN 'US$' ELSE T1.\"DocCur\" END AS \"moneda\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN (T1.\"DocTotal\"/T1.\"SysRate\") ELSE T1.\"DocTotalFC\" END AS \"importe\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN ((T1.\"DocTotal\"-T1.\"PaidToDate\")/T1.\"SysRate\") ELSE ((T1.\"DocTotalFC\" - IFNULL(T1.\"PaidFC\", 0)) - IFNULL(T1.\"WTSumFC\", 0)) END AS \"saldo\", " +
            "T2.\"SlpName\" AS \"vendedor\", " +
            "T3.\"CreditLine\" AS \"lc\" " +
            "FROM B1H_BATT_PROD2.\"OINV\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OSLP\" T2 ON T1.\"SlpCode\"  =  T2.\"SlpCode\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T3 ON T1.\"CardCode\" = T3.\"CardCode\" " +
            "WHERE (T1.\"DocTotal\" - IFNULL(T1.\"PaidToDate\", 0)) > 0 " +
            "AND T1.\"CardCode\" NOT IN ('C40167525') " +
            "UNION ALL " +
            "SELECT " +
            "T1.\"ShortName\" AS \"ruc\", " +
            "T2.\"CardName\" AS \"nombre\", " +
            "T3.\"Number\" AS \"documento\", " +
            "CASE WHEN T1.\"Account\" IN ('12142001', '12122002', '12132002') THEN T1.\"Ref2\" ELSE CONCAT('LE-', T1.\"Ref2\") END AS \"comprobante\", " +
            "TO_VARCHAR(T1.\"RefDate\", 'YYYY-MM-DD') AS \"emision\", " +
            "TO_VARCHAR(T1.\"DueDate\", 'YYYY-MM-DD') AS \"vencimiento\", " +
            "T1.\"FCCurrency\" AS \"moneda\", " +
            "T1.\"FCDebit\" AS \"importe\", " +
            "T1.\"BalFcDeb\" AS \"saldo\", " +
            "T4.\"SlpName\" AS \"vendedor\", " +
            "T2.\"CreditLine\" AS \"lc\" " +
            "FROM B1H_BATT_PROD2.\"JDT1\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T2 ON T1.\"ShortName\" = T2.\"CardCode\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OJDT\" T3 ON T1.\"TransId\"   = T3.\"TransId\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OSLP\" T4 ON T2.\"SlpCode\"   = T4.\"SlpCode\" " +
            "WHERE T1.\"Account\" IN ('12142001', '12122002', '12132002', '12342001') " +
            "AND T1.\"BalFcDeb\" > 0 " +
            "AND T1.\"MthDate\" IS NULL " +
            ") AS \"SALDOS_UNIFICADOS\" " +
            "GROUP BY \"SALDOS_UNIFICADOS\".\"nombre\" " +
            "ORDER BY SUM(\"SALDOS_UNIFICADOS\".\"saldo\") DESC " +
            "LIMIT 10", nativeQuery = true)
    List<FacturasPorCobrarTopDiezResponse> facturasPorCobrarTopDiez();

    @Query(value = "SELECT \"ruc\", \"nombre\", \"comprobante\", \"vencimiento\", \"moneda\", \"importe\", \"saldo\", \"diasVencido\" FROM (" +
            "SELECT " +
            "T1.\"CardCode\" AS \"ruc\", " +
            "T1.\"CardName\" AS \"nombre\", " +
            "T1.\"DocNum\" AS \"documento\"," +
            "CASE WHEN T1.\"NumAtCard\" IS NOT NULL THEN CONCAT('FA-', T1.\"NumAtCard\") ELSE CONCAT('FA-', CAST(T1.\"DocNum\" AS NVARCHAR)) END AS \"comprobante\", " +
            "TO_VARCHAR(T1.\"DocDate\",    'YYYY-MM-DD') AS \"emision\", " +
            "TO_VARCHAR(T1.\"DocDueDate\", 'YYYY-MM-DD') AS \"vencimiento\", " +
            "DAYS_BETWEEN(T1.\"DocDueDate\", CURRENT_DATE)         AS \"diasVencido\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN 'US$' ELSE T1.\"DocCur\" END AS \"moneda\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN (T1.\"DocTotal\"/T1.\"SysRate\") ELSE T1.\"DocTotalFC\" END AS \"importe\", " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN ((T1.\"DocTotal\"-T1.\"PaidToDate\")/T1.\"SysRate\") ELSE ((T1.\"DocTotalFC\" - IFNULL(T1.\"PaidFC\", 0)) - IFNULL(T1.\"WTSumFC\", 0)) END AS \"saldo\", " +
            "T2.\"SlpName\" AS \"vendedor\", " +
            "T3.\"CreditLine\" AS \"lc\" " +
            "FROM B1H_BATT_PROD2.\"OINV\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OSLP\" T2 ON T1.\"SlpCode\"  =  T2.\"SlpCode\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T3 ON T1.\"CardCode\" = T3.\"CardCode\" " +
            "WHERE (T1.\"DocTotal\" - IFNULL(T1.\"PaidToDate\", 0)) > 0 " +
            "AND T1.\"CardCode\" NOT IN ('C40167525') " +
            "UNION ALL " +
            "SELECT " +
            "T1.\"ShortName\" AS \"ruc\", " +
            "T2.\"CardName\" AS \"nombre\", " +
            "T3.\"Number\" AS \"documento\", " +
            "CASE WHEN T1.\"Account\" IN ('12142001', '12122002', '12132002') THEN T1.\"Ref2\" ELSE CONCAT('LE-', T1.\"Ref2\") END AS \"comprobante\", " +
            "TO_VARCHAR(T1.\"RefDate\", 'YYYY-MM-DD') AS \"emision\", " +
            "TO_VARCHAR(T1.\"DueDate\", 'YYYY-MM-DD') AS \"vencimiento\", " +
            "DAYS_BETWEEN(T1.\"DueDate\", CURRENT_DATE)         AS \"diasVencido\", " +
            "T1.\"FCCurrency\" AS \"moneda\", " +
            "T1.\"FCDebit\" AS \"importe\", " +
            "T1.\"BalFcDeb\" AS \"saldo\", " +
            "T4.\"SlpName\" AS \"vendedor\", " +
            "T2.\"CreditLine\" AS \"lc\" " +
            "FROM B1H_BATT_PROD2.\"JDT1\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T2 ON T1.\"ShortName\" = T2.\"CardCode\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OJDT\" T3 ON T1.\"TransId\"   = T3.\"TransId\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OSLP\" T4 ON T2.\"SlpCode\"   = T4.\"SlpCode\" " +
            "WHERE T1.\"Account\" IN ('12142001', '12122002', '12132002', '12342001') " +
            "AND T1.\"BalFcDeb\" > 0 " +
            "AND T1.\"MthDate\" IS NULL " +
            ") AS \"SALDOS_UNIFICADOS\" " +
            "ORDER BY \"diasVencido\" DESC " +
            "LIMIT 10", nativeQuery = true)
    List<FacturasPorCobrarTopDiezVencidosResponse> facturasPorCobrarTopDiezMasVencidas(); //FALTA AGREGAR AL SERVICE Y CONTROLLER

    @Query(value = "SELECT " +
            "SUM(CASE WHEN \"dias\" <= 0                    THEN \"saldo\" ELSE 0 END) AS \"no_vencido\", " +
            "SUM(CASE WHEN \"dias\" BETWEEN  1  AND  30    THEN \"saldo\" ELSE 0 END) AS \"vencido_0_30\", " +
            "SUM(CASE WHEN \"dias\" BETWEEN  31 AND  45    THEN \"saldo\" ELSE 0 END) AS \"vencido_31_45\", " +
            "SUM(CASE WHEN \"dias\" BETWEEN  46 AND  60    THEN \"saldo\" ELSE 0 END) AS \"vencido_46_60\", " +
            "SUM(CASE WHEN \"dias\" BETWEEN  61 AND  90    THEN \"saldo\" ELSE 0 END) AS \"vencido_61_90\", " +
            "SUM(CASE WHEN \"dias\" BETWEEN  91 AND 180    THEN \"saldo\" ELSE 0 END) AS \"vencido_91_180\", " +
            "SUM(CASE WHEN \"dias\" > 180                  THEN \"saldo\" ELSE 0 END) AS \"vencido_180_mas\" " +
            "FROM ( " +
            "SELECT " +
            "CASE WHEN T1.\"DocCur\" = 'S/' THEN ((T1.\"DocTotal\" - T1.\"PaidToDate\") / T1.\"SysRate\") " +
            "ELSE ((T1.\"DocTotalFC\" - IFNULL(T1.\"PaidFC\", 0)) - IFNULL(T1.\"WTSumFC\", 0)) END AS \"saldo\", " +
            "DAYS_BETWEEN(T1.\"DocDueDate\", CURRENT_DATE) AS \"dias\" " +
            "FROM B1H_BATT_PROD2.\"OINV\" T1 " +
            "WHERE (T1.\"DocTotal\" - IFNULL(T1.\"PaidToDate\", 0)) > 0 " +
            "AND T1.\"CardCode\" NOT IN ('C40167525') " +
            "UNION ALL " +
            "SELECT " +
            "T1.\"BalFcDeb\"                               AS \"saldo\", " +
            "DAYS_BETWEEN(T1.\"DueDate\", CURRENT_DATE)    AS \"dias\" " +
            "FROM B1H_BATT_PROD2.\"JDT1\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T2 ON T1.\"ShortName\" = T2.\"CardCode\" " +
            "INNER JOIN B1H_BATT_PROD2.\"OSLP\" T4 ON T2.\"SlpCode\"   = T4.\"SlpCode\" " +
            "WHERE T1.\"Account\" IN ('12142001', '12122002', '12132002', '12342001') " +
            "AND T1.\"BalFcDeb\" > 0 " +
            "AND T1.\"MthDate\" IS NULL " +
            ") AS \"S\"", nativeQuery = true)
    List<ResumenCarteraResponse> resumenCartera();
}
