package com.battilana.sap.service;

import com.battilana.sap.entity.DetalleBorradores;

import java.util.List;

public interface DetalleBorradoresService {
    List<DetalleBorradores> buscarDetalleDraftPorDocEntry(Integer docEntry);
}
