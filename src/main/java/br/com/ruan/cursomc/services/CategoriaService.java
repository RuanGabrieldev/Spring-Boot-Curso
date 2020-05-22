package br.com.ruan.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


import br.com.ruan.cursomc.model.CategoriaModel;
import br.com.ruan.cursomc.repository.CategoriaRepository;
import br.com.ruan.cursomc.services.exceptions.ObjetoNaoEncontradoException;
import br.com.ruan.cursomc.services.exceptions.ViolacaoIntegridadeDadosException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	
	public CategoriaModel find(Integer id) {
		
		Optional<CategoriaModel> obj = repo.findById(id);
		return obj.orElseThrow(() -> 
			new ObjetoNaoEncontradoException("Objeto não encontrado! Id: " + id + ", Tipo: "+ CategoriaModel.class.getName()));
	}
	
	public List<CategoriaModel> findAll() {
		
		return repo.findAll();
	}
	
	
	
	public CategoriaModel insert(CategoriaModel obj) {
		//garantindo que é um objeto novo
		obj.setId(null);
		return repo.save(obj);
	}
	
	
	public CategoriaModel update(CategoriaModel obj){
		
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new ViolacaoIntegridadeDadosException("Não é possível excluir uma categoria que possuí produtos");
		}
	}    
	
	
	public Page<CategoriaModel> findPage(Integer page, Integer LinesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, LinesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
		
	}
	
	
	
}
