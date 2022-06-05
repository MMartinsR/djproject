package br.com.jsf_pfproject.djp.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jsf_pfproject.djp.model.Aluno;

/**
 * Classe específica de conexão com o banco de dados
 * 
 * @author Mariana Martins
 *
 */
public class AlunoDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Criação da conexão
	private static EntityManager managerAluno = ConnectionFactory.getEntityManager();
	
	/**
	 * Método responsável por retornar caso uma dada matricula já exista em banco de dados
	 * quando um aluno está sendo cadastrado.
	 * 
	 * Para a realização da busca em banco é utilizada uma NamedQuery "Aluno.obterAluno"
	 * 
	 * @param matricula matricula a ser buscada no banco de dados.
	 * @return retorna true caso exista um objeto com as mesmas caracteriscas das passadas por parâmetro, no banco de dados.
	 */
	public boolean existeMatricula(String matricula) {
		
		Query query = managerAluno.createNamedQuery("Aluno.obterAluno");
		query.setParameter("matricula", matricula);
		
		@SuppressWarnings("unchecked")
		List<Aluno> listObjetos = query.getResultList();
		return listObjetos != null && !listObjetos.isEmpty();
	}

}
