package br.com.jsf_pfproject.djp.converter;


import java.util.Collection;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.jsf_pfproject.djp.model.Base;



@FacesConverter("objectConverter")
public class ObjectConverter implements Converter {	
	
	/**
	 * Metodo que é chamado na inicialização do converter que pega os objetos da Lista e pega os ids deles através de reflexão
	 * e retorna esses ids em forma de String.
	 * 
	 * @param value objeto que será retornado como String
	 * 
	 */
	@Override 
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		// Primeira linha em branco da combo caso exista, 
		// Aqui simulamos que o id desse campo em branco seja "-1"
		if (value == null || value == "") {
			return "-1";
		}
		
		// Validando se o value é uma instancia da interface base (todas as entidades - Aluno, Professor e Agendamento
		// implementam esta interface) se sim, retorno o id desse objeto, convertendo para string.
		if(value instanceof Base){
			return ((Base) value).getId().toString();
		} else {
			return "-1";
		}

	}	

	
	/**
	 * Quando selecionamos um arquivo na combo por exemplo, ele pega o objeto e seta o valor do componente.
	 * 
	 * @param value String que será convertida a Objeto
	 */
	@Override 
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		// Validação de possível string vazia, retornamos um objeto nulo.
		if (value.equals("")) {
			return null; 
		}
		
		// Tentamos converter o String value para um Long id
		try {
			Long id = Long.valueOf(value);
			Collection items = (Collection) component.getAttributes().get("items");			
			
			return findById(items, id);

		} catch (Exception ex) {
			throw new ConverterException("Não foi possível aplicar conversão de item com valor "
					+ "["+ value + "] no componente [" + component.getId()+ "]", ex);
		}
	}

	
	/** Retorna o objeto pelo id  */
	private Object findById(Collection collection, Long idToFind) {

		for (Object obj : collection) {
			
			if (obj instanceof Base && idToFind.equals(((Base) obj).getId())) {
				return obj;
			}			
		}
		return null;
	}
	


}