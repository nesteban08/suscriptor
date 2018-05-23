package com.suscriptor.servicios;

import com.lighthouse.respuestajson.Data;
import com.lighthouse.respuestajson.Objeto;
import com.lighthouse.respuestajson.RespuestaJson;
import com.lighthouse.respuestajson.RespuestaJsonBuilder;
import com.lighthouse.respuestajson.utilidad.SerializadorFecha;

import java.lang.reflect.Type;

import java.sql.SQLException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Parameter;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author vacardozo
 * @param <T> Entidad tipo.
 */
@Stateless
public class ServicioFachada<T> {

    private final EntityManagerFactory fabricaAdministradorEntidad;

    private static final Logger LOGGER = LogManager.getLogger(ServicioFachada.class);

    private final RespuestaJsonBuilder respuestaJsonBuilder;
    private final HashMap<Type, Object> adaptadores;

    public ServicioFachada() {
        adaptadores = new HashMap<>();
        // TODO: Cambiar variable formato por propiedad
        adaptadores.put(Date.class, new SerializadorFecha("dd/MM/yyyy HH:mm:ss"));
        respuestaJsonBuilder = new RespuestaJsonBuilder()
                .setAdaptadores(adaptadores)
                .setEscapeHTML(false);
        fabricaAdministradorEntidad = Persistence.createEntityManagerFactory("subscriptor_PU");
    }

    private EntityManager getAdministradorEntidad() {
        return fabricaAdministradorEntidad.createEntityManager();
    }

    /**
     * Devuelve una lista tipada.
     *
     * @param valores Los valores a buscar.
     * @param parametros
     * @param nombreConsulta La consulta a realzar en el entorno de
     * persistencia.
     * @return La lista tipada.
     * @throws java.sql.SQLException
     */
    public synchronized List<T> buscar(Object[] valores, String[] parametros, String nombreConsulta) throws PersistenceException, SQLException {
        LOGGER.info("Abriendo conexión db.");
        EntityManager administradorEntidad = getAdministradorEntidad();
        List resultado = null;
        try {
            StoredProcedureQuery procedimiento = crearConsulta(valores, parametros,
                    nombreConsulta, administradorEntidad);
            resultado = procedimiento.getResultList();
        } finally {
            if (null != administradorEntidad
                    && administradorEntidad.isOpen()) {
                administradorEntidad.clear();
                administradorEntidad.close();
                LOGGER.info("Conexión cerrada db.");
            }
        }
        return resultado;
    }

    public synchronized void ejecutar(Object[] valores, String[] parametros, String nombreConsulta) throws SQLException {
        LOGGER.info("Abriendo conexión db.");
        EntityManager administradorEntidad = getAdministradorEntidad();
        try {
            StoredProcedureQuery procedimiento = crearConsulta(valores, parametros,
                    nombreConsulta, administradorEntidad);
            procedimiento.execute();
        } finally {
            if (null != administradorEntidad
                    && administradorEntidad.isOpen()) {
                administradorEntidad.clear();
                administradorEntidad.close();
                LOGGER.info("Conexión cerrada db.");
            }
        }
    }

    public Response getRespuesta(Data objeto, Response.Status estadoRespuesta) {
        RespuestaJson respuestaJson = respuestaJsonBuilder.crear();
        String serializado = respuestaJson.setData(objeto).serializar();

        return Response
                .status(estadoRespuesta)
                .entity(serializado)
                .type(MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    /**
     * Crea una consulta con los parámetros dados.
     *
     * @param valores Los valores a buscar.
     * @param parametros Los parámetros de la consulta.
     * @param nombreConsulta La consulta a crear.
     * @return
     */
    private StoredProcedureQuery crearConsulta(Object[] valores,
            String[] parametros, String nombreConsulta,
            EntityManager administradorEntidad) {
        StoredProcedureQuery procedimiento = administradorEntidad.
                createNamedStoredProcedureQuery(nombreConsulta);

        if (null != parametros) {
            for (int i = 0; i < parametros.length; i++) {
                setParametro(procedimiento, procedimiento.
                        getParameter(parametros[i]), valores[i]);
            }
        }

        return procedimiento;
    }

    /**
     * Establece los valores de los parametros para el procedimiento.
     *
     * @param procedimiento El procedimiento a ejecutar.
     * @param parametro El parámetro del procedimiento.
     * @param valor El valor del parámetro.
     */
    private void setParametro(StoredProcedureQuery procedimiento,
            Parameter<?> parametro, Object valor) {
        procedimiento.setParameter(parametro.getName(), valor);
    }

 

}
