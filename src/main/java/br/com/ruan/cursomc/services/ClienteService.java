package br.com.ruan.cursomc.services;

import java.nio.channels.UnsupportedAddressTypeException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.ruan.cursomc.dto.ClienteDTO;
import br.com.ruan.cursomc.model.CategoriaModel;
import br.com.ruan.cursomc.model.ClienteModel;
import br.com.ruan.cursomc.repository.ClienteRepository;
import br.com.ruan.cursomc.services.exceptions.ObjetoNaoEncontradoException;
import br.com.ruan.cursomc.services.exceptions.ViolacaoIntegridadeDadosException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	
	public ClienteModel find(Integer id) {
		Optional<ClienteModel> cliente = repo.findById(id);
		
		return cliente.orElseThrow(() ->
		new ObjetoNaoEncontradoException("Objeto não encontrado! Id: " + id + ", Tipo: "+ ClienteModel.class.getName()));
	} 
	
	public List<ClienteModel> findAll() {
		
		return repo.findAll();
	}

	
	public ClienteModel update(ClienteModel obj){
		
		ClienteModel newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new ViolacaoIntegridadeDadosException("Não é possível excluir um Cliente que possuí pedidos");
		}
	}    
	
	
	public Page<ClienteModel> findPage(Integer page, Integer LinesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, LinesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
		
	}
	
	public ClienteModel fromDTO(ClienteDTO objDTO) {
		return new ClienteModel(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}	
	
	
	private void updateData(ClienteModel newObj, ClienteModel obj) {
		
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	
}
