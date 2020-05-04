package br.com.ruan.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ruan.cursomc.model.CategoriaModel;
import br.com.ruan.cursomc.repository.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	
	@Autowired
	private CategoriaRepository repo;

	@Override
	public void run(String... args) throws Exception {

		CategoriaModel cat1 = new CategoriaModel(null, "Informática");
		CategoriaModel cat2 = new CategoriaModel(null, "Escritório");
		
		repo.saveAll(Arrays.asList(cat1, cat2));
	}

}
