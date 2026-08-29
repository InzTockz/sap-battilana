package com.battilana.sap.entity.articulos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OWHS", schema = "B1H_BATT_PRUCOM0726")
//@Table(name = "OWHS", schema = "B1H_BATT_PROD2")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Almacenes {

    @Id
    @Column(name = "\"WhsCode\"")
    private String whsCode;

    @Column(name = "\"WhsName\"")
    private String whsName;
}
