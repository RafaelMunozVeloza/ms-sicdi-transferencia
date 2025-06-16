package com.ejercito.transferencia.infrastructure.controller;

import com.ejercito.transferencia.application.dto.*;
import com.ejercito.transferencia.application.impl.*;
import com.ejercito.transferencia.domain.model.*;
import com.ejercito.transferencia.application.dto.*;
import com.ejercito.transferencia.application.impl.*;

import com.ejercito.transferencia.domain.model.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/apiv1/transferencia-archivo")
public class TransferenciaArchivoRestController extends UtilRestController {

    /**
     * Ruta raíz del controlador
     */
    public static final String PATH = "/transferencia-archivo";
    private static final Logger LOG = LoggerFactory.getLogger(TransferenciaArchivoRestController.class);

    public static final Long ESTADO_RECHAZADO = 50L;
    private static final String TRANSFERENCIASPROCESO = "PROCESO";
    private static final String TRANSFERENCIASREALIZADAS = "ORIGEN";
    private static final String TRANSFERENCIASRECIBIDAS = "DESTINO";

    /**
     * Servicio de transferencia de archivo
     */
    @Autowired
    private TransferenciaArchivoService transferenciaService;

    /**
     * Servicio de usuarios
     */

//    Validar por Refactoring de SICDI
//    @Autowired
//    private UsuarioService usuarioService;

    /**
     * Servicio de expediente
     */
//    Validar por Refactoring de SICDI
//    @Autowired
//    private ExpedienteService expedienteService;

    @Autowired
    private TransExpedienteDetalleService transExpedienteDetalleService;

    @Autowired
    private TransferenciaArchivoDetalleService transferenciaArchivoDetalleService;

//Validar por Refactoring de SICDI
//    @Autowired
//    private DocumentoDependenciaService documentoDependenciaService;

    @Autowired
    private TransferenciaTransicionService transferenciaTransicionService;

    @Autowired
    private TransferenciaEstadoService transferenciaEstadoService;

    @Autowired
    private TransferenciaObservacionService transferenciaObservacionService;

    /**
     * Servicio de cargos
     */
    //Validar por Refactoring de SICDI
//    @Autowired
//    CargosRepository cargosRepository;

    @Autowired
    private TransJustificacionDefectoService transJustificacionDefectoService;


