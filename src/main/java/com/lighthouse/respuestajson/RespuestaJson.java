package com.lighthouse.respuestajson;

import com.google.gson.Gson;

/**
 *
 * @author vacardozo
 */
public class RespuestaJson {
    private final Gson gson;
    private final FormatoRespuesta formatoRespuesta;
    
    RespuestaJson(Gson gson) {
        this.gson = gson;
        formatoRespuesta = new FormatoRespuesta();
    }
    
    public RespuestaJson setData(Data objeto) {
        formatoRespuesta.setData(objeto);
        return this;
    }
    
    public String serializar() {
        return gson.toJson(formatoRespuesta);
    }
}
