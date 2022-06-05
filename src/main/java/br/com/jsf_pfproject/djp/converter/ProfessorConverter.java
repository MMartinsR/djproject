package br.com.jsf_pfproject.djp.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.jsf_pfproject.djp.model.Professor;
import br.com.jsf_pfproject.djp.service.ProfessorService;

@FacesConverter("profConv")
public class ProfessorConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;
	
	private ProfessorService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
	// método que consulta o objeto no bd e converte esse dado do bd para objeto (modelo)
		if (id != null || !id.matches("\\d+")) {
			return null;
		}
		// consulta no banco de dados os dados

		Professor professor = new Professor();
		professor.setId(Long.parseLong(id));
//		int index = profList.indexOf(professor);
		//return profList.get(Integer.parseInt(id)) ;  // retorno do dado do banco como objeto
		return service.buscarPorId(professor);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		// método que retorna ao combobox da tela o dado requerido como string para exibição
		if (obj != null && !obj.equals("")) {
			Professor professor = (Professor) obj;
			if (professor.getId() != null) {
				return professor.getId().toString();
			}
		}
		return null;  // retorno string do objeto passado para a tela
	}

}
