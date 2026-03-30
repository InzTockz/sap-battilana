package com.battilana.sap.service;

import com.battilana.sap.entity.FacturasProveedor;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface FacturasProveedorService {

    List<FacturasProveedor> listarTodo();
    List<FacturasProveedor> listarPorInicialesIMP();
    List<FacturasProveedor> listarPorFechasInicialesIMP(String fecha1, String fecha2) throws ParseException;
    List<FacturasProveedor> listarPorInicialesP();
    List<FacturasProveedor> listarPorFechasInicialesP(String fecha1, String fecha2) throws ParseException;
    Optional<FacturasProveedor> buscarPorId(Integer id);
    List<FacturasProveedor> buscarPorFechasVencimiento(String fecha1, String fecha2);
}
