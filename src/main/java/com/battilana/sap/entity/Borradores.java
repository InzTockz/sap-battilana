package com.battilana.sap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//TABLA DE DRAFT O BORRADORES
@Entity
@Table(name = "ODRF", schema = "B1H_BATT_DESA_DE")
//@Table(name = "ODRF", schema = "B1H_BATT_PROD2")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Borradores {

    @Id
    @Column(name = "\"DocEntry\"")
    private Integer docEntry;

    @Column(name = "\"ObjType\"")
    private String objType; //TIPO DE DOCUMENTO POR EJEMPLO SIEMPRE SERA 17

    @Column(name = "\"DocDate\"")
    private LocalDate docDate; // NO SE QUE ES PERO DICE QUE ES IMPORTANTE

    @Column(name = "\"CreateDate\"")
    private LocalDate createDate; // FECHA DE CREACION DEL DOCUMENTO

    //**
    @Column(name = "\"DocStatus\"")
    private String docStatus; // ESTADO DEL DOCUMENTO

    //**
    @Column(name = "\"CANCELED\"")
    private String canceled; // CONTROLA EL ESTADO DE CANCELACION DEL DOCUMENTO

    @Column(name = "\"CardCode\"")
    private String cardCode; // CODIGO DEL CLIENTE

    @Column(name = "\"CardName\"")
    private String cardName; // NOMBRE DEL CLIENTE

    @Column(name = "\"SlpCode\"")
    private Integer slpCode; // CODIGO DEL VENDEDOR

    @Column(name = "\"OwnerCode\"")
    private Integer ownerCode; // CODIGO DE PROPIETARIO

    @Column(name = "\"WddStatus\"")
    private String wddStatus; // ETAPA DE PROCESO DE APROBACION

    @Column(name = "\"Comments\"")
    private String comments; //COMENTARIOS DEL DOCUMENTO

    @Column(name = "\"DocCur\"")
    private String docCur; // TIPO DE MONERA

    @Column(name = "\"DocTotal\"")
    private Double docTotal; // EL PRECIO TOTAL DE TODOS LOS PRODUCTOS DEL DRAFT
}
