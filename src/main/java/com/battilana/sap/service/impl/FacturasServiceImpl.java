package com.battilana.sap.service.impl;

import com.battilana.sap.entity.Facturas;
import com.battilana.sap.repository.FacturasRepository;
import com.battilana.sap.service.FacturasService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FacturasServiceImpl implements FacturasService {

    private final FacturasRepository facturasRepository;

    public FacturasServiceImpl(FacturasRepository facturasRepository) {
        this.facturasRepository = facturasRepository;
    }

    @Override
    public List<Facturas> listarTodo() {
        return this.facturasRepository.findAll();
    }

    @Override
    public List<Facturas> listarPorInicialesIMP() {
        return this.facturasRepository.findByImp();
    }

    @Override
    public List<Facturas> listarPorFechasInicialesIMP(String fecha1, String fecha2) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fec1 = sdf.parse(fecha1);
        Date fec2 = sdf.parse(fecha2);

        return this.facturasRepository.findPorDocDueDatePorImp(fec1, fec2);
    }

    @Override
    public List<Facturas> listarPorInicialesP() {
        return this.facturasRepository.findByP();
    }

    @Override
    public List<Facturas> listarPorFechasInicialesP(String fecha1, String fecha2) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fec1 = sdf.parse(fecha1);
        Date fec2 = sdf.parse(fecha2);

        return this.facturasRepository.findByDocDueDateByP(fec1, fec2);
    }

    @Override
    public Optional<Facturas> buscarPorId(Integer id) {
        return this.facturasRepository.findById(id);
    }

    @Override
    public List<Facturas> buscarPorFechasVencimiento(String fecha1, String fecha2) {
        return this.facturasRepository.findPorDocDueDate(fecha1, fecha2);
    }
}
