package br.com.jsf_pfproject.djp.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jsf_pfproject.djp.model.Agendamento;


/**
 * Classe específica de conexão com o banco de dados
 * 
 * @author Mariana Martins
 *
 */
public class AgendamentoDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Criação da conexão
	private static EntityManager managerAgendamento = ConnectionFactory.getEntityManager();
	
	/**
	 * Método responsável por retornar caso uma data de Agendamento e um id de um professor já existam em banco de dados
	 * quando um novo agendamento está sendo realizado.
	 * 
	 * Para a realização da busca em banco é utilizada uma NamedQuery "Agendamento.obterAgendamento"
	 * 
	 * @param data data de agendamento a ser buscada no banco de dados
	 * @param id id de um professor a ser buscado no banco
	 * @return retorna true caso exista um objeto com as mesmas caracteriscas das passadas por parâmetro, no banco de dados.
	 */
	public boolean existeObjeto(Date data, Long id) {
		
		Query query = managerAgendamento.createNamedQuery("Agendamento.obterAgendamento");
		query.setParameter("data", data);
		query.setParameter("idProfessor", id);

		@SuppressWarnings("unchecked")
		List<Agendamento> listObjetos = query.getResultList();
		return listObjetos != null && !listObjetos.isEmpty();
	}

}
