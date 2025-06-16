
package com.ejercito.transferencia.infrastructure.adaptador;


import com.ejercito.transferencia.domain.model.TransferenciaEstado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaEstadoRepository extends JpaRepository<TransferenciaEstado, Long> {
    
}
