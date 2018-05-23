package com.suscriptor.utilidad;

import com.suscriptor.constantes.SuscriptorConstantes;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;

import java.util.ResourceBundle;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

/**
 *
 * @author vacardozo
 */
public class Utilidad {

    public final static String ARCHIVO_PROPIEDADES_SGO = "com.bbva.propiedades.propiedades_sgo";

    public final static String REGISTRO_NO_CREADO = "El registro no fue creado.";
    public final static String REGISTRO_NULO = "El registro no tiene ningún valor.";
    public final static String CAMPO_OBLICATORIO = "Campo obligatorio %s.";
    public final static String REGISTRO_NO_ACTUALIZADO = "El registro no fue actualizado.";
    public final static String ACCESO_NO_AUTORIZADO = "Acceso no autorizado.";

    public final static String PARAMETRO_USUARIO = "USUARIO_SGO";
    
    private final static String RUTA_BASE_RECURSO_PRODUCCION = "/pr/SGO";
    private final static String RUTA_BASE_RECURSO_CALIDAD = "/qa/SGO";
    private final static String RUTA_BASE_RECURSO_DESARROLLO = "/de/SGO";

    private static ResourceBundle propiedadesSGO;

    public static String getNombreColumna(String nombrePropiedad, Class<?> tipo) {
        String anotacion = null;
        for (Field campo : tipo.getDeclaredFields()) {
            if (campo.getName().equals(nombrePropiedad)) {
                if (campo.isAnnotationPresent(Column.class)) {
                    anotacion = campo.getAnnotation(Column.class).name();
                } else if (campo.isAnnotationPresent(JoinColumn.class)) {
                    anotacion = campo.getAnnotation(JoinColumn.class).name();
                }
                break;
            }
        }
        return anotacion;
    }

    private static void crearPropiedadesSGO() {
        propiedadesSGO = ResourceBundle.getBundle(ARCHIVO_PROPIEDADES_SGO);
    }

    private static ResourceBundle getPropiedadesSGO() {
        if (null == propiedadesSGO) {
            crearPropiedadesSGO();
        }
        return propiedadesSGO;
    }

    public static String getDiasConsultaManualSinModificar() {
        return getPropiedadesSGO().getString("dias_consulta_manual_sin_modificar");
    }

    public static void limpiarPropiedadesSGO() {
        propiedadesSGO = null;
        crearPropiedadesSGO();
    }

    public static String getURLBaseRecursos() {
        String ruta = "";

        if (new File(RUTA_BASE_RECURSO_PRODUCCION).exists()) {
            ruta = RUTA_BASE_RECURSO_PRODUCCION;
        } else if (new File(RUTA_BASE_RECURSO_CALIDAD).exists()) {
            ruta = RUTA_BASE_RECURSO_CALIDAD;
        } else {
            ruta = RUTA_BASE_RECURSO_DESARROLLO;
        }
        return ruta;
    }
}
