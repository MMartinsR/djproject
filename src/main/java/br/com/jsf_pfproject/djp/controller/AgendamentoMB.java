package br.com.jsf_pfproject.djp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsf_pfproject.djp.exception.AgendamentoException;
import br.com.jsf_pfproject.djp.model.Agendamento;
import br.com.jsf_pfproject.djp.model.Aluno;
import br.com.jsf_pfproject.djp.model.Professor;
import br.com.jsf_pfproject.djp.service.AgendamentoService;
import br.com.jsf_pfproject.djp.service.AlunoService;
import br.com.jsf_pfproject.djp.service.ProfessorService;
import br.com.jsf_pfproject.djp.utility.Message;

/**
 * Classe para controlar a view Agendamento.xhtml
 * 
 * @author Mariana Martins
 *
 */
@Named("agendamentoBean")
@ViewScoped
public class AgendamentoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Agendamento agendamento;
	@Inject
	private Professor professor;
	@Inject
	private Aluno aluno;

	@Inject
	private AgendamentoService agendamentoService;
	@Inject
	private ProfessorService professorService;
	@Inject
	private AlunoService alunoService;

	private List<Agendamento> agendamentos;
	private List<Professor> professores;
	private List<Aluno> alunos;
	
	/**
	 * Carrega para a view Agendamento.xhtml os dados do banco.
	 * 
	 * professores - selectOneMenu
	 * agendamentos - dataTable
	 * alunos - selectOneMenu
	 */
	@PostConstruct
	public void carregar() {
		this.professores = professorService.listarTodos();
		this.agendamentos = agendamentoService.listarTodos();
		this.alunos = alunoService.listarTodos();
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}
	
	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public List<Professor> getProfessores() {
		return professores;
	}
	
	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}
	
	public List<Aluno> getAlunos() {
		return alunos;
	}
	
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	/**
	 * Tratamento de novo agendamento
	 * Caso sucesso:
	 * - salva em banco de dados,
	 * 		@param agendamento - objeto a ser salvo 
	 * 
	 * - campos do formulário são esvaziados,
	 * 		método limparFormulario()
	 * 
	 * - recarrega os dados nos componentes selectOneMenu e datatable da view Agendamento.xhtml.
	 * 		método carregar()
	 * 
	 * - exibe mensagem de sucesso
	 * 		classe Message
	 * 		método info()
	 * 
	 * Caso fracasso:
	 * - exibe na view uma mensagem pré-definida de erro.
	 * 		classe Message
	 * 		método erro()
	 */
	public void novoAgendamento() {
		
		try {		
			
			agendamentoService.salvar(agendamento);
			limparFormulario();
			carregar();

			Message.info("Novo agendamento salvo com sucesso");

		} catch (AgendamentoException e) {
			Message.erro(e.getMessage());
		}
	}
	
	/**
	 * Tratamento de remoção de agendamento
	 * Caso sucesso:
	 * - remove do banco de dados,
	 * 		@param agendamento - objeto a ser removido 
	 * 
	 * - campos do formulário são esvaziados,
	 * 		método limparFormulario()
	 * 
	 * - recarrega os dados nos componentes selectOneMenu e datatable da view Agendamento.xhtml.
	 * 		método carregar()
	 * 
	 * - exibe mensagem de sucesso
	 * 		classe Message
	 * 		método info()
	 * 
	 * Caso fracasso:
	 * - exibe na view uma mensagem pré-definida de erro.
	 * 		classe Message
	 * 		método erro()
	 */
	public void excluirAgendamento() {

		try {
			agendamentoService.remover(agendamento);
			limparFormulario();
			carregar();

			Message.info("Agendamento removido com sucesso");

		} catch (AgendamentoException e) {

			Message.erro(e.getMessage());
		}
	}

	private void limparFormulario() {

		agendamento = new Agendamento();
	}

}
