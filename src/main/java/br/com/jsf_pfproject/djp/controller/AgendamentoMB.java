package br.com.jsf_pfproject.djp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsf_pfproject.djp.model.Agendamento;
import br.com.jsf_pfproject.djp.model.Professor;
import br.com.jsf_pfproject.djp.service.AgendamentoService;
import br.com.jsf_pfproject.djp.service.ProfessorService;

@Named("aBean")
@ViewScoped
public class AgendamentoMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Agendamento agendamento;
	@Inject
	private Professor professor;
	
	@Inject 
	private AgendamentoService service;

	private List<Agendamento> agendamentos;
	private List<Professor> professores;

	
//	@PostConstruct
//	public void carregar() {
//		agendamentos = service.listarTodos();
//	}
	
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

	public String novoAgendamento() {
		
		agendamentos.add(agendamento);
		limparCampos();
		return "/Agendamentos";
	}
	
	private void limparCampos() {		
		
		agendamento = new Agendamento();
	}
	


	
	
	
	
	
	

}
