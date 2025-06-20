package com.ejercito.transferencia.application.impl;


import com.ejercito.transferencia.infrastructure.adaptador.TransferenciaArchivoDetalleRepository;
import com.ejercito.transferencia.domain.model.TransferenciaArchivo;
import com.ejercito.transferencia.domain.model.TransferenciaArchivoDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Servicio con las reglas de negocio para el proceso de transferencia de
 * archivo detalle.
 *
 * @author samuel.delgado@controltechcg.com
 * @since Ago 27, 2018
 * @version 1.0.0 (feature-120).
 */
@Service
public class TransferenciaArchivoDetalleService {


    /**
     * Repositorio de detalle de transferencia.
     */
    @Autowired
    private TransferenciaArchivoDetalleRepository transferenciaArchivoDetalleRepository;

    /**
     * Servicio para documentos dependencia.
     */
    //Validar por Refactoring de SICD
//    @Autowired
//    private DocumentoDependenciaService documentoDependenciaService;

    /***
     * Guarda un documento en la transferencia
     * @param transferenciaArchivo transferencia a guradar documento
     * //@param documentoDependencia documento dependencia asociado
     * //@param usuario usuario que realiza la transferencia
     */
    public void guardarDocumentoTransferencia(TransferenciaArchivo transferenciaArchivo
    ) /** Parametros que usa el metodo(DocumentoDependencia documentoDependencia, Usuario usuario) **/
    {
        TransferenciaArchivoDetalle transferenciaArchivoDetalle = new TransferenciaArchivoDetalle();
        //transferenciaArchivoDetalle.setDocumentoDependencia(documentoDependencia);
        //transferenciaArchivoDetalle.setDocumentoID(documentoDependencia.getDocumento().getId());
        transferenciaArchivoDetalle.setActivo(1);
        transferenciaArchivoDetalle.setIndRealizado(0);
        //transferenciaArchivoDetalle.setAnteriorDependencia(transferenciaArchivo.getOrigenDependencia());
        //transferenciaArchivoDetalle.setAnteriorUsuario(usuario);
        //transferenciaArchivoDetalle.setAnteriorFechaAsignacion(documentoDependencia.getCuando());
        //transferenciaArchivoDetalle.setNuevoDependencia(transferenciaArchivo.getDestinoDependencia());
        //transferenciaArchivoDetalle.setNuevoUsuario(transferenciaArchivo.getDestinoUsuario());
        transferenciaArchivoDetalle.setNuevoFechaAsignacion(new Date());
        transferenciaArchivoDetalle.setTransferenciaArchivo(transferenciaArchivo);
        transferenciaArchivoDetalleRepository.save(transferenciaArchivoDetalle);
    }

    /**
     * Elimina rodos los documentos de transferencia archivo
     *
     * @param transferenciaArchivo
     */
    public void eliminarDocumentosTransferencia(TransferenciaArchivo transferenciaArchivo) {
        List<TransferenciaArchivoDetalle> documentos = transferenciaArchivoDetalleRepository.findAllByTransferenciaArchivoAndActivo(transferenciaArchivo, 1);
        for (TransferenciaArchivoDetalle documento : documentos) {
            documento.setActivo(0);
            transferenciaArchivoDetalleRepository.save(documento);
        }
    }

    public void eliminarDocumentosExpedientesTransferencia(TransferenciaArchivo transferenciaArchivo) /** Parametros que usa el metodo (Usuario usuario) **/
    {
        List<TransferenciaArchivoDetalle> documentos = transferenciaArchivoDetalleRepository.findAllTransferenciaExpedienteExpId(/**usuario.getId()**/ null, transferenciaArchivo.getId());
        for (TransferenciaArchivoDetalle documento : documentos) {
            documento.setActivo(0);
            transferenciaArchivoDetalleRepository.save(documento);
        }
    }

