package com.battilana.sap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//TABLA DE DETALLE DRAFTS O DETALLE DE BORRADORES
@Entity
//@Table(name = "DRF1", schema = "B1H_BATT_DESA_DE")
@Table(name = "DRF1", schema = "B1H_BATT_PROD2")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleBorradores {

    @Id
    @Column(name = "\"LineNum\"")
    private Integer lineNum;

    @Column(name = "\"ItemCode\"")
    private String itemCode;

    @Column(name = "\"Dscription\"")
    private String description;

    @Column(name = "\"Quantity\"")
    private Double quantity;

    @Column(name = "\"UomCode\"")
    private String uomCode;

    @Column(name = "\"WhsCode\"")
    private String whsCode;

    @Column(name = "\"PriceBefDi\"")
    private Double priceBefDi; //PRECIO POR UNIDAD

    @Column(name = "\"DiscPrcnt\"")
    private Double discPrnct; // DESCUENTO ESP.

    @Column(name = "\"Price\"")
    private Double price; //PRECIO TRAS EL DESCUENTO

    @Column(name = "\"PriceAfVAT\"")
    private Double priceAfVat; //PRECIO BRUTO DESPUES DEL DESCUENTO

    @Column(name = "\"TotalSumSy\"")
    private Double totalSumSy; //PRECIO FINAL POR PRODUCTO

    @Column(name = "\"DocEntry\"")
    private Integer docEntry;
}
