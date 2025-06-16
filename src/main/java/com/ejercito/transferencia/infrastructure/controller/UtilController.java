package com.ejercito.transferencia.infrastructure.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.ejercito.transferencia.domain.model.AppConstants;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class UtilController {

    public static final String REDIRECT_ANGULAR_PATH = "forward:/index.html";

    /**
     * Variable para activar los controladores migrados
     */
    @Value("${migration.redirect}")
    @Getter
    private Boolean migRedirect;

    /**
     * 2018-09-24 samuel.delgado@controltechcg.com Issue #174 (SICDI-Controltech)
     * feature-174: constante para páginas del administrador
     */
    public static final Integer ADMIN_PAGE_SIZE = 15;

    @Value("${com.mil.imi.sicdi.ofs.key}")
    private String OFSKey;

    //Validar por Refactoring de SICDI
//    @Autowired
//    UsuarioRepository usuR;

    //Validar por Refactoring de SICDI
//    @Autowired
//    private RolRepository rolRep;

    private final Map<String, Integer> userIds = new HashMap<>();
    private final Map<Integer, String> userNombres = new HashMap<>();
    private final Map<Integer, String> userLogins = new HashMap<>();

    /**
     * Establece el formato de fecha
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
                true, 10));
    }

    /**
     * Obtiene el usuario activo en sesión.
     *
     * @param principal Objeto de A&A.
     * @return Usuario activo.
     */
    //Validar por Refactoring de SICDI
//    protected Usuario getUsuario(Principal principal) {
//
//        Usuario usuario;
//        /*
//         * 2017-09-12 jgarcia@controltechcg.com Issue #123 (SICDI-Controltech)
//         * hotfix-123: Corrección en búsqueda de usuarios para que únicamente
//         * presente información de usuarios activos.
//         */
//
//        usuario = usuR.getByLoginAndActivoTrue(principal.getName());
//        return usuario;
//    }

    /**
     * Obtiene el login del usuario
     *
     * @param id User id
     * @return User login
     */
    protected String login(Integer id) {
        String login = userLogins.get(id);
        if (StringUtils.isBlank(login)) {
            //Validar por Refactoring de SICDI
            //Usuario u = usuR.getReferenceById(id);
            //indexUsuario(u);
            return userLogins.get(id);
        }
        return login;
    }



    /**
     * Obtiene el nombre del usuario
     *
     * @param id User id
     * @return User name
     */
    protected String nombre(Integer id) {
        String x = userNombres.get(id);
        if (StringUtils.isBlank(x)) {
            //Validar por Refactoring de SICDI
            //Usuario u = usuR.getReferenceById(id);
            //indexUsuario(u);
            return userNombres.get(id);
        }
        return x;
    }

    /**
     * Obtiene el id del usuario principal
     *
     * @return User id
     */
    protected Integer getUsuarioId(Principal principal) {
        Integer id = userIds.get(principal.getName());
        if (id == null) {

            //Validar por Refactoring de SICDI
//            Usuario u = getUsuario(principal);
//            id = u.getId();
//            indexUsuario(u);
        }
        return id;
    }

    /**
     * Elimina la información en memoria de un usuario
     */
    protected synchronized void unindex(Principal principal) {
        if(principal == null){
            return;
        }
        String login = principal.getName();
        Integer id = getUsuarioId(principal);
        userIds.remove(login);
        userLogins.remove(id);
        userNombres.remove(id);
    }

    /**
     * Almacena la información del usuario en memoria
     *
     * //@param u User
     */
    //Validar por Refactoring de SICDI
//    private synchronized void indexUsuario(Usuario u) {
//
//        String login = u.getLogin();
//        String nombre = u.getNombre();

        //String grado = u.getUsuGrado().getId();
//        if (StringUtils.isNotBlank(grado) && !AppConstants.SIN_GRADO.equals(grado)) {
//            nombre = grado + ". " + nombre;
//        }

        //Integer id = u.getId();

//        userIds.put(login, id);
//        userNombres.put(id, nombre);
//        userLogins.put(id, login);

//    }

    public boolean isAuthorized(String roles) {

        String[] split = roles.split(",");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> granted = authentication.getAuthorities();
        boolean is = true;
        for (String rol : split) {
            boolean found = false;
            for (GrantedAuthority grantedAuthority : granted) {
                if (grantedAuthority.getAuthority().equals(rol)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                is = false;
            }
        }
        return is;
    }

    /**
     * Indica si el usuario en sesión tiene asignado un rol.
     *
     * @param rol ID del rol.
     * @return {@code true} si el usuario en sesión tiene asignado el rol; de lo
     * contrario, {@code false}.
     */
    /*
     * 2018-05-08 jgarcia@controltechcg.com Issue #160 (SICDI-Controltech)
     * feature-160.
     */
    public boolean isAuthorizedRol(final String rol) {
        final Collection<? extends GrantedAuthority> authorities = SecurityContextHolder
                .getContext().getAuthentication().getAuthorities();
        for (final GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(rol)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return True if Principal has any admin role, False instead
     */
    public boolean hasAdminRole() {
        /*
         * 2017-06-22 jgarcia@controltechcg.com Issue #111 (SICDI-Controltech)
         * hotfix-111: Corrección en implementación de función utilitaria que
         * indica si el usuario tiene rol administrador.
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> granted = authentication.getAuthorities();

        for (GrantedAuthority grantedAuthority : granted) {
            if (grantedAuthority.getAuthority().startsWith(AppConstants.PREFIX_ROLE_ADMIN)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return True if Principal has any archivo role, False instead
     */
    public boolean hasArchivoRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> granted = authentication.getAuthorities();
        boolean isArchivo = false;
        for (GrantedAuthority grantedAuthority : granted) {
            if (grantedAuthority.getAuthority().startsWith(AppConstants.PREFIX_ROLE_ARCHIVO)) {
                isArchivo = true;
                break;
            }
        }

        return isArchivo;
    }

    /**
     * @return The first admin role found for the principal
     */
    public String getFirstAdminRolePath() {
        boolean pathFound = false;
        String rolePath = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> granted = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : granted) {
            if (grantedAuthority.getAuthority().startsWith(AppConstants.PREFIX_ROLE_ADMIN)) {
                rolePath = getPathForRole(grantedAuthority.getAuthority());
                if (rolePath != null) {
                    pathFound = true;
                    break;
                }

            }
        }
        if (pathFound) {
            return rolePath;
        } else {
            return "redirect:/access-denied";
        }

    }

    /**
     * @return The first admin role found for the principal
     */
    public String getFirstArchivoRolePath() {
        boolean pathFound = false;
        String rolePath = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> granted = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : granted) {
            if (grantedAuthority.getAuthority().startsWith(AppConstants.PREFIX_ROLE_ARCHIVO)) {
                rolePath = getPathForRole(grantedAuthority.getAuthority());
                if (rolePath != null) {
                    pathFound = true;
                    break;
                }

            }
        }
        if (pathFound) {
            return rolePath;
        } else {
            return "redirect:/access-denied";
        }

    }

    private String getPathForRole(String role) {
        switch (role) {
            case AppConstants.ADMIN_PERFILES:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_PERFILES);
            case AppConstants.ADMIN_DASHBOARD:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_DASHBOARD);
            case AppConstants.ADMIN_DEPENDENCIAS:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_DEPENDENCIAS);
            case AppConstants.ADMIN_USUARIOS:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_USUARIOS);
            case AppConstants.ADMIN_TIPOS_DESTINATARIO:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_TIPOS_DESTINATARIO);
            case AppConstants.ADMIN_DESTINATARIOS:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_DESTINATARIOS);
            case AppConstants.ADMIN_TRD:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_TRD);
            case AppConstants.ADMIN_AUDITORIA:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_AUDITORIA);
            case AppConstants.ADMIN_CLASIFICACIONES:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_CLASIFICACIONES);
            case AppConstants.ADMIN_PLANTILLAS:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_PLANTILLAS);
            case AppConstants.ADMIN_PROCESOS:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_PROCESOS);
            case AppConstants.ADMIN_TIPOLOGIA:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_TIPOLOGIA);
            case AppConstants.ADMIN_TIPO_DOCUMENTO:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_TIPO_DOCUMENTO);
            case AppConstants.ADMIN_BANDEJAS:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_BANDEJAS);
            case AppConstants.ADMIN_CAPACITACION:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_CAPACITACION);
            case AppConstants.ADMIN_GRADOS:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_GRADOS);
            case AppConstants.ADMIN_INFORMES:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_INFORMES);
            case AppConstants.ADMIN_FORMATOS:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_FORMATOS);
            case AppConstants.ARCHIVO_CENTRAL:
                return String.format("redirect:%s", AppConstants.PATH_ARCHIVO_CENTRAL);
            case AppConstants.ADMIN_PAR_NOMBRE_EXPEDIENTE:
                return String.format("redirect:%s", AppConstants.PATH_ADMIN_PAR_NOMBRE_EXPEDIENTE);
            default:
                return null;
        }
    }

    public String getPathArchivo() {

        HashMap<String, String> grantsMap = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> granted = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : granted) {
            if (grantedAuthority.getAuthority().startsWith(AppConstants.PREFIX_ROLE_ARCHIVO)) {
                grantsMap.put(grantedAuthority.getAuthority(), grantedAuthority.getAuthority());
            }
        }
        if (grantsMap.containsKey(AppConstants.ARCHIVO_CENTRAL)) {
            return String.format("redirect:%s", AppConstants.PATH_ARCHIVO_CENTRAL);
        } else {
            return "redirect:/access-denied";
        }
    }

    public String getPathAdmin() {

        HashMap<String, String> grantsMap = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> granted = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : granted) {
            if (grantedAuthority.getAuthority().startsWith(AppConstants.PREFIX_ROLE_ADMIN)) {
                grantsMap.put(grantedAuthority.getAuthority(), grantedAuthority.getAuthority());
            }
        }

        if (grantsMap.containsKey(AppConstants.ADMIN_PERFILES)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_PERFILES);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_DASHBOARD)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_DASHBOARD);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_DEPENDENCIAS)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_DEPENDENCIAS);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_USUARIOS)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_USUARIOS);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_TIPOS_DESTINATARIO)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_TIPOS_DESTINATARIO);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_DESTINATARIOS)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_DESTINATARIOS);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_TRD)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_TRD);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_AUDITORIA)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_AUDITORIA);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_CLASIFICACIONES)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_CLASIFICACIONES);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_PLANTILLAS)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_PLANTILLAS);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_PROCESOS)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_PROCESOS);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_TIPOLOGIA)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_TIPOLOGIA);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_TIPO_DOCUMENTO)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_TIPO_DOCUMENTO);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_BANDEJAS)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_BANDEJAS);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_CAPACITACION)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_CAPACITACION);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_GRADOS)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_GRADOS);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_INFORMES)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_INFORMES);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_FORMATOS)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_FORMATOS);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_CARGOS)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_CARGOS);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_LOG)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_LOG);
        }
        if (grantsMap.containsKey(AppConstants.ADMIN_FIRMA)
                || grantsMap.containsKey(AppConstants.ADMIN_FIRMA_ARCHIVO)) {
            return String.format("redirect:%s", AppConstants.PATH_ADMIN_FIRMA);
        } else {
            return "redirect:/access-denied";
        }
    }

    /**
     * Agrega el controlador al modelo
     *
     * @return UtilController
     */
    @ModelAttribute("utilController")
    protected UtilController getUtilController() {
        return this;
    }

    /**
     * Reescribe los mensajes flash para que se renueven para el siguiente
     * request
     */
    public void byPassFlassAttributes(RedirectAttributes redirect, Model model) {
        Map<String, Object> map = model.asMap();
        for (String k : map.keySet()) {
            if (k.startsWith("FLASH")) {
                redirect.addFlashAttribute(k, map.get(k));
            }
        }
    }

    /*
     * 2018-09-24 samuel.delgado@controltechcg.com Issue #174 (SICDI-Controltech)
     * feature-174: Adición para la paginación.
     */

    /**
     * Agrega al modelo los atributos necesarios para la paginación.
     *
     * @param count    total de elementos
     * @param model    modelo
     * @param page     página
     * @param pageSize tamaño de paginas
     */
    public void adminPageable(Long count, Model model, Integer page, Integer pageSize) {
        int totalPages = 0;
        String labelInfo = "";

        if (count > 0) {
            //Validar por Refactoring de SICDI
//            PaginacionDTO paginacionDTO = PaginacionUtil.retornaParametros(toIntExact(count), page, pageSize);
//            totalPages = paginacionDTO.getTotalPages();
//            labelInfo = paginacionDTO.getLabelInformacion();
        }

        model.addAttribute("pageIndex", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("labelInformacion", labelInfo);
    }

    /***
     * Encripta un mensaje (AES)
     * @param value mensaje a encriptar
     * @return mensaje encriptado
     */
    /*
     * feature-gogs-26 samuel.delgado@controltechcg.com (SIGDI-CONTROLTECH): método que encripta
     */
    public String encrypt(String value) {
        final String initVector = "RandomInitVector";
        if (value != null) {
            try {
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
                SecretKeySpec sKeySpec = new SecretKeySpec(OFSKey.getBytes(StandardCharsets.UTF_8), "AES");

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);

                byte[] encrypted = cipher.doFinal(value.getBytes());
                //Validar por Refactoring de SICDI
                //return Base64.encodeBase64URLSafeString(encrypted);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    /***
     * Desencripta un mensaje (AES)
     * @ value mensaje a desencriptar
     * @return mensaje desencriptado
     */
    /*
     * feature-gogs-26 samuel.delgado@controltechcg.com (SIGDI-CONTROLTECH): método que desencripta
     */
    public String decrypt(String encrypted) {
        final String initVector = "RandomInitVector";
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec sKeySpec = new SecretKeySpec(OFSKey.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);

            //Validar por Refactoring de SICDI
           // byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

          //  return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Retorna el sistema operativo y el navegador de una petición (HttpServletRequest)
     * @param request petición
     * @return string
     */
    /*
     * feature-gogs-26 samuel.delgado@controltechcg.com (SIGDI-CONTROLTECH): método que da el nombre del navegador y OS
     */
    public String getOsAndBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        String user = userAgent.toLowerCase();

        String os;
        String browser = "";

        //=================OS=======================
        if (userAgent.toLowerCase().contains("windows")) {
            os = "Windows";
        } else if (userAgent.toLowerCase().contains("mac")) {
            os = "Mac";
        } else if (userAgent.toLowerCase().contains("x11")) {
            os = "Unix";
        } else if (userAgent.toLowerCase().contains("android")) {
            os = "Android";
        } else if (userAgent.toLowerCase().contains("iphone")) {
            os = "IPhone";
        } else {
            os = "UnKnown, More-Info: " + userAgent;
        }
        //===============Browser===========================
        if (user.contains("msie")) {
            String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser = substring.split(" ")[0].replace("MSIE", "IE")
                    + "-" + substring.split(" ")[1];
        } else {
            if (user.contains("safari") && user.contains("version")) {
                browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]
                        + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            } else if (user.contains("opr") || user.contains("opera")) {
                if (user.contains("opera"))
                    browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]
                            + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0])
                            .split("/")[1];
                else if (user.contains("opr"))
                    browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0])
                            .replace("/", "-")).replace("OPR", "Opera");
            } else if (user.contains("chrome")) {
                browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0])
                        .replace("/", "-");
            } else if ((user.contains("mozilla/7.0")) || (user.contains("netscape6"))
                    || (user.contains("mozilla/4.7")) || (user.contains("mozilla/4.78"))
                    || (user.contains("mozilla/4.08")) || (user.contains("mozilla/3"))) {
                //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
                browser = "Netscape-?";

            } else if (user.contains("firefox")) {
                browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0])
                        .replace("/", "-");
            } else if (user.contains("rv")) {
                browser = "IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
            } else {
                browser = "UnKnown, More-Info: " + userAgent;
            }
        }
        return os + " -- " + browser;
    }

    /***
     * Retorna la ip de una petición (HttpServletRequest)
     * @param request petición
     * @return string
     */
    /*
     * feature-gogs-26 samuel.delgado@controltechcg.com (SIGDI-CONTROLTECH): método que da la ip en un Request
     */
    public String getIpRequest(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Real-IP");
        if (ipAddress == null) {
            ipAddress = request.getHeader("REMOTE_ADDR");
        }
        ipAddress = (ipAddress == null || ipAddress.isBlank()) ? request.getHeader("X-FORWARDED-FOR") : ipAddress;
//        System.out.println("----------------> IP : "+ ipAddress +" <-----------------------------");
        return (ipAddress == null || ipAddress.isBlank()) ? "0.0.0.0" : ipAddress;
    }

    /***
     * Retorna el nombre de la máquina en una petición (HttpServletRequest)
     * @param request petición
     * @return string
     */
    /*
     * feature-gogs-26 samuel.delgado@controltechcg.com (SIGDI-CONTROLTECH): método que da el nombre de la máquina que
     * realiza un request.
     */
    public String getNameMachine(HttpServletRequest request) {
        String computerName = "not found";
        String remoteAddress = request.getHeader("REMOTE_ADDR");
        try {
            InetAddress inetAddress = InetAddress.getByName(remoteAddress);
            computerName = inetAddress.getHostName();
            if (computerName.equalsIgnoreCase("localhost")) {
                computerName = InetAddress.getLocalHost().getCanonicalHostName();
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown Host Exception: " + e.getMessage());
        }
        return computerName;
    }

    /***
     * genera un código QR dado un string
     * @param text texto a convertir en string
     * @return Path qrCode img Path
     * @throws WriterException Exception generating QR
     * @throws IOException Exception getting template
     */
    /*
     * feature-gogs-26 samuel.delgado@controltechcg.com (SIGDI-CONTROLTECH): método que genera el código QR
     */
    public synchronized String generateQrCode(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                text,
                BarcodeFormat.QR_CODE,
                75, 75); // width x height
        Path path = FileSystems.getDefault().getPath("JSA-QRCode.png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        return path.toString();
    }

    /**
     * Genera un código MD5 con base en un arreglo de bytes
     *
     * @param bytes arreglo de bytes
     * @return MD5 code
     */
    public String generateMd5(byte[] bytes) {
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(bytes);
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashText = bigInt.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException ex) {
            return "md5-error";
        }
    }

    public static String decryptStringWithXORFromHex(String input, String key) {
        StringBuilder c = new StringBuilder();
        while (key.length() < input.length() / 2) {
            key += key;
        }
        for (int i = 0; i < input.length(); i += 2) {
            String hexValueString = input.substring(i, i + 2);
            int value1 = Integer.parseInt(hexValueString, 16);
            int value2 = key.charAt(i / 2);
            int xorValue = value1 ^ value2;
            c.append((char) xorValue);
        }
        return c.toString();
    }

    public static Date buildToUTC(final Date date) {
        final int offset = TimeZone.getDefault().getOffset(date.getTime());
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MILLISECOND, (-1 * offset));
        return calendar.getTime();
    }

    /***
     * Método que retorna si un usuario posee un rol
     * @param rolPermiso rol a consultar
     * @param usuario Usuario a consultar
     * @return true si posee el permiso false si no lo posee
     */
    //Validar por Refactoring de SICDI
//    public boolean hasPermisionRole(String rolPermiso, Usuario usuario) {
//        final List<Rol> roles = rolRep.allByUserID(usuario.getId());
//        for (Rol rol : roles) {
//            if (rol.getId().equals(rolPermiso)) {
//                return true;
//            }
//        }
//        return false;
//    }
}