package com.thiago.locadora.utils;

import java.util.Date;

public class DateUtils {

	public static boolean ehPeriodoValido(Date dataInicio, Date dataFim) {
		if (dataInicio == null || dataFim == null) {
			return false;
		}
		
		if (dataFim.before(dataInicio)) {
			return false;
		}
		
		Date hoje = new Date();
		if (dataInicio.before(hoje)) {
			return false;
		}
		
		return true;
	}

}
