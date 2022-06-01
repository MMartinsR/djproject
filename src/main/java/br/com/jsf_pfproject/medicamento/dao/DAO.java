package br.com.jsf_pfproject.medicamento.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jsf_pfproject.medicamento.model.Base;

public class DAO<T extends Base> implements Serializable {

	private static final long serialVersionUID = 1L;

	// Dao generico, que será responsável pelos métodos de acesso ao banco
	private static EntityManager manager = ConnectionFactory.getEntityManager();

	public T buscarPorId(Class<T> clazz, Long id) {

		return manager.find(clazz, id);
	}

	public void salvarAtualizar(T tobj) {

		try {

			manager.getTransaction().begin();
			
			if (tobj.getId() == null) {
				manager.persist(tobj);
			} else {
				manager.merge(tobj);
			}

			manager.getTransaction().commit();

		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	public void remover(Class<T> clazz, Long id) {

		T tobj = buscarPorId(clazz, id);

		try {

			manager.getTransaction().begin();
			
			manager.remove(tobj);
			
			manager.getTransaction().commit();

		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(String jpql){
		Query query = manager.createQuery(jpql);
		
		return query.getResultList();
	}

}
