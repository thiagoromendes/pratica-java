package com.evoluum.java.evoluumjava.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evoluum.java.evoluumjava.entidades.Categoria;
import com.evoluum.java.evoluumjava.servico.CategoriaServico;

@RestController
@RequestMapping("/categoria")
public class CategoriaControlador {

	@Autowired
	private CategoriaServico categoriaServico;
	
	@GetMapping
	public List<Categoria> listarTodas(){
		return categoriaServico.listarTodas();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Categoria>> buscarPorId(@PathVariable Long codigo){
		Optional<Categoria> categoria = categoriaServico.buscarPorId(codigo);
		return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	}
}
