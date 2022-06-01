package br.com.jsf_pfproject.djp.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.jsf_pfproject.djp.dao.DAO;
import br.com.jsf_pfproject.djp.model.Professor;

public class ProfessorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DAO<Professor> profDao;

	public void salvar(Professor p) {

		// aplicar condições de validação de campo aqui (ex if (lalala)

		profDao.salvarAtualizar(p);

	}

	public void remover(Professor p) {
		profDao.remover(Professor.class, p.getId());
	}

	public List<Professor> listarTodos() {
		return profDao.buscarTodos("select p from Professor p order by p.nome");
	}

}
