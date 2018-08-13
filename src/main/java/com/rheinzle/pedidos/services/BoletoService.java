package com.rheinzle.pedidos.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.rheinzle.pedidos.domain.PagamentoBoleto;

@Service
public class BoletoService {

	public void preencherPagamento(PagamentoBoleto pagamentoBoleto, Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagamentoBoleto.setDataVencimento(calendar.getTime());
	}

}
