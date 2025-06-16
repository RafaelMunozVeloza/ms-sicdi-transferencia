
package com.ejercito.transferencia.application.impl;


import com.ejercito.transferencia.infrastructure.adaptador.TransferenciaTransicionRepository;
import com.ejercito.transferencia.domain.model.TransferenciaArchivo;
import com.ejercito.transferencia.domain.model.TransferenciaEstado;
import com.ejercito.transferencia.domain.model.TransferenciaTransicion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Servicio para las transiciones de las transferencias.
 *
 * @author edisson.gonzalez@controltechcg.com
 * @version 08/22/2018 Issue #4 (SICDI-Controltech) feature-4
 */
@Service
public class TransferenciaTransicionService {
    
    @Autowired
    private TransferenciaTransicionRepository transferenciaTransicionRepository;
    
    @Autowired
    private TransferenciaEstadoService transferenciaEstadoService;
    
    /***
     * Método que crea un transición en una transferecia.
     * @param transferenciaArchivo transferencia
     * @param usuario usuario acción
     * @param idTransferencia  identificador del estado con el que se va a crear
     */

    public void crearTransicion(TransferenciaArchivo transferenciaArchivo, Long idTransferencia){ //Usuario usuario (Parametro que va dentro del metodo)
        TransferenciaEstado transferenciaEstado = transferenciaEstadoService.getById(idTransferencia);
        TransferenciaTransicion transferenciaTransicion = new TransferenciaTransicion();
        transferenciaTransicion.setTarId(transferenciaArchivo);
        transferenciaTransicion.setTraEstId(transferenciaEstado);
        //transferenciaTransicion.setUsuCreacion(usuario);
        transferenciaTransicion.setFecCreacion(new Date());
        transferenciaTransicionRepository.save(transferenciaTransicion);
    }
    
    /***
     * Busca una transición dentro de una transferencia de archivo 
     * @param transferenciaEstado 
     * @return 
     */
    public List<TransferenciaTransicion> findTransferenciaTransicionRechazado(TransferenciaEstado transferenciaEstado, TransferenciaArchivo transferenciaArchivo){
        return transferenciaTransicionRepository.findOneByTraEstIdAndTarId(transferenciaEstado, transferenciaArchivo);
    }
    
    /***
     * Lista las transiciones en una transferencia 
     * @param transferenciaArchivo transferencia 
     * @return lista de transiciones
     */
    public List<TransferenciaTransicion> findTransferenciaTransiciones(TransferenciaArchivo transferenciaArchivo){
        return transferenciaTransicionRepository.findByTarIdOrderByFecCreacionDesc(transferenciaArchivo);
    }
    
    
}
