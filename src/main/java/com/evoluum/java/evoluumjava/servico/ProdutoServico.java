package com.evoluum.java.evoluumjava.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.evoluum.java.evoluumjava.entidades.Produto;
import com.evoluum.java.evoluumjava.excecao.RegraDeNegocioExcepetion;
import com.evoluum.java.evoluumjava.repositorio.ProdutoRepositorio;

@Service
public class ProdutoServico {

	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	@Autowired
	private CategoriaServico categoriaServico;
	
	public List<Produto> listarTodos(Long codigoCategoria) {
		return produtoRepositorio.findByCategoriaCodigo(codigoCategoria);
	}

	public Optional<Produto> buscarPorId(Long codigo, Long codigoCategoria) {
		return produtoRepositorio.findByCodigoAndCategoriaCodigo(codigo, codigoCategoria);
	}

	public Produto salvar(Long codigoCategoria, Produto produto) {
		validarCategoriaDoProdutoExiste(codigoCategoria);
		validarProdutoDuplicado(produto);
		return produtoRepositorio.save(produto);
	}
	
	public Produto atualizar(Long codigoCategoria, Long codigoProduto, Produto produto) {
		Produto produtoSalvar = validarProdutoExiste(codigoProduto, codigoCategoria);
		validarCategoriaDoProdutoExiste(codigoCategoria);
		validarProdutoDuplicado(produto);
		BeanUtils.copyProperties(produto, produtoSalvar, "codigo");
		return produtoRepositorio.save(produtoSalvar);
	}
	
	public void apagar(Long codigoCategoria, Long codigoProduto) {
		Produto produto = validarProdutoExiste(codigoProduto, codigoCategoria);
		produtoRepositorio.delete(produto);
	}
	
	private Produto validarProdutoExiste(Long codigoProduto, Long codigoCategoria) {
		Optional<Produto> produto = buscarPorId(codigoProduto, codigoCategoria);
		if(!produto.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return produto.get();
	}

	private void validarProdutoDuplicado(Produto produto) {
		Optional<Produto> produtoPorDescricao = produtoRepositorio.findByDescricaoAndCategoriaCodigo(produto.getDescricao(), produto.getCategoria().getCodigo());
		if(produtoPorDescricao.isPresent() && produtoPorDescricao.get().getCodigo() != produto.getCodigo()){
			throw new RegraDeNegocioExcepetion(String.format("O produto %s já está cadastrado", produto.getDescricao()));
		}
	}
	
	private void validarCategoriaDoProdutoExiste(Long codigoCategoria) {
		if(codigoCategoria == null) {
			throw new RegraDeNegocioExcepetion("A categoria não pode ser nula");
		}
		
		if(!categoriaServico.buscarPorId(codigoCategoria).isPresent()) {
			throw new RegraDeNegocioExcepetion(String.format("A categoria de código %s informada não existe no cadastro", codigoCategoria));
		}
	}
}
