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
@Table(name = "TRANS_JUSTIFICACION_DEFECTO")
@XmlRootElement
@SuppressWarnings("PersistenceUnitPresent")
public class TransJustificacionDefecto implements Serializable {

    private static final long serialVersionUID = -3216316165675237241L;

    @Id
    @GenericGenerator(name = "SEQ_TRANS_JUS_DEFECTO", strategy = "sequence",
            parameters = {@Parameter(name = "sequence", value = "SEQ_TRANS_JUS_DEFECTO"),@Parameter(name = "allocationSize", value = "1")})
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_TRANS_JUS_DEFECTO")
    @Basic(optional = false)
    @Column(name = "TJD_ID")
    private Long tjdId;
    @Basic(optional = false)
    @Column(name = "TEXTO_OBSERVACION")
    private String textoObservacion;
    @Basic(optional = false)
    @Column(name = "ACTIVO")
    private Boolean activo;
    @Basic(optional = false)
    @Column(name = "CUANDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cuando;
    @Column(name = "CUANDO_MOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cuandoMod;
    //Validar por Refactoring de SICDI
//    @JoinColumn(name = "QUIEN", referencedColumnName = "USU_ID")
//    @ManyToOne(optional = false)
//    private Usuario quien;
//    @JoinColumn(name = "QUIEN_MOD", referencedColumnName = "USU_ID")
//    @ManyToOne
//    private Usuario quienMod;

    public TransJustificacionDefecto() {
    }

    public TransJustificacionDefecto(Long tjdId) {
        this.tjdId = tjdId;
    }

    public TransJustificacionDefecto(Long tjdId, String textoObservacion, Boolean activo, Date cuando) {
        this.tjdId = tjdId;
        this.textoObservacion = textoObservacion;
        this.activo = activo;
        this.cuando = cuando;
    }

    //Validar por Refactoring de SICDI
//    public Usuario getQuien() {
//        return quien;
//    }
//
//    public void setQuien(Usuario quien) {
//        this.quien = quien;
//    }
//
//    public Usuario getQuienMod() {
//        return quienMod;
//    }
//
//    public void setQuienMod(Usuario quienMod) {
//        this.quienMod = quienMod;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tjdId != null ? tjdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransJustificacionDefecto)) {
            return false;
        }
        TransJustificacionDefecto other = (TransJustificacionDefecto) object;
        if ((this.tjdId == null && other.tjdId != null) || (this.tjdId != null && !this.tjdId.equals(other.tjdId))) {
            return false;
        }
        return true;
    }
    //Validar por Refactoring de SICDI
//    @Override
//    public String toString() {
//        return "TransJustificacionDefecto{" + "tjdId=" + tjdId + ", textoObservacion=" + textoObservacion + ", activo=" + activo + ", cuando=" + cuando + ", cuandoMod=" + cuandoMod + ", quien=" + quien + ", quienMod=" + quienMod + '}';
//    }
}