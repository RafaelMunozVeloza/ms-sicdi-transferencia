package com.ejercito.transferencia.application.dto;

import com.ejercito.transferencia.domain.model.TransferenciaTransicion;

import java.util.Date;

public class TransferenciaTransicionDTO {
    private Long ttraId;
    private Date fecCreacion;
    private TransferenciaArchivoDTO tarId;
    private TransferenciaEstadoDTO traEstId;
    //private UsuarioDTO usuCreacion;

    public Long getTtraId() {
        return ttraId;
    }

    public void setTtraId(Long ttraId) {
        this.ttraId = ttraId;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public TransferenciaArchivoDTO getTarId() {
        return tarId;
    }

    public void setTarId(TransferenciaArchivoDTO tarId) {
        this.tarId = tarId;
    }

   // public UsuarioDTO getUsuCreacion() {
    //    return usuCreacion;
    //}

//Validar por Refactoring de SICDI

//    public void setUsuCreacion(UsuarioDTO usuCreacion) {
//        this.usuCreacion = usuCreacion;
//    }
//
//    public TransferenciaEstadoDTO getTraEstId() {
//        return traEstId;
//    }
//
//    public void setTraEstId(TransferenciaEstadoDTO traEstId) {
//        this.traEstId = traEstId;
//    }

    static public TransferenciaTransicionDTO customMapTransferenciaTransicion(TransferenciaTransicion ttrans) {

        TransferenciaTransicionDTO transferenciaTransicionDTO = new TransferenciaTransicionDTO();
        //transferenciaTransicionDTO.ttraId = ttrans.getTtraId();

        //transferenciaTransicionDTO.fecCreacion = ttrans.getFecCreacion();
        //transferenciaTransicionDTO.traEstId = new TransferenciaEstadoDTO(ttrans.getTraEstId());

        //transferenciaTransicionDTO.usuCreacion = UsuarioDTO.customMap(ttrans.getUsuCreacion());

        return transferenciaTransicionDTO;
    }
}
