package com.battilana.sap.repository.articulos;

import com.battilana.sap.dto.StockAlmacenResponse;
import com.battilana.sap.entity.articulos.StockAlmacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockAlmacenRepository extends JpaRepository<StockAlmacen, String> {

    @Query("SELECT new com.battilana.sap.dto.StockAlmacenResponse(" +
            "T1.onHand, " +
            "T1.isCommited, " +
            "(T1.onHand-T1.isCommited), " +
            "T2.invtryUom, " +
            "T1.itemCode) " +
            "FROM StockAlmacen T1 " +
            "INNER JOIN Articulos T2 ON T1.itemCode = T2.itemCode " +
            "WHERE T1.itemCode =:idItemCode " +
            "AND T1.whsCode =:idAlmacen")
    StockAlmacenResponse stockPorProductoYAlmacen(@Param("idItemCode") String idItemCode, @Param("idAlmacen") String idAlmacen);

}
