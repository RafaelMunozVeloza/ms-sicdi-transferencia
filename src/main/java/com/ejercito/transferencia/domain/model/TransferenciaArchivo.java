package com.ejercito.transferencia.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Entidad maestra de transferencia de archivo.
 *
 * @author jgarcia@controltechcg.com
 * @since Ago 25, 2017
 * @version 1.0.0 (feature-120).
 */
@Data
@Entity
@Table(name = "TRANSFERENCIA_ARCHIVO")
@SuppressWarnings("PersistenceUnitPresent")
public class TransferenciaArchivo implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 3630553950712061442L;

    /**
     * Tipo de transferencia total.
     */
    public static final String TOTAL_TIPO = "T";

    /**
     * Tipo de transferencia parcial.
     */
    public static final String PARCIAL_TIPO = "P";

    /**
     * Estado de transferencia creada.
     */
    public static final String CREADA_ESTADO = "I";
    
    /**
     * Estado de transferencia anulada.
     */
    public static final String ANULADA_ESTADO = "C";

    /**
     * Estado de transferencia aprobada.
     */
    public static final String APROBADA_ESTADO = "A";

    /**
     * Estado de transferencia rechazada.
     */
    public static final String RECHAZADA_ESTADO = "R";

    /**
     * ID.
     */
    @Id
    @GenericGenerator(name = "TRANSFERENCIA_ARCHIVO_SEQ", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "TRANSFERENCIA_ARCHIVO_SEQ")
                ,@Parameter(name = "allocationSize", value = "1")
            })
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "TRANSFERENCIA_ARCHIVO_SEQ")
    @Column(name = "TAR_ID")
    private Integer id;

    /**
     * Bandera de activo.
     */
    @Column(name = "ACTIVO")
    private Boolean activo;

    /**
     * Tipo de transferencia.
     */
    @Column(name = "TIPO_TRANSFERENCIA")
    private String tipo;

    /**
     * Estado.
     */
    @Column(name = "ESTADO")
    private String estado;

    /**
     * Usuario creador.
     */
    //Validar por Refactoring de SICDI
    //    @ManyToOne
    //    @JoinColumn(name = "CREADOR_USU_ID")
    //    private Usuario creadorUsuario;

    /**
     * Dependencia del usuario creador.
     */
    //Validar por Refactoring de SICDI
    //    @ManyToOne
    //    @JoinColumn(name = "CREADOR_DEP_ID")
    //    private Dependencia creadorDependencia;

    /**
     * Grado del usuario creador.
     */
    //Validar por Refactoring de SICDI
    //    @ManyToOne
    //    @JoinColumn(name = "CREADOR_GRA_ID")
    //    private Grados creadorGrado;


    /**
     * Fecha de creación.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;

    /**
     * Usuario origen.
     */
    //Validar por Refactoring de SICDI
    //    @ManyToOne
    //    @JoinColumn(name = "ORIGEN_USU_ID")
    //    private Usuario origenUsuario;

    /**
     * Dependencia del usuario origen.
     */
    //Validar por Refactoring de SICDI
    //    @ManyToOne
    //    @JoinColumn(name = "ORIGEN_DEP_ID")
    //    private Dependencia origenDependencia;

    /**
     * Clasificación del usuario origen.
     */
    //Validar por Refactoring de SICDI
    //    @ManyToOne
    //    @JoinColumn(name = "ORIGEN_CLA_ID")
    //    private Clasificacion origenClasificacion;

    /**
     * Grado del usuario origen.
     */
    //Validar por Refactoring de SICDI
    //    @ManyToOne
    //    @JoinColumn(name = "ORIGEN_GRA_ID")
    //    private Grados origenGrado;

    /**
     * Usuario destino.
     */
    //Validar por Refactoring de SICDI
    //    @ManyToOne
    //    @JoinColumn(name = "DESTINO_USU_ID")
    //    private Usuario destinoUsuario;

    /**
     * Dependencia del usuario destino.
     */
    //Validar por Refactoring de SICDI
    //    @ManyToOne
    //    @JoinColumn(name = "DESTINO_DEP_ID")
    //    private Dependencia destinoDependencia;

    /**
     * Clasificación del usuario destino.
     */
    //Validar por Refactoring de SICDI
    //    @ManyToOne
    //    @JoinColumn(name = "DESTINO_CLA_ID")
    //    private Clasificacion destinoClasificacion;

    /**
     * Grado del usuario destino.
     */
    //Validar por Refactoring de SICDI
    //    @ManyToOne
    //    @JoinColumn(name = "DESTINO_GRA_ID")
    //    private Grados destinoGrado;

    /**
     * Número de documentos.
     */
    @Column(name = "NUM_DOCUMENTOS")
    private Integer numeroDocumentos;

    /**
     * Fecha de aprobación.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_APROBACION")
    private Date fechaAprobacion;

    /**
     * Código OFS del acta.
     */
    @Column(name = "ACTA_OFS")
    private String actaOFS;

    /**
     * ID de la transferencia anterior a transferir de nuevo mediante
     * transferencia tipo {@link #PARCIAL_TIPO}.
     */
    @Column(name = "TAR_ANTERIOR")
    private Integer transferenciaAnteriorID;

    /**
     * Clasificación de origen de la transferencia anterior.
     */
    //Validar por Refactoring de SICDI
    //    @ManyToOne
    //    @JoinColumn(name = "TAR_ORIGEN_CLA_ID")
    //    private Clasificacion transferenciaAnteriorOrigenClasificacion;

    /**
     * Número de radicado.
     */
    @Column(name = "NUM_RADICADO")
    private String numeroRadicado;
    
    // 2018-03-12 edison.gonzalez@controltechcg.com Issue #151 (SIGDI-Controltech):
    // Se añade las columnas de los cargos de los usuarios de origen, creador y destino.
    /**
     * Cargo del usuario destino.
     */
    //Validar por Refactoring de SICDI
    //    @JoinColumn(name = "USU_DESTINO_CARGO", referencedColumnName = "CAR_ID")
    //    @ManyToOne
    //    private Cargo usuDestinoCargo;
    /**
     * Cargo del usuario origen.
     */
    //Validar por Refactoring de SICDI
    //    @JoinColumn(name = "USU_ORIGEN_CARGO", referencedColumnName = "CAR_ID")
    //    @ManyToOne
    //    private Cargo usuOrigenCargo;
    /**
     * Cargo del usuario creador.
     */
    //Validar por Refactoring de SICDI
    //    @JoinColumn(name = "USU_CREADOR_CARGO", referencedColumnName = "CAR_ID")
    //    @ManyToOne
    //    private Cargo usuCreadorCargo;

    /**
     * 2018-08-23 edison.gonzalez@controltechcg.com Issue #4 (SIGDI-Controltech)
     * Justificación de la transferencia
     */
    @Column(name = "JUSTIFICACION")
    private String justificacion;
    
    /**
     * 2018-08-23 edison.gonzalez@controltechcg.com Issue #4 (SIGDI-Controltech)
     * Usuario asignado en la que se encuentra la transferencia
     */
    @Column(name = "USUARIO_ASIGNADO")
    private int usuarioAsignado;
    
    /**
     * 2018-08-23 edison.gonzalez@controltechcg.com Issue #4 (SIGDI-Controltech)
     * Indica si la transferencia fue aprobada
     */
    @Column(name = "IND_APROBADO")
    private int indAprobado;
    
    /**
     * 2018-08-23 edison.gonzalez@controltechcg.com Issue #4 (SIGDI-Controltech)
     * Acta asociada a la transferencia del archivo
     */
    //Validar por Refactoring de SICDI
    //    @JoinColumn(name = "DOC_ID", referencedColumnName = "DOC_ID")
    //    @ManyToOne
    //    private Documento docId;
    
    /**
     * 2018-09-17 edison.gonzalez@controltechcg.com Issue #4 (SIGDI-Controltech)
     * Codigo OFS del fuid
     */
    @Column(name = "FUID")
    private String fuid;

    /**
     * Constructor vacío.
     */
    public TransferenciaArchivo() {
    }

    /**
     * Constructor. Crea el registro como activo y con estado
     * {@link #CREADA_ESTADO}. El registro inicial queda con 0 documentos, en
     * espera que la continuación del proceso de creación establezca el valor
     * correcto.
     *
     * @param tipo Tipo de transferencia.
     * @param creadorUsuario Usuario creador.
     * @param creadorDependencia Dependencia del usuario creador.
     * @param creadorGrado Grado del usuario creador.
     * @param usuCreadorCargo Cargo del usuario creador.
     * @param fechaCreacion Fecha de creación.
     * @param origenUsuario Usuario origen.
     * @param origenDependencia Depependencia del usuario origen.
     * @param origenClasificacion Clasificación del usuario origen.
     * @param origenGrado Grado del usuario origen.
     * @param usuOrigenCargo Cargo del usuario origen.
     * @param destinoUsuario Usuario destino.
     * @param destinoDependencia Dependencia del usuario destino.
     * @param destinoClasificacion Clasificación del usuario destino.
     * @param destinoGrado Grado del usuario destino.
     * @param usuDestinoCargo Cargo del usuario destino.
     * @param transferenciaAnteriorID ID de la transferencia anterior.
     * @param transferenciaAnteriorOrigenClasificacion Clasificación de origen
     * de la transferencia anterior.
     */

    //Validar por Refactoring de SICDI
    //    public TransferenciaArchivo(String tipo, Usuario creadorUsuario,
    //                                Dependencia creadorDependencia, Grados creadorGrado,
    //                                Cargo usuCreadorCargo, Date fechaCreacion, Usuario origenUsuario,
    //                                Dependencia origenDependencia, Clasificacion origenClasificacion,
    //                                Grados origenGrado, Cargo usuOrigenCargo, Usuario destinoUsuario,
    //                                Dependencia destinoDependencia, Clasificacion destinoClasificacion,
    //                                Grados destinoGrado, Cargo usuDestinoCargo,
    //                                Integer transferenciaAnteriorID,
    //                                Clasificacion transferenciaAnteriorOrigenClasificacion) {
    //        this.tipo = tipo;
    //        this.creadorUsuario = creadorUsuario;
    //        this.creadorDependencia = creadorDependencia;
    //        this.creadorGrado = creadorGrado;
    //        this.usuCreadorCargo = usuCreadorCargo;
    //        this.fechaCreacion = new Date(fechaCreacion.getTime());
    //        this.origenUsuario = origenUsuario;
    //        this.origenDependencia = origenDependencia;
    //        this.origenClasificacion = origenClasificacion;
    //        this.origenGrado = origenGrado;
    //        this.usuOrigenCargo = usuOrigenCargo;
    //        this.destinoUsuario = destinoUsuario;
    //        this.destinoDependencia = destinoDependencia;
    //        this.destinoClasificacion = destinoClasificacion;
    //        this.destinoGrado = destinoGrado;
    //        this.usuDestinoCargo = usuDestinoCargo;
    //        this.transferenciaAnteriorID = transferenciaAnteriorID;
    //        this.transferenciaAnteriorOrigenClasificacion
    //                = transferenciaAnteriorOrigenClasificacion;
    //
    //        activo = true;
    //        estado = CREADA_ESTADO;
    //        numeroDocumentos = 0;
    //    }


    /**
     * Obtiene el usuario creador.
     *
     * @return Usuario.
     */
    //Validar por Refactoring de SICDI
    //    public Usuario getCreadorUsuario() {
    //        return creadorUsuario;
    //    }

    /**
     * Establece el usuario creador.
     *
     * @param creadorUsuario Usuario.
     */
    //Validar por Refactoring de SICDI
    //    public void setCreadorUsuario(Usuario creadorUsuario) {
    //        this.creadorUsuario = creadorUsuario;
    //    }

    /**
     * Obtiene la dependencia del usuario creador.
     *
     * @return Dependencia.
     */

    //Validar por Refactoring de SICDI
    //    public Dependencia getCreadorDependencia() {
    //        return creadorDependencia;
    //    }

    /**
     * Establece la dependencia del usuario creador.
     *
     * @param creadorDependencia Dependencia.
     */
    //Validar por Refactoring de SICDI
    //    public void setCreadorDependencia(Dependencia creadorDependencia) {
    //        this.creadorDependencia = creadorDependencia;
    //    }

    /**
     * Obtiene el grado del usuario creador.
     *
     * @return Grado.
     */
    //Validar por Refactoring de SICDI
    //    public Grados getCreadorGrado() {
    //        return creadorGrado;
    //    }

    /**
     * Establece el grado del usuario creador.
     *
     * @param creadorGrado Grado.
     */
    //Validar por Refactoring de SICDI
    //    public void setCreadorGrado(Grados creadorGrado) {
    //        this.creadorGrado = creadorGrado;
    //    }

    /**
     * Obtiene la fecha de creación.
     *
     * @return Fecha.
     */
    public Date getFechaCreacion() {
        return new Date(fechaCreacion.getTime());
    }

    /**
     * Establece la fecha de creación.
     *
     * @param fechaCreacion Fecha.
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = new Date(fechaCreacion.getTime());
    }

    /**
     * Obtiene el usuario origen.
     *
     * @return Usuario.
     */
    //Validar por Refactoring de SICDI
    //    public Usuario getOrigenUsuario() {
    //        return origenUsuario;
    //    }
    //
    /**
     * Establece el usuario origen.
     *
     * @param origenUsuario Usuario.
     */
    //Validar por Refactoring de SICDI
    //    public void setOrigenUsuario(Usuario origenUsuario) {
    //        this.origenUsuario = origenUsuario;
    //    }

    /**
     * Obtiene la dependencia del usuario origen.
     *
     * @return Dependencia.
     */
    //Validar por Refactoring de SICDI
    //    public Dependencia getOrigenDependencia() {
    //        return origenDependencia;
    //    }

    /**
     * Establece la dependencia del usuario origen.
     *
     * @param origenDependencia Dependencia.
     */
    //Validar por Refactoring de SICDI
    //public void setOrigenDependencia(Dependencia origenDependencia) {
    //        this.origenDependencia = origenDependencia;
    //    }

    /**
     * Obtiene la clasificacíón del usuario origen.
     *
     * @return Clasificación.
     */
    //Validar por Refactoring de SICDI
    //    public Clasificacion getOrigenClasificacion() {
    //        return origenClasificacion;
    //    }

    /**
     * Establece la clasificación del usuario origen.
     *
     * @param origenClasificacion Clasificación.
     */
    //Validar por Refactoring de SICDI
    //    public void setOrigenClasificacion(Clasificacion origenClasificacion) {
    //        this.origenClasificacion = origenClasificacion;
    //    }

    /**
     * Obtiene el grado del usuario origen.
     *
     * @return Grado.
     */
    //Validar por Refactoring de SICDI
    //    public Grados getOrigenGrado() {
    //        return origenGrado;
    //    }

    /**
     * Establece el grado del usuario origen.
     *
     * @param origenGrado Grado.
     */
    //Validar por Refactoring de SICDI
    //    public void setOrigenGrado(Grados origenGrado) {
    //        this.origenGrado = origenGrado;
    //    }

    /**
     * Obtiene el usuario destino.
     *
     * @return Usuario.
     */
    //Validar por Refactoring de SICDI
    //    public Usuario getDestinoUsuario() {
    //        return destinoUsuario;
    //    }

    /**
     * Establece el usuario destino.
     *
     * @param destinoUsuario Usuario.
     */
    //Validar por Refactoring de SICDI
    //    public void setDestinoUsuario(Usuario destinoUsuario) {
    //        this.destinoUsuario = destinoUsuario;
    //    }

    /**
     * Obtiene la dependencia del usuario destino.
     *
     * @return Dependencia.
     */
    //Validar por Refactoring de SICDI
    //    public Dependencia getDestinoDependencia() {
    //        return destinoDependencia;
    //    }

    /**
     * Establece la dependencia del usuario destino.
     *
     * @param destinoDependencia Dependencia.
     */
    //Validar por Refactoring de SICDI
    //    public void setDestinoDependencia(Dependencia destinoDependencia) {
    //        this.destinoDependencia = destinoDependencia;
    //    }

    /**
     * Obtiene la clasificación del usuario destino.
     *
     * @return Clasificación.
     */
    //Validar por Refactoring de SICDI
    //    public Clasificacion getDestinoClasificacion() {
    //        return destinoClasificacion;
    //    }

    /**
     * Establece la clasificación del usuario destino.
     *
     * @param destinoClasificacion Clasificación.
     */
    //Validar por Refactoring de SICDI
    //    public void setDestinoClasificacion(Clasificacion destinoClasificacion) {
    //        this.destinoClasificacion = destinoClasificacion;
    //    }

    /**
     * Obtiene el grado del usuario destino.
     *
     * @return Grado.
     */
    //Validar por Refactoring de SICDI
    //    public Grados getDestinoGrado() {
    //        return destinoGrado;
    //    }

    /**
     * Establece el grado del usuario destino.
     *
     * @param destinoGrado Grado.
     */
    //Validar por Refactoring de SICDI
    //    public void setDestinoGrado(Grados destinoGrado) {
    //        this.destinoGrado = destinoGrado;
    //    }


    /**
     * Obtiene la clasificación de origen de la transferencia anterior.
     *
     * @return Clasificación.
     */

    //Validar por Refactoring de SICDI
    //    public Clasificacion getTransferenciaAnteriorOrigenClasificacion() {
    //        return transferenciaAnteriorOrigenClasificacion;
    //    }

    /**
     * Establece la clasificación de origen de la transferencia anterior.
     *
     * @param transferenciaAnteriorOrigenClasificacion Clasificación.
     */
    //Validar por Refactoring de SICDI
    //    public void setTransferenciaAnteriorOrigenClasificacion(Clasificacion transferenciaAnteriorOrigenClasificacion) {
    //        this.transferenciaAnteriorOrigenClasificacion = transferenciaAnteriorOrigenClasificacion;
    //    }


    /**
     * Obtiene el cargo del usuario destino.
     *
     * @return Cargo.
     */
    //Validar por Refactoring de SICDI
    //    public Cargo getUsuDestinoCargo() {
    //        return usuDestinoCargo;
    //    }

    /**
     * Establece el cargo del usuario destino.
     *
     * @param usuDestinoCargo Cargo
     */
    //Validar por Refactoring de SICDI
    //    public void setUsuDestinoCargo(Cargo usuDestinoCargo) {
    //        this.usuDestinoCargo = usuDestinoCargo;
    //    }

    /**
     * Obtiene el cargo del usuario origen.
     *
     * @return Cargo.
     */
    //Validar por Refactoring de SICDI
    //    public Cargo getUsuOrigenCargo() {
    //        return usuOrigenCargo;
    //    }

    /**
     * Establece el cargo del usuario origen.
     *
     * @param usuOrigenCargo Cargo
     */
    //Validar por Refactoring de SICDI
    //    public void setUsuOrigenCargo(Cargo usuOrigenCargo) {
    //        this.usuOrigenCargo = usuOrigenCargo;
    //    }

    /**
     * Obtiene el cargo del usuario creador.
     *
     * @return Cargo.
     */
    //Validar por Refactoring de SICDI
    //    public Cargo getUsuCreadorCargo() {
    //        return usuCreadorCargo;
    //    }

    /**
     * Establece el cargo del usuario creador.
     *
     * @param usuCreadorCargo Cargo
     */
    //Validar por Refactoring de SICDI
    //    public void setUsuCreadorCargo(Cargo usuCreadorCargo) {
    //        this.usuCreadorCargo = usuCreadorCargo;
    //    }


    /**
     * Obtiene el acta de la transferencia.
     *
     * @return Acta.
     */
    //Validar por Refactoring de SICDI
    //    public Documento getDocId() {
    //        return docId;
    //    }

    
    /**
     * Establece el acta de la transferencia
    // * @param docId
     */
    //Validar por Refactoring de SICDI
    //    public void setDocId(Documento docId) {
    //        this.docId = docId;
    //    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.activo);
        hash = 47 * hash + Objects.hashCode(this.tipo);
        hash = 47 * hash + Objects.hashCode(this.estado);
        //hash = 47 * hash + Objects.hashCode(this.creadorUsuario);
        //hash = 47 * hash + Objects.hashCode(this.creadorDependencia);
        //hash = 47 * hash + Objects.hashCode(this.creadorGrado);
        hash = 47 * hash + Objects.hashCode(this.fechaCreacion);
        //hash = 47 * hash + Objects.hashCode(this.origenUsuario);
        //hash = 47 * hash + Objects.hashCode(this.origenDependencia);
        //hash = 47 * hash + Objects.hashCode(this.origenClasificacion);
        //hash = 47 * hash + Objects.hashCode(this.origenGrado);
        //hash = 47 * hash + Objects.hashCode(this.destinoUsuario);
        //hash = 47 * hash + Objects.hashCode(this.destinoDependencia);
        //hash = 47 * hash + Objects.hashCode(this.destinoClasificacion);
        //hash = 47 * hash + Objects.hashCode(this.destinoGrado);
        hash = 47 * hash + Objects.hashCode(this.numeroDocumentos);
        hash = 47 * hash + Objects.hashCode(this.fechaAprobacion);
        hash = 47 * hash + Objects.hashCode(this.actaOFS);
        hash = 47 * hash + Objects.hashCode(this.transferenciaAnteriorID);
        //hash = 47 * hash + Objects.hashCode(this.transferenciaAnteriorOrigenClasificacion);
        hash = 47 * hash + Objects.hashCode(this.numeroRadicado);
        //hash = 47 * hash + Objects.hashCode(this.usuDestinoCargo);
        //hash = 47 * hash + Objects.hashCode(this.usuOrigenCargo);
        //hash = 47 * hash + Objects.hashCode(this.usuCreadorCargo);
        hash = 47 * hash + Objects.hashCode(this.justificacion);
        hash = 47 * hash + this.usuarioAsignado;
        hash = 47 * hash + this.indAprobado;
        //hash = 47 * hash + Objects.hashCode(this.docId);
        return hash;
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
        final TransferenciaArchivo other = (TransferenciaArchivo) obj;
        if (this.usuarioAsignado != other.usuarioAsignado) {
            return false;
        }
        if (this.indAprobado != other.indAprobado) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.actaOFS, other.actaOFS)) {
            return false;
        }
        if (!Objects.equals(this.numeroRadicado, other.numeroRadicado)) {
            return false;
        }
        if (!Objects.equals(this.justificacion, other.justificacion)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.activo, other.activo)) {
            return false;
        }
        //Validar por Refactoring de SICDI
