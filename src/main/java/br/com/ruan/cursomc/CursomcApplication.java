package br.com.ruan.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ruan.cursomc.model.CategoriaModel;
import br.com.ruan.cursomc.model.CidadeModel;
import br.com.ruan.cursomc.model.ClienteModel;
import br.com.ruan.cursomc.model.EnderecoModel;
import br.com.ruan.cursomc.model.EstadoModel;
import br.com.ruan.cursomc.model.ItemPedidoModel;
import br.com.ruan.cursomc.model.PagamentoComBoletoModel;
import br.com.ruan.cursomc.model.PagamentoComCartaoModel;
import br.com.ruan.cursomc.model.PagamentoModel;
import br.com.ruan.cursomc.model.PedidoModel;
import br.com.ruan.cursomc.model.ProdutoModel;
import br.com.ruan.cursomc.model.enums.EstadoPagamento;
import br.com.ruan.cursomc.model.enums.TipoCliente;
import br.com.ruan.cursomc.repository.CategoriaRepository;
import br.com.ruan.cursomc.repository.CidadeRepository;
import br.com.ruan.cursomc.repository.ClienteRepository;
import br.com.ruan.cursomc.repository.EnderecoRepository;
import br.com.ruan.cursomc.repository.EstadoRepository;
import br.com.ruan.cursomc.repository.ItemPedidoRepository;
import br.com.ruan.cursomc.repository.PagamentoRepository;
import br.com.ruan.cursomc.repository.PedidoRepository;
import br.com.ruan.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
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
	
	@Override
	public void run(String... args) throws Exception {

		CategoriaModel cat1 = new CategoriaModel(null, "Informática");
		CategoriaModel cat2 = new CategoriaModel(null, "Escritório");
		ProdutoModel p1 = new ProdutoModel(null, "Computador", 2000);
		ProdutoModel p2 = new ProdutoModel(null, "Impressora", 800);
		ProdutoModel p3 = new ProdutoModel(null, "Mouse", 80);
		EstadoModel est1 = new EstadoModel(null, "Minas Gerais");
		EstadoModel est2 = new EstadoModel(null, "São Paulo");
		CidadeModel c1 = new CidadeModel(null, "Uberlândia", est1);
		CidadeModel c2 = new CidadeModel(null, "São Paulo", est2);
		CidadeModel c3 = new CidadeModel(null, "Campinas", est2);
		
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		 
		est1.setCidades(Arrays.asList(c1));
		est2.setCidades(Arrays.asList(c2, c3));
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		
		
		ClienteModel cli1 = new ClienteModel(null, "Maria Silva", "maria@gmail.com", "25415354862", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("11969610696", "11967987443"));
		
		EnderecoModel e1 = new EnderecoModel(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		EnderecoModel e2 = new EnderecoModel(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		PedidoModel ped1 = new PedidoModel(null, sdf.parse("30/09/2020 10:36"), cli1, e1);
		PedidoModel ped2 = new PedidoModel(null, sdf.parse("10/10/2020 18:30"), cli1, e2);
		
		PagamentoModel pagto1 = new PagamentoComCartaoModel(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		PagamentoModel pagto2 = new PagamentoComBoletoModel(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("17/11/2020 18:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		clienteRepository.save(cli1);
		
		
		ItemPedidoModel ip1 = new ItemPedidoModel(0.0, 1, 2000.0, ped1, p1);
		ItemPedidoModel ip2 = new ItemPedidoModel(0.0, 2, 80.0, ped1, p3);
		ItemPedidoModel ip3 = new ItemPedidoModel(100.0, 1, 800.0, ped2, p1);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
