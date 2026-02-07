package com.battilana.sap.service.impl;

import com.battilana.sap.entity.DetalleBorradores;
import com.battilana.sap.repository.DetalleBorradoresRepository;
import com.battilana.sap.service.DetalleBorradoresService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleBorradoresServiceImpl implements DetalleBorradoresService {

    private final DetalleBorradoresRepository detalleBorradoresRepository;

    public DetalleBorradoresServiceImpl(DetalleBorradoresRepository detalleBorradoresRepository) {
        this.detalleBorradoresRepository = detalleBorradoresRepository;
    }

    @Override
    public List<DetalleBorradores> buscarDetalleDraftPorDocEntry(Integer docEntry) {
        return this.detalleBorradoresRepository.findDetalleBorradoresPorDocEntry(docEntry);
    }
}
