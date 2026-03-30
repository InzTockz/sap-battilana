package com.battilana.sap.repository;

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
}
