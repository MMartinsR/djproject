package br.com.jsf_pfproject.djp.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.jsf_pfproject.djp.dao.DAO;
import br.com.jsf_pfproject.djp.exception.AlunoException;
import br.com.jsf_pfproject.djp.model.Aluno;
import br.com.jsf_pfproject.djp.utility.ValidationUtil;

/**
 *  Classe de serviço Aluno
 *  Responsável pela intermediação entre o controlador da view e a classe DAO de gerenciamento de banco de dados.
 *  
 * @author Mariana Martins
 *
 */
public class AlunoService implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private DAO<Aluno> alunoDao;


	public void salvar(Aluno aluno) throws AlunoException{
		
		if(!ValidationUtil.dataNascimentoValida(aluno.getDataNascimento())) {		
			throw new AlunoException("Data de nascimento inválida");
		}


		alunoDao.salvarAtualizar(aluno);

	}

	public void remover(Aluno aluno) throws AlunoException{
		if (aluno.getAgendamentosAluno() != null && !aluno.getAgendamentosAluno().isEmpty()) {
			throw new AlunoException("Não é possível deletar um aluno que possui agendamentos.");
		}
		alunoDao.remover(Aluno.class, aluno.getId());
	}

	public List<Aluno> listarTodos() {
		return alunoDao.buscarTodos(Aluno.class);
	}

}