//        if (!Objects.equals(this.creadorUsuario, other.creadorUsuario)) {
//            return false;
//        }
//        if (!Objects.equals(this.creadorDependencia, other.creadorDependencia)) {
//            return false;
//        }
//        if (!Objects.equals(this.creadorGrado, other.creadorGrado)) {
//            return false;
//        }
        if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
            return false;
        }
        //Validar por Refactoring de SICDI
//        if (!Objects.equals(this.origenUsuario, other.origenUsuario)) {
//            return false;
//        }
//        if (!Objects.equals(this.origenDependencia, other.origenDependencia)) {
//            return false;
//        }
//        if (!Objects.equals(this.origenClasificacion, other.origenClasificacion)) {
//            return false;
//        }
//        if (!Objects.equals(this.origenGrado, other.origenGrado)) {
//            return false;
//        }
//        if (!Objects.equals(this.destinoUsuario, other.destinoUsuario)) {
//            return false;
//        }
//        if (!Objects.equals(this.destinoDependencia, other.destinoDependencia)) {
//            return false;
//        }
//        if (!Objects.equals(this.destinoClasificacion, other.destinoClasificacion)) {
//            return false;
//        }
//        if (!Objects.equals(this.destinoGrado, other.destinoGrado)) {
//            return false;
//        }
        if (!Objects.equals(this.numeroDocumentos, other.numeroDocumentos)) {
            return false;
        }
        if (!Objects.equals(this.fechaAprobacion, other.fechaAprobacion)) {
            return false;
        }
        if (!Objects.equals(this.transferenciaAnteriorID, other.transferenciaAnteriorID)) {
            return false;
        }
        //Validar por Refactoring de SICDI
//        if (!Objects.equals(this.transferenciaAnteriorOrigenClasificacion, other.transferenciaAnteriorOrigenClasificacion)) {
//            return false;
//        }
//        if (!Objects.equals(this.usuDestinoCargo, other.usuDestinoCargo)) {
//            return false;
//        }
//        if (!Objects.equals(this.usuOrigenCargo, other.usuOrigenCargo)) {
//            return false;
//        }
//        if (!Objects.equals(this.usuCreadorCargo, other.usuCreadorCargo)) {
//            return false;
//        }
//        if (!Objects.equals(this.docId, other.docId)) {
//            return false;
//        }
        return true;
    }
    
    

}
