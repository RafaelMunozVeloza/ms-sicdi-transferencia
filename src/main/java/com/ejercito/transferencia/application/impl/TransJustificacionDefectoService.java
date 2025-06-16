package com.ejercito.transferencia.application.impl;


import com.ejercito.transferencia.application.util.BusinessLogicException;
import com.ejercito.transferencia.application.util.ReflectionException;
import com.ejercito.transferencia.application.util.ReflectionUtil;
import com.ejercito.transferencia.infrastructure.adaptador.TransJustificacionDefectoRepository;
import com.ejercito.transferencia.domain.model.TransJustificacionDefecto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Servicio para las justificaciones de las transferencias.
 *
 * @author edisson.gonzalez@controltechcg.com
 * @version 08/22/2018 Issue #4 (SICDI-Controltech) feature-4
 */
@Service
public class TransJustificacionDefectoService {

    private static final Logger LOG = LoggerFactory.getLogger(TransJustificacionDefectoService.class);

    /**
     * Repositorio Justificacion de Transferencia por Defecto
     */
    @Autowired
    private TransJustificacionDefectoRepository transJustificacionDefectoRepository;

   
    /**
     * *
     * Lista todas las observaciones
     *
     * @param sort
     * @return
     */
    public List<TransJustificacionDefecto> findAll(Sort sort) {
        return transJustificacionDefectoRepository.findAll(sort);
    }
    
    /***
     * Retorna la pagina de las observaciones
     * @param pageable paginador
     * @return pagina
     */
    public Page<TransJustificacionDefecto> findAll(Pageable pageable) {
        return transJustificacionDefectoRepository.findAll(pageable);
    }

    /**
     * *
     * Lista todas las observaciones activas
     *
     * @param sort
     * @return
     */
    public List<TransJustificacionDefecto> findActive(Sort sort) {
        return transJustificacionDefectoRepository.getByActivoTrue(sort);
    }
    
     /**
     * 
     * Lista todas las observaciones activas
     *
     * @param pageable paginador
     * @return lista de observaciones
     */
    public Page<TransJustificacionDefecto> findActive(Pageable pageable) {
        return transJustificacionDefectoRepository.findAllByActivoTrue(pageable);
    }

    /**
     * *
     * Busca una justifcacion por defecto por id
     *
     * @param id identificador de la justificacion por defecto
     * @return
     */
    public TransJustificacionDefecto findOne(Long id) {
        return transJustificacionDefectoRepository.getOne(id);
    }

    public void crearTransJustificacionDefecto(TransJustificacionDefecto justificacionDefecto) /**Usuario usuario (Parametro que utiliza el metodo)**/
        throws BusinessLogicException, ReflectionException {
        final String textoObservacion = justificacionDefecto.getTextoObservacion();
        if (textoObservacion == null || textoObservacion.trim().length() == 0) {
            throw new BusinessLogicException("El texto del nombre es obligatorio.");
        }

        final int textoObservacionColumnLength = ReflectionUtil.getColumnLength(TransJustificacionDefecto.class, "textoObservacion");
        if (textoObservacion.trim().length() > textoObservacionColumnLength) {
            throw new BusinessLogicException("El texto de la observación permite máximo " + textoObservacionColumnLength + " caracteres.");
        }

        TransJustificacionDefecto transJustificacionDefecto = transJustificacionDefectoRepository.findOneByTextoObservacionAndActivoTrue(textoObservacion.toUpperCase());
        System.err.println("transJustificacionDefecto= " + transJustificacionDefecto);
        if (transJustificacionDefecto != null) {
            throw new BusinessLogicException("Este nombre ya existe.");
        }

        justificacionDefecto.setTextoObservacion(textoObservacion.toUpperCase());
        justificacionDefecto.setActivo(Boolean.TRUE);
        //justificacionDefecto.setQuien(usuario);
        justificacionDefecto.setCuando(new Date());
        //justificacionDefecto.setQuienMod(usuario);
        justificacionDefecto.setCuandoMod(new Date());

        transJustificacionDefectoRepository.saveAndFlush(justificacionDefecto);
    }

    public void editarTransJustificacionDefecto(TransJustificacionDefecto justificacionDefecto) /**Usuario usuario (Parametro que utiliza el metodo)**/
        throws BusinessLogicException, ReflectionException {

        final String textoObservacion = justificacionDefecto.getTextoObservacion();
        if (textoObservacion == null || textoObservacion.trim().length() == 0) {
            throw new BusinessLogicException("El texto de laobservacion es obligatorio.");
        }

        final int textoObservacionColumnLength = ReflectionUtil.getColumnLength(TransJustificacionDefecto.class, "textoObservacion");
        if (textoObservacion.trim().length() > textoObservacionColumnLength) {
            throw new BusinessLogicException("El texto de la observacion permite máximo " + textoObservacionColumnLength + " caracteres.");
        }

        TransJustificacionDefecto transJustificacionDefecto = transJustificacionDefectoRepository.findOneByTextoObservacionAndActivoTrue(textoObservacion);
        if (transJustificacionDefecto != null && !transJustificacionDefecto.getTjdId().equals(justificacionDefecto.getTjdId()) && transJustificacionDefecto != null) {
            throw new BusinessLogicException("Este texto  ya existe.");
        }

        justificacionDefecto.setTextoObservacion(textoObservacion.toUpperCase());

        TransJustificacionDefecto justificacionDefectoAnterior
                = findOne(justificacionDefecto.getTjdId());

        //justificacionDefecto.setQuien(justificacionDefectoAnterior.getQuien());
        justificacionDefecto.setCuando(justificacionDefectoAnterior.getCuando());
        justificacionDefecto.setActivo(justificacionDefectoAnterior.getActivo());

        //justificacionDefecto.setQuienMod(usuario);
        justificacionDefecto.setCuandoMod(new Date());

        transJustificacionDefectoRepository.saveAndFlush(justificacionDefecto);
    }

    /**
     * Eliminar una observación por defecto
     *
     * @param transJustificacionDefecto obsevación por defecto a ser eliminada
     * //@param usuario Usuario que aplico el cambio
     */
    public void eliminarTransJustificacionDefecto(TransJustificacionDefecto transJustificacionDefecto) /**Usuario usuario (Parametro que utiliza el metodo)**/  {
        //transJustificacionDefecto.setQuien(usuario);
        transJustificacionDefecto.setCuando(new Date());
        transJustificacionDefecto.setActivo(Boolean.FALSE);
        transJustificacionDefectoRepository.saveAndFlush(transJustificacionDefecto);
    }

    /**
     * Lista todas las observaciones por defecto activas, ordenadas por el
     * texto.
     *
     * @return Lista de observaciones activas ordenadas por texto.
     */
    public List<TransJustificacionDefecto> listarActivas() {
        return transJustificacionDefectoRepository.findAllByActivoTrueOrderByTextoObservacionAsc();
    }
    
}
