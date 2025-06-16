package com.ejercito.transferencia.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Entidad detalle de transferencia de archivo.
 *
 * @author jgarcia@controltechcg.com
 * @since Ago 25, 2017
 * @version 1.0.0 (feature-120).
 */
@Data
@Entity
@Table(name = "TRANSFERENCIA_ARCHIVO_DETALLE")
@SuppressWarnings("PersistenceUnitPresent")
public class TransferenciaArchivoDetalle implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -8298741164687466772L;

    /**
     * ID.
     */
    @Id
    @GenericGenerator(name = "TRANSFERENCIA_ARCHIVO_DET_SEQ", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "TRANSFERENCIA_ARCHIVO_DET_SEQ")
                ,@Parameter(name = "allocationSize", value = "1")
            })
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "TRANSFERENCIA_ARCHIVO_DET_SEQ")
    @Column(name = "TAD_ID")
    private Integer id;

    /**
     * Transferencia de archivo.
     */
    @ManyToOne
    @JoinColumn(name = "TAR_ID")
    private TransferenciaArchivo transferenciaArchivo;

    /**
     * Documento dependencia.
     */
    //Validar por Refactoring de SICD
//    @ManyToOne
//    @JoinColumn(name = "DCDP_ID")
//    private DocumentoDependencia documentoDependencia;

    /**
     * ID del documento.
     */

    @Column(name = "DOC_ID")
    private String documentoID;

    /**
     * Anterior dependencia.
     */
    //Validar por Refactoring de SICD
//    @ManyToOne
//    @JoinColumn(name = "ANTERIOR_DEP_ID")
//    private Dependencia anteriorDependencia;

    /**
     * Anterior usuario.
     */
    //Validar por Refactoring de SICD
