package br.com.jsf_pfproject.djp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import br.com.jsf_pfproject.djp.utility.ValidationUtil;

class AgendamentoServiceTest {

	@Test
	void dataNascimentoNaoDeveSerValida() {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date date = null;
		try {
			date = formatter.parse("24/07/2022");

		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertEquals(false, ValidationUtil.dataNascimentoValida(date));

	}
	
	@Test
	void dataNascimentoDeveSerValida() {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date date = null;
		try {
			date = formatter.parse("24/07/1998");

		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertEquals(true, ValidationUtil.dataNascimentoValida(date));

	}

}
