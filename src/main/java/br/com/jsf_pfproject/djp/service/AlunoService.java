package br.com.jsf_pfproject.djp.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.jsf_pfproject.djp.dao.DAO;
import br.com.jsf_pfproject.djp.model.Aluno;

public class AlunoService implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private DAO<Aluno> alunoDao;

	public void salvar(Aluno a) {

		// aplicar condições de validação de campo aqui (ex if (lalala)

		alunoDao.salvarAtualizar(a);

	}

	public void remover(Aluno a) {
		alunoDao.remover(Aluno.class, a.getId());
	}

	public List<Aluno> listarTodos() {
		return alunoDao.buscarTodos("select a from Aluno a order by a.nome");
	}

}
