package br.com.jsf_pfproject.djp.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jsf_pfproject.djp.model.Base;


/**
 * Classe DAO genérico, responsável por métodos de acesso ao banco
 * 
 * @author Mariana Martins
 *
 * @param <T>
 */
public class DAO<T extends Base> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Iniciada a conexão
	private static EntityManager manager = ConnectionFactory.getEntityManager();

	/**
	 * Método de busca por ID 
	 * 
	 * @param clazz define de qual classe irá buscar objetos
	 * @param id espera um id de um objeto desta classe
	 * @return retorna um objeto do tipo da classe passada, que terá id compatível com o passado por parâmetro
	 */
	public T buscarPorId(Class<T> clazz, Long id) {

		return manager.find(clazz, id);
	}

	/**
	 * Método para salvar e atualizar dados no banco de dados.
	 * 
	 * @param tobj espera que um objeto seja passado, que será o objeto a ser salvo ou atualizado.
	 */
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
	
	/**
	 * Método para remover dados do banco
	 * 
	 * @param clazz define de qual classe irá remover objetos
	 * @param id espera um id de um objeto da classe definida
	 */
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
	
	/**
	 * Método para buscar todos os dados de definida classe no banco
	 * 
	 * @param clazz define a classe que deverá ser pesquisada
	 * @return retorna uma lista de objetos desta definida classe
	 */
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(Class<T> clazz) {

		String sql = " select object(o) from " + clazz.getName() + " as o ";
		Query query = manager.createQuery(sql);

		return query.getResultList();
	}

}
