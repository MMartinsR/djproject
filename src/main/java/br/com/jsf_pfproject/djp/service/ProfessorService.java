package br.com.jsf_pfproject.djp.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.jsf_pfproject.djp.dao.DAO;
import br.com.jsf_pfproject.djp.exception.ProfessorException;
import br.com.jsf_pfproject.djp.model.Professor;
import br.com.jsf_pfproject.djp.utility.ValidationUtil;

/**
 *  Classe de serviço Professor
 *  Responsável pela intermediação entre o controlador da view e a classe DAO de gerenciamento de banco de dados.
 *  
 * @author Mariana Martins
 *
 */
public class ProfessorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DAO<Professor> profDao;

	public void salvar(Professor professor) {


		if(!ValidationUtil.dataNascimentoValida
				(professor.getDataNascimento())) {
			
			throw new ProfessorException("Data de nascimento inválida");

		}
		
		profDao.salvarAtualizar(professor);

	}

	public void remover(Professor professor) {
		
		if (professor.getAgendamentosProfessor() != null && !professor.getAgendamentosProfessor().isEmpty()) {
			throw new ProfessorException("Não é possível deletar um professor que possui agendamentos.");
		}
		
		profDao.remover(Professor.class, professor.getId());
	}

	public List<Professor> listarTodos() {
		return profDao.buscarTodos(Professor.class);
	}
	
	public Professor buscarPorId(Professor professor) {
		
		return profDao.buscarPorId(Professor.class, professor.getId());
	}

}
