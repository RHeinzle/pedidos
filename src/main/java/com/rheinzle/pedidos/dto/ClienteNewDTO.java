package com.rheinzle.pedidos.dto;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClienteNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;

	private String email;

	private String cpfCnpj;

	private Integer tipo;

	private String telefone;

	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private Integer cidade;

	// private List<EnderecoDTO> enderecos = new ArrayList<>();

	// private Set<String> telefones = new HashSet<>();

}
