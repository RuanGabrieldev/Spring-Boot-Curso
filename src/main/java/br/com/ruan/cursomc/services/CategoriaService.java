package br.com.ruan.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ruan.cursomc.model.CategoriaModel;
import br.com.ruan.cursomc.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	
	public CategoriaModel buscar(Integer id) {
		
		Optional<CategoriaModel> obj = repo.findById(id);
		
		return obj.orElse(null);
	}
	
	
	
	
}
