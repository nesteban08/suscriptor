package com.lighthouse.respuestajson;

/**
 *
 * @author vacardozo
 */
public class DetalleError {
    private String dominio;
    private String motivo;
    private String mensaje;

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