    //Validar por Refactoring de SICDI
//    @Autowired
//    ModelMapper modelMapper;

//    public void ConfigureModelMapper() {
//        Configuration conf = modelMapper.getConfiguration();
//        conf.setSkipNullEnabled(true);
//
//
//        PropertyMap<TransExpedienteDetalle, TransExpedienteDetalleDTO> expIdMap = new PropertyMap<>() {
//            protected void configure() {
//                //Validar por Refactoring de SICDI
//                //map().setExpId(source.getExpId().getExpId());
//            }
//        };
//        modelMapper.addMappings(expIdMap);
//    }
//
//    @Autowired
//    public TransferenciaArchivoRestController(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//        ConfigureModelMapper();
//    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<Model> listarTransferencias(Principal principal, Model model,
                                                      @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                      @RequestParam(value = "tipoTransferencia", defaultValue = TRANSFERENCIASPROCESO)
                                                      String tipoTransferencia) {
        if (!genericSessionValidation(principal)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        //Validar por Refactoring de SICDI
        //final Usuario origenUsuario = getUsuario(principal);

        List<TransferenciaArchivoDTO> transferencias = new ArrayList<>();
        int count;
        int totalPages = 0;
        String labelInformacion = "";

//Validar por Refactoring de SICDI
//        switch (tipoTransferencia) {
//            case TRANSFERENCIASPROCESO:
//                count = transferenciaService.findCountProcesoByUsuarioId(origenUsuario.getId());
//                if (count > 0) {
//                    PaginacionDTO paginacionDTO = PaginacionUtil.retornaParametros(count, pageIndex, pageSize);
//                    totalPages = paginacionDTO.getTotalPages();
//                    transferencias = transferenciaService.findAllProcesoByUsuarioId(origenUsuario.getId(),
//                            paginacionDTO.getRegistroInicio(), paginacionDTO.getRegistroFin());
//                    labelInformacion = paginacionDTO.getLabelInformacion();
//                }
//                break;
//            case TRANSFERENCIASREALIZADAS:
//                count = transferenciaService.findCountRealizadasByUsuarioId(origenUsuario.getId());
//                if (count > 0) {
//                    PaginacionDTO paginacionDTO = PaginacionUtil.retornaParametros(count, pageIndex, pageSize);
//                    totalPages = paginacionDTO.getTotalPages();
//                    transferencias = transferenciaService.findAllRealizadasByUsuarioId(origenUsuario.getId(),
//                            paginacionDTO.getRegistroInicio(), paginacionDTO.getRegistroFin());
//                    labelInformacion = paginacionDTO.getLabelInformacion();
//                }
//                break;
//            case TRANSFERENCIASRECIBIDAS:
//                count = transferenciaService.findCountRecibidasByUsuarioId(origenUsuario.getId());
//                if (count > 0) {
//                    PaginacionDTO paginacionDTO = PaginacionUtil.retornaParametros(count, pageIndex, pageSize);
//                    totalPages = paginacionDTO.getTotalPages();
//                    transferencias = transferenciaService.findAllRecibidasByUsuarioId(origenUsuario.getId(),
//                            paginacionDTO.getRegistroInicio(), paginacionDTO.getRegistroFin());
//                    labelInformacion = paginacionDTO.getLabelInformacion();
//                }
//                break;
//        }

        model.addAttribute("transferencias", transferencias);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("labelInformacion", labelInformacion);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("tipoTransferencia", tipoTransferencia);

        return ResponseEntity.ok().body(model);
    }

    /**
     * Presenta el formulario de creación para el proceso de transferencia de
     * archivo en gestion.
     *
     * @param principal Objeto principal de A&A.
     * @param model     Modelo de UI.
     * @return Nombre del template Freemarker del formulario.
     */
    @RequestMapping(value = "/crearTransferenciaGestion", method = RequestMethod.GET)
    public ResponseEntity<Model> crearFormularioTransferenciaGestionGET(Principal principal, Model model) {
        if (!genericSessionValidation(principal)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        //Validar por Refactoring de SICDI
        //final Usuario origenUsuario = getUsuario(principal);


        //UsuarioDTO origenUsuarioDTO = UsuarioDTO.customMap(origenUsuario);
        //Dependencia dependencia = origenUsuario.getDependencia();
        //DependenciaDTO dependenciaDTO = DependenciaDTO.getDTO(dependencia);
        //model.addAttribute("origenUsuario", origenUsuarioDTO);
        //model.addAttribute("dependencia", dependenciaDTO);

//        if (origenUsuario.getUsuCargoPrincipalId() == null) {
//            model.addAttribute(AppConstants.FLASH_ERROR,
//                    "ATENCIÓN: El usuario en sesión no tiene cargo principal en el sistema.");
//        }

//        if (!transferenciaService.hayPlantillaActiva()) {
//            model.addAttribute(AppConstants.FLASH_ERROR,
//                    "ATENCIÓN: No hay plantilla activa para la generación del acta de transferencia de archivo.");
//        }

//        model.addAttribute("origenUsuarioDescripcion", transferenciaService
//                .getUsuarioDescripcion(origenUsuario, true));
       // model.addAttribute("cargosXusuario", cargosXusuario(principal));
        model.addAttribute("justificacionesDefecto", listarJustificacionesPorDefectoActivas());

        return ResponseEntity.ok().body(model);
    }

    /**
     * Valida el formulario de creación de la transferencia. En caso exitoso,
     * redirecciona al formulario de confirmación; de lo contrario, redirecciona
     * al formulario de creación, indicando los errores en la operación.
     *
     * @param origenUsuarioID  ID del usuario de origen de la transferencia.
     * @param destinoUsuarioID ID del usuario de destino de la transferencia.
     * @param justificacion
     * @param cargoOrigen
     * @param principal        Objeto principal de A&A.
     * @param model            Modelo de UI.
     * @return Nombre del template Freemarker redirigido.
     *///Validar por Refactoring de SICDI
    @RequestMapping(value = "/crearTransferenciaGestion", method = RequestMethod.POST)
    public ResponseEntity<Model> crearFormularioTransferenciaGestionPOST(
            @RequestParam("origenUsuario") Integer origenUsuarioID,
            @RequestParam("destinoUsuario") Integer destinoUsuarioID,
            @RequestParam(value = "cargoOrigen") Integer cargoOrigen,
            @RequestParam(value = "justificacion") String justificacion,
            Principal principal, Model model) {

//        try {
//            if (!genericSessionValidation(principal)) {
//                //model.addAttribute(ERROR_TAG, AppConstants.SESSION_EXPIRED);
//                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//            }
//            //final Usuario origenUsuario = getUsuario(principal);
//            model.addAttribute("cargoOrigen", cargoOrigen);
//            model.addAttribute("justificacion", justificacion);
//
//            if (destinoUsuarioID == null) {
////                model.addAttribute(AppConstants.FLASH_ERROR,
////                        "Error Debe seleccionar un usuario destino de la transferencia.");
////                return ResponseEntity.badRequest().body(model);
//            }
//
//            //final Usuario destinoUsuario = usuarioService.findOne(destinoUsuarioID);
//
//            final TransferenciaArchivoValidacionDTO validacionDTO = transferenciaService
//                    .validarTransferenciaGestion(origenUsuario, cargoOrigen, destinoUsuario, justificacion);
//            if (!validacionDTO.isOK()) {
//                model.addAttribute(AppConstants.FLASH_ERROR, buildFlashErrorMessage(validacionDTO));
//                return ResponseEntity.badRequest().body(model);
//            }
//
//            TransferenciaArchivo ta = transferenciaService
//                    .crearEncabezadoTransferenciaGestion(origenUsuario, cargoOrigen, destinoUsuario, justificacion);
//            model.addAttribute(AppConstants.FLASH_SUCCESS,
//                    "El encabezado de la transferencia del archivo ha sido creada satisfactoriamente.");
//
//            List<Expediente> expedientes = expedienteService.getExpedientesXusuarioCreador(origenUsuario);
//            if (expedientes.isEmpty()) {
//                model.addAttribute("redirect", PATH + "/seleccionar-documentos/" + ta.getId());
//                return ResponseEntity.ok().body(model);
//            } else {
//                model.addAttribute(AppConstants.FLASH_ERROR,
//                        "Error al seleccionar los documentos. Por favor vuelva a intentar.");
//            }
//            model.addAttribute("redirect", PATH + "/seleccionar-expediente/" + ta.getId());
//            return ResponseEntity.ok().body(model);
//        } catch (Exception ex) {
//            model.addAttribute(AppConstants.FLASH_ERROR,
//                    "Error creando la transferencia. Por favor comuníquese con el administrador del sistema.");
//            return ResponseEntity.badRequest().body(model);
//        }
        return null;
    }


    /**
     * Construye el mensaje de error a partir del DTO de validación.
     *
     * @param validacionDTO DTO de validación.
     * @return Mensaje de error.
     */
    private String buildFlashErrorMessage(final TransferenciaArchivoValidacionDTO validacionDTO) {
        final StringBuilder builder = new StringBuilder();

        for (String error : validacionDTO) {
            builder.append(error).append("<br>");
        }

        return builder.toString().trim();
    }

    /**
     * Carga el listado de cargos del usuario en sesión.
     */
//Validar por Refactoring de SICDI
//    public List<CargoDTO> cargosXusuario(Principal principal) {
//        System.out.println("Entro a cargosxUsuario");
//        Usuario usuarioSesion = getUsuario(principal);
//        List<Object[]> list = cargosRepository.findCargosXusuario(usuarioSesion.getId());
//        List<CargoDTO> cargoDTOs = list.stream().map(os -> new CargoDTO(((BigDecimal) os[0]).intValue(), (String) os[1]))
//                .collect(Collectors.toList());
//        return cargoDTOs;
//    }

    public List<TransJustificacionDefectoDTO> listarJustificacionesPorDefectoActivas() {
        List<TransJustificacionDefecto> justificacionesActivas = transJustificacionDefectoService.listarActivas();
        List<TransJustificacionDefectoDTO> justificacionesActivasDTO = justificacionesActivas.stream()
                .map(TransJustificacionDefectoDTO::getDTO).collect(Collectors.toList());

        return justificacionesActivasDTO;
    }

    /**
     * Método que retorna la página para seleccionar los expedientes.
     *
     * @param transId   Identificador de la transferencia
     * @param principal usuario en sesión
     * @param model     modelo del template
     * @return página para seleccionar los expedientes.
     */
    @RequestMapping(value = "/seleccionar-expediente", method = RequestMethod.GET)
    public ResponseEntity<Model> seleccionarExpedientes(@RequestParam("trans") Integer transId,
                                                        Principal principal, Model model) {
        if (!genericSessionValidation(principal)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        //Usuario usuarioSesion = getUsuario(principal);



        TransferenciaArchivo transferenciaArchivo = transferenciaService.findOneTransferenciaArchivo(transId);
        //Validar por Refactoring de SICDI
//        if (!transferenciaService.permisoEditarTransferencia(transferenciaArchivo, usuarioSesion)) {
//            return ResponseEntity.badRequest().body(model);
//        }

        //Validar por Refactoring de SICDI
        //List<Expediente> expedientes = expedienteService.getExpedientesXusuarioCreador(usuarioSesion);
        List<TransExpedienteDetalle> expedientesSeleccionados = transExpedienteDetalleService
                .buscarXTransferenciaArchivo(transferenciaArchivo);
//        List<TransExpedienteDetalle> expedientesEnOtrasTransferencias = transExpedienteDetalleService
//                .buscarOtrosExpedientesEnTranseferencia(usuarioSesion, transferenciaArchivo);
        model.addAttribute("transId", transId);

        //Validar por Refactoring de SICDI
//        List<ExpedienteDTO> expedienteDTOs = expedientes.stream()
//                .map(os -> modelMapper.map(os, ExpedienteDTO.class)).collect(Collectors.toList());

        //Validar por Refactoring de SICDI
//        List<TransExpedienteDetalleDTO> expedientesSeleccionadosDTOs = expedientesSeleccionados.stream()
//                .map(TransExpedienteDetalleDTO::new).collect(Collectors.toList());
//
//        List<TransExpedienteDetalleDTO> expedientesOtrasTransfDTOs = expedientesEnOtrasTransferencias.stream()
//                .map(TransExpedienteDetalleDTO::new).collect(Collectors.toList());

//        model.addAttribute("expedientes", expedienteDTOs);
//        model.addAttribute("expedientesSeleccionados", expedientesSeleccionadosDTOs);
//        model.addAttribute("expedientesEnOtrasTransferencias", expedientesOtrasTransfDTOs);

        return ResponseEntity.ok().body(model);
    }

    /**
     * Método para seleccionar los expedientes
     *
     * @param transId     Identificador de la transición
     * @param principal   usuario en sesión
     * @param model       modelo del template
     * @param expedientes expedientes para agregar
     * @return página resumen.
     */
    @RequestMapping(value = "/seleccionar-expediente", method = RequestMethod.POST)
    public ResponseEntity<String> asignarExpedientes(@RequestParam("trans") Integer transId,
                                                     Principal principal, Model model,
                                                     @RequestParam(value = "expedientes", required = false)
                                                     Long[] expedientes) {
        if (!genericSessionValidation(principal)) {
            model.addAttribute(ERROR_TAG, AppConstants.SESSION_EXPIRED);
            return ResponseEntity.badRequest().body("security-denied");
        }

        //Validar por Refactoring de SICDI
        //final Usuario usuarioSesion = getUsuario(principal);
        final TransferenciaArchivo transferenciaArchivo = transferenciaService.findOneTransferenciaArchivo(transId);
//        if (!transferenciaService.permisoEditarTransferencia(transferenciaArchivo, usuarioSesion)) {
//            return ResponseEntity.badRequest().body("security-denied");
//        }
        transExpedienteDetalleService.eliminarExpedientesTransferencia(transferenciaArchivo);
        //transferenciaArchivoDetalleService.eliminarDocumentosExpedientesTransferencia(transferenciaArchivo, usuarioSesion);
        if (expedientes != null) {
            for (Long expediente : expedientes) {
//Validar por Refactoring de SICDI
                //Expediente pExpediente = expedienteService.finById(expediente);
//                List<DocumentoDependencia> documentosExpediente = documentoDependenciaService
//                        .documentosExpedienteUsuario(pExpediente, usuarioSesion);
//                List<DocumentoDependencia> documentosEnTransferencia = documentoDependenciaService
//                        .listarDocumentosOtrasTransferencias(usuarioSesion, transferenciaArchivo);
//                transferenciaArchivoDetalleService.guardarListaDocumentosTransferencia(transferenciaArchivo,
//                        documentosExpediente, documentosEnTransferencia, usuarioSesion);
//                transExpedienteDetalleService.guardarExpedienteTransferencia(transferenciaArchivo,
//                        pExpediente, usuarioSesion);
            }
        }
        List<TransferenciaTransicion> findTransferenciaTransicionRechazado = transferenciaTransicionService
                .findTransferenciaTransicionRechazado(transferenciaEstadoService.getById(ESTADO_RECHAZADO),
                        transferenciaArchivo);
        if (!findTransferenciaTransicionRechazado.isEmpty()) {
            return ResponseEntity.ok().body(PATH + "/resumen/" + transId);
        }
        return ResponseEntity.ok().body(PATH + "/seleccionar-documentos/" + transId);
    }

    /**
     * Método que retorna la página para seleccionar los documentos que van a estar en la transferencia de archivo
     *
     * @param transId   Identificador de la transferencia
     * @param principal usuario en sesión
     * @param model     modelo del template
     * @return página para seleccionar los documentos.
     */
    @RequestMapping(value = "/seleccionar-documentos", method = RequestMethod.GET)
    public ResponseEntity<Model> seleccionarDocumentos(@RequestParam("trans") Integer transId, Principal principal,
                                                       Model model) {
        if (!genericSessionValidation(principal)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        //Validar por Refactoring de SICDI
        //Usuario usuarioSesion = getUsuario(principal);

        TransferenciaArchivo transferenciaArchivo = transferenciaService.findOneTransferenciaArchivo(transId);

        //Validar por Refactoring de SICDI
//        if (!transferenciaService.permisoEditarTransferencia(transferenciaArchivo, usuarioSesion)) {
//            return ResponseEntity.badRequest().body(model);
//        }

        //Validar por Refactoring de SICDI
//        List<TrdDTO> documentoXtrdDadoUsuario = documentoDependenciaService
//                .documentoXtrdDadoUsuario(usuarioSesion, transferenciaArchivo.getUsuOrigenCargo().getId());
//        List<DocumentoDependencia> documentosEnTransferencia = documentoDependenciaService
//                .listarDocumentosOtrasTransferencias(usuarioSesion, transferenciaArchivo);
//        List<TransferenciaArchivoDetalle> documentosXTransferenciaArchivo = transferenciaArchivoDetalleService
//                .buscarDocumentosTransferencia(transferenciaArchivo);
//
//        List<TrdApiDTO> trdDTOs = documentoXtrdDadoUsuario.stream().map(TrdApiDTO::getWithDocsDTO)
//                .collect(Collectors.toList());
//
//        List<DocumentoDependenciaDTO> documentosEntransferenciaDTOs = documentosEnTransferencia.stream()
//                .map(DocumentoDependenciaDTO::customMap).collect(Collectors.toList());

//        List<TransferenciaArchivoDetalleDTO> transferenciaArchivoDetalleDTOs = documentosXTransferenciaArchivo.stream()
//                .map(TransferenciaArchivoDetalleDTO::customMapTransferenciaArchivoDetalle).collect(Collectors.toList());

        model.addAttribute("transId", transId);
        //model.addAttribute("trds", trdDTOs);
        //model.addAttribute("documentosXTransferenciaArchivo", transferenciaArchivoDetalleDTOs);
        //model.addAttribute("documentosEnTransferencia", documentosEntransferenciaDTOs);
        return ResponseEntity.ok().body(model);
    }

    /**
     * Método que recibe los documentos seleccionados y los registra en el sistema.
     *
     * @param transId    Identificador de la transferencia
     * @param documentos Identificadores de los documentos a asignar
     * @param principal  usuario en sesión
     * @param model      modelo del template
     * @return página de continuación del proceso.
     */
    @RequestMapping(value = "/seleccionar-documentos", method = RequestMethod.POST)
    public ResponseEntity<String> asignarDocumentos(@RequestParam("trans") Integer transId,
                                                    @RequestParam(value = "documentos", required = false)
                                                    Integer[] documentos,
                                                    Principal principal, Model model) {
        if (!genericSessionValidation(principal)) {
            return ResponseEntity.badRequest().body("security-denied");
        }

        //Validar por Refactoring de SICDI
        //Usuario usuarioSesion = getUsuario(principal);
        TransferenciaArchivo transferenciaArchivo = transferenciaService.findOneTransferenciaArchivo(transId);
//        List<DocumentoDependencia> documentosEnTransferencia = documentoDependenciaService
//                .listarDocumentosOtrasTransferencias(usuarioSesion, transferenciaArchivo);

//        if (!transferenciaService.permisoEditarTransferencia(transferenciaArchivo, usuarioSesion)) {
//            return ResponseEntity.badRequest().body("security-denied");
//        }

        transferenciaArchivoDetalleService.eliminarDocumentosTransferencia(transferenciaArchivo);

        //Validar por Refactoring de SICDI
//        if (documentos != null) {
//            for (Integer documento : documentos) {
//                boolean find = documentosEnTransferencia.stream()
//                        .anyMatch(documentoDependencia -> documentoDependencia.getId().equals(documento));
//                if (!find) {
//                    DocumentoDependencia documentoDependencia = documentoDependenciaService.buscarPorId(documento);
//                    transferenciaArchivoDetalleService.guardarDocumentoTransferencia(transferenciaArchivo,
//                            documentoDependencia, usuarioSesion);
//                }
//            }
//        }
        return ResponseEntity.ok().body(PATH + "/resumen/" + transId);
    }

    /**
     * Método que retorna la página para ver el resumen de la transferencia.
     *
     * @param transId   Identificador de la transferencia
     * @param principal usuario en sesión
     * @param model     modelo del template
     * @return página para ver el resumen del expediente.
     */
    @RequestMapping(value = "/resumen", method = RequestMethod.GET)
    public ResponseEntity<Model> resumenExpediente(@RequestParam("trans") Integer transId, Principal principal,
                                                   Model model) {
        if (!genericSessionValidation(principal)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        System.out.println("-----------START EXP RESUME CONTROLLER-----------");
        System.out.println("Resume tranf: " + transId);
        long startTime_ = System.currentTimeMillis();

        long startTime = System.currentTimeMillis();

        //Validar por Refactoring de SICDI
        //Usuario usuarioSesion = getUsuario(principal);
        TransferenciaArchivo transferenciaArchivo = transferenciaService.findOneTransferenciaArchivo(transId);

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Get user and TranfArch takes " + duration + " ms");

        startTime = System.currentTimeMillis();
        transferenciaArchivo.setFuid(encrypt(transferenciaArchivo.getFuid()));
        TransferenciaArchivoDTO transferenciaArchivoDTO = TransferenciaArchivoDTO
                .customMapTransferenciaArchivoDTO(transferenciaArchivo);
        duration = System.currentTimeMillis() - startTime;
        System.out.println("Map TransArch takes " + duration + " ms");

        startTime = System.currentTimeMillis();
        //Validar por Refactoring de SICDI
        //UsuarioDTO userSessionDto = new UsuarioDTO(usuarioSesion);

        duration = System.currentTimeMillis() - startTime;
        System.out.println("Map UserSession takes " + duration + " ms");

        /*Alerta en caso de no haber un jefe de dependencia asignado y ya hay una transferencia pendiente*/

        //Validar por Refactoring de SICDI
//        if (transferenciaArchivo.getOrigenDependencia().getJefe() == null) {
//           // model.addAttribute(FLASH_ERROR, PERMISOS_TRANSFERENCIA);
//            return ResponseEntity.badRequest().body(model);
//        }

        //Validar por Refactoring de SICDI
//        if (!transferenciaService.permisoVerTransferencia(transferenciaArchivo, usuarioSesion)) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }

        startTime = System.currentTimeMillis();
        List<TransExpedienteDetalle> expedientesSeleccionados = transExpedienteDetalleService
                .buscarXTransferenciaArchivo(transferenciaArchivo);
        List<TransferenciaArchivoDetalle> documentosXTransferenciaArchivo = transferenciaArchivoDetalleService
                .buscarDocumentosTransferencia(transferenciaArchivo);
        List<TransferenciaTransicion> transiciones = transferenciaTransicionService
                .findTransferenciaTransiciones(transferenciaArchivo);
        List<TransferenciaObservacion> observaciones = transferenciaObservacionService
                .observacionesPorTranferencia(transferenciaArchivo);
        duration = System.currentTimeMillis() - startTime;
        System.out.println("Get TransfLists takes " + duration + " ms");

        startTime = System.currentTimeMillis();

        //Validar por Refactoring de SICDI
//        List<TransExpedienteDetalleDTO> expedientesSeleccionadosDTOs = expedientesSeleccionados.stream()
//                .map(TransExpedienteDetalleDTO::new).collect(Collectors.toList());

        List<TransferenciaArchivoDetalleDTO> transferenciaArchivoDetalleDTOs = documentosXTransferenciaArchivo.stream()
                .map(TransferenciaArchivoDetalleDTO::customMapTransferenciaArchivoDetalle).collect(Collectors.toList());

        List<TransferenciaTransicionDTO> transferenciaTransicionDTOs = transiciones.stream()
                .map(TransferenciaTransicionDTO::customMapTransferenciaTransicion).collect(Collectors.toList());

        List<TransferenciaObservacionDTO> transferenciaObservacionDTOs = observaciones.stream()
                .map(TransferenciaObservacionDTO::customMapTransferenciaObservacionDTO).collect(Collectors.toList());

        duration = System.currentTimeMillis() - startTime;
        System.out.println("Map TransfLists takes " + duration + " ms");

        model.addAttribute("transferenciaArchivo", transferenciaArchivoDTO);
        model.addAttribute("transiciones", transferenciaTransicionDTOs);
        //model.addAttribute("usuario", userSessionDto);
        //model.addAttribute("expedientesSeleccionados", expedientesSeleccionadosDTOs);
        model.addAttribute("documentosXTransferenciaArchivo", transferenciaArchivoDetalleDTOs);
        model.addAttribute("observaciones", transferenciaObservacionDTOs);
        model.addAttribute("justificacionesDefecto", listarJustificacionesPorDefectoActivas());

//        if (transferenciaArchivo.getDestinoUsuario().getId().equals(usuarioSesion.getId())
//                && transferenciaArchivo.getIndAprobado() == 1) {

            startTime = System.currentTimeMillis();

            //Validar por Refactoring de SICDI
//            List<TransferenciaArchivoDetalle> documentosNoPosesionTransferencia = transferenciaArchivoDetalleService
//                    .documentosNoPosesionTransferencia(transferenciaArchivo, usuarioSesion);
//            List<DocumentoDependencia> documentosTransferencia = documentoDependenciaService
//                    .listarDocumentosOtrasTransferencias(usuarioSesion, transferenciaArchivo);

            //Validar por Refactoring de SICDI
//            for (TransferenciaArchivoDetalle documento : documentosXTransferenciaArchivo) {
//                if (documentosTransferencia.stream()
//                        .anyMatch(documentoDependencia -> documento.getDocumentoDependencia().getId()
//                                .equals(documentoDependencia.getId()))) {
//                    documentosNoPosesionTransferencia.add(documento);
//                }
//            }

            duration = System.currentTimeMillis() - startTime;
            System.out.println("Get TransfDocs takes " + duration + " ms");

            startTime = System.currentTimeMillis();

        //Validar por Refactoring de SICDI
//            List<TransferenciaArchivoDetalleDTO> documentosNoPosesionTransferenciaDTOs = documentosNoPosesionTransferencia
//                    .stream().map(TransferenciaArchivoDetalleDTO::customMapTransferenciaArchivoDetalle)
//                    .collect(Collectors.toList());
            duration = System.currentTimeMillis() - startTime;
            System.out.println("Map TransfDocs takes " + duration + " ms");

            startTime = System.currentTimeMillis();

            //Validar por Refactoring de SICDI
//            List<TransExpedienteDetalle> expedientesNoPosesionTransferencia = transExpedienteDetalleService
//                    .expedientesNoPosesionTransferencia(transferenciaArchivo, usuarioSesion);
//            expedientesNoPosesionTransferencia.addAll(transExpedienteDetalleService
//                    .buscarOtrosExpedientesEnTranseferencia(usuarioSesion, transferenciaArchivo));
            duration = System.currentTimeMillis() - startTime;
            System.out.println("Get TransfExp takes " + duration + " ms");

            startTime = System.currentTimeMillis();

            //Validar por Refactoring de SICDI
//            List<TransExpedienteDetalleDTO> expedientesNoPosesionTransferenciaDTOs = expedientesNoPosesionTransferencia
//                    .stream().map(TransExpedienteDetalleDTO::new).collect(Collectors.toList());
            duration = System.currentTimeMillis() - startTime;
            System.out.println("Map TransfExp takes " + duration + " ms");

            //model.addAttribute("documentosNoPosesionTransferencia", documentosNoPosesionTransferenciaDTOs);
//            model.addAttribute("expedientesNoPosesionTransferencia", expedientesNoPosesionTransferenciaDTOs);
 //       }

        duration = System.currentTimeMillis() - startTime_;
        System.out.println("----------- END EXP RESUME CONTROLLER " + duration + " ms -----------");
        return ResponseEntity.ok().body(model);
    }

    /**
     * Método que permite enviar la transferencia al usuario destino
     *
     * @param transId   identificador de la transferencia
     * @param principal usuario en sesión
     * @return página de resumen de transferencia
     */
    @RequestMapping(value = "/enviarTransferencia", method = RequestMethod.GET)
    public ResponseEntity<String> enviarTransferencia(@RequestParam("transId") Integer transId, Principal principal) {
        if (!genericSessionValidation(principal)) {
            return ResponseEntity.badRequest().body("security-denied");
        }

        //Usuario usuarioSesion = getUsuario(principal);
        TransferenciaArchivo transferenciaArchivo = transferenciaService.findOneTransferenciaArchivo(transId);

        //Validar por Refactoring de SICDI
//        if (!transferenciaService.permisoEditarTransferencia(transferenciaArchivo, usuarioSesion)) {
//            return ResponseEntity.badRequest().body("security-denied");
//        }


        //Validar por Refactoring de SICDI
//        Usuario usuario = transferenciaArchivo.getDestinoUsuario();
//        if (!usuario.getUsuActivo()) {
//            return ResponseEntity.badRequest().body("El usuario destino " + usuario.getUsuGrado().getId()
//                    + " " + usuario.getNombre() + " no se encuentra activo en el sistema por: "
//                    + usuario.getRazonInhabilitar().getTextoRazon());
//        }

        //transferenciaService.enviarTransferencia(transferenciaArchivo, usuarioSesion);
        return ResponseEntity.ok().body(PATH + "/resumen/" + transId);
    }

    /**
     * Método para recibir la transferencia por el usuario destino.
     *
     * @param transId   Identificador de la transferencia
     * @param cargo     Identificador del cargo seleccionado
     * @param principal usuario en sesión
     * @return Código de respuesta exitoso, en caso contrario 400 o 401
     */
    @RequestMapping(value = "/recibir-destinatario", method = RequestMethod.POST)
    public ResponseEntity<?> recibirTransferenciaDestinatario(@RequestParam("trans") Integer transId,
                                                              @RequestParam("cargo") Integer cargo, Principal principal) {
        if (!genericSessionValidation(principal)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        //Validar por Refactoring de SICDI
       // Usuario usuarioSesion = getUsuario(principal);


        //Cargo pCargo = cargosRepository.findById(cargo).get();
        TransferenciaArchivo transferenciaArchivo = transferenciaService.findOneTransferenciaArchivo(transId);

//        if (!transferenciaService.permisoAprobarDestinatario(transferenciaArchivo, usuarioSesion)) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }

       // transferenciaService.aprobarDestinatario(transferenciaArchivo, pCargo, usuarioSesion);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Método para anular una transferencia
     *
     * @param transId   Identificador de una transferencia
     * @param principal usuario en sesión
     * @return página listar transferencias si todo sale correcto
     */
    @RequestMapping(value = "/anular", method = RequestMethod.GET)
    public ResponseEntity<String> anularTransferencia(@RequestParam("trans") Integer transId, Principal principal) {
        if (!genericSessionValidation(principal)) {
            return ResponseEntity.ok().body("/bandeja/entrada");
        }

        //Validar por Refactoring de SICDI
        //Usuario usuarioSesion = getUsuario(principal);
        TransferenciaArchivo transferenciaArchivo = transferenciaService.findOneTransferenciaArchivo(transId);

//        if (!transferenciaService.permisoEditarTransferencia(transferenciaArchivo, usuarioSesion)) {
//            return ResponseEntity.ok().body("/bandeja/entrada");
//        }

//        transferenciaService.anularTransferencia(transferenciaArchivo, usuarioSesion);
        return ResponseEntity.ok().body(PATH + "/listar");
    }

    @RequestMapping(value = "/devolver", method = RequestMethod.POST)
    public ResponseEntity<?> devolverTransferencia(@RequestParam("trans") Integer transId,
                                                   @RequestParam("usuId") Integer usuId,
                                                   @RequestParam(value = "justificacion") String justificacion,
                                                   Principal principal, Model model) {
        if (!genericSessionValidation(principal)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        //final Usuario origenUsuario = getUsuario(principal);
        //final Usuario usuario = usuarioService.findOne(usuId);
        final TransferenciaArchivo transferenciaArchivo = transferenciaService.findOneTransferenciaArchivo(transId);

        //Validar por Refactoring de SICDI
//        if (!transferenciaService.permisoReenviarTransferencia(transferenciaArchivo, origenUsuario)) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }

        //Validar por Refactoring de SICDI
//        if (origenUsuario.getId().equals(usuId)) {
//            return new ResponseEntity<>("No puede reenviarse una transferencia a usted mismo.", HttpStatus.BAD_REQUEST);
//        }

        //Validar por Refactoring de SICDI
//        if (usuario.getClasificacion().getOrden() < transferenciaArchivo.getDestinoUsuario().getClasificacion().getOrden()) {
//            return new ResponseEntity<>("La transferencia no se puede efectuar por que el usuario "
//                    + usuario.getUsuGrado().getId() + " " + usuario.getNombre()
//                    + " tiene una clasificación menor a la suya. </br> Por favor verificar e intentar nuevamente.",
//                    HttpStatus.BAD_REQUEST);
//        }
//        if (usuario.getDependencia().getJefe() == null) {
//            return new ResponseEntity<>("La transferencia no se puede efectuar por que la dependencia del usuario "
//                    + usuario.getUsuGrado().getId() + " " + usuario.getNombre()
//                    + " no tiene un jefe de dependencia. </br> Por favor verificar e intentar nuevamente.",
//                    HttpStatus.BAD_REQUEST);
//        }
//        if (!usuario.getUsuActivo()) {
//            return new ResponseEntity<>("El usuario destino " + usuario.getUsuGrado().getId() + " "
//                    + usuario.getNombre() + " no se encuentra activo en el sistema por: "
//                    + usuario.getRazonInhabilitar().getTextoRazon(), HttpStatus.BAD_REQUEST);
//        }

        //final Usuario usuarioDestino = usuarioService.findOne(usuId);
        try {

            //Validar por Refactoring de SICDI
//            TransferenciaArchivo trReenviada = transferenciaService.reenviarTransferencia(transferenciaArchivo,
//                    usuarioDestino justificacion);
//            String retorno = "/transferencia-archivo/resumen/" + trReenviada.getId();
//            return ResponseEntity.ok(retorno);
        } catch (Exception ex) {
            LOG.error("Error al reenviar Transferencia id: " + transId, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    /**
     * Método para aprobar la trasferencia
     *
     * @param transId   Identificador de la transferencia
     * @param principal usuario en sesión
     * @return página resumen de la transferencia
     */
    @RequestMapping(value = "/aprobar", method = RequestMethod.GET)
    public ResponseEntity<String> aprobarTransferencia(@RequestParam("trans") Integer transId, Principal principal) {
        if (!genericSessionValidation(principal)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        //Validar por Refactoring de SICDI
        //Usuario usuarioSesion = getUsuario(principal);
        TransferenciaArchivo transferenciaArchivo = transferenciaService.findOneTransferenciaArchivo(transId);

//        if (!transferenciaService.permisoAprobarJefe(transferenciaArchivo, usuarioSesion)) {
//            return ResponseEntity.badRequest().body("security-denied");
//        }

        //Validar por Refactoring de SICDI
//        Usuario usuario = transferenciaArchivo.getDestinoUsuario();
//        if (!usuario.getUsuActivo()) {
//            return ResponseEntity.badRequest().body("El usuario destino " + usuario.getUsuGrado().getId()
//                    + " " + usuario.getNombre() + " no se encuentra activo en el sistema por: "
//                    + usuario.getRazonInhabilitar().getTextoRazon());
//        }

//        transferenciaService.aprobarTransferencia(transferenciaArchivo, usuarioSesion);
        return ResponseEntity.ok(PATH + "/resumen/" + transId);
    }

    /**
     * Método para rechazar la transferencia ya sea por el usuario destinatario o jefe dependencia origen.
     *
     * @param transId     Identificador de la transferencia
     * @param observacion observación realizada
     * @param principal   usuario en sesión
     * @param req         request
     * @return Código de respuesta exitoso, en caso contrario 400 o 401
     */
    @RequestMapping(value = "/rechazar", method = RequestMethod.POST)
    public ResponseEntity<?> rechazarTransferencia(@RequestParam("trans") Integer transId,
                                                   @RequestParam(value = "observacion") String observacion,
                                                   Principal principal, HttpServletRequest req) {

        if (!genericSessionValidation(principal)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        //Validar por Refactoring de SICDI
        //Usuario usuarioSesion = getUsuario(principal);
        TransferenciaArchivo transferenciaArchivo = transferenciaService.findOneTransferenciaArchivo(transId);

//        if (!transferenciaService.permisoRechazar(transferenciaArchivo, usuarioSesion)) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }

        //transferenciaService.rechazarTransferencia(transferenciaArchivo, observacion, usuarioSesion);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
