package br.com.ruan.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ruan.cursomc.model.CategoriaModel;
import br.com.ruan.cursomc.model.CidadeModel;
import br.com.ruan.cursomc.model.EstadoModel;
import br.com.ruan.cursomc.model.ProdutoModel;
import br.com.ruan.cursomc.repository.CategoriaRepository;
import br.com.ruan.cursomc.repository.CidadeRepository;
import br.com.ruan.cursomc.repository.EstadoRepository;
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
	}

}
