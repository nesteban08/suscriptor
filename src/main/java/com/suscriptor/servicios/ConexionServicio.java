package com.suscriptor.servicios;

import com.suscriptor.entidad.Conexion;
import com.suscriptor.persistencia.ConexionPersistencia;

import com.lighthouse.respuestajson.Data;
import com.lighthouse.respuestajson.Objeto;
import com.suscriptor.constantes.SuscriptorConstantes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author nvillegas
 */
@Path("/conexiones")
public class ConexionServicio extends ServicioFachada<Conexion> {
    
    
    @Context
    private UriInfo context;
       
    @EJB
    private ConexionPersistencia conexionPersistencia;
    

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearConexion(Conexion conexion) {
        System.out.println("Codigo "+conexion.getCodigo());
        System.out.println("nombre "+conexion.getNombre());
        System.out.println("Capacidades "+conexion.getCapacidades());
        System.out.println("Tipo Servicio "+conexion.getTipoServicio());
        System.out.println("Url "+conexion.getUrl());
        System.out.println("XSLT estructura "+conexion.getXslt_estructura());
        System.out.println("XSLT mapeo "+conexion.getXslt_mapeo());
        System.out.println("ID "+conexion.getId());
        
        Response.Status codigo = Response.Status.BAD_REQUEST;
        String mensaje = "";
        Data objeto = new Data();

        if (conexion != null) {
            try{
                if(mensaje.equals("")){
                    
                    if(!conexion.getCodigo().equals("") &&
                       !conexion.getNombre().equals("") &&
                       !conexion.getCapacidades().equals("") &&
                       !conexion.getTipoServicio().equals("") &&
                       !conexion.getUrl().equals("") &&
                       !conexion.getXslt_estructura().equals("") && 
                       !conexion.getXslt_mapeo().equals("")){
                        
                        Conexion conexionPreExistente = conexionPersistencia.consultarConexionPorCodigo(conexion.getCodigo());
                        if(conexionPreExistente!=null){
                            codigo = Response.Status.PRECONDITION_FAILED;
                            objeto.setStatus(SuscriptorConstantes.CODIGO_EXISTENTE);
                        }else{
                            conexionPersistencia.crearConexion(conexion);
                            Conexion nuevaConexion = conexionPersistencia.consultarConexionPorCodigo(conexion.getCodigo());
                            objeto.setRegistros(nuevaConexion);
                            codigo = Response.Status.CREATED;
                        }
                    }else{
                        codigo = Response.Status.BAD_REQUEST;
                        objeto.setStatus(SuscriptorConstantes.PARAMETROS_REQUERIDOS);
                    }
                    
                    
                    
                }
            }catch(Exception ex){
                ex.printStackTrace();
                Logger.getLogger(ConexionServicio.class.getName()).log(Level.SEVERE, null, ex);
                codigo = Response.Status.INTERNAL_SERVER_ERROR;
                mensaje = ex.getMessage();
                objeto.setStatus(mensaje);
            }
          
        }

        return super.getRespuesta(objeto, codigo);
    }
    
    @GET
    @Path("/{conexion}")
    public Response getConexionPorId(@PathParam("conexion") Integer id) {
        System.out.println("Ingresa a la consulta de conexiones para la conexion con id "+id);
        Response.Status codigo = Response.Status.BAD_REQUEST;
        Conexion conexion = conexionPersistencia.consultarPorId(id);
        Data objeto = new Data();
        String mensaje="";
        
        try{
        
            if (conexion!=null){
                codigo = Response.Status.OK;
                objeto.setRegistros(conexion);
            }else{
                codigo = Response.Status.NOT_FOUND;
                objeto.setStatus(SuscriptorConstantes.CONEXION_NO_ENCONTRADA);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            Logger.getLogger(ConexionServicio.class.getName()).log(Level.SEVERE, null, ex);
            codigo = Response.Status.INTERNAL_SERVER_ERROR;
            mensaje = ex.getMessage();
            objeto.setStatus(mensaje);
        }
        
        return super.getRespuesta(objeto, codigo);
    }
    
    
    @GET
    @Path("/codigo/{codigo}")
    public Response getConexionPorCodigo(@PathParam("codigo") String codigoConexion) {
        System.out.println("Ingresa a la consulta de conexiones para la conexion con codigo "+codigoConexion);
        
        Response.Status codigo = Response.Status.BAD_REQUEST;
        Conexion conexion = conexionPersistencia.consultarConexionPorCodigo(codigoConexion);
        Data objeto = new Data();
        String mensaje="";
        
        try{
        
            if (conexion!=null){
                codigo = Response.Status.OK;
                objeto.setRegistros(conexion);
                return super.getRespuesta(objeto, codigo);
            }else{
                codigo = Response.Status.NOT_FOUND;
                objeto.setStatus(SuscriptorConstantes.CONEXION_NO_ENCONTRADA);
                return super.getRespuesta(objeto, codigo);
            }

        }catch(Exception ex){
            ex.printStackTrace();
            Logger.getLogger(ConexionServicio.class.getName()).log(Level.SEVERE, null, ex);
            codigo = Response.Status.INTERNAL_SERVER_ERROR;
            mensaje = ex.getMessage();
            objeto.setStatus(mensaje);
        }
        
        return super.getRespuesta(objeto, codigo);
    }
    
   

    @GET
    public Response getConexiones() {
        System.out.println("Ingresa a la consulta de todas las conexiones");
        Response.Status codigo = Response.Status.BAD_REQUEST;
        Data objeto = new Data();
        String mensaje="";
     
        try{
            List<Conexion> conexiones = conexionPersistencia.consultarTodasLasConexiones();
            objeto.setRegistros(conexiones);
            codigo = Response.Status.OK;
        }catch(Exception ex){
            ex.printStackTrace();
            Logger.getLogger(ConexionServicio.class.getName()).log(Level.SEVERE, null, ex);
            codigo = Response.Status.INTERNAL_SERVER_ERROR;
            mensaje = ex.getMessage();
            objeto.setStatus(mensaje);
        }
        
        return super.getRespuesta(objeto, codigo);
        
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarConexion(@PathParam("id")int id){
        System.out.println("Ingresa a eliminar la conexion con id "+id);
        Response.Status codigo = Response.Status.BAD_REQUEST;
        Data objeto = new Data();
        String mensaje="";
        
        try{
            conexionPersistencia.eliminarConexion(id);
            codigo = Response.Status.OK;
            
        }catch(Exception ex){
            ex.printStackTrace();
            Logger.getLogger(ConexionServicio.class.getName()).log(Level.SEVERE, null, ex);
            codigo = Response.Status.INTERNAL_SERVER_ERROR;
            mensaje = ex.getMessage();
            objeto.setStatus(mensaje);
        }
        
        return super.getRespuesta(objeto, codigo);
    }
    
}
