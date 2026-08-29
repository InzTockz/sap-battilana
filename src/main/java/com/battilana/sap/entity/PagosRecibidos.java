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
@Table(name = "ORCT", schema = "B1H_BATT_PRUCOM0726")
//@Table(name = "ORCT", schema = "B1H_BATT_PROD2")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagosRecibidos {

    @Id
    @Column(name = "\"DocEntry\"")
    private Integer docEntry;

    @Column(name = "\"CardCode\"")
    private String cardCode;

    @Column(name = "\"DocDate\"")
    private LocalDate docDate;

    @Column(name = "\"DocDueDate\"")
    private LocalDate docDueDate;
}
