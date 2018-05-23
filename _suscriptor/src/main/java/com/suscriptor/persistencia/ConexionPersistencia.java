/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suscriptor.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import com.suscriptor.entidad.Conexion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
/**
 *
 * @author Tiesto
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless
public class ConexionPersistencia extends AbstractPersistenciaFacade<Conexion>{
    
    @PersistenceContext
    private EntityManager em;
    
    public ConexionPersistencia(){
        super(Conexion.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Conexion> consultarTodasLasConexiones(){
        TypedQuery<Conexion> query = em.createNamedQuery("Conexion.findAll", Conexion.class);
	return query.getResultList();
    }
    
    public Conexion consultarConexionPorCodigo(String codigo){
        TypedQuery<Conexion> query = em.createNamedQuery("Conexion.encontrarConexionPorCodigo", Conexion.class);
        query.setParameter("codigo", codigo);
        try{
            return query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    
    public Conexion consultarConexionPorId(Integer id){
        TypedQuery<Conexion> query = em.createNamedQuery("Conexion.encontrarConexionPorId", Conexion.class);
        query.setParameter("id", id);
        try{
            return query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    
    private int getSecuenciaConexion(){
        TypedQuery<Integer> query = em.createNamedQuery("Conexion.maxResults", Integer.class);
        Integer resultado = 0;
        resultado = query.getSingleResult();
       
        if(resultado==null){
            return 1;
        }else{
            return resultado+1;
        }
    }
    
    
    public void eliminarConexion(Integer id){
        Conexion conexionAEliminar = this.consultarPorId(id);
        this.borrar(conexionAEliminar);
    }
    
    public void crearConexion (Conexion conexion){
        conexion.setId(getSecuenciaConexion());
        this.crear(conexion);
    }
    
    
    
    
}
