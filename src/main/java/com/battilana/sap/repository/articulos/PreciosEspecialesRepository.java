package com.battilana.sap.repository.articulos;

import com.battilana.sap.dto.PreciosEspecialesResponse;
import com.battilana.sap.entity.articulos.PreciosEspeciales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PreciosEspecialesRepository extends JpaRepository<PreciosEspeciales, String> {

    @Query("SELECT new com.battilana.sap.dto.PreciosEspecialesResponse( " +
            "PE.cardCode," +
            "PE.itemCode," +
            "PE.price," +
            "PE.discount," +
            "LP.price," +
            "LP.priceList) " +
            "FROM ListaPrecios LP " +
            "LEFT JOIN PreciosEspeciales PE ON LP.itemCode = PE.itemCode AND PE.cardCode=:cardCode " +
            "WHERE LP.itemCode=:itemCode " +
            "AND LP.priceList=:priceList")
    PreciosEspecialesResponse buscarDescuento(
            @Param("cardCode") String cardCode,
            @Param("itemCode") String itemCode,
            @Param("priceList") Integer priceList);
}
