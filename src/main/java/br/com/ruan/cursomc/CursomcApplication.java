package br.com.ruan.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ruan.cursomc.model.CategoriaModel;
import br.com.ruan.cursomc.model.ProdutoModel;
import br.com.ruan.cursomc.repository.CategoriaRepository;
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

	@Override
	public void run(String... args) throws Exception {

		CategoriaModel cat1 = new CategoriaModel(null, "Informática");
		CategoriaModel cat2 = new CategoriaModel(null, "Escritório");
		ProdutoModel p1 = new ProdutoModel(null, "Computador", 2000);
		ProdutoModel p2 = new ProdutoModel(null, "Impressora", 800);
		ProdutoModel p3 = new ProdutoModel(null, "Mouse", 80);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}

}
