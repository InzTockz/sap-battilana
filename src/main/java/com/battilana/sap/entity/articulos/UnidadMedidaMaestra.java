package com.battilana.sap.entity.articulos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table(name = "OUOM", schema = "B1H_BATT_DESA_DE")
@Table(name = "OUOM", schema = "B1H_BATT_PROD2")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadMedidaMaestra {

    @Id
    @Column(name = "\"UomEntry\"")
    private Integer uomEntry;

    @Column(name = "\"UomCode\"")
    private String uomCode;

    @Column(name = "\"UomName\"")
    private String uomName;

    @Column(name = "\"Locked\"")
    private String Locked;
}
