package com.rheinzle.pedidos.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int codigo;
	private String descricao;

	public static TipoCliente toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (TipoCliente t : TipoCliente.values()) {
			if (codigo.equals(t.getCodigo())) {
				return t;
			}
		}

		throw new IllegalArgumentException("Enum não encontrado! Id: " + codigo);
	}

}
