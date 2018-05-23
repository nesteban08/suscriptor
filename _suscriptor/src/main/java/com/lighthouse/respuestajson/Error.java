package com.lighthouse.respuestajson;

import java.util.List;

/**
 *
 * @author vacardozo
 */
public class Error implements Objeto {
    
    private String codigo;
    private String mensaje;
    private List<DetalleError> errores;

    public Error(Object codigo, String mensaje) {
        this.codigo = codigo.toString();
        this.mensaje = mensaje;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setErrores(List<DetalleError> errores) {
        this.errores = errores;
    }

    @Override
    public void link(String link, String rel) {
        
    }
}
