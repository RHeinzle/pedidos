package com.rheinzle.pedidos.services;

import org.springframework.stereotype.Service;

import com.rheinzle.pedidos.domain.Cidade;
import com.rheinzle.pedidos.domain.Endereco;
import com.rheinzle.pedidos.dto.EnderecoDTO;

@Service
public class EnderecoService {

	public Endereco fromDTO(EnderecoDTO objDto) {
		Cidade cidade = new Cidade(objDto.getCidade(), null, null);
		return new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cidade, null);

	}

}
