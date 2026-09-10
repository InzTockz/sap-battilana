package com.battilana.sap.entity.articulos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OITB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Familias {

    @Id
    @Column(name = "\"ItmsGrpCod\"")
    private String itmsGrpCod;
}
