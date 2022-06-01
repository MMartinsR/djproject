package br.com.jsf_pfproject.dominio.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.jsf_pfproject.djp.model.Professor;

@FacesConverter(forClass = Professor.class)
public class ProfessorConverter implements Converter, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
	// método que consulta o objeto no bd e converte esse dado do bd para objeto (modelo)
		if (id != null && !id.isEmpty()) {
			// chamada ao banco desse dado atraves do id
		}
		return null;  // retorno do dado do banco como objeto 
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		// método que retorna ao combobox da tela o dado requerido como string para exibição
		return null;  // retorno string do objeto passado para a tela
	}

}
