package com.battilana.sap.repository;

import com.battilana.sap.dto.clientes.ClienteDeudorResponse;
import com.battilana.sap.entity.Cliente;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    @Query("SELECT C " +
            "FROM Cliente C " +
            "WHERE C.cardCode LIKE 'C%' AND C.slpCode > -1 AND C.frozenFor = 'N' " +
            "AND C.slpCode NOT IN (-1, 1, 4, 3, 27, 6, 5, 9, 12, 13, 14, 16, 15, 19, 20, 22, 23, 26, 25, 30, 29, 31) " +
            "ORDER BY C.slpCode ASC")
    List<Cliente> findClientes();

    @Query("SELECT C " +
            "FROM Cliente C " +
            "WHERE C.cardCode LIKE 'C%' AND C.slpCode > -1 AND C.frozenFor='N' " +
            "AND C.slpCode=:idVendedor " +
            "ORDER BY C.slpCode ASC")
    List<Cliente> findClientesPorIdVendedor(@Param("idVendedor") Integer idVendedor);

    @Query("SELECT C " +
            "FROM Cliente C " +
            "WHERE C.cardCode LIKE 'C%' AND C.frozenFor='N' " +
            "AND C.slpCode =:idVendedor " +
            "AND LOWER(C.cardName) LIKE LOWER(CONCAT('%', :cardName, '%')) " +
            "ORDER BY C.slpCode ASC")
    List<Cliente> findClientesPorVendedorYCliente(@Param("idVendedor") Integer idVendedor, @Param("cardName") String cardName, Pageable pageable);

    @Query("SELECT C " +
            "FROM Cliente C " +
            "WHERE C.cardCode=:cardCode")
    Cliente buscarClientePorCardCode(@Param("cardCode") String cardCode);

    @Query(value = "SELECT DISTINCT " +
            "S.\"ruc\", " +
            "S.\"nombre\" " +
            "FROM ( " +
            "SELECT T1.\"CardCode\" AS \"ruc\", T1.\"CardName\" AS \"nombre\", T1.\"SlpCode\" AS \"slpCode\" " +
            "FROM B1H_BATT_PROD2.\"OINV\" T1 " +
            "WHERE (T1.\"DocTotal\" - IFNULL(T1.\"PaidToDate\", 0)) > 0 " +
            "AND T1.\"CardCode\" NOT IN ('C40167525') " +
            "UNION ALL " +
            "SELECT T1.\"ShortName\", T2.\"CardName\", T2.\"SlpCode\" " +
            "FROM B1H_BATT_PROD2.\"JDT1\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T2 ON T1.\"ShortName\" = T2.\"CardCode\" " +
            "WHERE T1.\"Account\" IN ('12142001', '12122002', '12132002', '12342001') " +
            "AND T1.\"BalFcDeb\" > 0 " +
            "AND T1.\"MthDate\" IS NULL " +
            ") AS S " +
            "ORDER BY S.\"nombre\" ASC ", nativeQuery = true)
    List<ClienteDeudorResponse> buscarClientesDeudores();

    @Query(value = "SELECT DISTINCT " +
            "S.\"ruc\", " +
            "S.\"nombre\" " +
            "FROM ( " +
            "SELECT T1.\"CardCode\" AS \"ruc\", T1.\"CardName\" AS \"nombre\", T1.\"SlpCode\" AS \"slpCode\" " +
            "FROM B1H_BATT_PROD2.\"OINV\" T1 " +
            "WHERE (T1.\"DocTotal\" - IFNULL(T1.\"PaidToDate\", 0)) > 0 " +
            "AND T1.\"CardCode\" NOT IN ('C40167525') " +
            "UNION ALL " +
            "SELECT T1.\"ShortName\", T2.\"CardName\", T2.\"SlpCode\" " +
            "FROM B1H_BATT_PROD2.\"JDT1\" T1 " +
            "INNER JOIN B1H_BATT_PROD2.\"OCRD\" T2 ON T1.\"ShortName\" = T2.\"CardCode\" " +
            "WHERE T1.\"Account\" IN ('12142001', '12122002', '12132002', '12342001') " +
            "AND T1.\"BalFcDeb\" > 0 " +
            "AND T1.\"MthDate\" IS NULL " +
            ") AS S " +
            "WHERE S.\"slpCode\" =:idVendedor " +
            "ORDER BY S.\"nombre\" ASC ", nativeQuery = true)
    List<ClienteDeudorResponse> buscarClientesDeudoresPorVendedor(@Param("idVendedor") Integer vendedor);
}
