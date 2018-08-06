package com.rheinzle.pedidos.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	private List<EnderecoDTO> enderecos = new ArrayList<>();

	private Set<String> telefones = new HashSet<>();

}
