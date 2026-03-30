package com.battilana.sap.service.impl;

import com.battilana.sap.entity.FacturasProveedor;
import com.battilana.sap.repository.FacturasProveedorRepository;
import com.battilana.sap.service.FacturasProveedorService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FacturasProveedorServiceImpl implements FacturasProveedorService {

    private final FacturasProveedorRepository facturasProveedorRepository;

    public FacturasProveedorServiceImpl(FacturasProveedorRepository facturasProveedorRepository) {
        this.facturasProveedorRepository = facturasProveedorRepository;
    }

    @Override
    public List<FacturasProveedor> listarTodo() {
        return this.facturasProveedorRepository.findAll();
    }

    @Override
    public List<FacturasProveedor> listarPorInicialesIMP() {
        return this.facturasProveedorRepository.findByImp();
    }

    @Override
    public List<FacturasProveedor> listarPorFechasInicialesIMP(String fecha1, String fecha2) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fec1 = sdf.parse(fecha1);
        Date fec2 = sdf.parse(fecha2);

        return this.facturasProveedorRepository.findPorDocDueDatePorImp(fec1, fec2);
    }

    @Override
    public List<FacturasProveedor> listarPorInicialesP() {
        return this.facturasProveedorRepository.findByP();
    }

    @Override
    public List<FacturasProveedor> listarPorFechasInicialesP(String fecha1, String fecha2) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fec1 = sdf.parse(fecha1);
        Date fec2 = sdf.parse(fecha2);

        return this.facturasProveedorRepository.findByDocDueDateByP(fec1, fec2);
    }

    @Override
    public Optional<FacturasProveedor> buscarPorId(Integer id) {
        return this.facturasProveedorRepository.findById(id);
    }

    @Override
    public List<FacturasProveedor> buscarPorFechasVencimiento(String fecha1, String fecha2) {
        return this.facturasProveedorRepository.findPorDocDueDate(fecha1, fecha2);
    }
}
