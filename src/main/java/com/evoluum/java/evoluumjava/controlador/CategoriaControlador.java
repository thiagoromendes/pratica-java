package com.evoluum.java.evoluumjava.controlador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.evoluum.java.evoluumjava.dto.categoria.CategoriaRequestDTO;
import com.evoluum.java.evoluumjava.dto.categoria.CategoriaResponseDTO;
import com.evoluum.java.evoluumjava.entidades.Categoria;
import com.evoluum.java.evoluumjava.servico.CategoriaServico;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaControlador {

	@Autowired
	private CategoriaServico categoriaServico;

	@ApiOperation(value = "Listar")
	@GetMapping
	public List<CategoriaResponseDTO> listarTodas() {
		return categoriaServico.listarTodas().stream()
				.map(categoria -> CategoriaResponseDTO.converterParaCategoriaDTO(categoria))
				.collect(Collectors.toList());
	}

	@ApiOperation(value = "Buscar por código")
	@GetMapping("/{codigo}")
	public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long codigo) {
		Optional<Categoria> categoria = categoriaServico.buscarPorId(codigo);
		return categoria.isPresent()
				? ResponseEntity.ok(CategoriaResponseDTO.converterParaCategoriaDTO(categoria.get()))
				: ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Salvar")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody CategoriaRequestDTO categoriaDto) {
		Categoria categoriaSalva = categoriaServico.salvar(categoriaDto.converterParaEntidade());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(CategoriaResponseDTO.converterParaCategoriaDTO(categoriaSalva));
	}

	@ApiOperation(value = "Atualizar")
	@PutMapping("/{codigo}")
	public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody CategoriaRequestDTO categoriaDto) {
		Categoria categoriaAtualizada = categoriaServico.atualizar(codigo, categoriaDto.converterParaEntidade(codigo)); 
		return ResponseEntity.ok(CategoriaResponseDTO.converterParaCategoriaDTO(categoriaAtualizada));
	}

	@ApiOperation(value = "Apagar")
	@DeleteMapping(value = "/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagar(@PathVariable Long codigo) {
		categoriaServico.apagar(codigo);
	}

}
