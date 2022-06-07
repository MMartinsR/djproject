package br.com.jsf_pfproject.djp.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.jsf_pfproject.djp.dao.AgendamentoDAO;
import br.com.jsf_pfproject.djp.dao.DAO;
import br.com.jsf_pfproject.djp.exception.RemoverException;
import br.com.jsf_pfproject.djp.exception.ValidationException;
import br.com.jsf_pfproject.djp.model.Agendamento;
import br.com.jsf_pfproject.djp.utility.ValidationUtil;

/**
 * Classe de serviço Agendamento Responsável pela intermediação entre o
 * controlador da view e a classe DAO de gerenciamento de banco de dados.
 * 
 * @author Mariana Martins
 *
 */
public class AgendamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DAO<Agendamento> agendamentoDao;
	@Inject
	private AgendamentoDAO agendamentoDAO;

	public void salvar(Agendamento agendamento) {

		if (!ValidationUtil.dataAgendamentoValida(agendamento.getDataAgendamento())) {
			throw new ValidationException("Não é possível agendar para uma data e hora anterior a atual");
		}
		
		// Valida se o agendamento passado por parâmetro é nulo, se for é um novo agendamento
		if (agendamento.getId() != null) {
			
			// Se não for um novo agendamento, busco por id este agendamento no banco (update)
			Agendamento agendamentoTemp = agendamentoDao.buscarPorId(Agendamento.class, agendamento.getId());
			
			// Valido se data ou professor desse update foram alterados, se sim, valido se esta data ou professor já possuem
			// agendamentos vinculados a eles
			if (!agendamentoTemp.getDataAgendamento().equals(agendamento.getDataAgendamento())
					|| !agendamentoTemp.getProfessor().getId().equals(agendamento.getProfessor().getId())) {				
				
				validaDataDuplicada(agendamento);
			} 
			
		} else {
			validaDataDuplicada(agendamento);
		}

		agendamentoDao.salvarAtualizar(agendamento);

	}

	public void remover(Agendamento agendamento) {
		
		if (agendamento.getId() == null) {
			throw new RemoverException("Não existe agendamento com esses parâmetros");
			
		}		
		agendamentoDao.remover(Agendamento.class, agendamento.getId());
		
	}

	public List<Agendamento> listarTodos() {
		return agendamentoDao.buscarTodos(Agendamento.class);
	}

	/**
	 * Validação da existência de um agendamento com os mesmos parâmetros data/hora,
	 * professor.
	 * 
	 * @param agendamento
	 */
	private void validaDataDuplicada(Agendamento agendamento) {
		if (agendamentoDAO.existeObjeto(agendamento.getDataAgendamento(), agendamento.getProfessor().getId())) {			
			throw new ValidationException("O professor já possui agendamento para esta data e hora");
		}
	}
}
