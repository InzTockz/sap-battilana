package com.battilana.sap.entity.articulos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OWHS")
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
