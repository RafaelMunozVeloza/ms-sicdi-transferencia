
package com.ejercito.transferencia.application.impl;


import com.ejercito.transferencia.infrastructure.adaptador.TransferenciaObservacionRepository;
import com.ejercito.transferencia.domain.model.TransferenciaArchivo;
import com.ejercito.transferencia.domain.model.TransferenciaObservacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Servicio para las observaciones de las transferencias.
 *
 * @author edisson.gonzalez@controltechcg.com
 * @version 08/22/2018 Issue #4 (SICDI-Controltech) feature-4
 */
@Service
public class TransferenciaObservacionService {
    
    /**
     * Respositorio para las observaciones de las transferencias
     */
    @Autowired
    private TransferenciaObservacionRepository transferenciaObservacionRepository;
    
    public void crearObservacon(TransferenciaArchivo transferenciaArchivo, String observacion){ //Usuario usuario(Parametro que usa el metodo)
        TransferenciaObservacion transferenciaObservacion = new TransferenciaObservacion();
        transferenciaObservacion.setTarId(transferenciaArchivo);
        transferenciaObservacion.setTraObservacion(observacion);
        transferenciaObservacion.setFecCreacion(new Date());
        //transferenciaObservacion.setUsuId(usuario);
        transferenciaObservacionRepository.save(transferenciaObservacion);
    }
    
    /**
     * Lista las observaciones dada una transferencia
     * @param transferencia transferencia
     * @return lista de observaciones
     */
    public List<TransferenciaObservacion> observacionesPorTranferencia(TransferenciaArchivo transferencia){
        return transferenciaObservacionRepository.findByTarIdOrderByFecCreacionDesc(transferencia);
    }
    
    
}
