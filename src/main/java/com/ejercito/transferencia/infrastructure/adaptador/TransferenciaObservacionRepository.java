
package com.ejercito.transferencia.infrastructure.adaptador;


import com.ejercito.transferencia.domain.model.TransferenciaArchivo;
import com.ejercito.transferencia.domain.model.TransferenciaObservacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferenciaObservacionRepository extends JpaRepository<TransferenciaObservacion, Long> {
    
    /**
     * Lista las observaciones de una transferencia
     * @param tarId transferencia
     * @return lista de observaciones
     */
    public List<TransferenciaObservacion> findByTarIdOrderByFecCreacionDesc(TransferenciaArchivo tarId);
}
