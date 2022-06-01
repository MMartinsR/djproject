package br.com.jsf_pfproject.djp.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.jsf_pfproject.djp.dao.DAO;
import br.com.jsf_pfproject.djp.model.Agendamento;

public class AgendamentoService implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private DAO<Agendamento> ageDao;

	public void salvar(Agendamento ag) {

		// aplicar condições de validação de campo aqui (ex if (lalala)

		ageDao.salvarAtualizar(ag);

	}

	public void remover(Agendamento ag) {
		ageDao.remover(Agendamento.class, ag.getId());
	}

	public List<Agendamento> listarTodos() {
		return ageDao.buscarTodos("select ag from Agendamento ag order by ag.nome");
	}


}
