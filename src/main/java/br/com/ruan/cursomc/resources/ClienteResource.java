package br.com.ruan.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ruan.cursomc.model.CategoriaModel;
import br.com.ruan.cursomc.model.ClienteModel;
import br.com.ruan.cursomc.repository.ClienteRepository;
import br.com.ruan.cursomc.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	ClienteService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id){
		ClienteModel cliente = service.findById(id);
		return ResponseEntity.ok().body(cliente);		
	} 
	
	
}
