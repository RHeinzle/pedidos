package com.rheinzle.pedidos.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.rheinzle.pedidos.domain.enums.TipoCliente;
import com.rheinzle.pedidos.dto.ClienteNewDTO;
import com.rheinzle.pedidos.repositories.ClienteRepository;
import com.rheinzle.pedidos.resources.exceptions.FieldMessage;
import com.rheinzle.pedidos.services.validation.utils.Documento;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo() == TipoCliente.PESSOAFISICA.getCodigo() && !Documento.isValidCPF(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CPF inválido"));
		} else if (objDto.getTipo() == TipoCliente.PESSOAJURIDICA.getCodigo()
				&& !Documento.isValidCNPJ(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));
		}

		if (repo.findByEmail(objDto.getEmail()) != null) {
			list.add(new FieldMessage("email", "E-mail já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}