//    @ManyToOne
//    @JoinColumn(name = "ANTERIOR_QUIEN")
//    private Usuario anteriorUsuario;

    /**
     * Anterior fecha de asignación.
     */
    @Column(name = "ANTERIOR_CUANDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date anteriorFechaAsignacion;

    /**
     * Nueva dependencia.
     */
    //Validar por Refactoring de SICD
//    @ManyToOne
//    @JoinColumn(name = "NUEVO_DEP_ID")
//    private Dependencia nuevoDependencia;

    /**
     * Nuevo usuario.
     */
    //Validar por Refactoring de SICD
//    @ManyToOne
//    @JoinColumn(name = "NUEVO_QUIEN")
//    private Usuario nuevoUsuario;

    /**
     * Nueva fecha de asignación.
     */
    @Column(name = "NUEVO_CUANDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nuevoFechaAsignacion;
    
    /**
     * 2018-08-23 edison.gonzalez@controltechcg.com Issue #4 (SIGDI-Controltech)
     * Verifica si el documento fue transferido
     */
    @Column(name = "IND_REALIZADO")
    private int indRealizado;
    
    /**
     * 2018-08-23 edison.gonzalez@controltechcg.com Issue #4 (SIGDI-Controltech)
     * Verifica si el documento se encuentra disponible para transferir
     */
    @Column(name = "ACTIVO")
    private int activo;

    /**
     * Constructor vacío.
     */
    public TransferenciaArchivoDetalle() {
    }

    /**
     * Constructor.
     *
     * @param transferenciaArchivo Transferencia de archivo.
     * //@param documentoDependencia Documento dependencia.
     * @param documentoID ID del documento.
     * //@param anteriorDependencia Anterior dependencia.
     * //@param anteriorUsuario Anterior usuario.
     * //@param anteriorFechaAsignacion Anterior fecha de asignación.
     * //@param nuevoDependencia Nueva dependencia.
     * //@param nuevoUsuario Nuevo usuario.
     * @param nuevoFechaAsignacion Nueva fecha de asignación.
     */
    public TransferenciaArchivoDetalle(TransferenciaArchivo transferenciaArchivo,
                                       String documentoID, Date anteriorFechaAsignacion, Date nuevoFechaAsignacion) /**  DocumentoDependencia documentoDependencia,
                                            Dependencia anteriorDependencia, Usuario anteriorUsuario, Dependencia nuevoDependencia, Usuario nuevoUsuario
                                            (Parametros que utiliza el metodo)**/ {
        this.transferenciaArchivo = transferenciaArchivo;
        //this.documentoDependencia = documentoDependencia;
        this.documentoID = documentoID;
        //this.anteriorDependencia = anteriorDependencia;
        //this.anteriorUsuario = anteriorUsuario;
        this.anteriorFechaAsignacion = new Date(anteriorFechaAsignacion.getTime());
        //this.nuevoDependencia = nuevoDependencia;
        //this.nuevoUsuario = nuevoUsuario;
        this.nuevoFechaAsignacion = new Date(nuevoFechaAsignacion.getTime());
    }

    /**
     * Establece la transferencia de archivo.
     *
     * @param transferenciaArchivo Transferencia de archivo.
     */
    public void setTransferenciaArchivo(TransferenciaArchivo transferenciaArchivo) {
        this.transferenciaArchivo = transferenciaArchivo;
    }

    /**
     * Obtiene el documento dependencia.
     *
     * @return Documento dependencia.
     */
    //Validar por Refactoring de SICD
//    public DocumentoDependencia getDocumentoDependencia() {
//        return documentoDependencia;
//    }

    /**
     * Establece el documento dependencia.
     *
     * @param documentoDependencia Documento dependencia.
     */
    //Validar por Refactoring de SICD
//    public void setDocumentoDependencia(DocumentoDependencia documentoDependencia) {
//        this.documentoDependencia = documentoDependencia;
//    }

    /**
     * Obtiene el ID del documento.
     *
     * @return ID del documento.
     */
    public String getDocumentoID() {
        return documentoID;
    }

    /**
     * Establece el ID del documento.
     *
     * @param documentoID ID del documento.
     */
    public void setDocumentoID(String documentoID) {
        this.documentoID = documentoID;
    }

    /**
     * Obtiene la anterior dependencia.
     *
     * @return Anterior dependencia.
     */
    //Validar por Refactoring de SICD
//    public Dependencia getAnteriorDependencia() {
//        return anteriorDependencia;
//    }

    /**
     * Establece la anterior dependencia.
     *
     * @param anteriorDependencia Anterior dependencia.
     */
    //Validar por Refactoring de SICD
//    public void setAnteriorDependencia(Dependencia anteriorDependencia) {
//        this.anteriorDependencia = anteriorDependencia;
//    }

    /**
     * Obtiene el anterior usuario.
     *
     * @return Anterior usuario.
     */
    //Validar por Refactoring de SICD
//    public Usuario getAnteriorUsuario() {
//        return anteriorUsuario;
//    }

    /**
     * Establece el anterior usuario.
     *
     * @param anteriorUsuario Anterior usuario.
     */
    //Validar por Refactoring de SICD
//    public void setAnteriorUsuario(Usuario anteriorUsuario) {
//        this.anteriorUsuario = anteriorUsuario;
//    }

    /**
     * Obtiene la anterior fecha de asignación.
     *
     * @return Fecha de asignación.
     */
    public Date getAnteriorFechaAsignacion() {
        return new Date(anteriorFechaAsignacion.getTime());
    }

    /**
     * Establece la anterior fecha de asignación.
     *
     * @param anteriorFechaAsignacion Fecha de asignación.
     */
    public void setAnteriorFechaAsignacion(Date anteriorFechaAsignacion) {
        this.anteriorFechaAsignacion = new Date(anteriorFechaAsignacion.getTime());
    }

    /**
     * Obtiene la nueva dependencia.
     *
     * @return Nueva dependencia.
     */
    //Validar por Refactoring de SICD
//    public Dependencia getNuevoDependencia() {
//        return nuevoDependencia;
//    }

    /**
     * Establece la nueva dependencia.
     *
     * @param nuevoDependencia Nueva dependencia.
     */
    //Validar por Refactoring de SICD
//    public void setNuevoDependencia(Dependencia nuevoDependencia) {
//        this.nuevoDependencia = nuevoDependencia;
//    }

    /**
     * Obtiene el nuevo usuario.
     *
     * @return Nuevo usuario.
     */
    //Validar por Refactoring de SICD
//    public Usuario getNuevoUsuario() {
//        return nuevoUsuario;
//    }

    /**
     * Establece el nuevo usuario.
     *
     * @param nuevoUsuario Nuevo usuario.
     */
    //Validar por Refactoring de SICD
//    public void setNuevoUsuario(Usuario nuevoUsuario) {
//        this.nuevoUsuario = nuevoUsuario;
//    }

    /**
     * Obtiene la nueva fecha de asignación.
     *
     * @return Fecha de asignación.
     */
    public Date getNuevoFechaAsignacion() {
        return new Date(nuevoFechaAsignacion.getTime());
    }

    /**
     * Establece la nueva fecha de asignación.
     *
     * @param nuevoFechaAsignacion Fecha de asignación.
     */
    public void setNuevoFechaAsignacion(Date nuevoFechaAsignacion) {
        this.nuevoFechaAsignacion = new Date(nuevoFechaAsignacion.getTime());
    }

    /**
     * Obtiene el indicador si se realizo la transferencia del documento
     * @return Indicador
     */
    public int getIndRealizado() {
        return indRealizado;
    }

    /**
     * Establece si se realizo la transferencia del documento
     * @param indRealizado 
     */
    public void setIndRealizado(int indRealizado) {
        this.indRealizado = indRealizado;
    }

    /**
     * Obtiene el indicador si el documento se encuentra disponible para la
     * transferencia
     * @return Indicador
     */
    public int getActivo() {
        return activo;
    }

    /**
     * Establece el indicador si el documento se encuentra disponible para la
     * transferencia
     * @param activo 
     */
    public void setActivo(int activo) {
        this.activo = activo;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TransferenciaArchivoDetalle other = (TransferenciaArchivoDetalle) obj;
        if (this.indRealizado != other.indRealizado) {
            return false;
        }
        if (this.activo != other.activo) {
            return false;
        }
        if (!Objects.equals(this.documentoID, other.documentoID)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.transferenciaArchivo, other.transferenciaArchivo)) {
            return false;
        }
        //Validar por Refactoring de SICD
//        if (!Objects.equals(this.documentoDependencia, other.documentoDependencia)) {
//            return false;
//        }
//        if (!Objects.equals(this.anteriorDependencia, other.anteriorDependencia)) {
//            return false;
//        }
//        if (!Objects.equals(this.anteriorUsuario, other.anteriorUsuario)) {
//            return false;
//        }
        if (!Objects.equals(this.anteriorFechaAsignacion, other.anteriorFechaAsignacion)) {
            return false;
        }
        //Validar por Refactoring de SICD
//        if (!Objects.equals(this.nuevoDependencia, other.nuevoDependencia)) {
//            return false;
//        }
//        if (!Objects.equals(this.nuevoUsuario, other.nuevoUsuario)) {
//            return false;
//        }
        if (!Objects.equals(this.nuevoFechaAsignacion, other.nuevoFechaAsignacion)) {
            return false;
        }
        return true;
    }
}