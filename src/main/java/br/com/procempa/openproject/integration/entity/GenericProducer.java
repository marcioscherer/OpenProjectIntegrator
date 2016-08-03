package br.com.procempa.openproject.integration.entity;


import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericProducer {
	@PersistenceContext
	private EntityManager entityManager;	
	
	@Produces
	public EntityManager getEntityManager() {
		return entityManager;
	}	
	
}
