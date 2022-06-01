package br.com.jsf_pfproject.djp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	
	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("jsf-pfproject");
	
	public static EntityManager getEntityManager() {
		
		return factory.createEntityManager();
	}
}
