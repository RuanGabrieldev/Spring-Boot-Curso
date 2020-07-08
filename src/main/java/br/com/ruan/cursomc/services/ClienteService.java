package br.com.ruan.cursomc.services;

import java.nio.channels.UnsupportedAddressTypeException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.ruan.cursomc.dto.ClienteDTO;
import br.com.ruan.cursomc.dto.ClienteNewDTO;
import br.com.ruan.cursomc.model.CategoriaModel;
import br.com.ruan.cursomc.model.CidadeModel;
import br.com.ruan.cursomc.model.ClienteModel;
import br.com.ruan.cursomc.model.EnderecoModel;
import br.com.ruan.cursomc.model.enums.TipoCliente;
import br.com.ruan.cursomc.repository.CidadeRepository;
import br.com.ruan.cursomc.repository.ClienteRepository;
import br.com.ruan.cursomc.repository.EnderecoRepository;
import br.com.ruan.cursomc.services.exceptions.ObjetoNaoEncontradoException;
import br.com.ruan.cursomc.services.exceptions.ViolacaoIntegridadeDadosException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public ClienteModel find(Integer id) {
		Optional<ClienteModel> cliente = repo.findById(id);
		
		return cliente.orElseThrow(() ->
		new ObjetoNaoEncontradoException("Objeto não encontrado! Id: " + id + ", Tipo: "+ ClienteModel.class.getName()));
	} 
	
	public List<ClienteModel> findAll() {
		
		return repo.findAll();
	}

	
	@Transactional
	public ClienteModel insert(ClienteModel obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
	
	public ClienteModel fromDTO(ClienteNewDTO objDTO) {
		ClienteModel cliente = new ClienteModel(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
		Optional<CidadeModel> cidade = cidadeRepo.findById(objDTO.getCidadeId()); 
		EnderecoModel end = new EnderecoModel(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cliente, cidade.get());
		cliente.getEnderecos().add(end);
		cliente.getTelefones().add(objDTO.getTelefone1());
		
		if(objDTO.getTelefone2() != null) {
			cliente.getTelefones().add(objDTO.getTelefone2());
		}
		
		if(objDTO.getTelefone3() != null) {
			cliente.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cliente;
	}	
	
	
	private void updateData(ClienteModel newObj, ClienteModel obj) {
		
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	
}
