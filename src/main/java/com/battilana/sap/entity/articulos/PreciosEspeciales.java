package com.battilana.sap.entity.articulos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OSPP", schema = "B1H_BATT_PRUCOM0726")
//@Table(name = "OSPP", schema = "B1H_BATT_PROD2")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreciosEspeciales {

    @Id
    @Column(name = "\"CardCode\"")
    private String cardCode;

    @Column(name = "\"ItemCode\"")
    private String itemCode;

    @Column(name = "\"Price\"")
    private Double price;

    @Column(name = "\"Discount\"")
    private Double discount;
}
