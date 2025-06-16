
package com.ejercito.transferencia.application.impl;


import com.ejercito.transferencia.infrastructure.adaptador.TransferenciaEstadoRepository;
import com.ejercito.transferencia.domain.model.TransferenciaEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para identificar los estados de las transiciones de las transferencias.
 *
 * @author edisson.gonzalez@controltechcg.com
 * @version 08/22/2018 Issue #4 (SICDI-Controltech) feature-4
 */
@Service
public class TransferenciaEstadoService {
    
    @Autowired
    private TransferenciaEstadoRepository transferenciaEstadoRepository;
    
    /***
     * Trae un estado dado por un identificador
     * @param estadoId identificador de estado
     * @return TransferenciaEstado
     */
    public TransferenciaEstado getById(Long estadoId){
        //return transferenciaEstadoRepository.getOne(estadoId);
        //Actualizacion Spring 2
        return transferenciaEstadoRepository.getReferenceById(estadoId);
    }
}
