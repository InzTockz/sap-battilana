package com.battilana.sap.entity.articulos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table(name = "ITM1", schema = "B1H_BATT_DESA_DE")
@Table(name = "ITM1", schema = "B1H_BATT_PROD2")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListaPrecios {

    @Id
    @Column(name = "\"ItemCode\"")
    private String itemCode;

    @Column(name = "\"Price\"")
    private Double price;

    @Column(name = "\"PriceList\"")
    private Integer priceList;
}
