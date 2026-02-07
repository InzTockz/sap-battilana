package com.battilana.sap.service;

import com.battilana.sap.entity.Facturas;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface FacturasService {

    List<Facturas> listarTodo();
    List<Facturas> listarPorInicialesIMP();
    List<Facturas> listarPorFechasInicialesIMP(String fecha1, String fecha2) throws ParseException;
    List<Facturas> listarPorInicialesP();
    List<Facturas> listarPorFechasInicialesP(String fecha1, String fecha2) throws ParseException;
    Optional<Facturas> buscarPorId(Integer id);
    List<Facturas> buscarPorFechasVencimiento(String fecha1, String fecha2);
}
