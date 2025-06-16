package com.ejercito.transferencia.infrastructure.controller;

import com.ejercito.transferencia.application.impl.TransferenciaArchivoService;
import com.ejercito.transferencia.domain.model.AppConstants;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

@RestController
@RequestMapping(value = "apiv1/utilidad")
public class UtilRestController extends UtilController {

    /**
     * 2018-05-02 jgarcia@controltechcg.com Issue #159 (SICDI-Controltech)
     * feature-159: Constantes para los atributos a colocar en el HTTP Request.
     */
    private static final String USERNAME_ATTR = "username";
    private static final String USERPROFILE_ATTR = "userprofile";
    private static final String USER_DOMINIO_ATTR = "user_dominio";
    private static final String USER_CAN_USE_OWA_LINK_ATTR = "user_can_use_owa_link";
    private static final String OWA_LINK_URL_ATTR = "owa_link_url";
    private static final String USU_ACTIVO_ATT = "usuActivo";
    private static final String NOT_TRANS_ATT = "notTransferencia";
    private static final String USER_FIRMA_CARGA = "userFirmaCarga";
    private static final String USER_FIRMA = "userFirma";

    public static final String ERROR_TAG = "msjError";

    public static final String PAGE_INDEX_TAG = "pageIndex";

    public static final String TOTAL_PAGES_TAG = "totalPages";

    public static final String PAGE_SIZE_TAG = "pageSize";

    public static final String LABEL_INFORMACION_TAG = "labelInformacion";

    /**
     * Mapper object
     */

    //Validar por Refactoring de SICDI
//    @Autowired
//    ModelMapper mapper;

    //Validar por Refactoring de SICDI
//    @Autowired
//    CargosRepository cargosRepository;

    /*
     * 2018-05-02 jgarcia@controltechcg.com Issue #159 (SICDI-Controltech)
     * feature-159: URL al OWA.
     */
    @Value("${com.mil.imi.sicdi.owa.url}")
    private String owaURL;

    @Autowired
    private TransferenciaArchivoService transferenciaArchivoService;

    //Validar por Refactoring de SICDI
//    @Autowired
//    private UsuarioFirmaCargaService usuarioFirmaCargaService;

    //Validar por Refactoring de SICDI
//    @Autowired
//    UsuarioService usuarioService;


    @Value("${com.mil.imi.sicdi.ofs.key}")
    private String OFSKey;

    @RequestMapping(value = "/encrypt", method = {RequestMethod.GET, RequestMethod.POST})
    public String encrypt(@RequestParam(value = "value", required = true) String value) {
        final String initVector = "RandomInitVector";
        if (value!=null) {
            try {
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
                SecretKeySpec skeySpec = new SecretKeySpec(OFSKey.getBytes(StandardCharsets.UTF_8), "AES");

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

                byte[] encrypted = cipher.doFinal(value.getBytes());
                return Base64.encodeBase64URLSafeString(encrypted);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @ModelAttribute("utilController")
    protected UtilController getUtilController() {
        return null;
    }

    /**
     * verificación de existencia de usuario
     */
    public boolean genericSessionValidation(Principal principal)  {
        return principal != null;
    }

    @RequestMapping(value = "/userInfo", method = {RequestMethod.GET})
    public ResponseEntity<Model> userInfo(Model model, Principal principal) {
        if (!genericSessionValidation(principal)) {
            model.addAttribute(ERROR_TAG, AppConstants.SESSION_EXPIRED);
            return new ResponseEntity<>(model, HttpStatus.UNAUTHORIZED);
        }

        //Validar por Refactoring de SICDI
//        Usuario usuSession = getUsuario(principal);
//        String username = usuSession.getLogin();

        //final Usuario usuario = usuarioService.findByLoginAndActivo(username.trim().toLowerCase(), true);
//        if (usuario == null) {
//            model.addAttribute(ERROR_TAG, AppConstants.SESSION_EXPIRED);
//            return new ResponseEntity<>(model, HttpStatus.UNAUTHORIZED);
//        }

        //final Dominio dominio = usuario.getDominio();
        //final boolean visualizaLinkOWA = dominio.getActivo() && dominio.getVisualizaLinkOWA();

//        final Integer numNotificaciones = transferenciaArchivoService
//                .retornaNumeroNotificacionesPendientesTransferenciaArchivo(usuario.getId());

//        UsuarioFirmaCarga usuarioFirmaCarga = usuarioFirmaCargaService.findUsuarioCargaByUsuario(usuario);
//
//        setUserInformation(model, usuario.toString(), usuario.getPerfil().toString(), dominio.getNombre(),
//                visualizaLinkOWA, usuario.getUsuActivo(), numNotificaciones, usuarioFirmaCarga,
//                usuario.getImagenFirma() != null);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
//Validar por Refactoring de SICDI
//    private void setUserInformation(final Model request, final String userName, final String profileName,
//                                    final String dominioNombre, final boolean canUseOWALink, final Boolean usuarioActivo,
//                                    final Integer numNotificaciones, final UsuarioFirmaCarga usuarioFirmaCarga,
//                                    final boolean usuFirma) {
//        request.addAttribute(USERNAME_ATTR, String.valueOf(userName));
//        request.addAttribute(USERPROFILE_ATTR, String.valueOf(profileName));
//        request.addAttribute(USER_DOMINIO_ATTR, String.valueOf(dominioNombre));
//        request.addAttribute(USER_CAN_USE_OWA_LINK_ATTR, String.valueOf(canUseOWALink));
//        request.addAttribute(USU_ACTIVO_ATT, String.valueOf(usuarioActivo));
//       // request.addAttribute(OWA_LINK_URL_ATTR, String.valueOf(owaURL));
//        request.addAttribute(NOT_TRANS_ATT, String.valueOf(numNotificaciones));
//
//        //Validar por Refactoring de SICDI
//      //  if (usuarioFirmaCarga != null) {
//            //request.addAttribute(USER_FIRMA_CARGA, getMapper().map(usuarioFirmaCarga, UsuarioFirmaCargaDTO.class));
//      //  }
//        request.addAttribute(USER_FIRMA, String.valueOf(usuFirma));
//    }

//    public ModelMapper getMapper() {
//        return mapper;
//    }

}
