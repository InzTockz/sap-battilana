package com.battilana.sap.entity.articulos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table(name = "OITB", schema = "B1H_BATT_DESA_DE")
@Table(name = "OITB", schema = "B1H_BATT_PROD2")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GrupoArticulos {

    @Id
    @Column(name = "\"ItmsGrpCod\"")
    private Integer itmsGrpCod;

    @Column(name = "\"ItmsGrpNam\"")
    private String itmsGrpName;


}
