package br.com.jsf_pfproject.djp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsf_pfproject.djp.exception.AlunoException;
import br.com.jsf_pfproject.djp.model.Aluno;
import br.com.jsf_pfproject.djp.service.AlunoService;
import br.com.jsf_pfproject.djp.utility.Message;

/**
 * Classe para controlar a view Aluno.xhtml
 * 
 * @author Mariana Martins
 *
 */
@Named("alunoBean")
@ViewScoped
public class AlunoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Aluno aluno;

	@Inject
	private AlunoService alunoService;

	private List<Aluno> alunos;

	/**
	 * Carrega para a view Aluno.xhtml os dados do banco.
	 * 
	 * alunos - dataTable
	 */
	@PostConstruct
	public void carregar() {
		this.alunos = alunoService.listarTodos();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public List<Aluno> getAlunos() {
		return alunos;
	}
	
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	
	/**
	 * Tratamento de novo aluno
	 * Caso sucesso:
	 * - salva em banco de dados,
	 * 		@param aluno - objeto a ser salvo 
	 * 
	 * - campos do formulário são esvaziados,
	 * 		método limparFormulario()
	 * 
	 * - recarrega os dados no componente datatable da view Aluno.xhtml.
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
	public void novoAluno() {

		try {
			alunoService.salvar(aluno);
			limparFormulario();
			carregar();

			Message.info("Novo aluno salvo com sucesso");

		} catch (AlunoException e) {
			Message.erro(e.getMessage());
		}
	}

	/**
	 * Tratamento de remoção de aluno
	 * Caso sucesso:
	 * - remove do banco de dados,
	 * 		@param aluno - objeto a ser removido 
	 * 
	 * - campos do formulário são esvaziados,
	 * 		método limparFormulario()
	 * 
	 * - recarrega os dados no componente datatable da view Aluno.xhtml.
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
	public void excluirAluno() {

		try {
			alunoService.remover(aluno);
			limparFormulario();
			carregar();

			Message.info("Aluno removido com sucesso");

		} catch (AlunoException e) {

			Message.erro(e.getMessage());
		}finally {
			limparFormulario();
		}
	}

	private void limparFormulario() {
		aluno = new Aluno();
	}

}
