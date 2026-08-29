package com.battilana.sap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OCTG", schema = "B1H_BATT_PRUCOM0726")
//@Table(name = "OCTG", schema = "B1H_BATT_PROD2")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TerminosPago {

    @Id
    @Column(name = "\"GroupNum\"")
    private Integer groupNum;

    @Column(name = "\"PymntGroup\"")
    private String pymntGroup;
}
