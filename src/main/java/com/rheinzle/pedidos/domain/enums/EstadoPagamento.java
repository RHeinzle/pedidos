package com.rheinzle.pedidos.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private int codigo;
	private String descricao;

	public static EstadoPagamento toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (EstadoPagamento t : EstadoPagamento.values()) {
			if (codigo.equals(t.getCodigo())) {
				return t;
			}
		}

		throw new IllegalArgumentException("Enum não encontrado! Id: " + codigo);
	}

}
