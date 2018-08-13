package com.rheinzle.pedidos.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rheinzle.pedidos.domain.Cidade;
import com.rheinzle.pedidos.domain.Cliente;
import com.rheinzle.pedidos.domain.Endereco;
import com.rheinzle.pedidos.domain.enums.TipoCliente;
import com.rheinzle.pedidos.dto.ClienteDTO;
import com.rheinzle.pedidos.dto.ClienteNewDTO;
import com.rheinzle.pedidos.repositories.ClienteRepository;
import com.rheinzle.pedidos.repositories.EnderecoRepository;
import com.rheinzle.pedidos.services.exceptions.DataIntegrityException;
import com.rheinzle.pedidos.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);

		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = repo.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	public Cliente update(Cliente cliente) {
		Cliente newCliente = find(cliente.getId());
		updateData(newCliente, cliente);
		return repo.save(newCliente);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos!");
		}
	}

	public Cliente fromDTO(ClienteDTO clienteDto) {
		return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null);

	}

	public Cliente fromDTO(ClienteNewDTO clienteDto) {
		Cliente cliente = new Cliente(null, clienteDto.getNome(), clienteDto.getEmail(), clienteDto.getCpfCnpj(),
				TipoCliente.toEnum(clienteDto.getTipo()));

		Cidade cidade = new Cidade(clienteDto.getCidade(), null, null);
		Endereco endereco = new Endereco(null, clienteDto.getLogradouro(), clienteDto.getNumero(),
				clienteDto.getComplemento(), clienteDto.getBairro(), clienteDto.getCep(), cidade, cliente);

		cliente.setEnderecos(Arrays.asList(endereco));
		cliente.getTelefones().add(clienteDto.getTelefone());

//		cliente.setTelefones(clienteDto.getTelefones());
		return cliente;

	}

	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
	}
}
