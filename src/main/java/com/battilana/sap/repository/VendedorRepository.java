package com.battilana.sap.repository;

import com.battilana.sap.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

    @Query("SELECT V " +
            "FROM Vendedor V " +
            "WHERE V.slpCode > -1 " +
            "AND V.slpCode NOT IN (-1, 1, 4, 3, 27, 6, 5, 9, 12, 13, 14, 16, 15, 19, 20, 22, 23, 26, 25, 30, 29, 31) " +
            "AND V.active = 'Y' " +
            "ORDER BY V.slpCode ASC")
    List<Vendedor> findVendedores();
}
