package com.evoluum.java.evoluumjava.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.evoluum.java.evoluumjava.entidades.Categoria;
import com.evoluum.java.evoluumjava.excecao.RegraDeNegocioExcepetion;
import com.evoluum.java.evoluumjava.repositorio.CategoriaRepositorio;

@Service
public class CategoriaServico {

	@Autowired
	private CategoriaRepositorio categoriaRepositorio;

	public List<Categoria> listarTodas() {
		return categoriaRepositorio.findAll();
	}

	public Optional<Categoria> buscarPorId(Long codigo) {
		return categoriaRepositorio.findById(codigo);
	}

	public Categoria salvar(Categoria categoria) {
		validarCategoriaDuplicada(categoria);
		return categoriaRepositorio.save(categoria);
	}

	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaSalvar = validarCategoriaExiste(codigo);
		validarCategoriaDuplicada(categoria);
		BeanUtils.copyProperties(categoria, categoriaSalvar, "codigo");
		return categoriaRepositorio.save(categoriaSalvar);
	}

	public void apagar(Long codigo) {
		categoriaRepositorio.deleteById(codigo);
	}
	
	private Categoria validarCategoriaExiste(Long codigo) {
		Optional<Categoria> categoria = buscarPorId(codigo);
		if (categoria.isPresent()) {
			return categoria.get();
		}
		throw new EmptyResultDataAccessException(1);
	}

	private void validarCategoriaDuplicada(Categoria categoria) {

		Categoria categoriaEncontrada = categoriaRepositorio.findByNome(categoria.getNome());

		if (categoriaEncontrada != null && categoriaEncontrada.getCodigo() != categoria.getCodigo()) {
			throw new RegraDeNegocioExcepetion(String.format("A categoria %s já está cadastrada", categoria.getNome()));
		}
	}
}
