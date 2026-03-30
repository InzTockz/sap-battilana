package com.battilana.sap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table(name = "OSLP", schema = "B1H_BATT_PROD2")
@Table(name = "OSLP", schema = "B1H_BATT_DESA_DE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendedor {

    @Id
    @Column(name = "\"SlpCode\"")
    private Integer slpCode;

    @Column(name = "\"SlpName\"")
    private String slpName;

    @Column(name = "\"ListNum\"")
    private Integer listNum;

    @Column(name = "\"Active\"")
    private String active;
}
