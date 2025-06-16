/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejercito.transferencia.domain.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author egonzalezm
 * @since 1.8
 * @version 08/22/2018
 */
@Data
@Entity
@Table(name = "TRANS_EXPEDIENTE_DETALLE")
@XmlRootElement
@SuppressWarnings("PersistenceUnitPresent")
public class TransExpedienteDetalle implements Serializable {

    private static final long serialVersionUID = 4441097115657603301L;

    @Id
    @GenericGenerator(name = "seq_TRANS_EXP_DETALLE", strategy = "sequence",
            parameters = {@Parameter(name = "sequence", value = "seq_TRANS_EXP_DETALLE"),@Parameter(name = "allocationSize", value = "1")})
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_TRANS_EXP_DETALLE")
    @Basic(optional = false)
    @Column(name = "TRA_EXP_ID")
    private Long traExpId;
    @Column(name = "FEC_TRANSFERENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecTransferencia;
    @Basic(optional = false)
    @Column(name = "IND_REALIZADO")
    private int indRealizado;
    @Basic(optional = false)
    @Column(name = "ACTIVO")
    private int activo;
    //Validar por Refactoring de SICDI
//    @JoinColumn(name = "NUEVO_DEP_ID", referencedColumnName = "DEP_ID")
//    @ManyToOne(optional = false)
//    private Dependencia nuevoDepId;
//    @JoinColumn(name = "ANTERIOR_DEP_ID", referencedColumnName = "DEP_ID")
//    @ManyToOne(optional = false)
//    private Dependencia anteriorDepId;
//    @JoinColumn(name = "EXP_ID", referencedColumnName = "EXP_ID")
//    @ManyToOne(optional = false)
//    private Expediente expId;
//    @JoinColumn(name = "TAR_ID", referencedColumnName = "TAR_ID")
//    @ManyToOne(optional = false)
      private TransferenciaArchivo tarId;
//    @JoinColumn(name = "NUEVO_QUIEN", referencedColumnName = "USU_ID")
//    @ManyToOne(optional = false)
//    private Usuario nuevoQuien;
//    @JoinColumn(name = "ANTERIOR_QUIEN", referencedColumnName = "USU_ID")
//    @ManyToOne(optional = false)
//    private Usuario anteriorQuien;

    public TransExpedienteDetalle() {
    }

    public TransExpedienteDetalle(Long traExpId) {
        this.traExpId = traExpId;
    }

    public TransExpedienteDetalle(Long traExpId, int indRealizado, int activo) {
        this.traExpId = traExpId;
        this.indRealizado = indRealizado;
        this.activo = activo;
    }


//Validar por Refactoring de SICDI
//    public Dependencia getNuevoDepId() {
//        return nuevoDepId;
//    }
//
//    public void setNuevoDepId(Dependencia nuevoDepId) {
//        this.nuevoDepId = nuevoDepId;
//    }
//
//    public Dependencia getAnteriorDepId() {
//        return anteriorDepId;
//    }
//
//    public void setAnteriorDepId(Dependencia anteriorDepId) {
//        this.anteriorDepId = anteriorDepId;
//    }
//
//    public Expediente getExpId() {
//        return expId;
//    }
//
//    public void setExpId(Expediente expId) {
//        this.expId = expId;
//    }
//
//    public TransferenciaArchivo getTarId() {
//        return tarId;
//    }
//
//    public void setTarId(TransferenciaArchivo tarId) {
//        this.tarId = tarId;
//    }
//
//    public Usuario getNuevoQuien() {
//        return nuevoQuien;
//    }
//
//    public void setNuevoQuien(Usuario nuevoQuien) {
//        this.nuevoQuien = nuevoQuien;
//    }
//
//    public Usuario getAnteriorQuien() {
//        return anteriorQuien;
//    }
//
//    public void setAnteriorQuien(Usuario anteriorQuien) {
//        this.anteriorQuien = anteriorQuien;
//    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransExpedienteDetalle)) {
            return false;
        }
        TransExpedienteDetalle other = (TransExpedienteDetalle) object;
        if ((this.traExpId == null && other.traExpId != null) || (this.traExpId != null && !this.traExpId.equals(other.traExpId))) {
            return false;
        }
        return true;
    }
//Validar por Refactoring de SICDI
//    @Override
//    public String toString() {
//        return "TransExpedienteDetalle{" + "traExpId=" + traExpId + ", fecTransferencia=" + fecTransferencia + ", indRealizado=" + indRealizado + ", activo=" + activo + ", nuevoDepId=" + nuevoDepId + ", anteriorDepId=" + anteriorDepId + ", expId=" + expId + ", tarId=" + tarId + ", nuevoQuien=" + nuevoQuien + ", anteriorQuien=" + anteriorQuien + '}';
//    }
}