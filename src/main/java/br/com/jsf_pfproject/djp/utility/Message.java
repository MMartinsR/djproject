package br.com.jsf_pfproject.djp.utility;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *  Classe de mensagens personalizadas pra a view
 *  
 *  Responsável por gerar as mensagens que são apresentadas nos documentos xhtml.
 *  
 * @author Mariana Martins
 *
 */
public class Message {

	public static void info(String msg) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public static void erro(String msg) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public static void aviso(String msg) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

}
