package br.com.jsf_pfproject.djp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe modelo de Agendamento
 * 
 * @author Mariana Martins
 *
 */

/**
 * NamedQuery Agendamento.obterAgendamento
 * Esta NamedQuery é responsável por buscar no banco uma possível 
 * data de agendamento e id de professor, iguais aos que estão sendo passados ao modelo
 * para evitar que sejam inseridos em banco novos agendamentos com um mesmo professor em uma mesma data e hora já existente.
 */
@Entity
@Table(name = "tb_agendamento")
@NamedQueries({
    @NamedQuery(name = "Agendamento.obterAgendamento",
            query = "select A from Agendamento A " +
                    " where A.dataAgendamento = :data and "
                    + "A.professor.id = :idProfessor")
    }
)
public class Agendamento implements Serializable, Base {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String assunto;
	private Date dataAgendamento;
	private String observacoes;

	// Relações muitos para um entre agendamento e professor/aluno
	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;

	@ManyToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;
	
//	
//
//	public Agendamento() {
//		Agendamento agendTemp = new Agendamento();
//		agendTemp.setAssunto(a.getAssunto());
//		agendTemp.setId(a.getId());
//		agendTemp.setAluno(a.getAluno());
//		agendTemp.setDataAgendamento(a.getDataAgendamento());
//		agendTemp.setObservacoes(a.getObservacoes());
//		agendTemp.setProfessor(a.getProfessor());
//		
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
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

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		return Objects.equals(id, other.id);
	}

}
