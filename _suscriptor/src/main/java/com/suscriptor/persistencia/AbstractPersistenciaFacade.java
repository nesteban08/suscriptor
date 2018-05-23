/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suscriptor.persistencia;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Tiesto
 */
public abstract class AbstractPersistenciaFacade<T> {
    
    
    /*
	 * Entidad asociada a la fachada de persistencia
	 */
	private Class<T> entityClass;
	

	/**
	 * Constructor general de la facha
	 * 
	 * @param entityClass
	 *            Clase de la entidad asociada
	 */
	public AbstractPersistenciaFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * Obtiene el entity Manager asociado
	 * 
	 * @return Entity Manager
	 */

	protected abstract EntityManager getEntityManager();

	/**
	 * Almacena un nuevo registro en la base de datos
	 * 
	 * @param entity
	 *            Entidad a registrar
	 */
	
	public void crear(T entity) {
		getEntityManager().persist(entity);
		getEntityManager().flush();
	}
	
	public void crearmerge(T entity) {
		
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory( "subscriptor_PU" );
		EntityManager em = entityManagerFactory.createEntityManager();
				
		em.getTransaction().begin();
		em.persist(entity);
	
		em.getTransaction().commit();
		em.close();
	
	}

	/**
	 * Modifica el registro en la base de datos
	 * 
	 * @param entity
	 *            Entidad que se quiere modificar
	 */
	public void modificar(T entity) {
		getEntityManager().merge(entity);
		getEntityManager().flush();
	}

	/**
	 * Borra el registro en la base de datos
	 * 
	 * @param entity
	 *            Entidad que se quiere eliminar
	 */
	public void borrar(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
		getEntityManager().flush();
	}

	/**
	 * Consulta una entidad por su identificador en la base de datos
	 * 
	 * @param id
	 *            Identificador del objeto
	 * @return Objeto con el identificador indicado
	 */
	public T consultarPorId(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	/**
	 * Consulta todos los registros de la entidad en la base de datos
	 * 
	 * @return Objetos de la entidad
	 */
	public List<T> consultarTodos() {
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
				.createQuery(entityClass);
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	/**
	 * Consulta los registros en un rango indicado
	 * 
	 * @param range
	 *            Rango a buscar
	 * @return Objetos de la entidad en el rango indicado
	 */
	public List<T> consultarRango(int[] range) {
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
				.createQuery(entityClass);
		cq.select(cq.from(entityClass));
		TypedQuery<T> q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	/**
	 * Cuenta el número de registros de la entidad
	 * 
	 * @return Número de registros
	 */
	public Long contarTodos() {
		CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder()
				.createQuery(Long.class);
		Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		TypedQuery<Long> q = getEntityManager().createQuery(cq);
		return q.getSingleResult();
	}
    
}
