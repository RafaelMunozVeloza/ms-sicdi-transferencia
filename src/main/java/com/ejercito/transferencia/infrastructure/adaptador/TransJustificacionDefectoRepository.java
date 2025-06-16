
package com.ejercito.transferencia.infrastructure.adaptador;


import com.ejercito.transferencia.domain.model.TransJustificacionDefecto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransJustificacionDefectoRepository extends JpaRepository<TransJustificacionDefecto, Long> {
    
    /**
     * Lista todas las observaciones activas
     * @param sort
     * @return 
     */
    List<TransJustificacionDefecto> getByActivoTrue(Sort sort);

    public TransJustificacionDefecto findOneByTextoObservacionAndActivoTrue(String textoObservacion);

    public List<TransJustificacionDefecto> findAllByActivoTrueOrderByTextoObservacionAsc();
    /**
    * 2018-09-24 samuel.delgado@controltechcg.com Issue #174 (SICDI-Controltech)
    * feature-174: Adición para la paginación.
    */
    public Page<TransJustificacionDefecto> findAllByActivoTrue(Pageable pageable);
    
}
