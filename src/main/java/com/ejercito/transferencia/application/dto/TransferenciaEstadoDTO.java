package com.ejercito.transferencia.application.dto;

import com.ejercito.transferencia.domain.model.TransferenciaEstado;
import lombok.Data;

import java.io.Serializable;

@Data
public class TransferenciaEstadoDTO implements Serializable {
    private Long traEstId;
    private String traEstNombre;

    public TransferenciaEstadoDTO(TransferenciaEstado transferenciaEstado) {
        this.traEstId = transferenciaEstado.getTraEstId();
        this.traEstNombre = transferenciaEstado.getTraEstNombre();
    }
}
