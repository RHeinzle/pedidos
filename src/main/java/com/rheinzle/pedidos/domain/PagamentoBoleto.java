package com.rheinzle.pedidos.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.rheinzle.pedidos.domain.enums.EstadoPagamento;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PagamentoBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Date dataVencimento;
	private Date dataPagamento;

	public PagamentoBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

}
