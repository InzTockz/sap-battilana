package com.battilana.sap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
//@Table(name = "OPCH", schema = "B1H_BATT_PRUCOM0726")
@Table(name = "OPCH", schema = "B1H_BATT_PROD2")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturasProveedor {

    @Id
    @Column(name = "\"DocEntry\"")
    private Integer docEntry;

    @Column(name = "\"DocNum\"")
    private Integer docNum;

    @Column(name = "\"DocType\"")
    private String docType;

    @Column(name = "\"CANCELED\"")
    private String canceled;

    @Column(name = "\"DocStatus\"")
    private String docStatus;

    @Column(name = "\"ObjType\"")
    private String objType;

    @Column(name = "\"DocDate\"")
    private Date docDate;

    @Column(name = "\"DocDueDate\"")
    private Date docDueDate;

    @Column(name = "\"CardCode\"")
    private String cardCode;

    @Column(name = "\"CardName\"")
    private String cardName;

    @Column(name = "\"NumAtCard\"")
    private String numAtCard;

    @Column(name = "\"DocCur\"")
    private String docCur;

    @Column(name = "\"DocTotal\"")
    private BigDecimal docTotal;

    @Column(name = "\"DocTotalFC\"")
    private BigDecimal docTotalFC;
}
