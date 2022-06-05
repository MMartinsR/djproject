package br.com.jsf_pfproject.djp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe responsável por criar conexões com o banco de dados
 * 
 * @author Mariana Martins
 *
 */
public class ConnectionFactory {
	
	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("jsf-pfproject");
	
	public static EntityManager getEntityManager() {
		
		return factory.createEntityManager();
	}
}
