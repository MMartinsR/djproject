package br.com.jsf_pfproject.djp.utility;

import java.util.Calendar;
import java.util.Date;

/**
 *  Classe de validações
 *  Responsável por algumas funções de validação de datas.
 *  
 * @author Mariana Martins
 *
 */
public class ValidationUtil {
	
	// Valida se a data de nascimento que está sendo inserida, está entre um determinado padrão
	public static boolean dataNascimentoValida(Date dataNascimento) {
		
		if (dataNascimento == null) {
			return false;
		}		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataNascimento);
		int anoNasc = calendar.get(Calendar.YEAR);
		
		Date date = new Date();
		calendar.setTime(date);

		int anoAtual = calendar.get(Calendar.YEAR);			
		int idade = anoAtual - anoNasc;			
		
		if (idade > 100 || idade < 6) {
			return false;
		}

		return true;
	}
	
	// Valida se a data de Agendamento é anterior a data atual
	public static boolean dataAgendamentoValida(Date dataAgendamento) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataAgendamento);
		
		Date dateAgendamento = calendar.getTime();
		Date date = new Date();
		
		if (dateAgendamento.after(date)) {
			return true;
		}
		
		return false;
	}

}
