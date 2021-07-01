package com.evoluum.java.evoluumjava.controlador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.evoluum.java.evoluumjava.dto.produto.ProdutoRequestDTO;
import com.evoluum.java.evoluumjava.dto.produto.ProdutoResponseDTO;
import com.evoluum.java.evoluumjava.entidades.Produto;
import com.evoluum.java.evoluumjava.servico.ProdutoServico;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Produto")
@RestController
@RequestMapping("/categoria{codigoCategoria}/produto")
public class ProdutoControlador {

	@Autowired
	private ProdutoServico produtoServico;

	@ApiOperation(value = "Listar")
	@GetMapping
	public List<ProdutoResponseDTO> listarTodos(@PathVariable Long codigoCategoria) {
		return produtoServico.listarTodos(codigoCategoria).stream()
				.map(produto -> ProdutoResponseDTO.converterParaProdutoDTO(produto)).collect(Collectors.toList());
	}

	@ApiOperation(value = "Buscar por código")
	@GetMapping("/{codigo}")
	public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long codigoCategoria,
			@PathVariable Long codigo) {
		Optional<Produto> produto = produtoServico.buscarPorId(codigo, codigoCategoria);
		return produto.isPresent() ? ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produto.get()))
				: ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Salvar")
	@PostMapping
	public ResponseEntity<ProdutoResponseDTO> salvar(@PathVariable Long codigoCategoria,
			@Valid @RequestBody ProdutoRequestDTO produtoDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.converterParaProdutoDTO(
				produtoServico.salvar(codigoCategoria, produtoDto.converterParaEntidade(codigoCategoria))));
	}

	@ApiOperation(value = "Atualizar")
	@PutMapping("/{codigoProduto}")
	public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long codigoCategoria,
			@PathVariable Long codigoProduto, @Valid @RequestBody ProdutoRequestDTO produtoDto) {
		Produto produtoAtualizado = produtoServico.atualizar(codigoCategoria, codigoProduto,
				produtoDto.converterParaEntidade(codigoCategoria, codigoProduto));
		return ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produtoAtualizado));
	}

	@ApiOperation(value = "Apagar")
	@DeleteMapping("/{codigoProduto}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto) {
		produtoServico.apagar(codigoCategoria, codigoProduto);
	}

}
