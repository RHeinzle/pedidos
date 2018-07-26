package com.rheinzle.pedidos;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rheinzle.pedidos.domain.Categoria;
import com.rheinzle.pedidos.domain.Cidade;
import com.rheinzle.pedidos.domain.Cliente;
import com.rheinzle.pedidos.domain.Endereco;
import com.rheinzle.pedidos.domain.Estado;
import com.rheinzle.pedidos.domain.ItemPedido;
import com.rheinzle.pedidos.domain.Pagamento;
import com.rheinzle.pedidos.domain.PagamentoBoleto;
import com.rheinzle.pedidos.domain.PagamentoCartao;
import com.rheinzle.pedidos.domain.Pedido;
import com.rheinzle.pedidos.domain.Produto;
import com.rheinzle.pedidos.domain.enums.EstadoPagamento;
import com.rheinzle.pedidos.domain.enums.TipoCliente;
import com.rheinzle.pedidos.repositories.CategoriaRepository;
import com.rheinzle.pedidos.repositories.CidadeRepository;
import com.rheinzle.pedidos.repositories.ClienteRepository;
import com.rheinzle.pedidos.repositories.EnderecoRepository;
import com.rheinzle.pedidos.repositories.EstadoRepository;
import com.rheinzle.pedidos.repositories.ItemPedidoRepository;
import com.rheinzle.pedidos.repositories.PagamentoRepository;
import com.rheinzle.pedidos.repositories.PedidoRepository;
import com.rheinzle.pedidos.repositories.ProdutoRepository;

@SpringBootApplication
public class PedidosApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(PedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/* CATEGORIAS */
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.setProdutos(Arrays.asList(p1, p2, p3));
		cat1.setProdutos(Arrays.asList(p2));

		p1.setCategorias(Arrays.asList(cat1));
		p2.setCategorias(Arrays.asList(cat1, cat2));
		p3.setCategorias(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		/* CIDADES */
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia,", est1);
		Cidade c2 = new Cidade(null, "São Paulo,", est2);
		Cidade c3 = new Cidade(null, "Campinas,", est2);

		est1.setCidades(Arrays.asList(c1));
		est2.setCidades(Arrays.asList(c2, c2));

		c1.setEstado(est1);
		c2.setEstado(est2);
		c3.setEstado(est2);

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		/* CLIENTES */
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", c2, cli1);

		cli1.setEnderecos(Arrays.asList(e1, e2));

		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		/* PEDIDOS */
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.setPedidos(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}
}
