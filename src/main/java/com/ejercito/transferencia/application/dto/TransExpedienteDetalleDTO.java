package com.ejercito.transferencia.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TransExpedienteDetalleDTO implements Serializable {
    private Long traExpId;
    private Date fecTransferencia;
    private int indRealizado;
    private int activo;
    private Integer nuevoDepId;
    private Integer anteriorDepId;
    private Long expId;
    private Integer tarId;
    //Validar por Refactoring de SICDI
   // private UsuarioDTO nuevoQuien;
   //private UsuarioDTO anteriorQuien;
    private String expNombre;
    private String expDepNombre;

//Validar por Refactoring de SICDI
//    public TransExpedienteDetalleDTO(TransExpedienteDetalle transExpedienteDetalle) {
//        this.traExpId = transExpedienteDetalle.getTraExpId();
//        this.fecTransferencia= transExpedienteDetalle.getFecTransferencia();
//        this.indRealizado = transExpedienteDetalle.getIndRealizado();
//        this.activo = transExpedienteDetalle.getActivo();
//        this.nuevoDepId = transExpedienteDetalle.getNuevoDepId().getId();
//        this.anteriorDepId = transExpedienteDetalle.getAnteriorDepId().getId();
//        this.expId = transExpedienteDetalle.getExpId().getExpId();
//        this.expNombre = transExpedienteDetalle.getExpId().getExpNombre();
//        this.expDepNombre = transExpedienteDetalle.getExpId().getDepId().getNombre();
//        this.tarId = transExpedienteDetalle.getTarId().getId();
//        this.nuevoQuien = new UsuarioDTO(transExpedienteDetalle.getNuevoQuien());
//        this.anteriorQuien = new UsuarioDTO(transExpedienteDetalle.getAnteriorQuien());
//    }
}
