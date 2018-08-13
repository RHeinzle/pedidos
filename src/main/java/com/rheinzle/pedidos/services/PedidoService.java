package com.rheinzle.pedidos.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rheinzle.pedidos.domain.ItemPedido;
import com.rheinzle.pedidos.domain.PagamentoBoleto;
import com.rheinzle.pedidos.domain.Pedido;
import com.rheinzle.pedidos.domain.enums.EstadoPagamento;
import com.rheinzle.pedidos.repositories.ItemPedidoRepository;
import com.rheinzle.pedidos.repositories.PagamentoRepository;
import com.rheinzle.pedidos.repositories.PedidoRepository;
import com.rheinzle.pedidos.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private ProdutoService produtoService;

	public Pedido find(Integer id) {
		Optional<Pedido> pedido = repo.findById(id);

		return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	@Transactional
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setData(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if (pedido.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagamentoBoleto = (PagamentoBoleto) pedido.getPagamento();
			boletoService.preencherPagamento(pagamentoBoleto, pedido.getData());
		}
		pedido = repo.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for (ItemPedido itemPedido : pedido.getItens()) {
			itemPedido.setDesconto(0.0);
			itemPedido.setPreco(produtoService.find(itemPedido.getProduto().getId()).getPreco());
			itemPedido.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());

		return pedido;
	}

}
