package br.com.ruan.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ruan.cursomc.dto.CategoriaDTO;
import br.com.ruan.cursomc.dto.ClienteDTO;
import br.com.ruan.cursomc.model.ClienteModel;
import br.com.ruan.cursomc.model.ClienteModel;
import br.com.ruan.cursomc.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	ClienteService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteModel> findById(@PathVariable("id") Integer id){
		ClienteModel cliente = service.find(id);
		return ResponseEntity.ok().body(cliente);		
	} 
	
	
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> find() {
		List<ClienteDTO> objs = service.findAll().stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(objs);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO){
		
		ClienteModel obj = service.fromDTO(objDTO);
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ClienteModel> delete(@PathVariable Integer id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "lenesPerPage", defaultValue = "24") Integer LinesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		
		Page<ClienteDTO> objs = service.findPage(page, LinesPerPage, orderBy, direction).map(obj -> new ClienteDTO(obj));
		
		return ResponseEntity.ok().body(objs);
	}
	
}
