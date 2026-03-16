package com.battilana.sap.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table(name = "OCRD", schema = "B1H_BATT_PROD2")
@Table(name = "OCRD", schema = "B1H_BATT_DESA_DE")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {

    @Id
    @Column(name = "\"CardCode\"")
    private String cardCode;

    @Column(name = "\"CardName\"")
    private String cardName;

    @Column(name = "\"E_Mail\"")
    private String email;

    @Column(name = "\"LicTradNum\"")
    private String licTradNum;

    @Column(name = "\"frozenFor\"")
    private String frozenFor;

    @Column(name = "\"ListNum\"")
    private Integer listNum;

    @Column(name = "\"SlpCode\"")
    private int slpCode;
}
