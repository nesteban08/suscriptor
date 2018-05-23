package com.lighthouse.respuestajson.utilidad;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 *
 * @author vacardozo
 */
public class SerializadorFecha implements Serializador, JsonSerializer<Date>, JsonDeserializer<Date> {
    private final String formatoFecha;
    
    public SerializadorFecha() {
        formatoFecha = "dd/MM/yyyy HH:mm:ss";
    }
    
    public SerializadorFecha(String formato) {
        this.formatoFecha = formato;
    }
    
    @Override
    public Type getTipoSerializador() {
        return Date.class;
    }
    
    @Override
    public JsonElement serialize(Date fecha, Type tipo,
            JsonSerializationContext contexto) {
        long tiempo = fecha.getTime();
        JsonPrimitive jsonPrimitive = new JsonPrimitive(tiempo);
        return jsonPrimitive;
    }
    
    @Override
    public Date deserialize(JsonElement json, Type tipo,
            JsonDeserializationContext contexto) throws JsonParseException {
        SimpleDateFormat simpleDateFormat = Utilidad.getFormateador();
        simpleDateFormat.applyPattern(formatoFecha);
        try {
            return simpleDateFormat.parse(json.getAsString());
        } catch (ParseException e) {
            return null;
        } finally {
            Utilidad.setFormateador(simpleDateFormat);
        }
    }
}