    /**
     * guarda una lista de documentos en una transferencia.
     *
     * @param transferenciaArchivo transferencuia a agregar los doucmentos
     *                             //@param documentos           lista de documentos
     *                             //@param usuario              usuario creador
     */
    //Validar por Refactoring de SICDI
//    public void guardarListaDocumentosTransferencia(TransferenciaArchivo transferenciaArchivo,
//            List<DocumentoDependencia> documentos, List<DocumentoDependencia> documentosEnTransferencia,
//            Usuario usuario){
//
//        for (DocumentoDependencia documento : documentos) {
//            if(documento.getCargo().getId().equals(transferenciaArchivo.getUsuOrigenCargo().getId())){
//                boolean find = false;
//                for (DocumentoDependencia documentoDependencia : documentosEnTransferencia) {
//                    if (documentoDependencia.getId().equals(documento.getId())) {
//                        find = true;
//                        break;
//                    }
//                }
//                if (!find) {
//                    guardarDocumentoTransferencia(transferenciaArchivo, documento, usuario);
//                }
//            }
//        }
//    }
//
    public List<TransferenciaArchivoDetalle> buscarDocumentosTransferencia(TransferenciaArchivo transferenciaArchivo) {
        return transferenciaArchivoDetalleRepository.findAllByTransferenciaArchivoAndActivo(transferenciaArchivo, 1);
    }

    public void transferirDocumentos(TransferenciaArchivo transferenciaArchivo) {
        List<TransferenciaArchivoDetalle> buscarDocumentosTransferencia = buscarDocumentosTransferencia(transferenciaArchivo);
        for (TransferenciaArchivoDetalle transferenciaArchivoDetalle : buscarDocumentosTransferencia) {
            transferenciaArchivoDetalle.setIndRealizado(1);
            //documentoDependenciaService.completarTransferencia(transferenciaArchivoDetalle.getDocumentoDependencia(),
            // transferenciaArchivoDetalle.getNuevoUsuario(), transferenciaArchivoDetalle.getNuevoDependencia(),
            //transferenciaArchivo.getUsuDestinoCargo());
        }
    }
}

    /***
     * Lista los documentos de la transferencia que no se encuentra en la custodia del usuario.
     * @param transferenciaArchivo transferencia de archivo
     * //@param usuario usuario a evaluar si tiene los documentos
     * @return lista de los documentos que no pertenecen al usuario.
     *///Validar por Refactoring de SICDI
//    public List<TransferenciaArchivoDetalle> documentosNoPosesionTransferencia(TransferenciaArchivo transferenciaArchivo) /** Parametro que usa este metodo (Usuario usuario)**/
//    {
//        List<TransferenciaArchivoDetalle> answ = new ArrayList<>();
//        List<TransferenciaArchivoDetalle> findAllByTransferenciaArchivoAndActivo = transferenciaArchivoDetalleRepository.findAllByTransferenciaArchivoAndActivo(transferenciaArchivo, 1);
//        for (TransferenciaArchivoDetalle transferenciaArchivoDetalle : findAllByTransferenciaArchivoAndActivo) {
//            if (!transferenciaArchivoDetalle.getDocumentoDependencia().getQuien().equals(usuario.getId())) {
//                answ.add(transferenciaArchivoDetalle);
//            }
//        }
//        return answ;
//        }
//    }

    /**
     * Genera los nuevos apuntadores a los documentos para la nueva transferencia
     *
     * @param anterior anterior transferencia.
     * @param nueva nueva transferencia.
     */
//    Validar por
//    Refactoring de
//    SICDI
//
//    public void reeviarDocumentosTransferencia(TransferenciaArchivo anterior, TransferenciaArchivo nueva) {
//        List<TransferenciaArchivoDetalle> documentos = transferenciaArchivoDetalleRepository.findAllByTransferenciaArchivoAndActivo(anterior, 1);
//        List<DocumentoDependencia> documentosEnTransferencia = documentoDependenciaService.listarDocumentosOtrasTransferencias(anterior.getDestinoUsuario(), anterior);
//        List<Integer> ifDocumentosTransferencia = new ArrayList<>();
//        for (DocumentoDependencia doc : documentosEnTransferencia) {
//            ifDocumentosTransferencia.add(doc.getId());
//        }
//        for (TransferenciaArchivoDetalle documento : documentos) {
//            if (!ifDocumentosTransferencia.contains(documento.getDocumentoDependencia().getId())) {
//                if (documento.getDocumentoDependencia().getQuien().equals(anterior.getDestinoUsuario().getId())) {
//                    guardarDocumentoTransferencia(nueva, documento.getDocumentoDependencia(), nueva.getOrigenUsuario());
//                }
//            }
//        }
//    }



