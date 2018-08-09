package com.rheinzle.pedidos.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.rheinzle.pedidos.services.validation.ClienteInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ClienteInsert
public class ClienteNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "E-mail inválido")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfCnpj;

	private Integer tipo;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String telefone;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String logradouro;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String numero;

	private String complemento;
	private String bairro;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String cep;

	private Integer cidade;

	// private Set<String> telefones = new HashSet<>();

}
