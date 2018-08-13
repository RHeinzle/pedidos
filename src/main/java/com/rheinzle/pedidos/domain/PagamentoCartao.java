package com.rheinzle.pedidos.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rheinzle.pedidos.domain.enums.EstadoPagamento;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("pagamentoCartao")
public class PagamentoCartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Integer numeroParcelas;

	public PagamentoCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}

}
