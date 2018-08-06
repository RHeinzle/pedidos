package com.rheinzle.pedidos.dto;

import java.io.Serializable;

import javax.persistence.Entity;

import com.rheinzle.pedidos.domain.Endereco;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EnderecoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;

	private Integer cidade;

	public EnderecoDTO(Endereco obj) {
		super();
		this.id = obj.getId();
		this.logradouro = obj.getLogradouro();
		this.numero = obj.getNumero();
		this.complemento = obj.getComplemento();
		this.bairro = obj.getBairro();
		this.cep = obj.getCep();
		this.cidade = obj.getCidade().getId();
	}

}
