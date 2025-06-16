package com.ejercito.transferencia.application.dto;



import com.ejercito.transferencia.domain.model.TransferenciaArchivoDetalle;

import java.util.Date;

public class TransferenciaArchivoDetalleDTO {
    private Integer id;
    private TransferenciaArchivoDTO transferenciaArchivo;
    //private DocumentoDependenciaDTO documentoDependencia;
    private String documentoID;
    //private DependenciaDTO anteriorDependencia;
    //private UsuarioDTO anteriorUsuario;
    private Date anteriorFechaAsignacion;
    //private DependenciaDTO nuevoDependencia;
    //private UsuarioDTO nuevoUsuario;
    private Date nuevoFechaAsignacion;
    private int indRealizado;
    private int activo;

    public TransferenciaArchivoDetalleDTO() {
    }

    public TransferenciaArchivoDetalleDTO(Integer id, TransferenciaArchivoDTO transferenciaArchivo,
                                          String documentoID,  Date anteriorFechaAsignacion, Date nuevoFechaAsignacion, int indRealizado, int activo)
    /** DocumentoDependenciaDTO documentoDependencia, DependenciaDTO anteriorDependencia, UsuarioDTO anteriorUsuario,
     DependenciaDTO nuevoDependencia, UsuarioDTO nuevoUsuario PARAMETROS QUE USA EL METODO**/
    {
        this.id = id;
        this.transferenciaArchivo = transferenciaArchivo;
        //this.documentoDependencia = documentoDependencia;
        this.documentoID = documentoID;
        //this.anteriorDependencia = anteriorDependencia;
       // this.anteriorUsuario = anteriorUsuario;
        this.anteriorFechaAsignacion = anteriorFechaAsignacion;
        //this.nuevoDependencia = nuevoDependencia;
        //this.nuevoUsuario = nuevoUsuario;
        this.nuevoFechaAsignacion = nuevoFechaAsignacion;
        this.indRealizado = indRealizado;
        this.activo = activo;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TransferenciaArchivoDTO getTransferenciaArchivo() {
        return transferenciaArchivo;
    }

    public void setTransferenciaArchivo(TransferenciaArchivoDTO transferenciaArchivo) {
        this.transferenciaArchivo = transferenciaArchivo;
    }

//    public DocumentoDependenciaDTO getDocumentoDependencia() {
//        return documentoDependencia;
//    }

//    public void setDocumentoDependencia(DocumentoDependenciaDTO documentoDependencia) {
//        this.documentoDependencia = documentoDependencia;
//    }

    public String getDocumentoID() {
        return documentoID;
    }

    public void setDocumentoID(String documentoID) {
        this.documentoID = documentoID;
    }

//    public DependenciaDTO getAnteriorDependencia() {
//        return anteriorDependencia;
//    }
//
//    public void setAnteriorDependencia(DependenciaDTO anteriorDependencia) {
//        this.anteriorDependencia = anteriorDependencia;
//    }
//
//    public UsuarioDTO getAnteriorUsuario() {
//        return anteriorUsuario;
//    }
//
//    public void setAnteriorUsuario(UsuarioDTO anteriorUsuario) {
//        this.anteriorUsuario = anteriorUsuario;
//    }

    public Date getAnteriorFechaAsignacion() {
        return anteriorFechaAsignacion;
    }

    public void setAnteriorFechaAsignacion(Date anteriorFechaAsignacion) {
        this.anteriorFechaAsignacion = anteriorFechaAsignacion;
    }

//    public DependenciaDTO getNuevoDependencia() {
//        return nuevoDependencia;
//    }
//
//    public void setNuevoDependencia(DependenciaDTO nuevoDependencia) {
//        this.nuevoDependencia = nuevoDependencia;
//    }
//
//    public UsuarioDTO getNuevoUsuario() {
//        return nuevoUsuario;
//    }
//
//    public void setNuevoUsuario(UsuarioDTO nuevoUsuario) {
//        this.nuevoUsuario = nuevoUsuario;
//    }

    public Date getNuevoFechaAsignacion() {
        return nuevoFechaAsignacion;
    }

    public void setNuevoFechaAsignacion(Date nuevoFechaAsignacion) {
        this.nuevoFechaAsignacion = nuevoFechaAsignacion;
    }

    public int getIndRealizado() {
        return indRealizado;
    }

    public void setIndRealizado(int indRealizado) {
        this.indRealizado = indRealizado;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    static public TransferenciaArchivoDetalleDTO customMapTransferenciaArchivoDetalle(TransferenciaArchivoDetalle tad) {
        //Validar por Refactoring de SICDI
        TransferenciaArchivoDetalleDTO transferenciaArchivoDetalleDTO = new TransferenciaArchivoDetalleDTO();
        transferenciaArchivoDetalleDTO.id = tad.getId();
        //transferenciaArchivoDetalleDTO.documentoDependencia = DocumentoDependenciaDTO.customMap(tad.getDocumentoDependencia());
        transferenciaArchivoDetalleDTO.documentoID = tad.getDocumentoID();
        //transferenciaArchivoDetalleDTO.anteriorDependencia = DependenciaDTO.getDTO(tad.getAnteriorDependencia());
        //transferenciaArchivoDetalleDTO.anteriorUsuario = UsuarioDTO.customMap(tad.getAnteriorUsuario());
        transferenciaArchivoDetalleDTO.anteriorFechaAsignacion = tad.getAnteriorFechaAsignacion();
        //transferenciaArchivoDetalleDTO.nuevoUsuario = UsuarioDTO.customMap(tad.getNuevoUsuario());
        transferenciaArchivoDetalleDTO.nuevoFechaAsignacion = tad.getNuevoFechaAsignacion();
        transferenciaArchivoDetalleDTO.indRealizado = tad.getIndRealizado();
        transferenciaArchivoDetalleDTO.activo = tad.getActivo();

        return transferenciaArchivoDetalleDTO;
    }
}
