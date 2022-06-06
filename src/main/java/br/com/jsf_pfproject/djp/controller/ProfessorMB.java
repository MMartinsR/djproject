package br.com.jsf_pfproject.djp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsf_pfproject.djp.exception.RemoverException;
import br.com.jsf_pfproject.djp.exception.ValidationException;
import br.com.jsf_pfproject.djp.model.Professor;
import br.com.jsf_pfproject.djp.service.ProfessorService;
import br.com.jsf_pfproject.djp.utility.Message;

/**
 * Classe para controlar a view Professor.xhtml
 * 
 * @author Mariana Martins
 *
 */
@Named("professorBean")
@ViewScoped
public class ProfessorMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Professor professor;

	@Inject
	private ProfessorService professorService;

	private List<Professor> professores;

	/**
	 * Carrega para a view Professor.xhtml os dados do banco.
	 * 
	 * professores - dataTable
	 */
	@PostConstruct
	public void carregar() {
		this.professores = professorService.listarTodos();
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	/**
	 * Tratamento de novo professor
	 * Caso sucesso:
	 * - salva em banco de dados,
	 * 		@param professor - objeto a ser salvo 
	 * 
	 * - campos do formulário são esvaziados,
	 * 		método limparFormulario()
	 * 
	 * - recarrega os dados no componente datatable da view Professor.xhtml.
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
	public void novoProfessor() {

		try {
			professorService.salvar(professor);
			limparFormulario();
			carregar();

			Message.info("Professor salvo com sucesso");

		} catch (ValidationException ev) {
			Message.erro(ev.getMessage());
		}
	}
	
	/**
	 * Tratamento de remoção de professor
	 * Caso sucesso:
	 * - remove do banco de dados,
	 * 		@param professor - objeto a ser removido 
	 * 
	 * - campos do formulário são esvaziados,
	 * 		método limparFormulario()
	 * 
	 * - recarrega os dados no componente datatable da view Professor.xhtml.
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
	public void excluirProfessor() {

		try {
			professorService.remover(professor);
			limparFormulario();
			carregar();

			Message.info("Professor removido com sucesso");

		} catch (RemoverException er) {
			Message.erro(er.getMessage());
			
		} catch (ValidationException ev) {
			Message.erro(ev.getMessage());
			
		} finally {
			limparFormulario();
		}
	}

	private void limparFormulario() {
		professor = new Professor();
	}

}
