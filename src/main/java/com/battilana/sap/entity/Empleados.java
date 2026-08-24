package com.battilana.sap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table(name = "OHEM", schema = "B1H_BATT_PRUCOM0726")
@Table(name = "OHEM", schema = "B1H_BATT_PROD2")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleados {

    @Id
    @Column(name = "\"empID\"")
    private Integer empId;

    @Column(name = "\"lastName\"")
    private String lastName;

    @Column(name = "\"firstName\"")
    private String firstName;
}
