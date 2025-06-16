package com.ejercito.transferencia.application.dto;

import com.ejercito.transferencia.domain.model.TransJustificacionDefecto;
import lombok.Data;

import java.util.Date;

@Data
public class TransJustificacionDefectoDTO {
    Integer tjdId;
    String textoObservacion;
    Boolean activo;
    Date cuando;
    String quien;

    public TransJustificacionDefectoDTO(Integer tjdId,
                                        String textoObservacion,
                                        Boolean activo,
                                        Date cuando,
                                        String quien) {
        this.tjdId = tjdId;
        this.textoObservacion = textoObservacion;
        this.activo = activo;
        this.cuando = cuando;
        this.quien = quien;
    }

    static public TransJustificacionDefectoDTO getDTO(TransJustificacionDefecto transJustificacionDefecto) {

//Validar por Refactoring de SICDI
//        return new TransJustificacionDefectoDTO(transJustificacionDefecto.getTjdId().intValue(), transJustificacionDefecto.getTextoObservacion(), transJustificacionDefecto.getActivo(),
//                transJustificacionDefecto.getCuando(), transJustificacionDefecto.getQuien().getNombre());

        return null;
    }
}
