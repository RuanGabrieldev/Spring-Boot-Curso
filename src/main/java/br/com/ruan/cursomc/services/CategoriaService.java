package br.com.ruan.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ruan.cursomc.model.CategoriaModel;
import br.com.ruan.cursomc.repository.CategoriaRepository;
import br.com.ruan.cursomc.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	
	public CategoriaModel buscar(Integer id) {
		
		Optional<CategoriaModel> obj = repo.findById(id);
		return obj.orElseThrow(() -> 
			new ObjetoNaoEncontradoException("Objeto não encontrado! Id: " + id + ", Tipo: "+ CategoriaModel.class.getName()));
	}
	
	
	public CategoriaModel insert(CategoriaModel obj) {
		//garantindo que é um objeto novo
		obj.setId(null);
		return repo.save(obj);
	}
	
}
