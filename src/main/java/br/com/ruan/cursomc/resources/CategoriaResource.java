package br.com.ruan.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import br.com.ruan.cursomc.model.CategoriaModel;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	
	@RequestMapping(method = RequestMethod.GET)
	public List<CategoriaModel> listar() {
		CategoriaModel cat1 = new CategoriaModel(1, "Informática");
		CategoriaModel cat2 = new CategoriaModel(1, "Escritório");
		
		List<CategoriaModel> listCat = new ArrayList();
		
		listCat.add(cat1);
		listCat.add(cat2);
		
		
		return listCat;
	}
	
}
