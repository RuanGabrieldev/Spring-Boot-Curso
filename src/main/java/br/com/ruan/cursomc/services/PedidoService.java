package br.com.ruan.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ruan.cursomc.model.PedidoModel;
import br.com.ruan.cursomc.repository.PedidoRepository;
import br.com.ruan.cursomc.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	
	public PedidoModel buscar(Integer id) {
		
		Optional<PedidoModel> obj = repo.findById(id);
		return obj.orElseThrow(() -> 
			new ObjetoNaoEncontradoException("Objeto não encontrado! Id: " + id + ", Tipo: "+ PedidoModel.class.getName()));
	}
	
	
	
	
}
