package com.battilana.sap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "OINV", schema = "B1H_BATT_PRUCOM0726")
//@Table(name = "OINV", schema = "B1H_BATT_PROD2")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FacturasCliente {

    @Id
    @Column(name = "\"DocEntry\"")
    private Integer docEntry;

    @Column(name = "\"CardCode\"")
    private String cardCode;

    @Column(name = "\"DocNum\"")
    private Integer docNum;

    @Column(name = "\"DocDate\"")
    private LocalDate docDate;

    @Column(name = "\"DocDueDate\"")
    private LocalDate docDueDate;

    @Column(name = "\"DocTotalFC\"")
    private Double docTotalFC;

    @Column(name = "\"PaidToDate\"")
    private Double paidFC;

    @Column(name = "\"DocStatus\"")
    private String docStatus;

}
