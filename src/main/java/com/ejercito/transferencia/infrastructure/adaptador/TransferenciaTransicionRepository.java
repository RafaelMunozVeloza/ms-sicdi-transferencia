
package com.ejercito.transferencia.infrastructure.adaptador;


import com.ejercito.transferencia.domain.model.TransferenciaArchivo;
import com.ejercito.transferencia.domain.model.TransferenciaEstado;
import com.ejercito.transferencia.domain.model.TransferenciaTransicion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferenciaTransicionRepository extends JpaRepository<TransferenciaTransicion, Long> {
    
    public List<TransferenciaTransicion> findOneByTraEstIdAndTarId(TransferenciaEstado transferenciaEstado, TransferenciaArchivo transferenciaArchivo);
    
    public List<TransferenciaTransicion> findByTarIdOrderByFecCreacionDesc(TransferenciaArchivo transferenciaArchivo);
}
