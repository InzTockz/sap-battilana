package com.battilana.sap.entity.articulos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OUGP", schema = "B1H_BATT_PRUCOM0726")
//@Table(name = "OUGP", schema = "B1H_BATT_PROD2")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadMedidaGrupal {

    @Id
    @Column(name = "\"UgpEntry\"")
    private Integer upgEntry;

    @Column(name = "\"UgpCode\"")
    private String ugpCode;

    @Column(name = "\"UgpName\"")
    private String ugpName;
}
