package com.lighthouse.respuestajson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vacardozo
 */
public class RespuestaJsonBuilder {

    private final GsonBuilder gsonBuilder;
    
    private Object objeto;

    public RespuestaJsonBuilder() {
        gsonBuilder = new GsonBuilder();
    }
    
    public RespuestaJsonBuilder setEscapeHTML(boolean deshabilitarEscapeHTML) {
        if(deshabilitarEscapeHTML) {
            gsonBuilder.disableHtmlEscaping();
        }
        return this;
    }

    public RespuestaJsonBuilder setExcluirCamposSinAnotacionExpose(boolean excluirCamposSinAnotacionExpose) {
        if(excluirCamposSinAnotacionExpose) {
            gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        }
        return this;
    }
    
    public RespuestaJsonBuilder setFormatoAgradable(boolean formatoAgradable) {
        if(formatoAgradable) {
            gsonBuilder.setPrettyPrinting();
        }
        return this;
    }

    public RespuestaJsonBuilder setAdaptadores(HashMap<Type, Object> mapaAdaptadores) {
        if (null != mapaAdaptadores && !mapaAdaptadores.isEmpty()) {
            for (Map.Entry<Type, Object> registro : mapaAdaptadores.entrySet()) {
                gsonBuilder.registerTypeAdapter(registro.getKey(),
                        registro.getValue());
            }
        }
        return this;
    }
    
    public RespuestaJsonBuilder setEstrategiaExclusion(ExclusionStrategy estrategiaExclusion) {
        gsonBuilder.setExclusionStrategies(estrategiaExclusion);
        return this;
    }
    public RespuestaJson crear() {
        return new RespuestaJson(gsonBuilder.create());
    }
}
