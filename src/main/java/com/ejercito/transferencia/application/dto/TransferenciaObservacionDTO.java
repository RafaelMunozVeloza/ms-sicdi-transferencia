package com.ejercito.transferencia.application.dto;


import com.ejercito.transferencia.domain.model.TransferenciaObservacion;

import java.util.Date;

public class TransferenciaObservacionDTO {
    private Long traObsId;
    private Date fecCreacion;
    private String traObservacion;
    private TransferenciaArchivoDTO tarId;
    //private UsuarioDTO usuId;

    public Long getTraObsId() {
        return traObsId;
    }

    public void setTraObsId(Long traObsId) {
        this.traObsId = traObsId;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public String getTraObservacion() {
        return traObservacion;
    }

    public void setTraObservacion(String traObservacion) {
        this.traObservacion = traObservacion;
    }

    public TransferenciaArchivoDTO getTarId() {
        return tarId;
    }

    public void setTarId(TransferenciaArchivoDTO tarId) {
        this.tarId = tarId;
    }

    //Validar por Refactoring de SICDI
//    public UsuarioDTO getUsuId() {
//        return usuId;
//    }
//
////    public void setUsuId(UsuarioDTO usuId) {
//        this.usuId = usuId;
//    }

    static public TransferenciaObservacionDTO customMapTransferenciaObservacionDTO(TransferenciaObservacion transferenciaObservacion){
//        ModelMapper modelMapper = new ModelMapper();
        TransferenciaObservacionDTO transferenciaObservacionDTO = new TransferenciaObservacionDTO();
        transferenciaObservacionDTO.setTraObsId(transferenciaObservacion.getTraObsId());
        transferenciaObservacionDTO.setFecCreacion(transferenciaObservacion.getFecCreacion());
        transferenciaObservacionDTO.setTraObservacion(transferenciaObservacion.getTraObservacion());
        transferenciaObservacionDTO.setTarId(TransferenciaArchivoDTO.customMapTransferenciaArchivoDTO(transferenciaObservacion.getTarId()));
        //Validar por Refactoring de SICDI
        //transferenciaObservacionDTO.setUsuId(UsuarioDTO.customMap(transferenciaObservacion.getUsuId()));

        return transferenciaObservacionDTO;
    }
}
