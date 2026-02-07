package com.battilana.sap.repository.articulos;

import com.battilana.sap.entity.articulos.Articulos;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticulosRepository extends JpaRepository<Articulos, String> {

    @Query("SELECT T1 " +
            "FROM Articulos T1 " +
            "INNER JOIN StockAlmacen T2 ON T2.itemCode = T1.itemCode " +
            "INNER JOIN Almacenes T3 ON T2.whsCode = T3.whsCode " +
            "INNER JOIN Familias T4 ON T4.itmsGrpCod = T1.itmsGrpCod " +
            "WHERE T3.whsCode =:idAlmacen AND T4.itmsGrpCod NOT IN (" +
            "'120', '121', '167', '168', '169', '170', '171', '172', '173', '166', '177', '178', '164', '165') " +
            "AND T1.itemName NOT LIKE 'MP-%' AND T1.itemName NOT LIKE 'MP - %' " +
            "AND T1.validFor='Y' AND (T1.frozenFor='N' OR T1.frozenFor IS NULL)")
    List<Articulos> findArticulosPorAlmacen(@Param("idAlmacen") String idAlmacen);

    @Query("SELECT T1 " +
            "FROM Articulos T1 " +
            "INNER JOIN StockAlmacen T2 ON T2.itemCode = T1.itemCode " +
            "INNER JOIN Almacenes T3 ON T2.whsCode = T3.whsCode " +
            "INNER JOIN Familias T4 ON T4.itmsGrpCod = T1.itmsGrpCod " +
            "WHERE LOWER(T1.itemName) LIKE LOWER(CONCAT('%', :nombre, '%')) " +
            "AND T3.whsCode =:idAlmacen AND T4.itmsGrpCod NOT IN (" +
            "'120', '121', '167', '168', '169', '170', '171', '172', '173', '166', '177', '178', '164', '165') " +
            "AND T1.itemName NOT LIKE 'MP-%' AND T1.itemName NOT LIKE 'MP - %' " +
            "AND T1.validFor='Y' AND (T1.frozenFor='N' OR T1.frozenFor IS NULL)")
    List<Articulos> findArticulosPorNombres(@Param("idAlmacen") String idAlmacen, @Param("nombre") String nombre, Pageable pageable);

}
