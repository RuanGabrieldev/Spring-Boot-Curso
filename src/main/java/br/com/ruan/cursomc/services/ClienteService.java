package br.com.ruan.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ruan.cursomc.model.CategoriaModel;
import br.com.ruan.cursomc.model.ClienteModel;
import br.com.ruan.cursomc.repository.ClienteRepository;
import br.com.ruan.cursomc.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	
	public ClienteModel findById(Integer id) {
		Optional<ClienteModel> cliente = repository.findById(id);
		
		return cliente.orElseThrow(() ->
		new ObjetoNaoEncontradoException("Objeto não encontrado! Id: " + id + ", Tipo: "+ ClienteModel.class.getName()));
	} 
	
	
}
