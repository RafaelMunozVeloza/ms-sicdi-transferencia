
package com.ejercito.transferencia.application.dto;

import com.ejercito.transferencia.domain.model.TransferenciaArchivo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author egonzalezm
 * @version 08/31/2018
 * @since 1.8
 */
@Data
public class TransferenciaArchivoDTO implements Serializable {

    private static final long serialVersionUID = -9074865635471580820L;

    private Integer tarId;
    private Boolean activo;
    private String estado;
    private Date fechaCreacion;
    private Integer usuIdOrigen;
    private String usuNomOrigen;
    private Integer carIdOrigen;
    private String carNomOrigen;
    private Integer usuIdDestino;
    private String usuNomDestino;
    private Integer carIdDestino;
    private String carNomDestino;
    private Integer depId;
    private Integer usuIdJefe;
    private String justificacion;
    private Integer usuarioAsignado;
    private Boolean indAprobado;
    private Integer numDocumentos;
    private Integer numExpedientes;
    private String ultEstado;
    private Boolean esUsuarioOrigen;
    private Boolean esJefe;
    private Boolean esUsuarioDestino;
    private String usuAsignado;
    //Validar por Refactoring de SICDI
    //private UsuarioDTO origenUsuario;
    //private DependenciaDTO origenDependencia;
    //private UsuarioDTO destinoUsuario;
    //private DependenciaDTO destinoDependencia;
    private String fuid;
    //private DocumentoDTO docId;

    static public TransferenciaArchivoDTO customMapTransferenciaArchivoDTO(TransferenciaArchivo transferenciaArchivo) {
        TransferenciaArchivoDTO transferenciaArchivoDTO = new TransferenciaArchivoDTO();
        transferenciaArchivoDTO.setTarId(transferenciaArchivo.getId() != null ? transferenciaArchivo.getId() : null);
        transferenciaArchivoDTO.setActivo(transferenciaArchivo.getActivo() != null ? transferenciaArchivo.getActivo() : false);
        transferenciaArchivoDTO.setEstado(transferenciaArchivo.getEstado() != null ? transferenciaArchivo.getEstado() : null);
        transferenciaArchivoDTO.setFechaCreacion(transferenciaArchivo.getFechaCreacion() != null ? transferenciaArchivo.getFechaCreacion() : null);
        //Validar por Refactoring de SICDI
//        transferenciaArchivoDTO.setUsuIdOrigen(transferenciaArchivo.getOrigenUsuario().getId() != null ? transferenciaArchivo.getOrigenUsuario().getId() : null);
//        transferenciaArchivoDTO.setUsuNomOrigen(transferenciaArchivo.getOrigenUsuario().getNombre() != null ? transferenciaArchivo.getOrigenUsuario().getNombre() : "");
//        transferenciaArchivoDTO.setCarIdOrigen(transferenciaArchivo.getUsuOrigenCargo().getId() != null ? transferenciaArchivo.getUsuOrigenCargo().getId() : null);
//        transferenciaArchivoDTO.setCarNomOrigen(transferenciaArchivo.getUsuOrigenCargo().getCarNombre() != null ? transferenciaArchivo.getUsuOrigenCargo().getCarNombre() : "");
//        transferenciaArchivoDTO.setUsuIdDestino(transferenciaArchivo.getDestinoUsuario().getId() != null ? transferenciaArchivo.getDestinoUsuario().getId() : null);
//        transferenciaArchivoDTO.setUsuNomDestino(transferenciaArchivo.getDestinoUsuario().getNombre() != null ? transferenciaArchivo.getDestinoUsuario().getNombre() : "");
//        transferenciaArchivoDTO.setDepId(transferenciaArchivo.getOrigenDependencia().getId() != null ? transferenciaArchivo.getOrigenDependencia().getId() : null);
//        transferenciaArchivoDTO.setUsuIdJefe(transferenciaArchivo.getOrigenDependencia().getJefe() != null ? transferenciaArchivo.getOrigenDependencia().getJefe().getId() : null);
        transferenciaArchivoDTO.setJustificacion(transferenciaArchivo.getJustificacion() != null ? transferenciaArchivo.getJustificacion() : "");
        transferenciaArchivoDTO.setUsuarioAsignado(transferenciaArchivo.getUsuarioAsignado());
        transferenciaArchivoDTO.setIndAprobado(transferenciaArchivo.getIndAprobado() == 1);
        transferenciaArchivoDTO.setNumDocumentos(transferenciaArchivo.getNumeroDocumentos() != null ? transferenciaArchivo.getNumeroDocumentos() : null);
        //Validar por Refactoring de SICDI
//        transferenciaArchivoDTO.setOrigenUsuario(UsuarioDTO.customMap(transferenciaArchivo.getOrigenUsuario()));
//        transferenciaArchivoDTO.setOrigenDependencia(DependenciaDTO.getLightDTO(transferenciaArchivo.getOrigenDependencia()));
//        transferenciaArchivoDTO.setDestinoUsuario(UsuarioDTO.customMap(transferenciaArchivo.getDestinoUsuario()));
//        transferenciaArchivoDTO.setDestinoDependencia(DependenciaDTO.getLightDTO(transferenciaArchivo.getDestinoDependencia()));
//        transferenciaArchivoDTO.setFuid(transferenciaArchivo.getFuid());
//        if (transferenciaArchivo.getDocId() != null) {
//            transferenciaArchivoDTO.setDocId(DocumentoDTO.customMapTransferenciaDocumentos(transferenciaArchivo.getDocId()));
//        }

        return transferenciaArchivoDTO;
    }
}