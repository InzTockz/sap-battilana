package com.battilana.sap.entity.articulos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table(name = "OITM", schema = "B1H_BATT_DESA_DE")
@Table(name = "OITM", schema = "B1H_BATT_PROD2")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Articulos {

    @Id
    @Column(name = "\"ItemCode\"")
    private String itemCode;

    @Column(name = "\"ItemName\"")
    private String itemName;

    @Column(name = "\"ItmsGrpCod\"")
    private String itmsGrpCod;

    @Column(name ="\"InvntryUom\"")
    private String invtryUom;

    @Column(name = "\"U_SYP_PRESENTACION\"")
    private String presentacion;

    @Column(name = "\"validFor\"")
    private String validFor;

    @Column(name = "\"frozenFor\"")
    private String frozenFor;
}
