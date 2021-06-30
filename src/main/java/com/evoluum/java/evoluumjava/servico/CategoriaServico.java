package com.evoluum.java.evoluumjava.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evoluum.java.evoluumjava.entidades.Categoria;
import com.evoluum.java.evoluumjava.repositorio.CategoriaRepositorio;

@Service
public class CategoriaServico {
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;

	public List<Categoria> listarTodas(){
		return categoriaRepositorio.findAll();
	}
	
	public Optional<Categoria> buscarPorId(Long codigo){
		return categoriaRepositorio.findById(codigo);
	}
}
