package com.evoluum.java.evoluumjava.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evoluum.java.evoluumjava.entidades.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long>{

	List<Produto> findByCategoriaCodigo(Long codigoCategoria);
	
	Optional<Produto> findByCodigoAndCategoriaCodigo(Long codigo, Long codigoCategoria);
	
	Optional<Produto> findByDescricaoAndCategoriaCodigo(String descricao, Long codigoCategoria);
}
