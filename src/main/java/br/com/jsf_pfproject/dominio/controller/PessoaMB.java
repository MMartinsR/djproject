package br.com.jsf_pfproject.dominio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsf_pfproject.dominio.model.Pessoa;

@Named(value = "bean")
@SessionScoped
public class PessoaMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pessoa pessoa;
	
//	@Inject
//	private Conversation conversation;
	
	private List<Pessoa> pessoas = new ArrayList<>();
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public String adicionar() {	
		
//		if (pessoas.isEmpty()) {  // Inicia o escopo quando a lista estiver vazia, ou seja, no inicio da utilização
//			beginConversation();
//		}
		pessoas.add(pessoa);
		limpar();		
		return null;
	}

	// ConversationScoped	
//	private String beginConversation() {
//		conversation.begin();
//		return null;
//	}
//	
//	public String endConversation() {
//		conversation.end();
//		return null;
//	}
	
	private void limpar() {		
		pessoa = new Pessoa();
	}

}
