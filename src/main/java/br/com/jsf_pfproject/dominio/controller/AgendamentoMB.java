package br.com.jsf_pfproject.dominio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsf_pfproject.dominio.model.Agendamento;

@Named("agendamentoBean")
@SessionScoped
public class AgendamentoMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Agendamento agendamento;

	private List<Agendamento> agendamentos = new ArrayList<>();

	
	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
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
